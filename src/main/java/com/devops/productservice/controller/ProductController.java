package com.devops.productservice.controller;

import com.devops.productservice.dto.APIResponse;
import com.devops.productservice.dto.ErrorResponse;
import com.devops.productservice.dto.request.ProductRequest;
import com.devops.productservice.dto.response.ProductResponse;
import com.devops.productservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name="CRUD REST APIs for Products in JMarkets",
        description = "CRUD REST APIs in JMarket to CREATE,UPDATE, FETCH and DELETE products details"
)

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;

    @Operation(
            summary = "Fetch All Product Details",
            description = "REST APIs to Fetch all Product detail"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description ="HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping
    public ResponseEntity<APIResponse<List<ProductResponse>>> findAllProducts() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.findAllProducts());
    }

    @Operation(
            summary = "Fetch Detail Product based on id",
            description = "REST APIs to Fetch Product detail"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description ="HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<ProductResponse>> findProductById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.findProductById(id));
    }

    @Operation(
            summary = "Check Availability of Product based on id and quantity",
            description = "REST APIs to Fetch all Product detail"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description ="HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping("/check/{id}")
    public ResponseEntity<Void> checkAvailability(@PathVariable Long id,
                                                  @RequestParam
                                                  @Positive(message = "Total price must be greater than zero")
                                                  int quantity) {
        productService.checkAvailability(id, quantity);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Update Quantity of Product based on id and quantity",
            description = "REST APIs to Update Product quantity"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description ="HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @PutMapping("/reduce/{id}")
    public ResponseEntity<APIResponse<ProductResponse>> reduceQuantity(@PathVariable Long id,
                                                                       @RequestParam
                                                                       @Positive(message = "Total price must be greater than zero")
                                                                       int quantity) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.reduceQuantity(id, quantity));
    }

    @Operation(
            summary = "Create Product",
            description = "REST APIs to Create Product detail"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description ="HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @PostMapping
    public ResponseEntity<APIResponse<ProductResponse>> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.createProduct(productRequest));
    }

    @Operation(
            summary = "Update Product based on id",
            description = "REST APIs to Fetch all Product detail"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description ="HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<ProductResponse>> updateProduct(@PathVariable Long id,@Valid @RequestBody ProductRequest productRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.updateProduct(id, productRequest));
    }

    @Operation(
            summary = "Delete Product based on id",
            description = "REST APIs to delete Product detail"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description ="HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<ProductResponse>> deleteProduct(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.deleteProduct(id));
    }
}
