package com.Ecommerce.dhruvzon.service.product;

import com.Ecommerce.dhruvzon.dto.product.*;



import java.util.List;

public interface ProductService {

    ProductResponseDTO createProduct(ProductCreateRequestDTO productCreateRequestDTO);
    ProductResponseDTO updateProduct(Long id, ProductUpdateRequestDTO productUpdateRequestDTO);
    ProductDetailDTO getProductById(Long id);
    List<ProductResponseDTO> createBulkProducts(List<ProductCreateRequestDTO> productCreateRequestDTOList);
    List<ProductListDTO> getAllProducts(int page, int size);
    List<ProductListDTO> searchProducts(String keyword, int page, int size);
    List<ProductListDTO> getProductsByCategory(String category, int page, int size);
    List<ProductListDTO> getProductsByBrand(String brand, int page, int size);
    List<ProductResponseDTO> getProductByName(String name);
    List<ProductListDTO> getProductsByStatus(String status, int page, int size);
    List<ProductListDTO> getProductByBrandAndName(String brand, String name, int page, int size);
    List<ProductListDTO> getProductByCategoryAndStatus(String category, String status, int page, int size);
    List<ProductListDTO> getProductByCategoryAndBrand(String category, String brand, int page, int size);
    ProductResponseDTO updateProductStatus(Long id, String status);
    void deleteProduct(Long id);

    boolean checkProductAvailability(Long id, Integer quantity);


}
