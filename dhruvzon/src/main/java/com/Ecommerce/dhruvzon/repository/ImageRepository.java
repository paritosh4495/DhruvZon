package com.Ecommerce.dhruvzon.repository;

import com.Ecommerce.dhruvzon.dto.image.ImageResponseDTO;
import com.Ecommerce.dhruvzon.model.Image;
import com.Ecommerce.dhruvzon.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findByProduct(Product product);
}
