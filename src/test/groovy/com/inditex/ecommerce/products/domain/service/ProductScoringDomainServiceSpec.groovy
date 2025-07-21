package com.inditex.ecommerce.products.domain.service

import com.inditex.ecommerce.products.domain.model.Product
import com.inditex.ecommerce.products.domain.model.Size
import com.inditex.ecommerce.products.domain.model.Stock
import com.inditex.ecommerce.products.domain.scoring.SalesUnitsScoring
import com.inditex.ecommerce.products.domain.scoring.ScoringStrategyRegistry
import com.inditex.ecommerce.products.domain.scoring.StockRatioScoring
import spock.lang.Specification

class ProductScoringDomainServiceRealSpec extends Specification {

    def "should calculate and sort scores using real Product and Stock with Size enum"() {
        given:
        def registry = new ScoringStrategyRegistry([
                new SalesUnitsScoring(),
                new StockRatioScoring()
        ])

        and: 'Product 1: high sales, partial stock'
        def p1 = new Product(
                1L,
                "Zapatilla A",
                100,
                new Stock([(Size.S): 10, (Size.M): 0])
        )

        and: 'Product 2: lower sales, full stock'
        def p2 = new Product(
                2L,
                "Zapatilla B",
                50,
                new Stock([(Size.S): 5, (Size.M): 5])
        )

        and:
        def service = new ProductScoringDomainService(registry)

        when:
        def result = service.calculateOrderedScores([p1, p2], 0.7d, 0.3d)

        then:
        result.size() == 2
        result[0].id() == 1L
        result[1].id() == 2L

        and: 'Calculated scores match expected (with tolerance)'
        Math.abs(result[0].score() - 0.85d) < 1e-6
        Math.abs(result[1].score() - 0.65d) < 1e-6
    }
}