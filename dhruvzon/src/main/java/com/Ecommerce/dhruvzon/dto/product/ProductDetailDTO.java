package com.Ecommerce.dhruvzon.dto.product;

import com.Ecommerce.dhruvzon.enums.ProductStatus;
import lombok.Data;

import java.math.BigDecimal;

//This DTO will be used in the product detail page or
// any endpoint that needs to return a full view of a product,
// including all associated data like images and reviews.

@Data
public class ProductDetailDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String brand;
    private Integer stockQuantity;
    private ProductStatus status;
    private List<ImageDTO> images; // Nested DTO for images
    private List<ReviewDTO> reviews; // Nested DTO for reviews
    private BigDecimal discount;
}
