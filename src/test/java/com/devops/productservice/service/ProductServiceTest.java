package com.devops.productservice.service;

import com.devops.productservice.dto.APIResponse;
import com.devops.productservice.dto.mapper.ProductMapper;
import com.devops.productservice.dto.request.ProductRequest;
import com.devops.productservice.dto.response.ProductResponse;
import com.devops.productservice.entity.Product;
import com.devops.productservice.exception.CustomException;
import com.devops.productservice.repository.ProductRepository;
import com.devops.productservice.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @DisplayName("Get All Product - Success Scenario")
    @Test
    void test_when_get_all_product() {
        // Mocking
        List<Product> mockProducts = List.of(
                new Product(1L, "Product1", 100, 10),
                new Product(2L, "Product2", 200, 20)
        );
        List<ProductResponse> mockResponses = List.of(
                new ProductResponse(1L, "Product1", 100, 10),
                new ProductResponse(2L, "Product2", 200, 20)
        );

        // Mock behavior
        when(productRepository.findAll()).thenReturn(mockProducts);
        when(productMapper.mapToApiResponseListDto(mockProducts))
                .thenReturn(new APIResponse<>(mockResponses));

        // Call method
        APIResponse<List<ProductResponse>> response = productService.findAllProducts();

        // Verify
        verify(productRepository).findAll();
        verify(productMapper).mapToApiResponseListDto(mockProducts);

        // Assertions
        assertNotNull(response);
        assertEquals(2, response.getData().size());
        assertEquals("Product1", response.getData().get(0).getName());
    }

    @DisplayName("Get Product By Id - Success Scenario")
    @Test
    void test_when_get_product_by_id() {
        // Mocking
        Product mockProduct = getMockProduct();
        APIResponse<ProductResponse> mockResponse = getMockProductResponse();

        // Mock behavior
        when(productRepository.findById(1L))
                .thenReturn(Optional.of(mockProduct));
        when(productMapper.mapToApiResponseDto(mockProduct))
                .thenReturn(mockResponse);

        // Call method
        APIResponse<ProductResponse> response = productService.findProductById(1L);

        // Verify
        verify(productRepository).findById(1L);
        verify(productMapper).mapToApiResponseDto(mockProduct);

        // Then
        assertNotNull(response);
        assertEquals("Macbook Pro M4 Pro", response.getData().getName());
        assertEquals(2200, response.getData().getPrice());
        assertEquals(100, response.getData().getQuantity());

    }

    @DisplayName("Get Product By Id - Failure Scenario")
    @Test
    void test_when_get_product_by_id_failure() {
        // Mock behavior
        when(productRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        // Call method
        CustomException exception = assertThrows(CustomException.class,
                ()-> productService.findProductById(1L));

        // Verify
        verify(productRepository).findById(anyLong());

        // Then
        assertEquals(404, exception.getStatus());
        assertEquals("Product with id 1 not found", exception.getMessage());
    }

    @DisplayName("Create Product - Success Scenario")
    @Test
    void test_when_create_product() {
        // Mocking
        Product mockProduct = getMockProduct();
        ProductRequest mockProductRequest = getMockProductRequest();

        // Mocking behavior
        when(productMapper.requestDtoToModel(mockProductRequest))
                .thenReturn(mockProduct);
        when(productRepository.saveAndFlush(mockProduct))
                .thenReturn(mockProduct);
        when(productMapper.mapToApiResponseDto(mockProduct))
                .thenReturn(getMockProductResponse());

        // call method
        APIResponse<ProductResponse> response = productService.createProduct(mockProductRequest);

        // Verify
        verify(productMapper).requestDtoToModel(mockProductRequest);
        verify(productRepository).saveAndFlush(mockProduct);
        verify(productMapper).mapToApiResponseDto(mockProduct);

        // Then
        assertNotNull(response);
        assertEquals("Macbook Pro M4 Pro", response.getData().getName());
        assertEquals(2200, response.getData().getPrice());
        assertEquals(100, response.getData().getQuantity());
    }

    @DisplayName("Update Product - Success Scenario")
    @Test
    void test_when_update_product() {
        // Mocking
        ProductRequest mockProductRequest = getMockProductRequest();
        Product mockProduct = getMockProduct();
        Product updateMockProduct = getMockProduct();
        updateMockProduct.setId(1L);
        APIResponse<ProductResponse> mockProductResponse = getMockProductResponse();


        // Mocking behavior
        when(productRepository.findById(1L))
                .thenReturn(Optional.of(mockProduct));
        when(productMapper.requestDtoToModel(mockProductRequest))
                .thenReturn(mockProduct);
        when(productRepository.saveAndFlush(updateMockProduct))
                .thenReturn(updateMockProduct);
        when(productMapper.mapToApiResponseDto(updateMockProduct))
                .thenReturn(mockProductResponse);

        // call method
        APIResponse<ProductResponse> response = productService.updateProduct(1L, mockProductRequest);

        // Verify
        verify(productRepository).findById(1L);
        verify(productMapper).requestDtoToModel(mockProductRequest);
        verify(productRepository).saveAndFlush(mockProduct);
        verify(productMapper, times(2)).mapToApiResponseDto(mockProduct);

        // Then
        assertNotNull(response);
        assertEquals(1L, updateMockProduct.getId());
        assertEquals("Macbook Pro M4 Pro", response.getData().getName());
        assertEquals(2200, response.getData().getPrice());
        assertEquals(100, response.getData().getQuantity());

    }

    @DisplayName("Delete Product - Success Scenario")
    @Test
    void test_when_delete_product() {
        // Mocking
        Product mockProduct = getMockProduct();
        APIResponse<ProductResponse> mockProductResponse = getMockProductResponse();

        // Mocking Behavior
        when(productRepository.findById(1L))
                .thenReturn(Optional.of(mockProduct));
        when(productMapper.mapToApiResponseDto(mockProduct))
                .thenReturn(mockProductResponse);

        // call method
        APIResponse<ProductResponse> response = productService.deleteProduct(1L);

        // verify
        verify(productRepository).findById(1L);
        verify(productMapper).mapToApiResponseDto(mockProduct);

        // Then
        assertNotNull(response);
        assertEquals("Macbook Pro M4 Pro", response.getData().getName());
        assertEquals(2200, response.getData().getPrice());
        assertEquals(100, response.getData().getQuantity());
    }

    @DisplayName("Delete Product - Failure Scenario")
    @Test
    void test_when_delete_product_failure() {
        // Mocking Behavior
        when(productRepository.findById(1L))
                .thenReturn(Optional.empty());

        // call method
        CustomException customException = assertThrows(CustomException.class,
                () -> productService.deleteProduct(1L));

        // verify
        verify(productRepository).findById(1L);
        verify(productRepository, never()).deleteById(1L);
        verifyNoInteractions(productMapper);

        // Then
        assertEquals(404, customException.getStatus());
        assertEquals("Product with id 1 not found", customException.getMessage());
    }

    @DisplayName("Check Availability - Success Scenario")
    @Test
    void test_when_check_availability() {
        // Mocking
        Product mockProduct = getMockProduct();
        APIResponse<ProductResponse> mockProductResponse = getMockProductResponse();

        // Mocking behavior
        when(productRepository.findById(1L))
                .thenReturn(Optional.of(mockProduct));
        when(productMapper.mapToApiResponseDto(mockProduct))
                .thenReturn(mockProductResponse);

        // call method
        productService.checkAvailability(1L, 10);

        // verify
        verify(productRepository).findById(1L);
        verify(productMapper).mapToApiResponseDto(mockProduct);
    }

    @DisplayName("Check Availability - Failure Scenario")
    @Test
    void test_when_check_availability_failure() {
        // Mocking
        Product mockProduct = getMockProduct();
        APIResponse<ProductResponse> mockProductResponse = getMockProductResponse();

        // Mocking behavior
        when(productRepository.findById(1L))
                .thenReturn(Optional.of(mockProduct));
        when(productMapper.mapToApiResponseDto(mockProduct))
                .thenReturn(mockProductResponse);

        // call method
        CustomException customException = assertThrows(CustomException.class,
                () -> productService.checkAvailability(1L, 500));

        // verify
        verify(productRepository).findById(1L);
        verify(productMapper).mapToApiResponseDto(mockProduct);

        // Then
        assertEquals(400, customException.getStatus());
        assertEquals("Product does not have sufficient quantity", customException.getMessage());
    }

    @DisplayName("Reduce Quantity - Success Scenario")
    @Test
    void test_when_reduce_quantity() {
        // Mocking
        Product mockProduct = getMockProduct();
        Product updateProduct = getMockProduct();
        updateProduct.setQuantity(90);
        APIResponse<ProductResponse> updateProductResponse = getMockProductResponse();
        updateProductResponse.getData().setQuantity(90);

        // Mocking behavior
        when(productRepository.findById(1L))
                .thenReturn(Optional.of(mockProduct));
        when(productMapper.responseDtoToModel(any(ProductResponse.class)))
                .thenReturn(mockProduct);
        when(productRepository.saveAndFlush(any(Product.class)))
                .thenReturn(updateProduct);
        when(productMapper.mapToApiResponseDto(any(Product.class)))
                .thenReturn(updateProductResponse);

        // call method
        APIResponse<ProductResponse> response = productService.reduceQuantity(1L, 10);

        // verify
        verify(productRepository,times(2)).findById(1L);
        verify(productMapper).responseDtoToModel(any(ProductResponse.class));
        verify(productRepository).saveAndFlush(any(Product.class));
        verify(productMapper, times(2)).mapToApiResponseDto(mockProduct);

        // Then
        assertNotNull(response);
        assertEquals("Macbook Pro M4 Pro", response.getData().getName());
        assertEquals(2200, response.getData().getPrice());
        assertEquals(90, response.getData().getQuantity());

    }

    @DisplayName("Reduce Quantity - Failure Scenario")
    @Test
    void test_when_reduce_quantity_failure() {
        // Mocking
        Product mockProduct = getMockProduct();
        APIResponse<ProductResponse> mockProductResponse = getMockProductResponse();

        // Mocking behavior
        when(productRepository.findById(1L))
                .thenReturn(Optional.of(mockProduct));
        when(productMapper.mapToApiResponseDto(mockProduct))
                .thenReturn(mockProductResponse);

        // call method
        CustomException customException = assertThrows(CustomException.class,
                () -> productService.reduceQuantity(1L, 500));

        // verify
        verify(productRepository).findById(1L);
        verify(productRepository,never()).saveAndFlush(any(Product.class));

        // Then
        assertEquals(400, customException.getStatus());
        assertEquals("Product does not have sufficient quantity", customException.getMessage());

    }

    private APIResponse<ProductResponse> getMockProductResponse() {
        ProductResponse productResponse = ProductResponse.builder()
                .id(1L)
                .name("Macbook Pro M4 Pro")
                .price(2200)
                .quantity(100)
                .build();
        return new APIResponse<>(productResponse);
    }

    private Product getMockProduct() {
        return Product.builder()
                .id(1L)
                .name("Macbook Pro M4 Pro")
                .price(2200)
                .quantity(100)
                .build();
    }

    private ProductRequest getMockProductRequest() {
        return ProductRequest.builder()
                .name("Macbook Pro M4 Pro")
                .price(2200)
                .quantity(100)
                .build();
    }
}