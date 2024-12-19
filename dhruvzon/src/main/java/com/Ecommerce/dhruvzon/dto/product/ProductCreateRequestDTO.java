package com.Ecommerce.dhruvzon.dto.product;


import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
// This DTO will be used when creating a new product.
// It ensures that only valid data is sent to the service layer.
@Data
public class ProductCreateRequestDTO {
    @NotBlank
    @Size(max = 90)
    private String name;

    @NotBlank
    @Size(max = 244)
    private String description;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    private String category; // Reference to the category Name

    @NotBlank
    @Size(max = 122)
    private String brand;

    @NotNull
    @Positive
    private Integer stockQuantity;

    @Min(value = 0)
    private BigDecimal discount = BigDecimal.ZERO; // Optional

}
