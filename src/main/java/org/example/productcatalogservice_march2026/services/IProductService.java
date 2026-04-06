package org.example.productcatalogservice_march2026.services;

import org.example.productcatalogservice_march2026.models.Product;

import java.util.List;

public interface IProductService {

    Product getProductById(Long id);

    List<Product> getAllProducts();

    Product createProduct(Product product);

    Product replaceProduct(Product input,Long id);
}
