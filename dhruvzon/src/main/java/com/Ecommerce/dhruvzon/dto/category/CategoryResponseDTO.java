package com.Ecommerce.dhruvzon.dto.category;

import lombok.Data;

@Data
public class CategoryResponseDTO {
    private Long id;
    private String name;
    private Long parentCategoryId;
}
