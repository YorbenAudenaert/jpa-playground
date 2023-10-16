package com.audenyo.jpaplayground.product.service;

import com.audenyo.jpaplayground.product.domain.Category;
import com.audenyo.jpaplayground.product.domain.Product;
import com.audenyo.jpaplayground.product.domain.ProductAttribute;
import com.audenyo.jpaplayground.product.port.CategoryPersistencePort;
import com.audenyo.jpaplayground.product.port.ProductPersistencePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UpdateProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateProductService.class);

    private final ProductPersistencePort productPersistencePort;
    private final CategoryPersistencePort categoryPersistencePort;

    public UpdateProductService(ProductPersistencePort productPersistencePort, CategoryPersistencePort categoryPersistencePort) {
        this.productPersistencePort = productPersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
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
        LOGGER.info("Saving product with new value...");
        productPersistencePort.saveProduct(product);
        updateProductAttributeDescription(productId, attributeId);
        productPersistencePort.getProductById(productId);
        productPersistencePort.getProductById(productId);
        productPersistencePort.getProductById(productId);
        productPersistencePort.getProductById(productId);
        // ...
        // I can retrieve this information as many times as I want without impacting performance
        // Hibernate persistent cache knows that I already have retrieved this information and will
        // return its cached response.
        return productPersistencePort.getProductById(productId);
    }

    @Transactional
    public Product updateProductCategory(String productId, String categoryId) {
        Product product = productPersistencePort.getProductById(productId);
        Category category = categoryPersistencePort.getById(categoryId);
        product.setCategory(category);
        return productPersistencePort.saveProduct(product);
    }

    private void updateProductAttributeDescription(String productId, String attributeId) {
        LOGGER.info("Unexpectedly change the attribute description!!!");
        List<ProductAttribute> attributes = new ArrayList<>();
        String unexpectedChange = "unexpected";
        Product product = productPersistencePort.getProductById(productId);
        ProductAttribute attribute = product.getAttribute(attributeId);
        attribute.setDescription(unexpectedChange);
        attributes.add(attribute);
        product.setProductAttributes(attributes);
        productPersistencePort.saveProduct(product);
    }
}
