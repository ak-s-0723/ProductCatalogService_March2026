package org.example.productcatalogservice_march2026.controllers;

import org.example.productcatalogservice_march2026.dtos.CategoryDto;
import org.example.productcatalogservice_march2026.dtos.ProductDto;
import org.example.productcatalogservice_march2026.models.Category;
import org.example.productcatalogservice_march2026.models.Product;
import org.example.productcatalogservice_march2026.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    //@Qualifier("storageProductService")
    private IProductService productService;


//    public ProductController(IProductService productService) {
//        this.productService = productService;
//    }

//  GET /products

    @GetMapping
    public List<ProductDto> getAllProducts() {
       List<Product> products = productService.getAllProducts();
       List<ProductDto> response = new ArrayList<>();
       for(Product product : products) {
           ProductDto productDto = from(product);
           response.add(productDto);
       }
       return response;
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long productId) {
        if(productId == 0) {
            //return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            throw new IllegalArgumentException("Please pass productId > 0");
        } else if(productId < 0) {
            throw new IllegalArgumentException("INVALID INPUT");
        }

       Product product = productService.getProductById(productId);
       if (product != null) {
           ProductDto productDto = from(product);
           return new ResponseEntity<>(productDto, HttpStatus.OK);
       } else {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }


    @PostMapping
    public ProductDto createProduct(@RequestBody  ProductDto input) {
        Product inputProduct = from(input);
        Product output = productService.createProduct(inputProduct);
        return from(output);
    }

    @PutMapping("{id}")
    public ProductDto replaceProduct(@PathVariable Long id,
                                     @RequestBody ProductDto input)  {
        Product inputProduct = from(input);
        Product product = productService.replaceProduct(inputProduct,id);
        return from(product);
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);

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

    private Product from(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        product.setDescription(productDto.getDescription());
        if(productDto.getCategory() != null) {
            Category category = new Category();
            category.setTitle(productDto.getCategory().getTitle());
            category.setId(productDto.getCategory().getId());
            product.setCategory(category);
        }
        return product;
    }

}