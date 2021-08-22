package com.rafdev.springboot.restapi.example.repository;

import com.rafdev.springboot.restapi.example.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CategoryRepositoryTest
{
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void saveCategory() {
        Category category = Category.builder()
                .name("Category name")
                .build();

        categoryRepository.save(category);
    }
}