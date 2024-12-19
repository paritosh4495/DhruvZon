package com.Ecommerce.dhruvzon.mapper;

import com.Ecommerce.dhruvzon.dto.category.CategoryDetailDTO;
import com.Ecommerce.dhruvzon.dto.category.CategoryRequestDTO;
import com.Ecommerce.dhruvzon.dto.category.CategoryResponseDTO;
import com.Ecommerce.dhruvzon.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryMapperTest {

    private CategoryMapper categoryMapper;

    @BeforeEach
    public void setUp() {
        categoryMapper = Mappers.getMapper(CategoryMapper.class);
    }

    @Test
    public void testToDetailDTO() {
        // Given
        Category parentCategory = new Category();
        parentCategory.setId(2L);
        parentCategory.setName("Products");

        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");
        category.setParentCategory(parentCategory);
        category.setSubCategories(Collections.emptyList()); // No subcategories for this test

        // When
        CategoryDetailDTO detailDTO = categoryMapper.toDetailDTO(category);

        // Then
        assertThat(detailDTO).isNotNull();
        assertThat(detailDTO.getParentCategoryId()).isEqualTo(2L);
        assertThat(detailDTO.getSubCategories()).isEmpty(); // Expecting no subcategories
    }

    @Test
    public void testToEntity() {
        // Given
        CategoryRequestDTO requestDTO = new CategoryRequestDTO();
        requestDTO.setParentCategoryId(2L);

        // When
        Category category = categoryMapper.toEntity(requestDTO);

        // Then
        assertThat(category).isNotNull();
        assertThat(category.getParentCategory()).isNotNull();
        assertThat(category.getParentCategory().getId()).isEqualTo(2L);
    }

    @Test
    public void testToResponseDTO() {
        // Given
        Category parentCategory = new Category();
        parentCategory.setId(2L);

        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");
        category.setParentCategory(parentCategory);

        // When
        CategoryResponseDTO responseDTO = categoryMapper.toResponseDTO(category);

        // Then
        assertThat(responseDTO).isNotNull();
        assertThat(responseDTO.getId()).isEqualTo(1L);
        assertThat(responseDTO.getName()).isEqualTo("Electronics");
        assertThat(responseDTO.getParentCategoryId()).isEqualTo(2L);
    }

    @Test
    public void testToDetailDTOs() {
        // Given
        Category category1 = new Category();
        category1.setId(1L);

        Category category2 = new Category();
        category2.setId(2L);

        List<Category> categories = List.of(category1, category2);

        // When
        List<CategoryDetailDTO> detailDTOS = categoryMapper.toDetailDTOs(categories);

        // Then
        assertThat(detailDTOS).hasSize(2);
    }
}
