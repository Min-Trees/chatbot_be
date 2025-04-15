package com.ai.SpringAI.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "sessions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Session {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    UUID sessionId;

    @Column(name = "user_id", nullable = false)
    UUID userId;

    @Column(name = "start_time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    Timestamp startTime;

    @Column(name = "last_active")
    Timestamp lastActive;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    SessionStatus status = SessionStatus.ACTIVE;

    // Enum để quản lý trạng thái phiên
    public enum SessionStatus {
        ACTIVE, ENDED
    }
}
