package com.ai.SpringAI.repository;

import com.ai.SpringAI.entity.Conversations;
import com.ai.SpringAI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConversationRepository extends JpaRepository<Conversations, UUID> {
    Optional<Conversations> findTopByUserOrderByStartTimeDesc(User user);
}

