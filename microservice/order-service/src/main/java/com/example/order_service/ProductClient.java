package com.example.order_service;

import com.example.product_service.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "product-service")

public interface ProductClient {
        @GetMapping("/products/{id}")
        Product getProduct(@PathVariable("id") Long id);
    }

