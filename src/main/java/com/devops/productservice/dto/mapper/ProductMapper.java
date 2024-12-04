package com.devops.productservice.dto.mapper;

import com.devops.productservice.dto.request.ProductRequest;
import com.devops.productservice.dto.response.ProductResponse;
import com.devops.productservice.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends APIResponseMapper<Product, ProductRequest, ProductResponse> {
}
