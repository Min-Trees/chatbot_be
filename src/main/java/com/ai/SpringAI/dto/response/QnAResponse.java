package com.ai.SpringAI.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QnAResponse {
    Integer qnaId;
    String question;
    String answer;
    Integer categoryId;
    LocalDateTime createdAt;
}
