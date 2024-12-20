package com.Ecommerce.dhruvzon.dto.category;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategoryResponseDTO {
    private Long id;
    private String name;
    private Long parentCategoryId;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private boolean hasSubcategories;

}
