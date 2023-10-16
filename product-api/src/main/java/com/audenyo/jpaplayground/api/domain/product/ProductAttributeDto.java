package com.audenyo.jpaplayground.api.domain.product;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public record ProductAttributeDto(String id, String description, String value) {
}
