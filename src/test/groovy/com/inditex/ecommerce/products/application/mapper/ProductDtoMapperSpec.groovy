package com.inditex.ecommerce.products.application.mapper

import com.inditex.ecommerce.generated.model.ProductScoreResponseDTO
import com.inditex.ecommerce.products.domain.model.Size
import com.inditex.ecommerce.products.domain.model.ProductScore

import spock.lang.Specification


class ProductDtoMapperSpec extends Specification {
    def "should map ProductScore to ProductScoreResponseDTO correctly"() {
        given:
        def domainStock = [
                (Size.S): 10,
                (Size.M): 5,
                (Size.L): 0
        ]

        def productScore = new ProductScore(
                1L,
                "Zapatilla Test",
                0.85d,
                100,
                domainStock
        )

        and:
        def mapper = new ProductDtoMapper()

        when:
        ProductScoreResponseDTO dto = mapper.toDto(productScore)

        then:
        dto.getId() == 1L
        dto.getName() == "Zapatilla Test"
        dto.getScore() == 0.85d
        dto.getSalesUnits() == 100

        and: 'Stock is correctly transformed to string keys'
        dto.getStock().size() == 3
        dto.getStock()["S"] == 10
        dto.getStock()["M"] == 5
        dto.getStock()["L"] == 0
    }
}