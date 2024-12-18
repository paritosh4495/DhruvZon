package com.Ecommerce.dhruvzon.dto.review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReviewCreateOrUpdateRequestDTO {

    @NotNull
    @Size(min = 1, max = 500) // Limit content length
    private String content;

    @NotNull
    @Min(1)
    @Max(5)
    private Float rating;

}
