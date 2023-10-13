package com.audenyo.jpaplayground.product.port;

import com.audenyo.jpaplayground.product.domain.Product;

import java.util.List;

public interface ProductPersistencePort {
    Product getProductById(String id);

    List<Product> getProducts();
    Product saveProduct(Product product);
}
