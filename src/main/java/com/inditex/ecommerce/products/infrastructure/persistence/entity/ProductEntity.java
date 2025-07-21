package com.inditex.ecommerce.products.infrastructure.persistence.entity;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Entity
@Table(name = "products")
@Getter
@Setter
public class ProductEntity {
    @Id
    private Long id;
    private String name;
    private int salesUnits;

    @Convert(converter = StockBySizeConverter.class)
    private Map<String, Integer> stockBySize;

    // Getters, setters, constructor
}
