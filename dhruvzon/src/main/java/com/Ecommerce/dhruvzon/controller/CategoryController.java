package com.Ecommerce.dhruvzon.controller;

import com.Ecommerce.dhruvzon.dto.category.CategoryDetailDTO;
import com.Ecommerce.dhruvzon.dto.category.CategoryRequestDTO;
import com.Ecommerce.dhruvzon.dto.category.CategoryResponseDTO;
import com.Ecommerce.dhruvzon.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {
        CategoryResponseDTO responseDTO = categoryService.createCategory(categoryRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(
            @PathVariable Long id,
            @RequestBody CategoryRequestDTO categoryRequestDTO) {
        CategoryResponseDTO responseDTO = categoryService.updateCategory(id, categoryRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDetailDTO> getCategoryById(@PathVariable Long id) {
        CategoryDetailDTO detailDTO = categoryService.getCategoryById(id);
        return ResponseEntity.ok(detailDTO);
    }


    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        List<CategoryResponseDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}/subcategories")
    public ResponseEntity<List<CategoryDetailDTO>> getSubCategories(@PathVariable Long id) {
        List<CategoryDetailDTO> subCategories = categoryService.getSubCategories(id);
        return ResponseEntity.ok(subCategories);
    }

    // Can change the return type to simple Other than Detail;
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/top-level")
    public ResponseEntity<List<CategoryDetailDTO>> getTopLevelCategories() {
        List<CategoryDetailDTO> topLevelCategories = categoryService.getTopLevelCategories();
        return ResponseEntity.ok(topLevelCategories);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

}
