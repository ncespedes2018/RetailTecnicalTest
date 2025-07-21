package com.inditex.ecommerce.products.domain.scoring

import spock.lang.Specification


class ScoringStrategyRegistrySpec extends Specification {

    def "should return the registered strategy by name"() {
        given:
        def sales = new SalesUnitsScoring()
        def stock = new StockRatioScoring()
        def registry = new ScoringStrategyRegistry([sales, stock])

        expect:
        registry.getStrategy("sales_units") == sales
        registry.getStrategy("stock_ratio") == stock
    }

    def "should throw exception when strategy is not found"() {
        given:
        def registry = new ScoringStrategyRegistry([])

        when:
        registry.getStrategy("not_found")

        then:
        def e = thrown(IllegalArgumentException)
        e.message == "No strategy registered for: not_found"
    }
}