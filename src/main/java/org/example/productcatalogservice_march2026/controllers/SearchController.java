package org.example.productcatalogservice_march2026.controllers;

import org.example.productcatalogservice_march2026.dtos.SearchRequestDto;
import org.example.productcatalogservice_march2026.models.Product;
import org.example.productcatalogservice_march2026.services.ISearchService;
import org.example.productcatalogservice_march2026.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private ISearchService searchService;

    //1. Why POST rather than GET
    //2. Why are we returning list<product> and not list<productDto> - answered

    @PostMapping
    public Page<Product> searchProducts(@RequestBody SearchRequestDto searchRequestDto) {
       return searchService.searchProducts(searchRequestDto.getQuery(),
               searchRequestDto.getPageSize(),
               searchRequestDto.getPageNumber());
    }
}
