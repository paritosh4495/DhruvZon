package com.Ecommerce.dhruvzon.dto.product;

import com.Ecommerce.dhruvzon.enums.ProductStatus;
import lombok.Data;

import java.math.BigDecimal;
// This DTO will be used in endpoints where you need to list products, such as in search results or category pages. It doesn't need all the detailed data, just enough to give an overview.
@Data
public class ProductListDTO {

    private Long id;
    private String name;
    private BigDecimal price;
    private String categoryName; // Only the category name, not the full category object
    private String brand;
    private Integer stockQuantity;
    private ProductStatus status; // To show if the product is active, inactive, etc.

}
