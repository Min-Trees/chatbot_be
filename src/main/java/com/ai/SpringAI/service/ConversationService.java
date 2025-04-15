package com.ai.SpringAI.service;

import com.ai.SpringAI.dto.response.ConversationResponse;
import com.ai.SpringAI.entity.Conversations;
import com.ai.SpringAI.repository.ConversationRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ConversationService {
    private ConversationRepository conversationRepository;

    public ConversationResponse getConversation(UUID conversationId) {
        Conversations conversation = conversationRepository.findById(conversationId).orElse(null);
        if (conversation == null) {
            return null;
        }
        return ConversationResponse.builder()
                .conversationId(conversation.getConversationsId())
                .user(conversation.getUser())
                .startTime(conversation.getStartTime())
                .endTime(conversation.getEndTime())
                .build();
    }

    public Page<ConversationResponse> getConversations(Pageable pageable) {
        Page<Conversations> conversationPage = conversationRepository.findAll(pageable);

        return conversationPage.map(conversation -> ConversationResponse.builder()
                .conversationId(conversation.getConversationsId())
                .user(conversation.getUser())
                .startTime(conversation.getStartTime())
                .endTime(conversation.getEndTime())
                .build()
        );
    }



}
