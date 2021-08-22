package com.rafdev.springboot.restapi.example.service;

import com.rafdev.springboot.restapi.example.entity.Category;

import java.util.List;

public interface CategoryService
{
    Category saveCategory(Category category);

    List<Category> getCategories();

    Category getCategoryById(Long id);

    Category updateCategory(Long id, Category category);

    void deleteCategory(Long id);
}
