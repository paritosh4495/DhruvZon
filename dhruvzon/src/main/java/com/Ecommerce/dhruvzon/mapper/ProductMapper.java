package com.Ecommerce.dhruvzon.mapper;


import com.Ecommerce.dhruvzon.dto.product.*;
import com.Ecommerce.dhruvzon.model.Product;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, ImageMapper.class, ReviewMapper.class})
public interface ProductMapper {

    // Map Product Entity to ProductDetailDTO
    ProductDetailDTO toProductDetailDTO(Product product);

    // Map Product Entity to ProductListDTO
    ProductListDTO toProductListDTO(Product product);

    // Map ProductCreateRequestDTO to Product Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "isDeleted", constant = "false")
    @Mapping(target = "status", constant = "ACTIVE")
    @Mapping(target = "category", ignore = true)  // We will set category in the service layer
    Product toProduct(ProductCreateRequestDTO productCreateRequestDTO);

    // Map ProductUpdateRequestDTO to Product Entity (Partial Updates)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "category", ignore = true)  // We will set category in the service layer
    Product toProduct(ProductUpdateRequestDTO productUpdateRequestDTO, @MappingTarget Product product);

    // Map Product Entity to ResponseDTO after creation or update
    ProductResponseDTO toResponseDTO(Product product);

    // Map List of Product Entities to List of ProductListDTOs
    List<ProductListDTO> toProductListDTOs(List<Product> products);
}
