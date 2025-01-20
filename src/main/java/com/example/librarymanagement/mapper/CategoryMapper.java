package com.example.librarymanagement.mapper;

import com.example.librarymanagement.dtos.request.CategoryRequest;
import com.example.librarymanagement.dtos.response.CategoryResponse;
import com.example.librarymanagement.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category categoryToCategory(CategoryRequest request);
    CategoryResponse categoryToCategoryResponse(Category category);

    void categoryToUpdateCategory(@MappingTarget Category category, CategoryRequest request);
}
