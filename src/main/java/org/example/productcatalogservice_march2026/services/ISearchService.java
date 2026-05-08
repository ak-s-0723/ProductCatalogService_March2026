package org.example.productcatalogservice_march2026.services;

import org.example.productcatalogservice_march2026.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISearchService {

    Page<Product> searchProducts(String query, Integer pageSize, Integer pageNumber);
}
