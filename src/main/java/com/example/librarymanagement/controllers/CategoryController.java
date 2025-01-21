package com.example.librarymanagement.controllers;

import com.example.librarymanagement.dtos.request.CategoryRequest;
import com.example.librarymanagement.dtos.response.CategoryResponse;
import com.example.librarymanagement.services.CategoryService;
import jakarta.annotation.security.RolesAllowed;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {

    CategoryService categoryService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.createCategory(request));
    }

    @GetMapping
    ResponseEntity<List<CategoryResponse>> getCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategories());
    }

    @PutMapping("/{categoryId}")
    ResponseEntity<CategoryResponse> updateCategory(@PathVariable("categoryId") Long id, @RequestBody CategoryRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.updateCategory(id, request));
    }

    @DeleteMapping("/{categoryId}")
    ResponseEntity<String> deleteCategory(@PathVariable("categoryId") Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body("Delete successfully");
    }

}
