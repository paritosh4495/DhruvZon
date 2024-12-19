package com.Ecommerce.dhruvzon.mapper;

import com.Ecommerce.dhruvzon.dto.category.CategoryDetailDTO;
import com.Ecommerce.dhruvzon.dto.category.CategoryRequestDTO;
import com.Ecommerce.dhruvzon.dto.category.CategoryResponseDTO;
import com.Ecommerce.dhruvzon.model.Category;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-19T13:07:59+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDetailDTO toDetailDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDetailDTO categoryDetailDTO = new CategoryDetailDTO();

        categoryDetailDTO.setParentCategoryId( categoryParentCategoryId( category ) );
        categoryDetailDTO.setSubCategories( toDetailDTOs( category.getSubCategories() ) );
        categoryDetailDTO.setId( category.getId() );
        categoryDetailDTO.setName( category.getName() );

        return categoryDetailDTO;
    }

    @Override
    public Category toEntity(CategoryRequestDTO categoryRequestDTO) {
        if ( categoryRequestDTO == null ) {
            return null;
        }

        Category category = new Category();

        category.setParentCategory( categoryRequestDTOToCategory( categoryRequestDTO ) );
        category.setName( categoryRequestDTO.getName() );

        return category;
    }

    @Override
    public CategoryResponseDTO toResponseDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();

        categoryResponseDTO.setId( category.getId() );
        categoryResponseDTO.setName( category.getName() );
        categoryResponseDTO.setParentCategoryId( categoryParentCategoryId( category ) );

        return categoryResponseDTO;
    }

    @Override
    public List<CategoryDetailDTO> toDetailDTOs(List<Category> categories) {
        if ( categories == null ) {
            return null;
        }

        List<CategoryDetailDTO> list = new ArrayList<CategoryDetailDTO>( categories.size() );
        for ( Category category : categories ) {
            list.add( toDetailDTO( category ) );
        }

        return list;
    }

    private Long categoryParentCategoryId(Category category) {
        Category parentCategory = category.getParentCategory();
        if ( parentCategory == null ) {
            return null;
        }
        return parentCategory.getId();
    }

    protected Category categoryRequestDTOToCategory(CategoryRequestDTO categoryRequestDTO) {
        if ( categoryRequestDTO == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryRequestDTO.getParentCategoryId() );

        return category;
    }
}
