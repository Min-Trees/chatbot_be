package com.ai.SpringAI.service;

import com.ai.SpringAI.dto.response.MessageResponse;
import com.ai.SpringAI.entity.Message;
import com.ai.SpringAI.repository.MessageRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    public MessageResponse getMessage(Integer messageId) {
        Message message = messageRepository.findById(messageId).orElse(null);
        assert message != null;
        return MessageResponse.builder()
                .messageId(messageId)
                .messageText(message.getMessageText())
                .sender(message.getSender())
                .conversation(message.getConversation())
                .sentAt(message.getSentAt())
                .build();

    }

    public Page<MessageResponse> getMessagesByConversation(UUID conversationId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "sentAt"));
        Page<Message> messages = messageRepository.findByConversation_ConversationsId(conversationId, pageable);

        return messages.map(message -> MessageResponse.builder()
                .messageId(message.getMessageId())
                .messageText(message.getMessageText())
                .sender(message.getSender())
                .conversation(message.getConversation())
                .sentAt(message.getSentAt())
                .build()
        );
    }
}
