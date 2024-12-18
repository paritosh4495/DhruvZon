package com.Ecommerce.dhruvzon.dto.product;

import com.Ecommerce.dhruvzon.dto.image.ImageResponseDTO;
import com.Ecommerce.dhruvzon.dto.review.ReviewResponseDTO;
import com.Ecommerce.dhruvzon.enums.ProductStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
// Added
public class ProductResponseDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String categoryName; // Just the category name or full category details if needed
    private String brand;
    private Integer stockQuantity;
    private ProductStatus status;
    private List<ImageResponseDTO> images; // Nested DTO for images
    private List<ReviewResponseDTO> reviews; // Nested DTO for reviews
    private BigDecimal discount;
}
