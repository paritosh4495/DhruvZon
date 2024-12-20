package com.Ecommerce.dhruvzon.service.image;

import com.Ecommerce.dhruvzon.dto.image.ImageRequestDTO;
import com.Ecommerce.dhruvzon.dto.image.ImageResponseDTO;
import com.Ecommerce.dhruvzon.exception.ImageNotFoundException;
import com.Ecommerce.dhruvzon.exception.ProductNotFoundException;
import com.Ecommerce.dhruvzon.mapper.ImageMapper;
import com.Ecommerce.dhruvzon.model.Image;
import com.Ecommerce.dhruvzon.model.Product;
import com.Ecommerce.dhruvzon.repository.ImageRepository;
import com.Ecommerce.dhruvzon.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;
    private final ImageMapper imageMapper;

    @Override
    public ImageResponseDTO addImage(Long productId, ImageRequestDTO imageRequestDTO) {
        Validate.notNull(imageRequestDTO, "imageRequestDTO cannot be null");
        log.info("Adding image for product ID: {}", productId);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + productId + " not found"));

        Image image = imageMapper.toImage(imageRequestDTO);
        image.setProduct(product);

        Image savedImage = imageRepository.save(image);
        log.info("Image added successfully with ID: {}", savedImage.getId());

        return imageMapper.toImageResponseDTO(savedImage);
    }

    @Override
    public List<ImageResponseDTO> addImages(Long productId, List<ImageRequestDTO> imageRequestDTOList) {
        Validate.notEmpty(imageRequestDTOList, "imageRequestDTOList cannot be empty");
        log.info("Adding multiple images for product ID: {}", productId);

        if (imageRequestDTOList.size() > 1000) {
            throw new IllegalArgumentException("Batch size exceeds limit of 1000");
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + productId + " not found"));

        List<Image> imagesToBeAdded = new ArrayList<>();

        for (ImageRequestDTO imageRequestDTO : imageRequestDTOList) {
            Image image = imageMapper.toImage(imageRequestDTO);
            image.setProduct(product);
            imagesToBeAdded.add(image);
        }

        List<Image> savedImages = imageRepository.saveAll(imagesToBeAdded);
        log.info("{} images added successfully for product ID: {}", savedImages.size(), productId);

        return savedImages.stream().map(imageMapper::toImageResponseDTO).collect(Collectors.toList());
    }

    @Override
    public ImageResponseDTO updateImage(Long imageId, ImageRequestDTO imageRequestDTO) {
        Validate.notNull(imageRequestDTO, "imageRequestDTO cannot be null");
        log.info("Updating image with ID: {}", imageId);

        Image existingImage = imageRepository.findById(imageId)
                .orElseThrow(() -> new ImageNotFoundException("Image with ID " + imageId + " not found"));

        existingImage.setUrl(imageRequestDTO.getUrl());
        existingImage.setFileName(imageRequestDTO.getFileName());
        existingImage.setFileType(imageRequestDTO.getFileType());

        Image updatedImage = imageRepository.save(existingImage);
        log.info("Image with ID: {} updated successfully", updatedImage.getId());

        return imageMapper.toImageResponseDTO(updatedImage);
    }

    @Override
    public void deleteImage(Long imageId) {
        log.info("Deleting image with ID: {}", imageId);

        Image image = imageRepository.findById(imageId)
                .orElseThrow(() -> new ImageNotFoundException("Image with ID " + imageId + " not found"));

        imageRepository.delete(image);
        log.info("Image with ID: {} deleted successfully", imageId);

    }

    @Override
    public List<ImageResponseDTO> getAllImages() {
        log.info("Fetching all images");

        return imageRepository.findAll()
                .stream()
                .map(imageMapper::toImageResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ImageResponseDTO> getImagesByProductId(Long productId) {
        log.info("Fetching images for product ID: {}", productId);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + productId + " not found"));

        List<Image> images = imageRepository.findByProduct(product);

        return images.stream()
                .map(imageMapper::toImageResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ImageResponseDTO getImageById(Long imageId) {
        Validate.notNull(imageId, "imageId cannot be null");
        log.info("Fetching image with ID: {}", imageId);

        Image image = imageRepository.findById(imageId)
                .orElseThrow(() -> new ImageNotFoundException("Image with ID " + imageId + " not found"));

        return imageMapper.toImageResponseDTO(image);
    }
}
