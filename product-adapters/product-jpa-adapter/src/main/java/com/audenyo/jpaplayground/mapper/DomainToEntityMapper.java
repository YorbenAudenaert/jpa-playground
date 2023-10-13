package com.audenyo.jpaplayground.mapper;

import com.audenyo.jpaplayground.domain.ProductAttributeEntity;
import com.audenyo.jpaplayground.domain.ProductEntity;
import com.audenyo.jpaplayground.product.domain.Product;
import com.audenyo.jpaplayground.product.domain.ProductAttribute;
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
        return entity;
    }

    public ProductAttributeEntity toEntity(ProductAttribute productAttribute) {
        ProductAttributeEntity entity = new ProductAttributeEntity();
        entity.setId(productAttribute.getId());
        entity.setDescription(productAttribute.getDescription());
        entity.setValue(productAttribute.getValue());
        return entity;
    }

}
