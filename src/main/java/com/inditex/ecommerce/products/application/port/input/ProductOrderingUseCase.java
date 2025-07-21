package com.inditex.ecommerce.products.application.port.input;

import com.inditex.ecommerce.generated.model.ProductScoreResponseDTO;
import com.inditex.ecommerce.products.domain.model.ProductScore;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.util.Collection;
import java.util.List;

public interface ProductOrderingUseCase {

    List<ProductScoreResponseDTO> orderProducts(@NotNull @DecimalMin("0") @DecimalMax("1") Double salesByUnits,
                                                @NotNull @DecimalMin("0") @DecimalMax("1") Double stockRatio);
}
