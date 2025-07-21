package com.inditex.ecommerce.products.infrastructure.config;

import com.inditex.ecommerce.products.domain.scoring.SalesUnitsScoring;
import com.inditex.ecommerce.products.domain.scoring.ScoringStrategy;
import com.inditex.ecommerce.products.domain.scoring.ScoringStrategyRegistry;
import com.inditex.ecommerce.products.domain.scoring.StockRatioScoring;
import com.inditex.ecommerce.products.domain.service.ProductScoringDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DomainServiceConfig {

    @Bean
    public ScoringStrategy salesCriterion(){
        return new SalesUnitsScoring();
    }

    @Bean
    public ScoringStrategy stockRatioCriterion(){
        return new StockRatioScoring();
    }

    @Bean
    public ScoringStrategyRegistry scoringStrategyRegistry(List<ScoringStrategy> strategies){
        return new ScoringStrategyRegistry(strategies);
    }

    @Bean
    public ProductScoringDomainService productScoringDomainService(ScoringStrategyRegistry registry){
        return new ProductScoringDomainService(registry);
    }
}
