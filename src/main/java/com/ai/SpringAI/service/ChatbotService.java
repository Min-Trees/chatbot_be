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
    private static final String PYTHON_API_URL = "http://localhost:8000/ask";
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
        if (chatRequest == null || chatRequest.getMessage() == null || chatRequest.getUserId() == null) {
            throw new IllegalArgumentException("Invalid chat request: message or userId is missing");
        }

        String userInput = chatRequest.getMessage().trim().toLowerCase();
        UUID userId;
        try {
            userId = UUID.fromString(chatRequest.getUserId());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid userId format: " + chatRequest.getUserId());
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        // Tạo JSON body cho FastAPI
        String jsonBody = String.format("{\"question\": \"%s\"}", userInput);

        // Tìm hoặc tạo cuộc hội thoại
        Conversations conversation = findOrCreateConversation(user);
        Timestamp now = new Timestamp(System.currentTimeMillis());

        // Lưu tin nhắn người dùng
        Message message = new Message();
        message.setSender(SenderType.USER);
        message.setMessageText(userInput);
        message.setSentAt(now);
        message.setConversation(conversation);
        messageRepository.save(message);

        // Thiết lập headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Gửi yêu cầu tới FastAPI
        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);
        ResponseEntity<PythonApiResponse> response;
        try {
            response = restTemplate.postForEntity(PYTHON_API_URL, entity, PythonApiResponse.class);
        } catch (Exception e) {
            // Ghi log lỗi và trả về thông báo lỗi
            System.err.println("Error communicating with FastAPI: " + e.getMessage());
            throw new RuntimeException("Failed to get response from Python API: " + e.getMessage());
        }

        // Xử lý phản hồi từ FastAPI
        PythonApiResponse apiResponse = response.getBody();
        String botAnswer = (apiResponse != null && apiResponse.getAnswer() != null)
                ? apiResponse.getAnswer()
                : "Xin lỗi, tôi không tìm thấy thông tin phù hợp.";

        // Lưu tin nhắn bot
        Message botMessage = new Message();
        botMessage.setConversation(conversation);
        botMessage.setSender(SenderType.BOT);
        botMessage.setMessageText(botAnswer);
        botMessage.setSentAt(new Timestamp(System.currentTimeMillis()));
        messageRepository.save(botMessage);

        return new ChatResponse(botAnswer);
    }

    // DTO cho phản hồi từ FastAPI
    public static class PythonApiResponse {
        private String answer;
        private String context; // Thêm trường context để khớp với phản hồi của FastAPI

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }
    }

    private Conversations findOrCreateConversation(User user) {
        return conversationRepository.findTopByUserOrderByStartTimeDesc(user)
                .orElseGet(() -> {
                    Conversations newConversation = new Conversations();
                    newConversation.setUser(user);
                    newConversation.setStartTime(new Timestamp(System.currentTimeMillis()));
                    return conversationRepository.save(newConversation);
                });
    }
}