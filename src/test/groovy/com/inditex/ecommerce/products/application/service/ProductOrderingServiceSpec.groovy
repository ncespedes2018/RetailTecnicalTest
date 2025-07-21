package com.inditex.ecommerce.products.application.service

import com.inditex.ecommerce.generated.model.ProductScoreResponseDTO
import com.inditex.ecommerce.products.domain.model.Product
import com.inditex.ecommerce.products.domain.model.ProductScore
import com.inditex.ecommerce.products.domain.model.Size
import com.inditex.ecommerce.products.domain.model.Stock
import com.inditex.ecommerce.products.domain.service.ProductScoringDomainService
import com.inditex.ecommerce.products.application.port.output.ProductRepositoryPort
import com.inditex.ecommerce.products.application.mapper.ProductDtoMapper

import spock.lang.Specification


class ProductOrderingServiceSpec extends Specification {

    def repository = Mock(ProductRepositoryPort)
    def mapper = new ProductDtoMapper()
    def scoringService = Mock(ProductScoringDomainService)

    def service = new ProductOrderingService(repository, mapper, scoringService)

    def "should order products and return mapped response DTOs"() {
        given:
        def domainStockA = [
                (Size.S): 10,
                (Size.M): 5,
                (Size.L): 0
        ]
        def domainStockB = [
                (Size.S): 2,
                (Size.M): 4,
                (Size.L): 10
        ]
        def domainProducts = [
                new Product(1L, "Producto A", 100, new Stock(domainStockA)),
                new Product(2L, "Producto B", 50, new Stock(domainStockB))
        ]

        def scores = [
                new ProductScore(1L, "Zapatilla A", 0.9, 100, domainStockA),
                new ProductScore(2L, "Zapatilla B", 0.6, 50, domainStockB)
        ]
        and:
        repository.findAll() >> domainProducts
        scoringService.calculateOrderedScores(domainProducts, 0.7d, 0.3d) >> scores

        when:
        def result = service.orderProducts(0.7d, 0.3d)

        then:
        result.size() == 2
        result[0].getId() == 1L
        result[1].getId() == 2L
    }
}