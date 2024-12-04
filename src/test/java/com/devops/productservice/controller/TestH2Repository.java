package com.devops.productservice.controller;

import com.devops.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestH2Repository extends JpaRepository<Product, Long> {
}
