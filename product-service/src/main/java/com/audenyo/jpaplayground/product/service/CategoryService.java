package com.audenyo.jpaplayground.product.service;

import com.audenyo.jpaplayground.product.domain.Category;
import com.audenyo.jpaplayground.product.port.CategoryPersistencePort;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryPersistencePort categoryPersistencePort;

    public CategoryService(CategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    public String createCategory(String description) {
        Category category = new Category(description);
        return categoryPersistencePort.save(category).getId();
    }

    public Category getCategory(String categoryId){
        return categoryPersistencePort.getById(categoryId);
    }
}
