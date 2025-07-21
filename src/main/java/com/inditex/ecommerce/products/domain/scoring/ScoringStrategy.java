package com.inditex.ecommerce.products.domain.scoring;

import com.inditex.ecommerce.products.domain.model.Product;

public sealed interface ScoringStrategy
        permits SalesUnitsScoring, StockRatioScoring /*, futuros… */ {

    /** Clave usada en JSON de pesos. */
    String name();

    /** Métrica cruda (puede estar en cualquier escala). */
    double rawScore(Product product);

    /**
     * ¿El rawScore ya está en [0,1]?
     * true  → el servicio lo utilizará tal cual;
     * false → el servicio lo normalizará automáticamente.
     */
    default boolean isAlreadyNormalized() { return false; }
}
