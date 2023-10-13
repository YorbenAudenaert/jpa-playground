package com.audenyo.jpaplayground.product.service;

import com.audenyo.jpaplayground.product.command.CreateProductAttributePair;
import com.audenyo.jpaplayground.product.command.CreateProductCommand;
import com.audenyo.jpaplayground.product.domain.Product;
import com.audenyo.jpaplayground.product.domain.ProductAttribute;
import com.audenyo.jpaplayground.product.port.ProductPersistencePort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreateProductService {

    private final ProductPersistencePort productPersistencePort;

    public CreateProductService(ProductPersistencePort productPersistencePort) {
        this.productPersistencePort = productPersistencePort;
    }

    @Transactional
    public String createProduct(CreateProductCommand command) {
        Product product = new Product();
        product.setDescription(command.description());
        product.setPrice(command.price());
        product.setProductAttributes(getAttributesFromPair(command.attributePairs()));
        return productPersistencePort.saveProduct(product).getId();
    }

    private List<ProductAttribute> getAttributesFromPair(List<CreateProductAttributePair> pairs) {
        List<ProductAttribute> attributes = new ArrayList<>();
        for (CreateProductAttributePair pair : pairs) {
            ProductAttribute attribute = new ProductAttribute();
            attribute.setDescription(pair.description());
            attribute.setValue(pair.value());
            attributes.add(attribute);
        }
        return attributes;
    }
}
