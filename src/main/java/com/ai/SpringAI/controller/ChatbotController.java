package com.ai.SpringAI.controller;

import com.ai.SpringAI.dto.request.ChatRequest;
import com.ai.SpringAI.dto.response.ChatResponse;
import com.ai.SpringAI.service.ChatbotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chatbot")
public class ChatbotController {

    @Autowired
    private ChatbotService chatbotService;

    @PostMapping("/ask")
    public ResponseEntity<ChatResponse> ask(@RequestBody ChatRequest chatRequest) {
        try {
            ChatResponse response = chatbotService.getResponse(chatRequest);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ChatResponse("Invalid request: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ChatResponse("Server error: " + e.getMessage()));
        }
    }

}