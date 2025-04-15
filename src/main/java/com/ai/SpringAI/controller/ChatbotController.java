package com.ai.SpringAI.controller;

import com.ai.SpringAI.dto.request.ChatRequest;
import com.ai.SpringAI.dto.response.ChatResponse;
import com.ai.SpringAI.service.ChatbotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chatbot")
public class ChatbotController {

    @Autowired
    private ChatbotService chatbotService;

    @PostMapping("/ask")
    public ChatResponse askQuestion(@RequestBody ChatRequest userInput) {
        return chatbotService.getResponse(userInput);
    }


}