package com.CatalogManagementSystem.Controller;

import com.CatalogManagementSystem.Model.Product;
import com.CatalogManagementSystem.Service.ProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@Validated
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Product> getProductById(@PathVariable Long id){
//        return productService.getProductsById(id)
//                .map(ResponseEntity::ok)
//                .orElseGet(()-> ResponseEntity.notFound().build());
//    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        logger.info("Fetching product with ID: {}", id);
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            logger.info("Product with ID: {} found", id);
            return ResponseEntity.ok(product.get());
        }
        logger.warn("Product with ID: {} not found", id);
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product){
        logger.info("Creating new product: {}", product);
        Product createProduct = productService.createProduct(product);
        logger.info("Product created with ID: {}",createProduct.getId());
        return ResponseEntity.ok(createProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,@Valid @RequestBody Product productDetails){
        logger.info("updating product with  ID: {}",id);
        Product product = productService.updateProduct(id,productDetails);
        if(product == null){
            logger.warn("Product with ID: {} not fount for update",id);
            return  ResponseEntity.notFound().build();
        }
        logger.info("Product with ID: {} updated",id);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        logger.info("Deleting product with ID: {}",id);
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<Product> searchProduct(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) Double maxRating){

        logger.info("Search request received with parameters - name: {}, brand: {}, category: {}, minPrice: {}, maxPrice: {}, minRating: {}, maxRating: {}",
                name, brand, category, minPrice, maxPrice, minRating, maxRating);

        List<Product> products;
        if (name != null) {
            products = productService.findByName(name);
        } else if (brand != null) {
            products = productService.findByBrand(brand);
        } else if (category != null) {
            products = productService.findByCategory(category);
        } else if (minPrice != null && maxPrice != null) {
            products = productService.findByPriceRange(minPrice, maxPrice);
        } else if (minRating != null && maxRating != null) {
            products = productService.findByRatingRange(minRating, maxRating);
        } else {
            products = productService.getAllProducts();
        }
        logger.info("Search results returned {} products", products.size());
        return products;
    }
}
