package com.devops.productservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        name = "Product Request",
        description = "Schema to hold Product Request information"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {

    @Schema(
            description = "Product name of JMarket ", example = "Macbook Pro M4 Pro"
    )
    @NotBlank(message = "Product name cannot be null or empty")
    private String name;

    @Schema(
            description = "Product price of JMarket", example = "2200"
    )
    @Positive(message = "Total price must be greater than zero")
    private double price;

    @Schema(
            description = "Product quantity of JMarket", example = "100"
    )
    @PositiveOrZero(message = "Product quantity must be greater than or equal to zero")
    private int quantity;
}
