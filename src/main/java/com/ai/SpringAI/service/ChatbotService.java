package com.ai.SpringAI.service;

import com.ai.SpringAI.dto.request.ChatRequest;
import com.ai.SpringAI.dto.response.ChatResponse;
import com.ai.SpringAI.entity.Message;
import com.ai.SpringAI.entity.SenderType;
import com.ai.SpringAI.repository.ConversationRepository;
import com.ai.SpringAI.entity.Conversations;
import com.ai.SpringAI.entity.User;
import com.ai.SpringAI.repository.MessageRepository;
import com.ai.SpringAI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.UUID;

@Service
public class ChatbotService {

    private final RestTemplate restTemplate;
    private static final String PYTHON_API_URL = "http://localhost:8000/get_answer";
    private final UserRepository userRepository;
    @Autowired
    private ConversationRepository conversationRepository;
    @Autowired
    private MessageRepository messageRepository;

    public ChatbotService(UserRepository userRepository) {
        this.restTemplate = new RestTemplate();
        this.userRepository = userRepository;
    }

    public ChatResponse getResponse(ChatRequest chatRequest) {
        String userInput = chatRequest.getMessage().trim().toLowerCase();
        UUID userId = chatRequest.getUserId();
        User user = userRepository.findById(userId).orElseThrow();
        // Tạo JSON body
        String jsonBody = String.format("{\"query\": \"%s\"}", userInput);
        Conversations conversation = findOrCreateConversation(user);
        Timestamp now = new Timestamp(System.currentTimeMillis());

        Message message = new Message();
        message.setSender(SenderType.USER);
        message.setMessageText(userInput);
        message.setSentAt(now);
        message.setConversation(conversation);
        messageRepository.save(message);


        // Thiết lập headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Gửi yêu cầu với HttpEntity
        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);
        ResponseEntity<PythonApiResponse> response = restTemplate.postForEntity(
                PYTHON_API_URL,
                entity,
                PythonApiResponse.class
        );

        // Xử lý phản hồi từ API
        String botAnswer = (response.getBody() != null && response.getBody().getAnswer() != null)
                ? response.getBody().getAnswer()
                : "Xin lỗi, tôi không tìm thấy thông tin phù hợp.";

        Message botMessage = new Message();
        botMessage.setConversation(conversation);
        botMessage.setSender(SenderType.BOT);
        botMessage.setMessageText(botAnswer);
        botMessage.setSentAt(new Timestamp(System.currentTimeMillis()));
        messageRepository.save(botMessage);
        return new ChatResponse(botAnswer);
    }

    // DTO cho response từ API
    static class PythonApiResponse {
        private String answer;
        private double similarity;

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public double getSimilarity() {
            return similarity;
        }

        public void setSimilarity(double similarity) {
            this.similarity = similarity;
        }
    }
    private Conversations findOrCreateConversation(User user) {
        System.out.println("da vao");
        return conversationRepository.findTopByUserOrderByStartTimeDesc(user)
                .orElseGet(() -> {
                    Conversations newConversation = new Conversations();
                    newConversation.setUser(user);
                    newConversation.setStartTime(new Timestamp(System.currentTimeMillis()));
                    return conversationRepository.save(newConversation);
                });
    }


}