package org.sdia.orderservice.web;

import org.sdia.orderservice.entities.Order;
import org.sdia.orderservice.model.Customer;
import org.sdia.orderservice.model.Product;
import org.sdia.orderservice.repositories.OrderRepository;
import org.sdia.orderservice.repositories.ProductItemRepository;
import org.sdia.orderservice.services.CustomerServiceClient;
import org.sdia.orderservice.services.InventoryServiceClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderRestController {
    private OrderRepository orderRepository;
    private ProductItemRepository productItemRepository;
    private CustomerServiceClient customerServiceClient;
    private InventoryServiceClient inventoryServiceClient;

    public OrderRestController(OrderRepository orderRepository,
                               ProductItemRepository productItemRepository,
                               CustomerServiceClient customerServiceClient,
                               InventoryServiceClient inventoryServiceClient) {
        this.orderRepository = orderRepository;
        this.productItemRepository = productItemRepository;
        this.customerServiceClient = customerServiceClient;
        this.inventoryServiceClient = inventoryServiceClient;
    }
    @GetMapping("/fullOrder/{id}")
    public Order getOrder(@PathVariable Long id){
        Order order=orderRepository.findById(id).get();
        Customer customer=customerServiceClient.findCustomerById(order.getCustomerID());
        order.setCustomer(customer);
        order.getProductItems().forEach(productItem -> {
            Product product=inventoryServiceClient.findProductById(productItem.getProductId());
            productItem.setProduct(product);
        });
        return order;
    }
}

