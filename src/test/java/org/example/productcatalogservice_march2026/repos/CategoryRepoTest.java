package org.example.productcatalogservice_march2026.repos;

import org.example.productcatalogservice_march2026.models.Category;
import org.example.productcatalogservice_march2026.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepoTest {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ProductRepo productRepo;

    //@Test
    public void addDataToRDS() {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Iphone");
        Category category = new Category();
        category.setId(2L);
        category.setTitle("Phones");
        categoryRepo.save(category);
        product.setCategory(category);
        productRepo.save(product);
    }

    //@Test
    @Transactional
    public void testFetchTypes() {
        Optional<Category> optionalCategory = categoryRepo.findById(3L);
        Category category = optionalCategory.get();
        System.out.println(category.getTitle());
        for(Product product : category.getProducts()) {
            System.out.println(product.getTitle());
        }
    }


    //@Test
    @Transactional
    public void testNPlusOneProblem() {
        List<Category> categoryList = categoryRepo.findAll();
        for(Category category : categoryList) {
            System.out.println(category.getTitle());
            for(Product product : category.getProducts()) {
                System.out.println(product.getTitle());
            }
        }
    }


}