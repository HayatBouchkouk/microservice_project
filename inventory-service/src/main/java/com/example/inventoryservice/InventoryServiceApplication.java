package com.example.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}



	@Bean
	public CommandLineRunner loadData(InventoryRepository repository)
	{
		return args ->
		{

			Inventory inventory=new Inventory();
			inventory.setSkuCode("iphone_13");
			inventory.setQuantity(10);

			Inventory inventory1=new Inventory();
			inventory1.setSkuCode("Samsung_Galaxy");
			inventory1.setQuantity(0);

			Inventory inventory2=new Inventory();
			inventory1.setSkuCode("iPad");
			inventory1.setQuantity(3);

			repository.save(inventory1);
			repository.save(inventory);
			repository.save(inventory2);
		};
	}

}
