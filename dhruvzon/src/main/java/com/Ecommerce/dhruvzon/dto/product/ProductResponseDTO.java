package com.Ecommerce.dhruvzon.dto.product;

import com.Ecommerce.dhruvzon.enums.ProductStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponseDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String categoryName; // Just the category name or full category details if needed
    private String brand;
    private Integer stockQuantity;
    private ProductStatus status;
    private List<ImageDTO> images; // Nested DTO for images
    private List<ReviewDTO> reviews; // Nested DTO for reviews
    private BigDecimal discount;
    private String message; // Success or error message
}
