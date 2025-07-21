package com.inditex.ecommerce.products.infrastructure.adapter.input.rest

import com.fasterxml.jackson.databind.ObjectMapper
import com.inditex.ecommerce.generated.model.ScoringWeightsRequestDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ContextConfiguration
class ProductControllerIntegrationSpec extends Specification {

    @Autowired
    MockMvc mockMvc

    @Autowired
    ObjectMapper objectMapper

    def "should return ordered product scores from /api/products/order"() {
        given:
        def request = new ScoringWeightsRequestDTO()
                .salesByUnits(0.7)
                .stockRatio(0.3)

        when:
        def response = mockMvc.perform(
                post("/api/products/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        )

        then:
        response.andExpect(status().isOk())
                .andExpect(jsonPath('$[0].id').exists())
                .andExpect(jsonPath('$[0].name').exists())
                .andExpect(jsonPath('$[0].score').exists())
                .andExpect(jsonPath('$[0].salesUnits').exists())
                .andExpect(jsonPath('$[0].stock').exists())
    }
}