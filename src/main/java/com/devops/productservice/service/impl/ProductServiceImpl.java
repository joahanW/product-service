package com.devops.productservice.service.impl;

import com.devops.productservice.dto.APIResponse;
import com.devops.productservice.dto.mapper.ProductMapper;
import com.devops.productservice.dto.request.ProductRequest;
import com.devops.productservice.dto.response.ProductResponse;
import com.devops.productservice.entity.Product;
import com.devops.productservice.exception.CustomException;
import com.devops.productservice.repository.ProductRepository;
import com.devops.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public APIResponse<List<ProductResponse>> findAllProducts() {
        return productMapper.mapToApiResponseListDto(
                productRepository.findAll()
        );
    }

    @Override
    public APIResponse<ProductResponse> findProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new CustomException(404,
                        String.format("Product with id %s not found", id))
        );
        return productMapper.mapToApiResponseDto(product);
    }

    @Override
    public APIResponse<ProductResponse> createProduct(ProductRequest productRequest) {
        Product product = productMapper.requestDtoToModel(productRequest);
        return productMapper.mapToApiResponseDto(
                productRepository.saveAndFlush(product)
        );
    }

    @Override
    public APIResponse<ProductResponse> updateProduct(Long id, ProductRequest productRequest) {
        findProductById(id);
        Product product = productMapper.requestDtoToModel(productRequest);
        product.setId(id);
        return productMapper.mapToApiResponseDto(
                productRepository.saveAndFlush(product)
        );
    }

    @Override
    public APIResponse<ProductResponse> deleteProduct(Long id) {
        APIResponse<ProductResponse> product = findProductById(id);
        productRepository.deleteById(id);
        return product;
    }

    @Override
    public void checkAvailability(Long id, int quantity) {
        ProductResponse product = findProductById(id).getData();
        if (product.getQuantity() < quantity) {
            throw new CustomException(400, "Product does not have sufficient quantity");
        }
    }

    @Override
    public APIResponse<ProductResponse> reduceQuantity(Long id, int quantity) {
        checkAvailability(id, quantity);
        ProductResponse response = findProductById(id).getData();
        Product product = productMapper.responseDtoToModel(response);
        product.setQuantity(response.getQuantity() - quantity);
        return productMapper.mapToApiResponseDto(
                productRepository.saveAndFlush(product)
        );
    }
}
