package com.inditex.ecommerce.products.domain.scoring;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ScoringStrategyRegistry {

    private final Map<String, ScoringStrategy> strategies;

    public ScoringStrategyRegistry(List<ScoringStrategy> strategyList) {
        this.strategies = strategyList.stream().collect(Collectors.toMap(
                ScoringStrategy::name,
                Function.identity()
        ));
    }

    public ScoringStrategy getStrategy(String name) {
        if (!strategies.containsKey(name)) {
            throw new IllegalArgumentException("No strategy registered for: " + name);
        }
        return strategies.get(name);
    }
}