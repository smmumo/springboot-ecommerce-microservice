package com.smumo.inventoryservice;

import com.smumo.inventoryservice.model.Inventory;
import com.smumo.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}


	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
 		 return  args -> {
			 Inventory inventory = new Inventory();
			 inventory.setQuantity(100);
			 inventory.setProductCode("iphone_13");

			 Inventory inventory2 = new Inventory();
			 inventory.setQuantity(0);
			 inventory.setProductCode("iphone_13_red");

			 inventoryRepository.save(inventory);
			 inventoryRepository.save(inventory2);
		 };
	}
}
