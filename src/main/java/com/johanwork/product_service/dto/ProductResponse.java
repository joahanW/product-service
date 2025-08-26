package com.johanwork.product_service.dto;

public record ProductResponse(
        Long id,
        String name,
        Double price,
        int quantity
) {
}
