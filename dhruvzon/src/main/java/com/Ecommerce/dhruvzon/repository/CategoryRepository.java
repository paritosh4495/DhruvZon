package com.Ecommerce.dhruvzon.repository;

import com.Ecommerce.dhruvzon.model.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(@NotNull @Size(min = 1, max = 233) String name);

    boolean existsByName(String categoryName);

    List<Category> findAllByParentCategoryIsNull();
}
