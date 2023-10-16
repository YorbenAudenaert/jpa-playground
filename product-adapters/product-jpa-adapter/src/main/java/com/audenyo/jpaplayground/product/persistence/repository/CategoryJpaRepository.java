package com.audenyo.jpaplayground.product.persistence.repository;

import com.audenyo.jpaplayground.product.persistence.domain.category.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, String> {

    CategoryEntity getById(String id);
}
