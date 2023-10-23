package org.sdia.inventoryservice;

import org.sdia.inventoryservice.entities.Product;
import org.sdia.inventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(ProductRepository productRepository){
		return args -> {
			productRepository.save(new Product(null,"Computer Desk Top HP",1500.00,16));
			productRepository.save(new Product(null,"Printer Epson",190.20,10));
			productRepository.save(new Product(null,"MacBook Pro Lap Top",6800,45));
			productRepository.findAll().forEach(System.out::println);
		};
	}

}
