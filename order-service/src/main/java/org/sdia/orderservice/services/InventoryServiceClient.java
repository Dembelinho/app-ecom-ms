package org.sdia.orderservice.services;

import org.sdia.orderservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="inventory-service")
public interface InventoryServiceClient {
    @GetMapping("/products/{id}?projection=fullProduct")
    public Product findProductById(@PathVariable("id") Long id);
    @GetMapping("/products?projection=fullProduct")
    public PagedModel<Product> findAll();
}
