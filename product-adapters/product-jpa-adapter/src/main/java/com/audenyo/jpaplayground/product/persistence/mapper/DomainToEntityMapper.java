package com.audenyo.jpaplayground.product.persistence.mapper;

import com.audenyo.jpaplayground.product.domain.Category;
import com.audenyo.jpaplayground.product.persistence.domain.ProductAttributeEntity;
import com.audenyo.jpaplayground.product.persistence.domain.ProductEntity;
import com.audenyo.jpaplayground.product.domain.Product;
import com.audenyo.jpaplayground.product.domain.ProductAttribute;
import com.audenyo.jpaplayground.product.persistence.domain.category.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class DomainToEntityMapper {

    public ProductEntity toEntity(Product product) {
        ProductEntity entity = new ProductEntity();
        entity.setId(product.getId());
        entity.setPrice(product.getPrice());
        entity.setDescription(product.getDescription());
        if (product.getProductAttributes() != null) {
            entity.setProductAttributes(product.getProductAttributes().stream().map(this::toEntity).toList());
        }
        entity.setCategory(this.toEntity(product.getCategory()));
        return entity;
    }

    public ProductAttributeEntity toEntity(ProductAttribute productAttribute) {
        ProductAttributeEntity entity = new ProductAttributeEntity();
        entity.setId(productAttribute.getId());
        entity.setDescription(productAttribute.getDescription());
        entity.setValue(productAttribute.getValue());
        return entity;
    }

    public CategoryEntity toEntity(Category category) {
        if (category == null) {
            return null;
        }
        CategoryEntity entity = new CategoryEntity();
        entity.setId(category.getId());
        entity.setDescription(category.getDescription());
        return entity;
    }
}
