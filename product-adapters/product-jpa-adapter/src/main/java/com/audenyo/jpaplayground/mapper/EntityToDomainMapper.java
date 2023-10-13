package com.audenyo.jpaplayground.mapper;

import com.audenyo.jpaplayground.domain.ProductAttributeEntity;
import com.audenyo.jpaplayground.domain.ProductEntity;
import com.audenyo.jpaplayground.product.domain.Product;
import com.audenyo.jpaplayground.product.domain.ProductAttribute;
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
        return p;
    }

    public ProductAttribute toDomain(ProductAttributeEntity entity) {
        ProductAttribute attribute = new ProductAttribute();
        attribute.setId(entity.getId());
        attribute.setDescription(entity.getDescription());
        attribute.setValue(entity.getValue());
        return attribute;
    }
}
