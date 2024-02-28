package com.audenyo.jpaplayground.api.domain.product;

import com.audenyo.jpaplayground.product.command.CreateProductCommand;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

@JsonSerialize
public record CreateProductDto(
        String description,
        Double price,
        String category,
        @JsonSerialize List<CreateProductAttributeDto> productAttributes) {

    public CreateProductDto {
        productAttributes = new ArrayList<>();
    }

    public CreateProductCommand toCommand() {
        return new CreateProductCommand(
                description,
                price,
                productAttributes.stream().map(CreateProductAttributeDto::toCommand).toList(),
                category);
    }
}
