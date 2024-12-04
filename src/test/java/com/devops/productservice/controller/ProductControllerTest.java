package com.devops.productservice.controller;

import com.devops.productservice.dto.APIResponse;
import com.devops.productservice.dto.request.ProductRequest;
import com.devops.productservice.dto.response.ProductResponse;
import com.devops.productservice.entity.Product;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @LocalServerPort
    private int port;

    private String baseUrl="http://localhost";

    private static RestTemplate restTemplate;

    private static boolean initialData = false;

    @Autowired
    private TestH2Repository h2Repository;

    @BeforeAll
    public static void init(){
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setUp() {
        baseUrl = baseUrl.concat(String.format(":%d/api/v1/products", port));
        if (!initialData){
            restTemplate.exchange(baseUrl, HttpMethod.POST,
                    new HttpEntity<>(ProductRequest.builder()
                            .name("Macbook Pro M3 Pro")
                            .price(1200)
                            .quantity(50).build())
                    , new ParameterizedTypeReference<APIResponse<ProductResponse>>() {
                    });
            initialData = true;
        }
    }

    @Test
    @Order(1)
    void test_create_product() {
        // GIVEN
        ProductRequest mockProductRequest = getMockProductRequest();

        // When
        APIResponse<ProductResponse> response = restTemplate.exchange(baseUrl, HttpMethod.POST,
                new HttpEntity<>(mockProductRequest),
                new ParameterizedTypeReference<APIResponse<ProductResponse>>() {
                }).getBody();

        // Then
        assertNotNull(response);
        assertEquals("Macbook Pro M4 Pro", response.getData().getName());
        assertEquals(2, h2Repository.findAll().size());
    }

    @Test
    @Order(2)
    void findAllProducts() {
        // When
        APIResponse<List<ProductResponse>> response = restTemplate.exchange(baseUrl, HttpMethod.GET,
                null,
                new ParameterizedTypeReference<APIResponse<List<ProductResponse>>>() {
                }).getBody();

        // Then
        assertNotNull(response);
        assertEquals(2, h2Repository.findAll().size());
    }

    @Test
    @Order(3)
    void findProductById() {
        // When
        APIResponse<ProductResponse> response = restTemplate.exchange(baseUrl+"/1", HttpMethod.GET,
                null,
                new ParameterizedTypeReference<APIResponse<ProductResponse>>() {
                }).getBody();

        // Then
        assertNotNull(response);
        assertEquals("Macbook Pro M3 Pro", response.getData().getName());
        assertEquals(1200, response.getData().getPrice());
        assertEquals(50, response.getData().getQuantity());

    }

    @Test
    @Order(4)
    void checkAvailability() {
        // When
        Map<String, Object> params = new HashMap<>();
        params.put("quantity", 10);

        ResponseEntity<Void> response = restTemplate.exchange(baseUrl + "/check/1?quantity={quantity}", HttpMethod.GET,
                null, Void.class, params);

        // Then
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    @Order(5)
    void reduceQuantity() {
        // When
        Map<String, Object> params = new HashMap<>();
        params.put("quantity", 10);

        APIResponse<ProductResponse> response = restTemplate.exchange(baseUrl+"/reduce/1?quantity={quantity}", HttpMethod.PUT,
                null,
                new ParameterizedTypeReference<APIResponse<ProductResponse>>() {
                }, params).getBody();

        // Then
        assertNotNull(response);
        assertEquals("Macbook Pro M3 Pro", response.getData().getName());
        assertEquals(40, response.getData().getQuantity());
    }



    @Test
    @Order(6)
    void updateProduct() {
        // When
        APIResponse<ProductResponse> response = restTemplate.exchange(baseUrl+"/1", HttpMethod.PUT,
                new HttpEntity<>(getMockProductRequest()),
                new ParameterizedTypeReference<APIResponse<ProductResponse>>() {
                }).getBody();

        // Then
        assertNotNull(response);
        assertEquals("Macbook Pro M4 Pro", response.getData().getName());
        assertEquals(100, response.getData().getQuantity());

    }

    @Test
    @Order(7)
    void deleteProduct() {
        ProductResponse data = restTemplate.exchange(baseUrl, HttpMethod.POST,
                new HttpEntity<>(ProductRequest.builder()
                        .name("Macbook Air M2")
                        .price(1200)
                        .quantity(20).build())
                , new ParameterizedTypeReference<APIResponse<ProductResponse>>() {
                }).getBody().getData();

        // When
        APIResponse<ProductResponse> response = restTemplate.exchange(baseUrl+"/"+data.getId(), HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<APIResponse<ProductResponse>>() {
                }).getBody();

        // Then
        assertNotNull(response);
        assertEquals("SUCCESS", response.getStatus());

    }

    private ProductRequest getMockProductRequest(){
        return ProductRequest.builder()
                .name("Macbook Pro M4 Pro")
                .price(2200)
                .quantity(100)
                .build();
    }
}