package com.example.productservice;

import com.example.productservice.models.Product;
import com.example.productservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ProductRepository productRepository)
	{
		return args ->
		{
			Product product= Product.builder()
					.name("Mac computer")
					.description("Mac")
					.price(2999.99)
					.build();

			Product product1= Product.builder()
					.name("Iphone_15")
					.description("The best product ever")
					.price(98765.98)
					.build();

			productRepository.save(product);
			productRepository.save(product1);

			System.out.println("The products has been added successfully!");
		};
	}

}
