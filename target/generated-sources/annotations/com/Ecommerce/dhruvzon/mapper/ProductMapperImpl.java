package com.Ecommerce.dhruvzon.mapper;

import com.Ecommerce.dhruvzon.dto.image.ImageResponseDTO;
import com.Ecommerce.dhruvzon.dto.product.ProductCreateRequestDTO;
import com.Ecommerce.dhruvzon.dto.product.ProductDetailDTO;
import com.Ecommerce.dhruvzon.dto.product.ProductListDTO;
import com.Ecommerce.dhruvzon.dto.product.ProductResponseDTO;
import com.Ecommerce.dhruvzon.dto.product.ProductUpdateRequestDTO;
import com.Ecommerce.dhruvzon.dto.review.ReviewResponseDTO;
import com.Ecommerce.dhruvzon.enums.ProductStatus;
import com.Ecommerce.dhruvzon.model.Category;
import com.Ecommerce.dhruvzon.model.Image;
import com.Ecommerce.dhruvzon.model.Product;
import com.Ecommerce.dhruvzon.model.Review;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-19T20:05:29+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ImageMapper imageMapper;
    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public ProductDetailDTO toProductDetailDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDetailDTO productDetailDTO = new ProductDetailDTO();

        productDetailDTO.setId( product.getId() );
        productDetailDTO.setName( product.getName() );
        productDetailDTO.setDescription( product.getDescription() );
        productDetailDTO.setPrice( product.getPrice() );
        productDetailDTO.setBrand( product.getBrand() );
        productDetailDTO.setCategory( categoryMapper.toCategoryDetailDTO( product.getCategory() ) );
        productDetailDTO.setStockQuantity( product.getStockQuantity() );
        productDetailDTO.setStatus( product.getStatus() );
        productDetailDTO.setImages( imageListToImageResponseDTOList( product.getImages() ) );
        productDetailDTO.setReviews( reviewListToReviewResponseDTOList( product.getReviews() ) );
        productDetailDTO.setDiscount( product.getDiscount() );

        return productDetailDTO;
    }

    @Override
    public ProductListDTO toProductListDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductListDTO productListDTO = new ProductListDTO();

        productListDTO.setCategoryName( productCategoryName( product ) );
        productListDTO.setId( product.getId() );
        productListDTO.setName( product.getName() );
        productListDTO.setPrice( product.getPrice() );
        productListDTO.setBrand( product.getBrand() );
        productListDTO.setStockQuantity( product.getStockQuantity() );
        productListDTO.setStatus( product.getStatus() );

        return productListDTO;
    }

    @Override
    public Product toProduct(ProductCreateRequestDTO productCreateRequestDTO) {
        if ( productCreateRequestDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setName( productCreateRequestDTO.getName() );
        product.setDescription( productCreateRequestDTO.getDescription() );
        product.setPrice( productCreateRequestDTO.getPrice() );
        product.setBrand( productCreateRequestDTO.getBrand() );
        product.setStockQuantity( productCreateRequestDTO.getStockQuantity() );
        product.setDiscount( productCreateRequestDTO.getDiscount() );

        product.setIsDeleted( false );
        product.setStatus( ProductStatus.ACTIVE );

        return product;
    }

    @Override
    public Product toProduct(ProductUpdateRequestDTO productUpdateRequestDTO, Product product) {
        if ( productUpdateRequestDTO == null ) {
            return product;
        }

        if ( productUpdateRequestDTO.getName() != null ) {
            product.setName( productUpdateRequestDTO.getName() );
        }
        if ( productUpdateRequestDTO.getDescription() != null ) {
            product.setDescription( productUpdateRequestDTO.getDescription() );
        }
        if ( productUpdateRequestDTO.getPrice() != null ) {
            product.setPrice( productUpdateRequestDTO.getPrice() );
        }
        if ( productUpdateRequestDTO.getBrand() != null ) {
            product.setBrand( productUpdateRequestDTO.getBrand() );
        }
        if ( productUpdateRequestDTO.getStockQuantity() != null ) {
            product.setStockQuantity( productUpdateRequestDTO.getStockQuantity() );
        }
        if ( productUpdateRequestDTO.getDiscount() != null ) {
            product.setDiscount( productUpdateRequestDTO.getDiscount() );
        }

        return product;
    }

    @Override
    public ProductResponseDTO toResponseDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();

        productResponseDTO.setCategoryName( productCategoryName( product ) );
        productResponseDTO.setId( product.getId() );
        productResponseDTO.setName( product.getName() );
        productResponseDTO.setDescription( product.getDescription() );
        productResponseDTO.setPrice( product.getPrice() );
        productResponseDTO.setBrand( product.getBrand() );
        productResponseDTO.setStockQuantity( product.getStockQuantity() );
        productResponseDTO.setStatus( product.getStatus() );
        productResponseDTO.setImages( imageListToImageResponseDTOList( product.getImages() ) );
        productResponseDTO.setReviews( reviewListToReviewResponseDTOList( product.getReviews() ) );
        productResponseDTO.setDiscount( product.getDiscount() );

        return productResponseDTO;
    }

    @Override
    public List<ProductListDTO> toProductListDTOs(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductListDTO> list = new ArrayList<ProductListDTO>( products.size() );
        for ( Product product : products ) {
            list.add( toProductListDTO( product ) );
        }

        return list;
    }

    protected List<ImageResponseDTO> imageListToImageResponseDTOList(List<Image> list) {
        if ( list == null ) {
            return null;
        }

        List<ImageResponseDTO> list1 = new ArrayList<ImageResponseDTO>( list.size() );
        for ( Image image : list ) {
            list1.add( imageMapper.toImageResponseDTO( image ) );
        }

        return list1;
    }

    protected List<ReviewResponseDTO> reviewListToReviewResponseDTOList(List<Review> list) {
        if ( list == null ) {
            return null;
        }

        List<ReviewResponseDTO> list1 = new ArrayList<ReviewResponseDTO>( list.size() );
        for ( Review review : list ) {
            list1.add( reviewMapper.toReviewResponseDTO( review ) );
        }

        return list1;
    }

    private String productCategoryName(Product product) {
        Category category = product.getCategory();
        if ( category == null ) {
            return null;
        }
        return category.getName();
    }
}
