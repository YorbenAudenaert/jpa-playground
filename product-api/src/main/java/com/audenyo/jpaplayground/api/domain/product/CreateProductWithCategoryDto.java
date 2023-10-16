package com.audenyo.jpaplayground.api.domain.product;

import com.audenyo.jpaplayground.product.command.CreateProductCommand;

import java.util.List;

public record CreateProductWithCategoryDto(String description, Double price,
                                           List<CreateProductAttributeDto> productAttributes, String categoryId) {
    public CreateProductCommand toCommand() {
        return new CreateProductCommand(
                description,
                price,
                productAttributes.stream().map(CreateProductAttributeDto::toCommand).toList(),
                categoryId
        );
    }
}
