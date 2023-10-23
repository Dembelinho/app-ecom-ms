package org.sdia.customerservice;

import org.sdia.customerservice.entities.Customer;
import org.sdia.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(CustomerRepository customerRepository){
		return args -> {
			customerRepository.save(new Customer(null,"Ayoub","Ayoub-contact@enset-media.ma"));
			customerRepository.save(new Customer(null,"Hamza","Hamza-contact@fstm.ma"));
			customerRepository.save(new Customer(null,"Yasser","Yasser-contact@ensam.ma"));
			customerRepository.findAll().forEach(System.out::println);
		};
	}

}
