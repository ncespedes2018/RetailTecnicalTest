package com.inditex.ecommerce.products.domain.model;

import java.util.Map;

public record Stock(Map<Size, Integer> quantities) {}


