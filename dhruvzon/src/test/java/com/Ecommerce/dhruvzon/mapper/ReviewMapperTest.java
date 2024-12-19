package com.Ecommerce.dhruvzon.mapper;

import com.Ecommerce.dhruvzon.dto.review.ReviewCreateOrUpdateRequestDTO;
import com.Ecommerce.dhruvzon.dto.review.ReviewResponseDTO;
import com.Ecommerce.dhruvzon.model.Review;
import com.Ecommerce.dhruvzon.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ReviewMapperTest {
    private ReviewMapper reviewMapper;

    @BeforeEach
    void setUp() {
        reviewMapper = Mappers.getMapper(ReviewMapper.class);
    }

    @Test
    void testToReviewResponseDTO() {
        Review review = new Review();
        review.setId(1L);
        review.setContent("Great product!");
        review.setRating(5f);
        User user = new User();
        user.setFirstName("John");
        review.setUser(user);

        ReviewResponseDTO responseDTO = reviewMapper.toReviewResponseDTO(review);

        assertNotNull(responseDTO);
        assertEquals(review.getId(), responseDTO.getId());
        assertEquals(review.getContent(), responseDTO.getContent());
        assertEquals(review.getRating(), responseDTO.getRating());
        assertEquals(user.getFirstName(), responseDTO.getUserName());
    }

    @Test
    void testToReview() {
        ReviewCreateOrUpdateRequestDTO requestDTO = new ReviewCreateOrUpdateRequestDTO();
        requestDTO.setContent("Great product!");
        requestDTO.setRating(5f);

        Review review = reviewMapper.toReview(requestDTO);

        assertNotNull(review);
        assertEquals(requestDTO.getContent(), review.getContent());
        assertEquals(requestDTO.getRating(), review.getRating());
        assertNull(review.getId());
        assertNull(review.getCreatedDate());
        assertNull(review.getModifiedDate());
        assertNull(review.getProduct());
        assertNull(review.getUser());
    }
}
