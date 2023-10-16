package com.audenyo.jpaplayground.product.persistence.domain;

import com.audenyo.jpaplayground.product.persistence.domain.category.CategoryEntity;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private double price;
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<ProductAttributeEntity> productAttributes;

    @ManyToOne
    @JoinColumn(name = "fk_category")
    private CategoryEntity category;

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

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        ProductEntity that = (ProductEntity) o;
        return Double.compare(price, that.price) == 0 &&
                Objects.equals(id, that.id) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, description);
    }
}
