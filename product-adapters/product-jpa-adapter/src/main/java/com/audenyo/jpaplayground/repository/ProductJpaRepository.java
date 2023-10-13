package com.audenyo.jpaplayground.repository;

import com.audenyo.jpaplayground.domain.ProductEntity;
import org.springframework.data.repository.CrudRepository;


public interface ProductJpaRepository extends CrudRepository<ProductEntity, Long> {

    ProductEntity getById(String id);
}
