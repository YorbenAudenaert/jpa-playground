package com.audenyo.jpaplayground.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private double price;
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ProductAttributeEntity> productAttributes;

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

    public List<ProductAttributeEntity> getProductAttributes() {
        return productAttributes;
    }

    public void setProductAttributes(List<ProductAttributeEntity> productAttributes) {
        this.productAttributes = productAttributes;
    }
}
