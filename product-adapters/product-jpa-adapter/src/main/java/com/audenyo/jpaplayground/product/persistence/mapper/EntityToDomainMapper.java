package com.audenyo.jpaplayground.product.persistence.mapper;

import com.audenyo.jpaplayground.product.domain.Category;
import com.audenyo.jpaplayground.product.persistence.domain.ProductAttributeEntity;
import com.audenyo.jpaplayground.product.persistence.domain.ProductEntity;
import com.audenyo.jpaplayground.product.domain.Product;
import com.audenyo.jpaplayground.product.domain.ProductAttribute;
import com.audenyo.jpaplayground.product.persistence.domain.category.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class EntityToDomainMapper {

    public Product toDomain(ProductEntity entity) {
        Product p = new Product();
        p.setId(entity.getId());
        p.setPrice(entity.getPrice());
        p.setDescription(entity.getDescription());
        if (entity.getProductAttributes() != null) {
            p.setProductAttributes(entity.getProductAttributes().stream().map(this::toDomain).toList());
        }
        p.setCategory(this.toDomain(entity.getCategory()));
        return p;
    }

    public ProductAttribute toDomain(ProductAttributeEntity entity) {
        ProductAttribute attribute = new ProductAttribute();
        attribute.setId(entity.getId());
        attribute.setDescription(entity.getDescription());
        attribute.setValue(entity.getValue());
        return attribute;
    }

    public Category toDomain(CategoryEntity entity) {
        if (entity == null) {
            return null;
        }
        Category category = new Category();
        category.setId(entity.getId());
        category.setDescription(entity.getDescription());
        return category;
    }
}
