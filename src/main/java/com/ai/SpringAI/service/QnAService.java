package com.ai.SpringAI.service;

import com.ai.SpringAI.dto.request.QnARequest;
import com.ai.SpringAI.dto.response.QnAResponse;
import com.ai.SpringAI.entity.Category;
import com.ai.SpringAI.entity.QnA;
import com.ai.SpringAI.repository.CategoryRepository;
import com.ai.SpringAI.repository.QnARepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class QnAService {

    private final CategoryRepository categoryRepository;
    private final QnARepository qnARepository;

    public QnAService(CategoryRepository categoryRepository, QnARepository qnARepository) {
        this.categoryRepository = categoryRepository;
        this.qnARepository = qnARepository;
    }

    public List<QnAResponse> addQnAList(List<QnARequest> requests, Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).get();

        List<QnA> qnAList = new ArrayList<>();
        List<QnAResponse> qnAResponseList = new ArrayList<>();

        for(QnARequest request : requests) {
            QnA qnA = new QnA();
            qnA.setCategory(category);
            qnA.setAnswer(request.getAnswer());
            qnA.setQuestion(request.getQuestion());
            qnA.setCreatedAt(LocalDateTime.now());
            qnAList.add(qnA);
            QnAResponse qnAResponse = new QnAResponse();
            qnAResponse.setCategoryId(categoryId);
            BeanUtils.copyProperties(qnA, qnAResponse);
            qnAResponseList.add(qnAResponse);
        }
        qnARepository.saveAll(qnAList);
        return qnAResponseList;
    }

}
