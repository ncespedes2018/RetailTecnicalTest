package com.inditex.ecommerce.products.infrastructure.adapter.output.persistence;

import com.inditex.ecommerce.products.application.port.output.ProductRepositoryPort;
import com.inditex.ecommerce.products.domain.model.Product;
import com.inditex.ecommerce.products.domain.model.Size;
import com.inditex.ecommerce.products.domain.model.Stock;
import com.inditex.ecommerce.products.infrastructure.persistence.entity.ProductEntity;
import com.inditex.ecommerce.products.infrastructure.persistence.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final ProductRepository repo;

    @Override
    public List<Product> findAll() {
        return repo.findAll().stream()
                .map(this::toDomain)
                .toList();
    }

    private Product toDomain(ProductEntity entity) {
        Map<Size, Integer> stockBySize = entity.getStockBySize().entrySet().stream()
                .collect(Collectors.toMap(
                        e -> Size.valueOf(e.getKey()),
                        Map.Entry::getValue
                ));
        return new Product(entity.getId(), entity.getName(),
                entity.getSalesUnits(), new Stock(stockBySize));
    }
}
