package com.ai.SpringAI.service;

import com.ai.SpringAI.dto.request.CategoryRequest;
import com.ai.SpringAI.dto.response.CategoryResponse;
import com.ai.SpringAI.entity.Category;
import com.ai.SpringAI.repository.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryResponse> addCategoryList(List<CategoryRequest> categoryRequests) {

        List<Category> categories = new ArrayList<>();
        List<CategoryResponse> categoryResponses = new ArrayList<>();

        for(CategoryRequest categoryRequest : categoryRequests) {

            Category category = new Category();
            category.setName(categoryRequest.getName());
            category.setDescription(categoryRequest.getDescription());
            categories.add(category);
            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setCategoryId(category.getCategoryId());
            categoryResponse.setName(category.getName());
            categoryResponse.setDescription(category.getDescription());
            categoryResponse.setQnas(category.getQnas());
            categoryResponses.add(categoryResponse);
        }
        categoryRepository.saveAll(categories);
        return categoryResponses;
    }
}
