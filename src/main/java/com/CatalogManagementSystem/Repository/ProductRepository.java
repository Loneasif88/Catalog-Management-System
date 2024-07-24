package com.CatalogManagementSystem.Repository;

import com.CatalogManagementSystem.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContaining(String name);
    List<Product> findByBrandContaining(String brand);
    List<Product> findByCategoryContaining(String category);

    @Query("SELECT p FROM Product p WHERE p.price >= :minPrice AND p.price <= :maxPrice")
    List<Product> findByPriceRange(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);

    @Query("SELECT p FROM Product p WHERE p.rating >= :minRating AND p.rating <= :maxRating")
    List<Product> findByRatingRange(@Param("minRating") Double minRating, @Param("maxRating") Double maxRating);

}
