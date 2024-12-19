package com.Ecommerce.dhruvzon.mapper;


import com.Ecommerce.dhruvzon.dto.image.ImageRequestDTO;
import com.Ecommerce.dhruvzon.dto.image.ImageResponseDTO;
import com.Ecommerce.dhruvzon.model.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    // Map Image Entity to ImageResponseDTO
    ImageResponseDTO toImageResponseDTO(Image image);

    // Map ImageRequestDTO to Image Entity
    @Mapping(target = "id", ignore = true) // ID is auto-generated
    @Mapping(target = "createdDate", ignore = true) // Managed by JPA
    @Mapping(target = "modifiedDate", ignore = true) // Managed by JPA
    @Mapping(target = "product", ignore = true) // Product association handled in service
    Image toImage(ImageRequestDTO imageRequestDTO);
}
