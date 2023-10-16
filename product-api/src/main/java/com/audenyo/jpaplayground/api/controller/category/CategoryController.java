package com.audenyo.jpaplayground.api.controller.category;

import com.audenyo.jpaplayground.api.domain.category.GetCategoryDto;
import com.audenyo.jpaplayground.product.domain.Category;
import com.audenyo.jpaplayground.product.service.CategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PutMapping
    public String createCategory(@RequestParam String description) {
        return categoryService.createCategory(description);
    }

    @GetMapping("/{categoryId}")
    public GetCategoryDto getCategory(@PathVariable String categoryId) {
        Category category = categoryService.getCategory(categoryId);
        return new GetCategoryDto(category.getId(), category.getDescription());
    }

    @PutMapping("/{categoryId}")
    public GetCategoryDto updateCategoryDescription(@PathVariable String categoryId, @RequestParam String newDescription) {
        Category category = categoryService.updateCategoryDescription(categoryId, newDescription);
        return new GetCategoryDto(category.getId(), category.getDescription());
    }

}
