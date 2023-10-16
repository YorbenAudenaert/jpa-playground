package com.audenyo.jpaplayground.product.persistence.repository;

import com.audenyo.jpaplayground.product.persistence.domain.ProductEntity;
import org.springframework.data.repository.CrudRepository;


public interface ProductJpaRepository extends CrudRepository<ProductEntity, String> {

    ProductEntity getById(String id);
}
