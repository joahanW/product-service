package com.johanwork.product_service.mapper;

import com.johanwork.product_service.dto.ProductRequest;
import com.johanwork.product_service.dto.ProductResponse;
import com.johanwork.product_service.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper implements ApiResponseMapper<Product, ProductRequest, ProductResponse> {

    @Override
    public Product requestToModel(ProductRequest request) {
        return Product.builder()
                .name(request.name())
                .price(request.price())
                .quantity(request.quantity())
                .build();
    }

    @Override
    public ProductResponse modelToResponse(Product model) {
        return new ProductResponse(
                model.getId(),
                model.getName(),
                model.getPrice(),
                model.getQuantity()
        );
    }

    @Override
    public List<ProductResponse> listModelToListResponse(List<Product> listModel) {
        if (listModel != null) {
            return listModel.stream()
                    .map(this::modelToResponse)
                    .collect(Collectors.toList());
        }
        return List.of();
    }
}
