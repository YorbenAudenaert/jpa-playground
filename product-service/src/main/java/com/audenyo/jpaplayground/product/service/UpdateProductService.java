package com.audenyo.jpaplayground.product.service;

import com.audenyo.jpaplayground.product.domain.Product;
import com.audenyo.jpaplayground.product.port.ProductPersistencePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateProductService.class);

    private final ProductPersistencePort productPersistencePort;

    public UpdateProductService(ProductPersistencePort productPersistencePort) {
        this.productPersistencePort = productPersistencePort;
    }

    @Transactional
    public Product updateProductPrice(String id, double price) {
        LOGGER.trace("Retrieving product info with ID {}", id);
        Product product = productPersistencePort.getProductById(id);
        product.setPrice(price);
        LOGGER.trace("Updating product {} to price = {}", id, price);
        return productPersistencePort.saveProduct(product);
    }

    @Transactional
    public Product updateProductAttributeValue(String productId, String attributeId, String value) {
        Product product = productPersistencePort.getProductById(productId);
        product.getAttribute(attributeId).setValue(value);
        return productPersistencePort.saveProduct(product);
    }
}
