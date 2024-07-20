package com.example.springairag;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Media;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.ai.openai.api.OpenAiImageApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@EnableAutoConfiguration
@RestController
public class ChatRestController {


    private ChatClient chatClient;


    @Value("classpath:/prompts/system-message.st") //injecter valeur
    private Resource systemMessageResource;

    /*@Value("classpath:/image.png") //injecter valeur
    private Resource image;*/


    public ChatRestController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping(value = "/chat", produces = MediaType.TEXT_PLAIN_VALUE)
    public String chat(String question) {
        String content = chatClient.prompt()
                .user(question)
                .call()
                .content();

        return content;
    }

    @GetMapping(value = "/chat2", produces = MediaType.TEXT_PLAIN_VALUE)
    public Flux<String> chat2(String question) {
        Flux<String> content = chatClient.prompt()
                .user(question)
                .stream().content();

        return content;
    }


    @PostMapping(value = "/sentiment")
    public Sentiment sentiment(String review) {
        return chatClient.prompt()
                .system(systemMessageResource)
                .user(review).call().entity(Sentiment.class);
    }

    @GetMapping("/describe")
    public Depense depenses() throws IOException {
        byte[] data = new ClassPathResource("image.png").getContentAsByteArray();
        String userMessageText = """
                Ton role est faire la reconaissance optique du texte
                qui se trouve dans l'image fournie.
                """;

        UserMessage userMessage = new UserMessage(userMessageText, List.of(
                new Media(MimeTypeUtils.IMAGE_PNG, data)
        ));
        Prompt prompt = new Prompt(userMessage);
        return chatClient
                .prompt().
                messages(userMessage)
                .call().entity(Depense.class);
    }


    @GetMapping(path = "/generateImage", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] generateImageDALLE() throws IOException {
        OpenAiImageApi openAiApi = new OpenAiImageApi("sk-proj-lx2DBLW1L4UjZKT1KLUnT3BlbkFJqkOT6O5ZEN8XUqUXN3Nl");
        OpenAiImageModel openAiImageModel = new OpenAiImageModel(openAiApi);
        ImageResponse response = openAiImageModel.call(
                new ImagePrompt("un chat avec un costume dans sa fête avec un café dans sa main droite ",
                        OpenAiImageOptions.builder()
                                .withModel("dall-e-3")
                                .withQuality("hd")
                                .withN(1)
                                .withResponseFormat("b64_json")
                                .withHeight(1024)
                                .withWidth(1024).build())
        );
        String image = response.getResult().getOutput().getB64Json();
        byte[] decode = Base64.getDecoder().decode(image);
        return decode;
    }
}
