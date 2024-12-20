package com.Ecommerce.dhruvzon.service.category;

import com.Ecommerce.dhruvzon.dto.category.CategoryDetailDTO;
import com.Ecommerce.dhruvzon.dto.category.CategoryRequestDTO;
import com.Ecommerce.dhruvzon.dto.category.CategoryResponseDTO;
import com.Ecommerce.dhruvzon.model.Category;

import java.util.List;

public interface CategoryService {

    CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO);

    CategoryResponseDTO updateCategory(Long categoryId, CategoryRequestDTO categoryRequestDTO);
    CategoryDetailDTO getCategoryById(Long id);
    Category getCategoryByName(String name);
    void deleteCategory(Long categoryId);

    List<CategoryResponseDTO> getAllCategories();

    List<CategoryDetailDTO> getSubCategories(Long parentCategoryId);

    List<CategoryDetailDTO> getTopLevelCategories();

    boolean isCategoryNameUnique(String categoryName);
    boolean doesCategoryExist(Long categoryId);
    

}
