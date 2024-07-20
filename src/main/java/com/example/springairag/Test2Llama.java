package com.example.springairag;

import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
/*import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;*/

import java.util.List;

public class Test2Llama {

    public static void main(String[] args) {
        /*
        OllamaApi ollamaApi = new OllamaApi();
        OllamaChatModel ollamaChatModel = new OllamaChatModel(ollamaApi, OllamaOptions.create()
                .withModel("mistral")
                );

        String systemMessageText = """
                Vous etes un assistant qui specialisé dans le domaine de l'analyse des sentiment.
                Votre taches est d'extraire à partir d'un commentaire le sentiment des différents aspects des ordinateurs
                achetés par des clients. Les aspects qui nous intéressent sont : 
                l'écran, la souris et le clavier. le sentiment peut etre : positif , négatif ou neutre 
                le résultat attendu sera au format JSON avec les champs suivants : 
                - clavier : le sentiments relatif au clavier 
                - souris : le sentiments relatif avec la souris
                - ecran : le sentiments relatif avec l'écran
                """;
        SystemMessage systemMessage = new SystemMessage(systemMessageText);



        String userInputText = """
                Je pense que je ne suis pas satisfait la qualité de l'écran , 
                mais le clavier est mauvais alors que pour la souris la qualité est plutot moyenne.
                par ailleur je pense que cet ordinateur consomme beaucoup d'énergie
                """;
        UserMessage userMessage = new UserMessage(userInputText);



        String userInputText1 = """
                Je pense que je suis satisfait la qualité de l'écran , 
                mais le clavier est mauvais alors que pour la souris la qualité est assez bonne.
                par ailleur je pense que cet ordinateur consomme beaucoup d'énergie
                
                """;
        UserMessage userMessage1 = new UserMessage(userInputText1);
        String response1 = """
                {
                  "clavier": "négatif",
                  "souris": "neutre",
                  "ecran": "positif"
                }
                """;
        AssistantMessage assistantMessage = new AssistantMessage(response1);

        Prompt zeroShopPrompt = new Prompt(List.of(systemMessage, userMessage));
        ChatResponse chatResponse =  ollamaChatModel.call(zeroShopPrompt);
        System.out.println(chatResponse.getResult().getOutput().getContent());

        */

    }
}
