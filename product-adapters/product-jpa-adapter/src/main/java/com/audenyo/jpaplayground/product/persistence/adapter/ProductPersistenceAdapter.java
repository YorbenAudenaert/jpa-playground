package com.audenyo.jpaplayground.product.persistence.adapter;

import com.audenyo.jpaplayground.product.persistence.domain.ProductEntity;
import com.audenyo.jpaplayground.product.persistence.mapper.DomainToEntityMapper;
import com.audenyo.jpaplayground.product.persistence.mapper.EntityToDomainMapper;
import com.audenyo.jpaplayground.product.domain.Product;
import com.audenyo.jpaplayground.product.port.ProductPersistencePort;
import com.audenyo.jpaplayground.product.persistence.repository.ProductJpaRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductPersistenceAdapter implements ProductPersistencePort {

    private final ProductJpaRepository productJpaRepository;
    private final EntityToDomainMapper entityToDomainMapper;
    private final DomainToEntityMapper domainToEntityMapper;

    public ProductPersistenceAdapter(ProductJpaRepository productJpaRepository, EntityToDomainMapper entityToDomainMapper, DomainToEntityMapper domainToEntityMapper) {
        this.productJpaRepository = productJpaRepository;
        this.entityToDomainMapper = entityToDomainMapper;
        this.domainToEntityMapper = domainToEntityMapper;
    }

    @Override
    public Product getProductById(String id) {
        return entityToDomainMapper.toDomain(productJpaRepository.getById(id));
    }

    @Override
    public List<Product> getProducts() {
        Iterable<ProductEntity> products = productJpaRepository.findAll();
        return Streamable.of(products).stream().map(entityToDomainMapper::toDomain).toList();
    }

    @Override
    public Product saveProduct(Product product) {
        ProductEntity entity = domainToEntityMapper.toEntity(product);
        return entityToDomainMapper.toDomain(productJpaRepository.save(entity));
    }
}
