package com.audenyo.jpaplayground.api.domain;

import java.util.List;

public record GetProductDto(String id, String description, double price, List<ProductAttributeDto> attributeValuePairs){

}
