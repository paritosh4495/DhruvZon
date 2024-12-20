package com.Ecommerce.dhruvzon.service.review;

import com.Ecommerce.dhruvzon.dto.review.ReviewCreateOrUpdateRequestDTO;
import com.Ecommerce.dhruvzon.dto.review.ReviewResponseDTO;

import java.util.List;

public interface ReviewService {

    ReviewResponseDTO createReview(Long productId, Long userID, ReviewCreateOrUpdateRequestDTO reviewCreateRequestDTO);
    ReviewResponseDTO updateReview(Long reviewId, ReviewCreateOrUpdateRequestDTO reviewUpdateRequestDTO);

    void deleteReview(Long reviewId);
    ReviewResponseDTO getReviewById(Long reviewId);
    List<ReviewResponseDTO> getAllReviews();
    List<ReviewResponseDTO> getReviewsByProduct(Long productId);
    List<ReviewResponseDTO> getReviewsByUser(Long userId);


}
