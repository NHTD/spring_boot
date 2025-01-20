package com.example.librarymanagement.services;

import com.example.librarymanagement.dtos.request.CategoryRequest;
import com.example.librarymanagement.dtos.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest request);
    List<CategoryResponse> getCategories();
    CategoryResponse updateCategory(Long id, CategoryRequest request);
    void deleteCategory(Long id);
}
