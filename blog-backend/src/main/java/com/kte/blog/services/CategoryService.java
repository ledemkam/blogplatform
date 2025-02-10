package com.kte.blog.services;

import com.kte.blog.domain.entities.Category;

import java.util.List;

public interface CategoryService {

    List<Category> listCategories();
    Category createCategory(Category category);
}
