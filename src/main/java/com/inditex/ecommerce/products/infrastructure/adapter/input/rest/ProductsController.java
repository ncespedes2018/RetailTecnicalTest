package com.inditex.ecommerce.products.infrastructure.adapter.input.rest;

import com.inditex.ecommerce.generated.model.ProductScoreResponseDTO;
import com.inditex.ecommerce.generated.model.ScoringWeightsRequestDTO;
import com.inditex.ecommerce.products.application.port.input.ProductOrderingUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductsController {

    private final ProductOrderingUseCase productOrderingUseCase;

    @PostMapping("/order")
    public ResponseEntity<List<ProductScoreResponseDTO>> orderProducts(
            @Valid @RequestBody ScoringWeightsRequestDTO scoringWeightsRequest) {
        return ResponseEntity.ok(
                productOrderingUseCase.orderProducts(scoringWeightsRequest.getSalesByUnits(),
                        scoringWeightsRequest.getStockRatio()));
    }
}
