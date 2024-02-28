package com.audenyo.jpaplayground.product.persistence.adapter;

import com.audenyo.jpaplayground.product.domain.Category;
import com.audenyo.jpaplayground.product.exception.ObjectNotFoundException;
import com.audenyo.jpaplayground.product.persistence.domain.category.CategoryEntity;
import com.audenyo.jpaplayground.product.persistence.mapper.DomainToEntityMapper;
import com.audenyo.jpaplayground.product.persistence.mapper.EntityToDomainMapper;
import com.audenyo.jpaplayground.product.persistence.repository.CategoryJpaRepository;
import com.audenyo.jpaplayground.product.port.CategoryPersistencePort;
import org.springframework.stereotype.Service;

@Service
public class CategoryPersistenceAdapter implements CategoryPersistencePort {

    private final CategoryJpaRepository categoryJpaRepository;
    private final EntityToDomainMapper entityToDomainMapper;
    private final DomainToEntityMapper domainToEntityMapper;

    public CategoryPersistenceAdapter(CategoryJpaRepository categoryJpaRepository, EntityToDomainMapper entityToDomainMapper, DomainToEntityMapper domainToEntityMapper) {
        this.categoryJpaRepository = categoryJpaRepository;
        this.entityToDomainMapper = entityToDomainMapper;
        this.domainToEntityMapper = domainToEntityMapper;
    }

    @Override
    public Category getById(String id) {
        CategoryEntity category = categoryJpaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Category not found"));
        return entityToDomainMapper.toDomain(category);
    }

    @Override
    public Category save(Category category) {
        CategoryEntity entity = domainToEntityMapper.toEntity(category);
        return entityToDomainMapper.toDomain(categoryJpaRepository.save(entity));
    }
}
