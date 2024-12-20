package com.Ecommerce.dhruvzon.service.category;


import com.Ecommerce.dhruvzon.dto.category.CategoryDetailDTO;
import com.Ecommerce.dhruvzon.dto.category.CategoryRequestDTO;
import com.Ecommerce.dhruvzon.dto.category.CategoryResponseDTO;
import com.Ecommerce.dhruvzon.exception.CategoryAlreadyExistsException;
import com.Ecommerce.dhruvzon.exception.CategoryNotFoundException;
import com.Ecommerce.dhruvzon.mapper.CategoryMapper;
import com.Ecommerce.dhruvzon.model.Category;
import com.Ecommerce.dhruvzon.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO) {
        if (categoryRequestDTO == null) {
            throw new IllegalArgumentException("CategoryRequestDTO cannot be null");
        }

        // Normalize and check if category name already exists
        String normalizedCategoryName = normalize(categoryRequestDTO.getName());
        if (!isCategoryNameUnique(normalizedCategoryName)) {
            throw new CategoryAlreadyExistsException("Category with name: " + normalizedCategoryName + " already exists");
        }

        // Initialize the category and set its name
        Category category = categoryMapper.toCategory(categoryRequestDTO);
        category.setName(normalizedCategoryName);

        // Handle parent category logic
        if (categoryRequestDTO.getParentCategoryId() != null) {
            // Fetch the parent category or throw an exception if not found
            Category parentCategory = categoryRepository.findById(categoryRequestDTO.getParentCategoryId())
                    .orElseThrow(() -> new CategoryNotFoundException("Parent category with id: "
                            + categoryRequestDTO.getParentCategoryId() + " not found"));

            // Check for circular reference in the category hierarchy
            if (hasCircularReference(category, parentCategory)) {
                throw new IllegalArgumentException("Circular reference detected in category hierarchy");
            }
            category.setParentCategory(parentCategory);
        } else {
            // If no parent category ID is provided, set parentCategory to null
            category.setParentCategory(null);
        }

        // Save the new category
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toCategoryResponseDTO(savedCategory);
    }

    @Override
    public CategoryResponseDTO updateCategory(Long categoryId, CategoryRequestDTO categoryRequestDTO) {
        if (categoryRequestDTO == null) {
            throw new IllegalArgumentException("CategoryRequestDTO cannot be null");
        }

        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id: " + categoryId + " not found"));

        String normalizedCategoryName = normalize(categoryRequestDTO.getName());
        if (!normalizedCategoryName.equals(existingCategory.getName()) && !isCategoryNameUnique(normalizedCategoryName)) {
            throw new CategoryAlreadyExistsException("Category with name: " + normalizedCategoryName + " already exists");
        }

        existingCategory.setName(normalizedCategoryName);

        if (categoryRequestDTO.getParentCategoryId() != null) {
            Category parentCategory = categoryRepository.findById(categoryRequestDTO.getParentCategoryId())
                    .orElseThrow(() -> new CategoryNotFoundException("Parent category with id: " + categoryRequestDTO.getParentCategoryId() + " not found"));

            if (hasCircularReference(existingCategory, parentCategory)) {
                throw new IllegalArgumentException("Circular reference detected in category hierarchy");
            }

            existingCategory.setParentCategory(parentCategory);
        }

        Category savedCategory = categoryRepository.save(existingCategory);
        return categoryMapper.toCategoryResponseDTO(savedCategory);
    }

    @Override
    public CategoryDetailDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id: " + id + " not found"));

        return categoryMapper.toCategoryDetailDTO(category);
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(normalize(name))
                .orElseThrow(() -> new CategoryNotFoundException("Category with name: " + name + " not found"));
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id: " + categoryId + " not found"));

        categoryRepository.delete(category);
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(categoryMapper::toCategoryResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDetailDTO> getSubCategories(Long parentCategoryId) {
        Category parentCategory = categoryRepository.findById(parentCategoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Parent category with id: " + parentCategoryId + " not found"));

        return parentCategory.getSubCategories().stream()
                .map(categoryMapper::toCategoryDetailDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDetailDTO> getTopLevelCategories() {
        List<Category> topLevelCategories = categoryRepository.findAllByParentCategoryIsNull();
        return topLevelCategories.stream()
                .map(categoryMapper::toCategoryDetailDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isCategoryNameUnique(String categoryName) {
        return !categoryRepository.existsByName(normalize(categoryName));
    }

    @Override
    public boolean doesCategoryExist(Long categoryId) {
        return categoryRepository.existsById(categoryId);
    }

    private String normalize(String name) {
        return name == null ? null : name.trim().toLowerCase();
    }

    private boolean hasCircularReference(Category child, Category parent) {
        Category current = parent;
        while (current != null) {
            if (current.equals(child)) {
                return true;
            }
            current = current.getParentCategory();
        }
        return false;
    }
}
