package com.example.librarymanagement.mapper;

import com.example.librarymanagement.dtos.request.CategoryRequest;
import com.example.librarymanagement.dtos.response.CategoryResponse;
import com.example.librarymanagement.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryRequest request);
    CategoryResponse toCategoryResponse(Category category);

    void toUpdateCategory(@MappingTarget Category category, CategoryRequest request);
}
