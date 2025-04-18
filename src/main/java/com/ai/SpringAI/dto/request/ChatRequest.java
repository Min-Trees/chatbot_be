package com.ai.SpringAI.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRequest {
    private String userId;
    private String message; // Nội dung tin nhắn từ người dùng
}