package com.audenyo.jpaplayground.product.persistence.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class ProductAttributeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn
    private ProductEntity product;

    private String description;

    private String value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductAttributeEntity that = (ProductAttributeEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(description, that.description) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, value);
    }
}
