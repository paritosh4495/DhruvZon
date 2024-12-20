package com.Ecommerce.dhruvzon.service.review;

import com.Ecommerce.dhruvzon.dto.review.ReviewCreateOrUpdateRequestDTO;
import com.Ecommerce.dhruvzon.dto.review.ReviewResponseDTO;
import com.Ecommerce.dhruvzon.exception.ProductNotFoundException;
import com.Ecommerce.dhruvzon.exception.ReviewNotFoundException;
import com.Ecommerce.dhruvzon.exception.UserNotFoundException;
import com.Ecommerce.dhruvzon.mapper.ReviewMapper;
import com.Ecommerce.dhruvzon.model.Product;
import com.Ecommerce.dhruvzon.model.Review;
import com.Ecommerce.dhruvzon.model.User;
import com.Ecommerce.dhruvzon.repository.ProductRepository;
import com.Ecommerce.dhruvzon.repository.ReviewRepository;
import com.Ecommerce.dhruvzon.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private static final Logger logger = LoggerFactory.getLogger(ReviewServiceImpl.class);

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public ReviewResponseDTO createReview(Long productId, Long userID, ReviewCreateOrUpdateRequestDTO reviewCreateRequestDTO) {
        logger.info("Creating review for product ID: {} and user ID: {}", productId, userID);

        if (reviewCreateRequestDTO == null) {
            logger.error("Review create request DTO is null");
            throw new IllegalArgumentException("Review cannot be null");
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> {
                    logger.error("Product not found with ID: {}", productId);
                    return new ProductNotFoundException("Product not found with id: " + productId);
                });
        User user = userRepository.findById(userID)
                .orElseThrow(() -> {
                    logger.error("User not found with ID: {}", userID);
                    return new UserNotFoundException("User not found with id: " + userID);
                });


        Review review = reviewMapper.toReview(reviewCreateRequestDTO);
        review.setProduct(product);
        review.setUser(user);

        reviewRepository.save(review);
        logger.info("Review created successfully for product ID: {} and user ID: {}", productId, userID);
        return reviewMapper.toReviewResponseDTO(review);
    }

    @Override
    public ReviewResponseDTO updateReview(Long reviewId, ReviewCreateOrUpdateRequestDTO reviewUpdateRequestDTO) {
        logger.info("Updating review with ID: {}", reviewId);

        if (reviewUpdateRequestDTO == null) {
            logger.error("Review update request DTO is null");
            throw new IllegalArgumentException("Review cannot be null");
        }

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> {
                    logger.error("Review not found with ID: {}", reviewId);
                    return new ReviewNotFoundException("Review not found with id: " + reviewId);
                });

        // Check if the review belongs to the user

        review.setContent(reviewUpdateRequestDTO.getContent());
        review.setRating(reviewUpdateRequestDTO.getRating());

        reviewRepository.save(review);
        logger.info("Review updated successfully with ID: {}", reviewId);
        return reviewMapper.toReviewResponseDTO(review);
    }

    @Override
    public void deleteReview(Long reviewId) {
        logger.info("Deleting review with ID: {}", reviewId);

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> {
                    logger.error("Review not found with ID: {}", reviewId);
                    return new ReviewNotFoundException("Review not found with id: " + reviewId);
                });

        reviewRepository.delete(review);
        logger.info("Review deleted successfully with ID: {}", reviewId);
    }

    @Override
    public ReviewResponseDTO getReviewById(Long reviewId) {
        logger.info("Fetching review with ID: {}", reviewId);

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> {
                    logger.error("Review not found with ID: {}", reviewId);
                    return new ReviewNotFoundException("Review not found with id: " + reviewId);
                });

        return reviewMapper.toReviewResponseDTO(review);
    }

    @Override
    public List<ReviewResponseDTO> getAllReviews() {
        logger.info("Fetching all reviews");

        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream().map(reviewMapper::toReviewResponseDTO).collect(Collectors.toList());
    }

    @Override
    public List<ReviewResponseDTO> getReviewsByProduct(Long productId) {
        logger.info("Fetching reviews for product ID: {}", productId);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> {
                    logger.error("Product not found with ID: {}", productId);
                    return new ProductNotFoundException("Product not found with id: " + productId);
                });

        List<Review> reviews = reviewRepository.findByProductId(productId);
        return reviews.stream().map(reviewMapper::toReviewResponseDTO).collect(Collectors.toList());
    }

    @Override
    public List<ReviewResponseDTO> getReviewsByUser(Long userId) {
        logger.info("Fetching reviews for user ID: {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    logger.error("User not found with ID: {}", userId);
                    return new UserNotFoundException("User not found with id: " + userId);
                });

        List<Review> reviews = reviewRepository.findByUserId(userId);
        return reviews.stream().map(reviewMapper::toReviewResponseDTO).collect(Collectors.toList());
    }
}
