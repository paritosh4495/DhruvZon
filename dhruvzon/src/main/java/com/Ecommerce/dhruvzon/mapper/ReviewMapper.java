package com.Ecommerce.dhruvzon.mapper;

import com.Ecommerce.dhruvzon.dto.review.ReviewCreateOrUpdateRequestDTO;
import com.Ecommerce.dhruvzon.dto.review.ReviewResponseDTO;
import com.Ecommerce.dhruvzon.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewMapper {


    // Map Review Entity to ReviewResponseDTO
    @Mapping(source = "user.name", target = "userName") // Map user name from the User entity
    ReviewResponseDTO toReviewResponseDTO(Review review);

    // Map ReviewCreateOrUpdateRequestDTO to Review Entity
    @Mapping(target = "id", ignore = true) // ID is auto-generated
    @Mapping(target = "createdDate", ignore = true) // Managed by JPA
    @Mapping(target = "modifiedDate", ignore = true) // Managed by JPA
    @Mapping(target = "product", ignore = true) // Product association handled in service
    @Mapping(target = "user", ignore = true) // User association handled in service
    Review toReview(ReviewCreateOrUpdateRequestDTO reviewCreateOrUpdateRequestDTO);
}
