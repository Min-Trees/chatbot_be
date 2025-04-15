package com.ai.SpringAI.dto.response;

import com.ai.SpringAI.entity.Conversations;
import com.ai.SpringAI.entity.SenderType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageResponse {
    Integer messageId;
    Conversations conversation;
    SenderType sender;
    String messageText;
    Timestamp sentAt;
}
