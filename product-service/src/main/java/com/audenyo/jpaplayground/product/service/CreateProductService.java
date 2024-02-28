package com.audenyo.jpaplayground.product.service;

import com.audenyo.jpaplayground.product.command.CreateProductAttributePair;
import com.audenyo.jpaplayground.product.command.CreateProductCommand;
import com.audenyo.jpaplayground.product.domain.Category;
import com.audenyo.jpaplayground.product.domain.Product;
import com.audenyo.jpaplayground.product.domain.ProductAttribute;
import com.audenyo.jpaplayground.product.port.CategoryPersistencePort;
import com.audenyo.jpaplayground.product.port.ProductPersistencePort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreateProductService {

    private final ProductPersistencePort productPersistencePort;
    private final CategoryPersistencePort categoryPersistencePort;

    public CreateProductService(ProductPersistencePort productPersistencePort, CategoryPersistencePort categoryPersistencePort) {
        this.productPersistencePort = productPersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Transactional
    public String createProduct(CreateProductCommand command) {
        Category category = categoryPersistencePort.getById(command.categoryId());
        Product product = getProductFromCommand(command);
        product.setCategory(category);
        return productPersistencePort.saveProduct(product).getId();
    }


    @Transactional
    public String createProductWithCategory(CreateProductCommand command) {
        Category category = categoryPersistencePort.getById(command.categoryId());
        Product product = getProductFromCommand(command);
        product.setCategory(category);
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

    private Product getProductFromCommand(CreateProductCommand command) {
        Product product = new Product();
        product.setDescription(command.description());
        product.setPrice(command.price());
        product.setProductAttributes(getAttributesFromPair(command.attributePairs()));
        return product;
    }
}
