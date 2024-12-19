package com.Ecommerce.dhruvzon.service.category;


import com.Ecommerce.dhruvzon.exception.CategoryNotFoundException;
import com.Ecommerce.dhruvzon.model.Category;
import com.Ecommerce.dhruvzon.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
    }
}
