package com.johanwork.product_service.service;

import com.johanwork.product_service.dto.ApiResponse;
import com.johanwork.product_service.dto.ProductRequest;
import com.johanwork.product_service.dto.ProductResponse;
import com.johanwork.product_service.entity.Product;

import java.util.List;

public interface ProductService {

    /**
     * Retrieves all products.
     *
     * @return ApiResponse containing list of all product responses
     */
    ApiResponse<List<ProductResponse>> getAllProducts();

    /**
     * Retrieves a product by its ID.
     *
     * @param id the ID of the product to retrieve
     * @return ApiResponse containing the product response
     */
    ApiResponse<ProductResponse> getProductById(Long id);

    /**
     * Creates a new product.
     *
     * @param product the product to create
     * @return ApiResponse containing the created product response
     */
    ApiResponse<ProductResponse> createProduct(ProductRequest request);

    /**
     * Updates an existing product.
     *
     * @param id the ID of the product to update
     * @param product the updated product
     * @return ApiResponse containing the updated product response
     */
    ApiResponse<ProductResponse> updateProduct(Long id, ProductRequest request);

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete
     * @return ApiResponse containing the deleted product response
     */
    ApiResponse<ProductResponse> deleteProduct(Long id);

}
