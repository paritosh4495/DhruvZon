package com.Ecommerce.dhruvzon.dto.category;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryRequestDTO {
    @NotNull
    @Size(min = 1, max = 100)
    private String name;
    private Long parentCategoryId; // Optional for hierarchy

}
