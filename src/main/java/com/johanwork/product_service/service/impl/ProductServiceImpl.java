package com.johanwork.product_service.service.impl;

import com.johanwork.product_service.constants.ProductConstant;
import com.johanwork.product_service.dto.ApiResponse;
import com.johanwork.product_service.dto.ProductRequest;
import com.johanwork.product_service.dto.ProductResponse;
import com.johanwork.product_service.entity.Product;
import com.johanwork.product_service.exception.CustomException;
import com.johanwork.product_service.mapper.ProductMapper;
import com.johanwork.product_service.repository.ProductRepository;
import com.johanwork.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ApiResponse<List<ProductResponse>> getAllProducts() {
        return productMapper.mapToListApiResponse(productRepository.findAll());
    }

    @Override
    public ApiResponse<ProductResponse> getProductById(Long id) {
        return productMapper.mapToApiResponse(getProduct(id));
    }

    @Override
    public ApiResponse<ProductResponse> createProduct(ProductRequest request) {
        Product product = productMapper.requestToModel(request);
        return productMapper.mapToApiResponse(productRepository.save(product));
    }

    @Override
    public ApiResponse<ProductResponse> updateProduct(Long id, ProductRequest request) {
        Product product = getProduct(id);
        product.setName(request.name());
        product.setPrice(request.price());
        product.setQuantity(request.quantity());
        return productMapper.mapToApiResponse(productRepository.save(product));
    }

    @Override
    public ApiResponse<ProductResponse> deleteProduct(Long id) {
        Product product = getProduct(id);
        productRepository.delete(product);
        return productMapper.mapToApiResponse(product);
    }

    private Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new CustomException(
                        String.format(ProductConstant.Error.PRODUCT_NOT_FOUND, id),
                        HttpStatus.NOT_FOUND,
                        ProductConstant.Error.TITLE_PRODUCT_NOT_FOUND
                ));
    }
}
