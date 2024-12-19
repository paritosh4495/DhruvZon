package com.Ecommerce.dhruvzon.mapper;

import com.Ecommerce.dhruvzon.dto.product.ProductCreateRequestDTO;
import com.Ecommerce.dhruvzon.dto.product.ProductDetailDTO;
import com.Ecommerce.dhruvzon.dto.product.ProductListDTO;
import com.Ecommerce.dhruvzon.dto.product.ProductUpdateRequestDTO;
import com.Ecommerce.dhruvzon.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductMapperTest {
    private ProductMapper productMapper;

    @BeforeEach
    void setUp() {
        productMapper = Mappers.getMapper(ProductMapper.class);
    }

    @Test
    void shouldMapProductToProductDetailDTO() {
        // Given
        Product product = new Product();
        product.setId(1L);
        product.setName("Sample Product");
        product.setPrice(BigDecimal.valueOf(100.0));

        // When
        ProductDetailDTO productDetailDTO = productMapper.toProductDetailDTO(product);

        // Then
        assertNotNull(productDetailDTO);
        assertEquals(1L, productDetailDTO.getId());
        assertEquals("Sample Product", productDetailDTO.getName());
        assertEquals(100.0, productDetailDTO.getPrice());
    }

    @Test
    void shouldMapProductCreateRequestDTOToProduct() {
        // Given
        ProductCreateRequestDTO createRequestDTO = new ProductCreateRequestDTO();
        createRequestDTO.setName("New Product");
        createRequestDTO.setPrice(BigDecimal.valueOf(150.0));

        // When
        Product product = productMapper.toProduct(createRequestDTO);

        // Then
        assertNotNull(product);
        assertEquals("New Product", product.getName());
        assertEquals(150.0, product.getPrice());
        assertFalse(product.getIsDeleted());
        assertEquals("ACTIVE", product.getStatus());
    }

    @Test
    void shouldMapProductUpdateRequestDTOToExistingProduct() {
        // Given
        Product existingProduct = new Product();
        existingProduct.setId(1L);
        existingProduct.setName("Old Product");

        ProductUpdateRequestDTO updateRequestDTO = new ProductUpdateRequestDTO();
        updateRequestDTO.setName("Updated Product");

        // When
        productMapper.toProduct(updateRequestDTO, existingProduct);

        // Then
        assertEquals("Updated Product", existingProduct.getName());
    }

    @Test
    void shouldMapProductListToProductListDTOs() {
        // Given
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Product 2");

        List<Product> products = List.of(product1, product2);

        // When
        List<ProductListDTO> productListDTOs = productMapper.toProductListDTOs(products);

        // Then
        assertNotNull(productListDTOs);
        assertEquals(2, productListDTOs.size());
        assertEquals("Product 1", productListDTOs.get(0).getName());
        assertEquals("Product 2", productListDTOs.get(1).getName());
    }

}
