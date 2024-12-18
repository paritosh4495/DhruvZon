package com.Ecommerce.dhruvzon.dto.image;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class ImageRequestDTO {

    @NotNull
    private String fileName;

    @NotNull
    @URL
    private String url;

    @NotNull
    private String fileType;
}
