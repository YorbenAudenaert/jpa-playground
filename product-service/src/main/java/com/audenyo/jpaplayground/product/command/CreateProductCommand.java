package com.audenyo.jpaplayground.product.command;

import java.util.List;

public record CreateProductCommand(String description, double price, List<CreateProductAttributePair> attributePairs, String categoryId) {
}
