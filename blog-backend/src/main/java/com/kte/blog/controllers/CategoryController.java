package com.kte.blog.controllers;

import com.kte.blog.domain.dtos.CategoryDto;
import com.kte.blog.domain.entities.Category;
import com.kte.blog.domain.request.LoginRequest;
import com.kte.blog.mappers.CategoryMapper;
import com.kte.blog.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCategories() {
       List<Category> categories = categoryService.listCategories();
         return ResponseEntity.ok(
                categories.stream()
                          .map(categoryMapper::toDto)
                          .toList()
         );
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody LoginRequest.CreateCategoryRequest createCategoryRequest) {
        Category category = categoryMapper.toEntity(createCategoryRequest);
        Category savedCategory = categoryService.createCategory(category);
        return  new ResponseEntity<>(categoryMapper.toDto(savedCategory), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

}
