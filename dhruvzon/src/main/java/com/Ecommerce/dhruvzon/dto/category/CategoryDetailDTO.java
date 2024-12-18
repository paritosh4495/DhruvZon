package com.Ecommerce.dhruvzon.dto.category;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDetailDTO {
    private Long id;
    private String name;
    private Long parentCategoryId; // Optional for parent reference
    private List<CategoryDetailDTO> subCategories = new ArrayList<>(); // For hierarchical structure
}
