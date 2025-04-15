package com.ai.SpringAI.dto.response;

import com.ai.SpringAI.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.util.UUID;
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConversationResponse {
    UUID conversationId;
    User user;
    Timestamp startTime;
    Timestamp endTime;
}
