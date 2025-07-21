package com.inditex.ecommerce.products.domain.scoring;

import com.inditex.ecommerce.products.domain.model.Product;

public final class SalesUnitsScoring implements ScoringStrategy {
    public String name() { return "sales_units"; }

    public double rawScore(Product p) { return p.salesUnits(); } // 0…∞

}
