package com.audenyo.jpaplayground.product.domain;

import java.util.List;

public class Product {
    private String id;
    private double price;
    private String description;
    private List<ProductAttribute> productAttributes;
    private Category category;

    public Product() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProductAttribute> getProductAttributes() {
        return productAttributes;
    }

    public void setProductAttributes(List<ProductAttribute> productAttributes) {
        this.productAttributes = productAttributes;
    }

    public ProductAttribute getAttribute(String attributeId) {
        return this.productAttributes.stream()
                .filter(attr -> attr.getId().equals(attributeId))
                .findFirst().orElseThrow();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
