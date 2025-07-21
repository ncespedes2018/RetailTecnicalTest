package com.inditex.ecommerce.products.application.port.output;

import com.inditex.ecommerce.products.domain.model.Product;

import java.util.List;

public interface ProductRepositoryPort {
    List<Product> findAll();
}

