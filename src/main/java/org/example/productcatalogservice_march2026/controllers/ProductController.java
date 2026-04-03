package org.example.productcatalogservice_march2026.controllers;

import org.example.productcatalogservice_march2026.models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

//  GET /products

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setTitle("Iphone");
        List<Product> products = new ArrayList<>();
        products.add(product1);
        return products;
    }

}