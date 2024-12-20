package com.Ecommerce.dhruvzon.controller;

import com.Ecommerce.dhruvzon.dto.review.ReviewCreateOrUpdateRequestDTO;
import com.Ecommerce.dhruvzon.dto.review.ReviewResponseDTO;
import com.Ecommerce.dhruvzon.response.ApiResponse;
import com.Ecommerce.dhruvzon.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // Create a review
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/add/product/{productId}/user/{userId}")
    public ResponseEntity<ApiResponse<ReviewResponseDTO>> createReview(
            @PathVariable Long productId,
            @PathVariable Long userId,
            @RequestBody ReviewCreateOrUpdateRequestDTO reviewCreateRequestDTO) {
        ReviewResponseDTO createdReview = reviewService.createReview(productId, userId, reviewCreateRequestDTO);
        ApiResponse<ReviewResponseDTO> response = new ApiResponse<>(createdReview, "Review created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Update a review
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/update/{reviewId}")
    public ResponseEntity<ApiResponse<ReviewResponseDTO>> updateReview(
            @PathVariable Long reviewId,
            @RequestBody ReviewCreateOrUpdateRequestDTO reviewUpdateRequestDTO) {
        ReviewResponseDTO updatedReview = reviewService.updateReview(reviewId, reviewUpdateRequestDTO);
        ApiResponse<ReviewResponseDTO> response = new ApiResponse<>(updatedReview, "Review updated successfully");
        return ResponseEntity.ok(response);
    }

    // Delete a review
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<ApiResponse<String>> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        ApiResponse<String> response = new ApiResponse<>(null, "Review deleted successfully");
        return ResponseEntity.ok(response);
    }

    // Get a review by ID
    @GetMapping("/{reviewId}")
    public ResponseEntity<ApiResponse<ReviewResponseDTO>> getReviewById(@PathVariable Long reviewId) {
        ReviewResponseDTO review = reviewService.getReviewById(reviewId);
        ApiResponse<ReviewResponseDTO> response = new ApiResponse<>(review, "Review fetched successfully");
        return ResponseEntity.ok(response);
    }

    // Get all reviews
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<ReviewResponseDTO>>> getAllReviews() {
        List<ReviewResponseDTO> reviews = reviewService.getAllReviews();
        ApiResponse<List<ReviewResponseDTO>> response = new ApiResponse<>(reviews, "All reviews fetched successfully");
        return ResponseEntity.ok(response);
    }

    // Get reviews by product ID
    @GetMapping("/product/{productId}")
    public ResponseEntity<ApiResponse<List<ReviewResponseDTO>>> getReviewsByProductId(@PathVariable Long productId) {
        List<ReviewResponseDTO> reviews = reviewService.getReviewsByProduct(productId);
        ApiResponse<List<ReviewResponseDTO>> response = new ApiResponse<>(reviews, "Reviews fetched successfully for product");
        return ResponseEntity.ok(response);
    }

    // Get reviews by user ID

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<ReviewResponseDTO>>> getReviewsByUserId(@PathVariable Long userId) {
        List<ReviewResponseDTO> reviews = reviewService.getReviewsByUser(userId);
        ApiResponse<List<ReviewResponseDTO>> response = new ApiResponse<>(reviews, "Reviews fetched successfully for user");
        return ResponseEntity.ok(response);
    }
}
