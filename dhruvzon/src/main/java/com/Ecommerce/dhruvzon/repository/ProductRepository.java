package com.Ecommerce.dhruvzon.repository;

import com.Ecommerce.dhruvzon.enums.ProductStatus;
import com.Ecommerce.dhruvzon.model.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(@NotBlank @Size(max = 90) String name);

    boolean existsByNameAndBrand(@NotBlank @Size(max = 90) String name, @NotBlank @Size(max = 122) String brand);

    List<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String keyword, String keyword1, Pageable pageable);

    List<Product> findByCategoryName(String category, Pageable pageable);

    List<Product> findByBrand(String brand, Pageable pageable);

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByStatus(ProductStatus productStatus, Pageable pageable);

    List<Product> findByBrandAndNameContainingIgnoreCase(String brand, String name, Pageable pageable);

    List<Product> findByCategoryNameAndStatus(String category, ProductStatus productStatus, Pageable pageable);

    List<Product> findByCategoryNameAndBrand(String category, String brand, Pageable pageable);
}
