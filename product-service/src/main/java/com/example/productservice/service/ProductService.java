package com.example.productservice.service;


import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.models.Product;
import com.example.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j

public class ProductService {

    private final ProductRepository productRepo;
    public void createProduct(ProductRequest productRequest)
    {
        Product product= Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepo.save(product);
        log.info("Product {} is saved ",product.getId());

    }

    public List<ProductResponse> getAllProducts() {

        List<Product> products=productRepo.findAll();

        /// converts each Product object into a ProductResponse object
      return   products.stream().map(this::mapToProductResponse).collect(Collectors.toList());
    }

    //This ProductResponse object contains only the necessary information to display on the catalog page
    private ProductResponse mapToProductResponse(Product product) {

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                //.price(product.getPrice())
                .build();
    }
}
