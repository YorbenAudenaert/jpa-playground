package com.audenyo.jpaplayground.api.domain.product;

import com.audenyo.jpaplayground.product.command.CreateProductCommand;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize
public record CreateProductDto(
        String description,
        Double price,
        @JsonSerialize List<CreateProductAttributeDto> productAttributes) {

    public CreateProductCommand toCommand() {
        return new CreateProductCommand(
                description,
                price,
                productAttributes.stream().map(CreateProductAttributeDto::toCommand).toList(),
                null);
    }
}
