package com.audenyo.jpaplayground.product.port;

import com.audenyo.jpaplayground.product.domain.Category;

public interface CategoryPersistencePort {
    Category getById(String id);
    Category save(Category category);
}
