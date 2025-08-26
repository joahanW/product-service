package com.johanwork.product_service.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CustomException extends RuntimeException {

    private HttpStatus status;
    private String title;

    public CustomException(String message, HttpStatus status, String title) {
        super(message);
        this.status = status;
        this.title = title;
    }
}
