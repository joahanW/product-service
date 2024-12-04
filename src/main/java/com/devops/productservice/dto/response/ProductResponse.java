package com.devops.productservice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        name = "Product Response",
        description = "Schema to hold Product Response information"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

    @Schema(
            description = "Product ID of JMarket", example = "1"
    )
    private Long id;

    @Schema(
            description = "Product name of JMarket", example = "Macbook Pro M4 Pro"
    )
    private String name;

    @Schema(
            description = "Product price of JMarket", example = "2200"
    )
    private double price;

    @Schema(
            description = "Product quantity of JMarket", example = "100"
    )
    private int quantity;
}
