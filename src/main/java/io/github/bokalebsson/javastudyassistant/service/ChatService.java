package io.github.bokalebsson.javastudyassistant.service;

import io.github.bokalebsson.javastudyassistant.prompt.ExpertiseLevel;
import io.github.bokalebsson.javastudyassistant.prompt.JavaStudyAssistantPromptBuilder;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    // Language model responsible for generating responses to user questions.
    private final ChatModel chatModel;

    // Builds the full prompt. (system + level + user question)
    private final JavaStudyAssistantPromptBuilder promptBuilder;

    // Constructor injection of required dependencies.
    public ChatService(ChatModel chatModel,
                       JavaStudyAssistantPromptBuilder promptBuilder) {
        this.chatModel = chatModel;
        this.promptBuilder = promptBuilder;
    }

    /**
     * Sends a user question to the AI model and returns the generated answer.
     */
    public String chat(String userMessage, ExpertiseLevel level) {

        // Build the full prompt text based on user input and expertise level.
        String promptText = promptBuilder.buildPrompt(userMessage, level);

        // This is the format required by Spring AI when sending input to the model.
        Prompt prompt = new Prompt(promptText);

        // Send the prompt to the language model and receive the generated response.
        ChatResponse response = chatModel.call(prompt);

        // Extract the generated answer text from the response and return it.
        return response.getResult()
                .getOutput()
                .getText();
    }
}
