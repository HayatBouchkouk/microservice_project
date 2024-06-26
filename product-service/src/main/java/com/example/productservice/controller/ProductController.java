package com.example.productservice.controller;


import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")

public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productrequest)
    {
        productService.createProduct(productrequest);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)

    public List<ProductResponse> getAllProducts()
    {
        return productService.getAllProducts();
    }


}
