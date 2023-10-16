package com.audenyo.jpaplayground.product.service;

import com.audenyo.jpaplayground.product.domain.Category;
import com.audenyo.jpaplayground.product.port.CategoryPersistencePort;
import com.audenyo.jpaplayground.product.port.ProductPersistencePort;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryPersistencePort categoryPersistencePort;
    private final ProductPersistencePort productPersistencePort;

    public CategoryService(CategoryPersistencePort categoryPersistencePort, ProductPersistencePort productPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
        this.productPersistencePort = productPersistencePort;
    }

    public String createCategory(String description) {
        Category category = new Category(description);
        return categoryPersistencePort.save(category).getId();
    }

    public Category updateCategoryDescription(String categoryId, String description){
        Category category = categoryPersistencePort.getById(categoryId);
        category.setDescription(description);
        categoryPersistencePort.save(category);
        productPersistencePort.getProducts();
        return categoryPersistencePort.getById(categoryId);
    }

    public Category getCategory(String categoryId){
        return categoryPersistencePort.getById(categoryId);
    }
}
