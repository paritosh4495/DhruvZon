package com.Ecommerce.dhruvzon.mapper;

import com.Ecommerce.dhruvzon.dto.review.ReviewCreateOrUpdateRequestDTO;
import com.Ecommerce.dhruvzon.dto.review.ReviewResponseDTO;
import com.Ecommerce.dhruvzon.model.Review;
import com.Ecommerce.dhruvzon.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-20T12:26:46+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class ReviewMapperImpl implements ReviewMapper {

    @Override
    public ReviewResponseDTO toReviewResponseDTO(Review review) {
        if ( review == null ) {
            return null;
        }

        ReviewResponseDTO reviewResponseDTO = new ReviewResponseDTO();

        reviewResponseDTO.setUserName( reviewUserFirstName( review ) );
        reviewResponseDTO.setId( review.getId() );
        reviewResponseDTO.setContent( review.getContent() );
        reviewResponseDTO.setRating( review.getRating() );
        reviewResponseDTO.setCreatedDate( review.getCreatedDate() );

        return reviewResponseDTO;
    }

    @Override
    public Review toReview(ReviewCreateOrUpdateRequestDTO reviewCreateOrUpdateRequestDTO) {
        if ( reviewCreateOrUpdateRequestDTO == null ) {
            return null;
        }

        Review review = new Review();

        review.setContent( reviewCreateOrUpdateRequestDTO.getContent() );
        review.setRating( reviewCreateOrUpdateRequestDTO.getRating() );

        return review;
    }

    private String reviewUserFirstName(Review review) {
        User user = review.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getFirstName();
    }
}
