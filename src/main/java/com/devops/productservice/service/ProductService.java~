package com.devops.productservice.service;

import com.devops.productservice.dto.APIResponse;
import com.devops.productservice.dto.request.ProductRequest;
import com.devops.productservice.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {

    /**
     *
     * @return All products detail
     */
    APIResponse<List<ProductResponse>> findAllProducts();

    /**
     *
     * @param id
     * @return Product detail
     */
    APIResponse<ProductResponse> findProductById(Long id);

    /**
     *
     * @param productRequest
     * @return Product detail
     */
    APIResponse<ProductResponse> createProduct(ProductRequest productRequest);

    /**
     *
     * @param id
     * @param productRequest
     * @return Product detail
     */
    APIResponse<ProductResponse> updateProduct(Long id, ProductRequest productRequest);

    /**
     *
     * @param id
     * @return Product detail
     */
    APIResponse<ProductResponse> deleteProduct(Long id);

    /**
     *
     * @param id
     * @param quantity
     */
    void checkAvailability(Long id, int quantity);

    /**
     *
     * @param id
     * @param quantity
     * @return Product detail
     */
    APIResponse<ProductResponse> reduceQuantity(Long id, int quantity);
}
