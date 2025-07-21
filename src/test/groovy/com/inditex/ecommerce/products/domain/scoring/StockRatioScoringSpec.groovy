package com.inditex.ecommerce.products.domain.scoring


import com.inditex.ecommerce.products.domain.model.Product
import com.inditex.ecommerce.products.domain.model.Stock
import spock.lang.Specification

class StockRatioScoringSpec extends Specification {

    def scoring = new StockRatioScoring()

    def "should compute stock ratio correctly"() {
        given:
        def stock = new Stock(stockMap)
        def product = new Product(1, "Test", 0, stock)

        expect:
        Math.abs(scoring.rawScore(product) - expected) < 0.0001

        where:
        stockMap                                        || expected
        [S: 0, M: 0, L: 0]                              || 0.0
        [S: 1, M: 1, L: 1]                              || 1.0
        [S: 5, M: 0, L: 0]                              || 1.0 / 3
        [S: 0, M: 2, L: 3]                              || 2.0 / 3
        [:]                                             || 0.0
    }

    def "should handle null stock safely"() {
        given:
        def product = new Product(1, "Test", 0, null)

        expect:
        scoring.rawScore(product) == 0.0
    }

    def "should return name as 'stock_ratio'"() {
        expect:
        scoring.name() == "stock_ratio"
    }

    def "should report score as already normalized"() {
        expect:
        scoring.isAlreadyNormalized()
    }
}
