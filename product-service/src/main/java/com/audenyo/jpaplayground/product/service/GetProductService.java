package com.audenyo.jpaplayground.product.service;

import com.audenyo.jpaplayground.product.domain.Product;
import com.audenyo.jpaplayground.product.port.ProductPersistencePort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetProductService {

    private final ProductPersistencePort productPersistencePort;

    public GetProductService(ProductPersistencePort productPersistencePort) {
        this.productPersistencePort = productPersistencePort;
    }

    public Product getProduct(String productId) {
        return productPersistencePort.getProductById(productId);
    }

    public List<Product> getAllProducts() {
        return productPersistencePort.getProducts();
    }
}
