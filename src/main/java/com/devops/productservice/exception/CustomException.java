package com.devops.productservice.exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException{

    private int status;

    public CustomException(int status, String message) {
        super(message);
        this.status = status;
    }
}
