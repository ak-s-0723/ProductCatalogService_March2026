package org.example.productcatalogservice_march2026.services;

import org.example.productcatalogservice_march2026.dtos.ProductDto;
import org.example.productcatalogservice_march2026.models.Product;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IProductService {

    Product getProductById(Long id);

    List<Product> getAllProducts();

    Product createProduct(Product product);

    Product replaceProduct(Product input,Long id);

   void deleteProduct(Long id);

    Product getProductDetailsBasedOnUserRole(Long productId,Long userId);
}
