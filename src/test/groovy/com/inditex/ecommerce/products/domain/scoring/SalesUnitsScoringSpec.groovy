package com.inditex.ecommerce.products.domain.scoring


import com.inditex.ecommerce.products.domain.model.Product
import com.inditex.ecommerce.products.domain.model.Stock
import spock.lang.Specification

class SalesUnitsScoringSpec extends Specification {
    def scoring = new SalesUnitsScoring()

    def "should return rawScore equal to product's sales units"() {
        given:
        def product = new Product(1, "Test Shirt", salesUnits, new Stock([:]))  // Usamos constructor real

        expect:
        scoring.rawScore(product) == expectedScore

        where:
        salesUnits || expectedScore
        0          || 0
        100        || 100
        9999       || 9999
    }

    def "should report name as 'sales_units'"() {
        expect:
        scoring.name() == "sales_units"
    }

    def "should indicate score is not normalized"() {
        expect:
        !scoring.isAlreadyNormalized()
    }
}

