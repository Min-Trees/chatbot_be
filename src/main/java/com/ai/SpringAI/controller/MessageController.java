package com.ai.SpringAI.controller;

import com.ai.SpringAI.dto.response.MessageResponse;
import com.ai.SpringAI.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class MessageController {
    @Autowired
    private MessageService messageService;

    public MessageResponse getMessage(@RequestBody Integer id) {
        return messageService.getMessage(id);
    }
    @GetMapping("/conversation/{conversationId}/messages")
    public ResponseEntity<Page<MessageResponse>> getMessagesByConversation(
            @PathVariable UUID conversationId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(messageService.getMessagesByConversation(conversationId, page, size));
    }

}
