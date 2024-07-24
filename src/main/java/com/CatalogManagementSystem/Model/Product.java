package com.CatalogManagementSystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Brand is mandatory")
    private String brand;
    private String description;

    @NotNull(message = "Price is mandatory")
    @Positive(message = "Price must be greater than zero")
    private Double price;

    @NotNull(message = "Quantity is mandatory")
    @Positive(message = "Quantity must be greater than zero")
    private Integer quantity;
    private String category;
    private LocalDateTime dateAdded;

    @PositiveOrZero(message = "Discount must be zero or greater")
    private Double discount;

    private String manufacturer;

    @Min(value = 0, message = "Rating must be between 0 and 5")
    @Max(value = 5, message = "Rating must be between 0 and 5")
    private Double rating;
}
