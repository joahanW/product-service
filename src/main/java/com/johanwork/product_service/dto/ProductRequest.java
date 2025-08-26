package com.johanwork.product_service.dto;

import jakarta.validation.constraints.*;

public record ProductRequest(

    @NotBlank(message = "Name cannot be a null or empty")
    String name,

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be greater than 0")
    Double price,

    @PositiveOrZero(message = "Quantity cannot be negative")
    int quantity
) {
}
