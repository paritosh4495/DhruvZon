package com.Ecommerce.dhruvzon.mapper;

import com.Ecommerce.dhruvzon.dto.image.ImageRequestDTO;
import com.Ecommerce.dhruvzon.dto.image.ImageResponseDTO;
import com.Ecommerce.dhruvzon.model.Image;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-19T20:05:28+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class ImageMapperImpl implements ImageMapper {

    @Override
    public ImageResponseDTO toImageResponseDTO(Image image) {
        if ( image == null ) {
            return null;
        }

        ImageResponseDTO imageResponseDTO = new ImageResponseDTO();

        imageResponseDTO.setId( image.getId() );
        imageResponseDTO.setUrl( image.getUrl() );

        return imageResponseDTO;
    }

    @Override
    public Image toImage(ImageRequestDTO imageRequestDTO) {
        if ( imageRequestDTO == null ) {
            return null;
        }

        Image image = new Image();

        image.setFileName( imageRequestDTO.getFileName() );
        image.setUrl( imageRequestDTO.getUrl() );
        image.setFileType( imageRequestDTO.getFileType() );

        return image;
    }
}
