package com.Ecommerce.dhruvzon.service.image;

import com.Ecommerce.dhruvzon.dto.image.ImageRequestDTO;
import com.Ecommerce.dhruvzon.dto.image.ImageResponseDTO;

import java.util.List;

public interface ImageService {

    ImageResponseDTO addImage(Long productId, ImageRequestDTO imageRequestDTO);
    List<ImageResponseDTO> addImages(Long productId, List<ImageRequestDTO> imageRequestDTOList);

    ImageResponseDTO updateImage(Long imageId,ImageRequestDTO imageRequestDTO);
    void deleteImage(Long imageId);

    List<ImageResponseDTO> getAllImages();
    List<ImageResponseDTO> getImagesByProductId(Long productId);

    ImageResponseDTO getImageById(Long imageId);

}
