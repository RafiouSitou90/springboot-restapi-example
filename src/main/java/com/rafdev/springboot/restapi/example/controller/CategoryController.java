package com.rafdev.springboot.restapi.example.controller;

import com.rafdev.springboot.restapi.example.entity.Category;
import com.rafdev.springboot.restapi.example.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController
{
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        super();
        this.categoryService = categoryService;
    }

    @PostMapping()
    public ResponseEntity<Category> saveCategory(@Valid @RequestBody Category category) {
        return new ResponseEntity<Category>(categoryService.saveCategory(category), HttpStatus.CREATED);
    }

    @GetMapping()
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long id) {
        return new ResponseEntity<Category>(categoryService.getCategoryById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") Long id, @Valid @RequestBody Category category) {
        return new ResponseEntity<Category>(categoryService.updateCategory(id, category), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);

        return new ResponseEntity<String>("Category deleted successfully!", HttpStatus.OK);
    }
}
