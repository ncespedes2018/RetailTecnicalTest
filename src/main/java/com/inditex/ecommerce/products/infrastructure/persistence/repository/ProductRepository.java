package com.inditex.ecommerce.products.infrastructure.persistence.repository;

import com.inditex.ecommerce.products.infrastructure.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}

