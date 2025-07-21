package com.inditex.ecommerce.products.domain.scoring;

import com.inditex.ecommerce.products.domain.model.Product;
import java.util.Map;

public final class StockRatioScoring implements ScoringStrategy {

    public String name() { return "stock_ratio"; }

    public double rawScore(Product p) {
        var q = p.stock() == null ? Map.<String,Integer>of() : p.stock().quantities();
        if (q.isEmpty()) return 0d;
        long withStock = q.values().stream().filter(v -> v != null && v > 0).count();
        return (double) withStock / q.size();                     // 0…1
    }
    @Override
    public boolean isAlreadyNormalized() { return true; }
}
