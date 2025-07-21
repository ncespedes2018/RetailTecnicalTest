package com.inditex.ecommerce.products.domain.model;

import java.util.Map;

public record ProductScore(Long id, String name, double score, int salesUnits, Map<Size, Integer> stock) {
}
