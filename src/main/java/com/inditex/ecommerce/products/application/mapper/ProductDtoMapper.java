package com.inditex.ecommerce.products.application.mapper;

import com.inditex.ecommerce.generated.model.ProductScoreResponseDTO;
import com.inditex.ecommerce.products.domain.model.ProductScore;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ProductDtoMapper {

    public ProductScoreResponseDTO toDto(ProductScore productScore) {
        Map<String, Integer> stock = productScore.stock().entrySet().stream()
                .collect(Collectors.toMap(
                        e -> e.getKey().name(),
                        Map.Entry::getValue
                ));
        return new ProductScoreResponseDTO(productScore.id(), productScore.name(),
                productScore.score(), productScore.salesUnits(), stock);
    }
}
