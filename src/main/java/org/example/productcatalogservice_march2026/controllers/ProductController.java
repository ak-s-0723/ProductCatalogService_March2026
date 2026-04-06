package org.example.productcatalogservice_march2026.controllers;

import org.example.productcatalogservice_march2026.dtos.CategoryDto;
import org.example.productcatalogservice_march2026.dtos.ProductDto;
import org.example.productcatalogservice_march2026.models.Product;
import org.example.productcatalogservice_march2026.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private IProductService productService;

//    public ProductController(IProductService productService) {
//        this.productService = productService;
//    }

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

    @GetMapping("/products/{id}")
    public ProductDto getProductById(@PathVariable("id") Long productId) {
       Product product = productService.getProductById(productId);
       return from(product);
    }

    @PostMapping("/products")
    public ProductDto createProduct(@RequestBody  ProductDto input) {
        return input;
    }


    private ProductDto from(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setTitle(product.getTitle());
        productDto.setId(product.getId());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        if(product.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setDescription(product.getCategory().getDescription());
            categoryDto.setTitle(product.getCategory().getTitle());
            categoryDto.setId(product.getCategory().getId());
            productDto.setCategory(categoryDto);
        }

        return productDto;
    }

}