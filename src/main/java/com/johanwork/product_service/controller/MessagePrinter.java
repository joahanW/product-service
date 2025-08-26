package com.johanwork.product_service.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessagePrinter {

    @Value("${accounts.message}")
    private String message;

    @PostConstruct
    public void printMessage() {
        System.out.println(message);
    }
}
