package com.audenyo.jpaplayground.api.domain;

import com.audenyo.jpaplayground.product.command.CreateProductCommand;

import java.util.List;

public record CreateProductDto(String description, Double price, List<CreateProductAttributeDto> productAttributes) {

    public CreateProductCommand toCommand() {
        return new CreateProductCommand(
                description,
                price,
                productAttributes.stream().map(CreateProductAttributeDto::toCommand).toList());
    }
}
