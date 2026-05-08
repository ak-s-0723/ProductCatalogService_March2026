package org.example.productcatalogservice_march2026.services;

import org.example.productcatalogservice_march2026.models.Product;
import org.example.productcatalogservice_march2026.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService implements ISearchService {
    @Autowired
    private ProductRepo productRepo;

    @Override
    public Page<Product> searchProducts(String query,
                                        Integer pageSize,
                                        Integer pageNumber) {
        Sort sortByPrice_Desc  = Sort.by("price").descending();
        Sort sortById_Desc = Sort.by("id").descending();
        Sort finalSort = sortByPrice_Desc.and(sortById_Desc);

       return productRepo.findProductByTitle(query, PageRequest.of(pageNumber,pageSize, finalSort));
    }
}
