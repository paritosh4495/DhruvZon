package com.Ecommerce.dhruvzon.dto.product;

import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

// This DTO will be used for partial updates to a product.
// If a user only wants to update the price or stock quantity,
// this DTO allows them to send only those fields.
@Data
public class ProductUpdateRequestDTO {

    private String name; // Optional
    private String description; // Optional

    @Positive
    private BigDecimal price; // Optional

    private String category; // Optional

    private String brand; // Optional

    @Positive
    private Integer stockQuantity; // Optional

    private BigDecimal discount; // Optional
}
