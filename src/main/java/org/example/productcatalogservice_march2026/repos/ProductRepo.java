package org.example.productcatalogservice_march2026.repos;

import org.example.productcatalogservice_march2026.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
}
