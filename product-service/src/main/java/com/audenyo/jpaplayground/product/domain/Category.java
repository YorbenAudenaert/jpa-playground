package com.audenyo.jpaplayground.product.domain;

public class Category {
    private String id;
    private String description;

    public Category() {
    }

    public Category(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
