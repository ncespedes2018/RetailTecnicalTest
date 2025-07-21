package com.inditex.ecommerce.products.domain.service;

import com.inditex.ecommerce.products.domain.scoring.ScoringStrategy;
import com.inditex.ecommerce.products.domain.scoring.ScoringStrategyRegistry;
import com.inditex.ecommerce.products.domain.model.Product;
import com.inditex.ecommerce.products.domain.model.ProductScore;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductScoringDomainService {

    private final ScoringStrategyRegistry registry;

    public ProductScoringDomainService(ScoringStrategyRegistry registry) {
        this.registry = registry;
    }

    public List<ProductScore> calculateOrderedScores(List<Product> products, Double salesByUnits, Double stockRatio) {

        Map<String, Double> weights = normalizeWeights(salesByUnits, stockRatio);

        List<ScoringStrategy> strategies = weights.keySet().stream()
                .map(registry::getStrategy)
                .toList();

        Map<String, Double> maxRaw = strategies.stream()
                .filter(s -> !s.isAlreadyNormalized())
                .collect(Collectors.toMap(
                        ScoringStrategy::name,
                        s -> products.stream().mapToDouble(s::rawScore).max().orElse(0d)
                ));

        return products.stream()
                .map(p -> toScore(p, strategies, weights, maxRaw))
                .sorted(Comparator.comparingDouble(ProductScore::score).reversed())
                .toList();
    }

    private ProductScore  toScore(Product p,
                                 List<ScoringStrategy> strategies,
                                 Map<String, Double> weights,
                                 Map<String, Double> maxRaw) {

        double total = strategies.stream()
                .mapToDouble(s -> {
                    double raw = s.rawScore(p);
                    double normalized = s.isAlreadyNormalized()
                            ? clamp01(raw)
                            : normalize(raw, maxRaw.getOrDefault(s.name(), 0d));
                    return normalized * weights.getOrDefault(s.name(), 0d);
                })
                .sum();

        return new ProductScore(p.id(), p.name(), total, p.salesUnits(), p.stock().quantities());
    }

    private double normalize(double value, double max) {
        return (max <= 0d) ? 0d : clamp01(value / max);
    }

    private double clamp01(double v) {
        return Math.max(0d, Math.min(1d, v));
    }

    private Map<String, Double> normalizeWeights(Double salesByUnits, Double stockRatio) {
        double sales = safe(salesByUnits);
        double stock = safe(stockRatio);
        double sum   = sales + stock;
        if (sum <= 0d) { sales = stock = 0.5; sum = 1d; }
        return Map.of(
                "sales_units", sales / sum,
                "stock_ratio", stock / sum
        );
    }

    private double safe(Double v) { return (v == null || v < 0d) ? 0d : v; }
}
