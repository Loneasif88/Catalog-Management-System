package com.CatalogManagementSystem.Service;

import com.CatalogManagementSystem.Model.Product;
import com.CatalogManagementSystem.Repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ProductService {

    private  static final Logger log = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;
//    private final ProductRepository productRepository;

//    public ProductService(ProductRepository productRepository){
//        this.productRepository = productRepository;
//    }

    // creating product
    public Product createProduct(Product product){
        log.info("Creating product : {}",product);
        return productRepository.save(product);
    }
    // Updating product
    public Product updateProduct(Long id, Product updateProduct){
        log.info("Updating product with id: {}",id);
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(updateProduct.getName());
                    product.setBrand(updateProduct.getBrand());
                    product.setDescription(updateProduct.getDescription());
                    product.setPrice(updateProduct.getPrice());
                    product.setQuantity(updateProduct.getQuantity());
                    product.setCategory(updateProduct.getCategory());
                    product.setManufacturer(updateProduct.getManufacturer());
                    product.setRating(updateProduct.getRating());
                    product.setDiscount(updateProduct.getDiscount());
                    return productRepository.save(product);
                }).orElseThrow(() -> new RuntimeException("Product Not Found"));
    }

    // Delete Product
    public  void deleteProduct(Long id){
        log.info("Deleting product with id: {}",id);
        productRepository.deleteById(id);
    }

    // Get all Products
    public List<Product> getAllProducts(){
        log.info("Retrieving all Products");
        return productRepository.findAll();
    }

    // Get Products by id
    public Optional<Product> getProductById(Long id){
        log.info("Retrieving Product with id: {}",id);
        return productRepository.findById(id);
    }

    // searching methods
    public List<Product> findByName(String name){
        return productRepository.findByNameContaining(name);
    }
    public List<Product> findByBrand(String brand){
        return productRepository.findByBrandContaining(brand);
    }
    public List<Product> findByCategory(String category){
        return productRepository.findByCategoryContaining(category);
    }
    public List<Product> findByPriceRange(Double minPrice, Double maxPrice){
        return productRepository.findByPriceRange(minPrice,maxPrice);
    }
    public List<Product> findByRatingRange(Double minRating, Double maxRating){
        return productRepository.findByRatingRange(minRating,maxRating);
    }
}
