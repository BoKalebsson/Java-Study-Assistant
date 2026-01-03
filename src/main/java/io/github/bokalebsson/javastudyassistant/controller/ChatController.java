package io.github.bokalebsson.javastudyassistant.controller;

import io.github.bokalebsson.javastudyassistant.dto.ChatRequest;
import io.github.bokalebsson.javastudyassistant.dto.ChatResponse;
import io.github.bokalebsson.javastudyassistant.service.ChatService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    /**
     * Handles incoming chat requests and returns an AI-generated response.
     */
    @PostMapping
    public ChatResponse chat(@Valid @RequestBody ChatRequest request) {

        String answer = chatService.chat(
                request.getMessage(),
                request.getLevel()
        );

        return new ChatResponse(answer);
    }
}
