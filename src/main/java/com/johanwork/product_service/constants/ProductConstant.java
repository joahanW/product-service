package com.johanwork.product_service.constants;

public class ProductConstant {
    private ProductConstant() {}

    public static final class Error {
        public static final String TITLE_PRODUCT_NOT_FOUND = "PRODUCT NOT FOUND";
        public static final String PRODUCT_NOT_FOUND = "Product not found with product id: %d";

        private Error() {}
    }
}
