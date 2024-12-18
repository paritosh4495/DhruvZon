package com.Ecommerce.dhruvzon.dto.review;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewResponseDTO {
    private Long id;
    private String content;
    private Float rating;
    private LocalDateTime createdDate;
    private String userName; // Reviewer's name
}
