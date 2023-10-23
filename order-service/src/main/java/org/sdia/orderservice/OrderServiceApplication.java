package org.sdia.orderservice;

import org.sdia.orderservice.entities.Order;
import org.sdia.orderservice.entities.ProductItem;
import org.sdia.orderservice.enums.OrderStatus;
import org.sdia.orderservice.model.Customer;
import org.sdia.orderservice.model.Product;
import org.sdia.orderservice.repositories.OrderRepository;
import org.sdia.orderservice.repositories.ProductItemRepository;
import org.sdia.orderservice.services.CustomerServiceClient;
import org.sdia.orderservice.services.InventoryServiceClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(OrderRepository orderRepository, ProductItemRepository productItemRepository,
							InventoryServiceClient inventoryServiceClient, CustomerServiceClient customerServiceClient) {
		return args -> {
			List<Customer> customers = customerServiceClient.allCustomers().getContent().stream().toList();
			List<Product> products=inventoryServiceClient.findAll().getContent().stream().toList();
			System.out.println(customers);System.out.println(products);
			Customer customer = customerServiceClient.findCustomerById(1L);
			Random random=new Random();
			for (int i=0;i<15;i++){
				Order order = Order.builder()
						.customerID(customers.get(random.nextInt(customers.size())).getId())
						.status(Math.random()<0.5? OrderStatus.CREATED:OrderStatus.DELIVRED)
						.createdAt(new Date())
						.build();
				Order order1= orderRepository.save(order);
				for (int j=0;j< products.size();j++) {
					if(Math.random()<0.3){
					ProductItem productItem= ProductItem.builder()
							.order(order1)
							.productId(products.get(j).getId())
							.price(products.get(j).getPrice())
							.quantity(1+ random.nextInt(16))
							.discount(Math.random())
							.build();
					productItemRepository.save(productItem);
					}
				}

			}

		};

	};
}
//57:32

