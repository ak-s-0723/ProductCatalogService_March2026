package org.example.productcatalogservice_march2026.repos;

import org.example.productcatalogservice_march2026.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    Optional<Product> findById(Long id);

    List<Product> findAll();

    Product save(Product product);

    void deleteById(Long id);

    Page<Product> findProductByTitle(String query, Pageable pageable);



    //Custom Queries

//    List<Product> findProductByPriceBetween(Double low, Double high);
//
//    List<Product> findAllByIsPrime(Boolean val);
//    List<Product> findAllByIsPrimeTrue();
//
//    List<Product> findAllByOrderByPriceDesc();
//
//    @Query("SELECT p.title from Product p WHERE p.id=?1")
//    String findProductNameById(Long id);
//
//    @Query("SELECT c.title from Product p join Category c on p.category.id=c.id WHERE p.id=:id")
//    String findCategoryNameFromProductId(@Param("id") Long id);
}
