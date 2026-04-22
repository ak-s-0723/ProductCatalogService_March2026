package org.example.productcatalogservice_march2026.services;

import org.example.productcatalogservice_march2026.exceptions.ProductAlreadyExistsException;
import org.example.productcatalogservice_march2026.exceptions.ProductNotFoundException;
import org.example.productcatalogservice_march2026.models.Product;
import org.example.productcatalogservice_march2026.models.State;
import org.example.productcatalogservice_march2026.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class StorageProductService implements IProductService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Product getProductById(Long id) {
       Optional<Product> productOptional = productRepo.findById(id);
       if(productOptional.isPresent()) return  productOptional.get();
       //You can throw an exception also
       return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        Optional<Product> productOptional = productRepo.findById(product.getId());
        if(productOptional.isEmpty()) {
            return productRepo.save(product);
        } else {
            throw new ProductAlreadyExistsException("Please try out some other product id");
        }
    }

    @Override
    public Product replaceProduct(Product input, Long id) {
        input.setId(id);
        Optional<Product> product = productRepo.findById(id);
        if(product.isPresent()) {
            input.setLastUpdatedAt(new Date());
            return productRepo.save(input);
        } else {
          throw new ProductNotFoundException("product not found with this id");
        }
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<Product> product = productRepo.findById(id);
        if (product.isEmpty()) {
            throw new ProductNotFoundException("product not found with this id");
        } else {
            Product p = product.get();
            if(p.getState().equals(State.ACTIVE)) {
                p.setState(State.INACTIVE);
                productRepo.save(p);
            } else {
              productRepo.deleteById(id);
            }
        }
    }
}
