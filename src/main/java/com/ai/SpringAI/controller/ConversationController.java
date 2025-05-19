package com.ai.SpringAI.controller;

import com.ai.SpringAI.dto.response.ConversationResponse;
import com.ai.SpringAI.dto.response.MessageResponse;
import com.ai.SpringAI.service.ConversationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/conversation")
public class ConversationController {
    private final ConversationService conversationService;

    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @GetMapping()
    public ResponseEntity<Page<ConversationResponse>> getMessagesByConversation(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(conversationService.getConversations(pageable));
    }
}
