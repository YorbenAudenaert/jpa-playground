package com.audenyo.jpaplayground.api.domain;

import com.audenyo.jpaplayground.product.command.CreateProductAttributePair;

public class CreateProductAttributeDto {
    private final String description;
    private final String value;

    public CreateProductAttributeDto(String description, String value) {
        this.description = description;
        this.value = value;
    }

    public CreateProductAttributePair toCommand() {
        return new CreateProductAttributePair(description, value);
    }


}
