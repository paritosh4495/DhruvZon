package com.Ecommerce.dhruvzon.mapper;

import com.Ecommerce.dhruvzon.dto.category.CategoryDetailDTO;
import com.Ecommerce.dhruvzon.dto.category.CategoryRequestDTO;
import com.Ecommerce.dhruvzon.dto.category.CategoryResponseDTO;
import com.Ecommerce.dhruvzon.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(source = "parentCategory.id", target = "parentCategoryId")
    @Mapping(source = "subCategories", target = "subCategories")
    CategoryDetailDTO toDetailDTO(Category category);

    @Mapping(source = "parentCategoryId", target = "parentCategory.id")
    Category toEntity(CategoryRequestDTO categoryRequestDTO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "parentCategory.id", target = "parentCategoryId")
    CategoryResponseDTO toResponseDTO(Category category);

    List<CategoryDetailDTO> toDetailDTOs(List<Category> categories);
}
