package com.ai.SpringAI.controller;

import com.ai.SpringAI.dto.request.QnARequest;
import com.ai.SpringAI.dto.response.QnAResponse;
import com.ai.SpringAI.service.QnAService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/QnA")
public class QnAController {
    private final QnAService qnAService;

    public QnAController(QnAService qnAService) {
        this.qnAService = qnAService;
    }

    @PostMapping("/{categoryId}/add")
    public List<QnAResponse> add(@RequestBody List<QnARequest> qnARequests, @PathVariable Integer categoryId) {
        return qnAService.addQnAList(qnARequests, categoryId);
    }
}
