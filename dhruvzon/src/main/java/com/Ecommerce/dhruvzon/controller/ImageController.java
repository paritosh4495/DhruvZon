package com.Ecommerce.dhruvzon.controller;

import com.Ecommerce.dhruvzon.dto.image.ImageRequestDTO;
import com.Ecommerce.dhruvzon.dto.image.ImageResponseDTO;
import com.Ecommerce.dhruvzon.response.ApiResponse;
import com.Ecommerce.dhruvzon.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    // Add a single image to a product
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{productId}/add")
    public ResponseEntity<ApiResponse<ImageResponseDTO>> addImage(
            @PathVariable Long productId,
            @RequestBody ImageRequestDTO imageRequestDTO) {
        ImageResponseDTO createdImage = imageService.addImage(productId, imageRequestDTO);
        ApiResponse<ImageResponseDTO> response = new ApiResponse<>(createdImage, "Image added successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Add multiple images to a product
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{productId}/bulk-add")
    public ResponseEntity<ApiResponse<List<ImageResponseDTO>>> addImages(
            @PathVariable Long productId,
            @RequestBody List<ImageRequestDTO> imageRequestDTOList) {
        List<ImageResponseDTO> createdImages = imageService.addImages(productId, imageRequestDTOList);
        ApiResponse<List<ImageResponseDTO>> response = new ApiResponse<>(createdImages, "Images added successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Update an image
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{imageId}/update")
    public ResponseEntity<ApiResponse<ImageResponseDTO>> updateImage(
            @PathVariable Long imageId,
            @RequestBody ImageRequestDTO imageRequestDTO) {
        ImageResponseDTO updatedImage = imageService.updateImage(imageId, imageRequestDTO);
        ApiResponse<ImageResponseDTO> response = new ApiResponse<>(updatedImage, "Image updated successfully");
        return ResponseEntity.ok(response);
    }

    // Delete an image
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{imageId}/delete")
    public ResponseEntity<ApiResponse<String>> deleteImage(@PathVariable Long imageId) {
        imageService.deleteImage(imageId);
        ApiResponse<String> response = new ApiResponse<>(null, "Image deleted successfully");
        return ResponseEntity.ok(response);
    }

    // Get all images
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<ImageResponseDTO>>> getAllImages() {
        List<ImageResponseDTO> images = imageService.getAllImages();
        ApiResponse<List<ImageResponseDTO>> response = new ApiResponse<>(images, "All images fetched successfully");
        return ResponseEntity.ok(response);
    }

    // Get images by product ID
    @GetMapping("/product/{productId}")
    public ResponseEntity<ApiResponse<List<ImageResponseDTO>>> getImagesByProductId(@PathVariable Long productId) {
        List<ImageResponseDTO> images = imageService.getImagesByProductId(productId);
        ApiResponse<List<ImageResponseDTO>> response = new ApiResponse<>(images, "Images fetched successfully for product");
        return ResponseEntity.ok(response);
    }

    // Get image by ID
    @GetMapping("/{imageId}")
    public ResponseEntity<ApiResponse<ImageResponseDTO>> getImageById(@PathVariable Long imageId) {
        ImageResponseDTO image = imageService.getImageById(imageId);
        ApiResponse<ImageResponseDTO> response = new ApiResponse<>(image, "Image fetched successfully");
        return ResponseEntity.ok(response);
    }
}
