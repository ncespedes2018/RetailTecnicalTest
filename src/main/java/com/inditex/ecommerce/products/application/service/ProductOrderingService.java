package com.inditex.ecommerce.products.application.service;

import com.inditex.ecommerce.generated.model.ProductScoreResponseDTO;
import com.inditex.ecommerce.products.application.port.input.ProductOrderingUseCase;
import com.inditex.ecommerce.products.application.port.output.ProductRepositoryPort;
import com.inditex.ecommerce.products.domain.model.Product;
import com.inditex.ecommerce.products.domain.service.ProductScoringDomainService;
import com.inditex.ecommerce.products.application.mapper.ProductDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductOrderingService implements ProductOrderingUseCase {
    private final ProductRepositoryPort repository;
    private final ProductDtoMapper mapper;
    private final ProductScoringDomainService scoringService;

    @Override
    public List<ProductScoreResponseDTO> orderProducts(Double salesByUnits, Double stockRatio) {
        List<Product> products = repository.findAll();
        return scoringService.calculateOrderedScores(products, salesByUnits, stockRatio)
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}

