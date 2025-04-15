package com.ai.SpringAI.controller;
import com.ai.SpringAI.dto.request.CategoryRequest;
import com.ai.SpringAI.dto.response.CategoryResponse;
import com.ai.SpringAI.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public List<CategoryResponse> add(@RequestBody List<CategoryRequest> requests) {
        return categoryService.addCategoryList(requests);
    }


}
