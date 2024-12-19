package com.Ecommerce.dhruvzon.repository;

import com.Ecommerce.dhruvzon.enums.ProductStatus;
import com.Ecommerce.dhruvzon.model.Category;
import com.Ecommerce.dhruvzon.model.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(@NotBlank @Size(max = 90) String name);

    boolean existsByNameAndBrand(@NotBlank @Size(max = 90) String name, @NotBlank @Size(max = 122) String brand);

    List<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String keyword, String keyword1, Pageable pageable);

    List<Product> findByCategoryName(String categoryName, Pageable pageable);

    List<Product> findByBrand(String brand, Pageable pageable);

    List<Product> findByBrandAndName(String brand, String name, Pageable pageable);

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByCategoryNameAndBrand(String categoryName, String brand, Pageable pageable);

    List<Product> findByStatus(ProductStatus productStatus, Pageable pageable);

    List<Product> findByCategoryNameAndStatus(String category, ProductStatus productStatus, Pageable pageable);

}
