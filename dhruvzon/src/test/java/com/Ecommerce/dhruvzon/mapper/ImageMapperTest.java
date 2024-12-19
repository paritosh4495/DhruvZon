package com.Ecommerce.dhruvzon.mapper;

import com.Ecommerce.dhruvzon.dto.image.ImageRequestDTO;
import com.Ecommerce.dhruvzon.dto.image.ImageResponseDTO;
import com.Ecommerce.dhruvzon.model.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ImageMapperTest {
    private ImageMapper imageMapper;

    @BeforeEach
    void setUp() {
        imageMapper = Mappers.getMapper(ImageMapper.class);
    }

    @Test
    void testToImageResponseDTO() {
        Image image = new Image();
        image.setId(1L);
        image.setUrl("https://example.com/image.jpg");

        ImageResponseDTO responseDTO = imageMapper.toImageResponseDTO(image);

        assertNotNull(responseDTO);
        assertEquals(image.getId(), responseDTO.getId());
        assertEquals(image.getUrl(), responseDTO.getUrl());
    }

    @Test
    void testToImage() {
        ImageRequestDTO requestDTO = new ImageRequestDTO();
        requestDTO.setUrl("https://example.com/image.jpg");

        Image image = imageMapper.toImage(requestDTO);

        assertNotNull(image);
        assertEquals(requestDTO.getUrl(), image.getUrl());
        assertNull(image.getId());
        assertNull(image.getCreatedDate());
        assertNull(image.getModifiedDate());
        assertNull(image.getProduct());
    }
}
