package com.ai.SpringAI.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Entity
@Table(name = "mesages")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer messageId;
    @ManyToOne
    @JoinColumn(name = "conversation_id", nullable = false)
    Conversations conversation;
    @Column(name = "sender", nullable = false)
    @Enumerated(EnumType.STRING)
    SenderType sender;

    @Column(name = "message_text", columnDefinition = "TEXT")
    String messageText;

    @Column(name = "sent_at", nullable = false)
    Timestamp sentAt;

}
