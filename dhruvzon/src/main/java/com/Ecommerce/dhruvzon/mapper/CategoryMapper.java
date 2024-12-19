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
    CategoryDetailDTO toCategoryDetailDTO(Category category);

    @Mapping(source = "parentCategoryId", target = "parentCategory.id")
    Category toCategory(CategoryRequestDTO categoryRequestDTO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "parentCategory.id", target = "parentCategoryId")
    CategoryResponseDTO toCategoryResponseDTO(Category category);

    List<CategoryDetailDTO> toCategoryDetailDTOs(List<Category> categories);
}
