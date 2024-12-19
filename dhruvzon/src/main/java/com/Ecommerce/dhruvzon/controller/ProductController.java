package com.Ecommerce.dhruvzon.controller;

import com.Ecommerce.dhruvzon.dto.product.*;
import com.Ecommerce.dhruvzon.response.ApiResponse;
import com.Ecommerce.dhruvzon.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // Create a new product ->WORKING
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> createProduct(@RequestBody ProductCreateRequestDTO productCreateRequestDTO) {
        ProductResponseDTO createdProduct = productService.createProduct(productCreateRequestDTO);
        ApiResponse<ProductResponseDTO> response = new ApiResponse<>(createdProduct, "Product created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Update an existing product -> WORKING
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> updateProduct(@PathVariable Long id, @RequestBody ProductUpdateRequestDTO productUpdateRequestDTO) {
        ProductResponseDTO updatedProduct = productService.updateProduct(id, productUpdateRequestDTO);
        ApiResponse<ProductResponseDTO> response = new ApiResponse<>(updatedProduct, "Product updated successfully");
        return ResponseEntity.ok(response);
    }

    // Get product by ID ->WORKING
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDetailDTO>> getProductById(@PathVariable Long id) {
        ProductDetailDTO product = productService.getProductById(id);
        ApiResponse<ProductDetailDTO> response = new ApiResponse<>(product, "Product fetched successfully");
        return ResponseEntity.ok(response);
    }

    // Bulk create products->WORKING
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add/bulk")
    public ResponseEntity<ApiResponse<List<ProductResponseDTO>>> createBulkProducts(@RequestBody List<ProductCreateRequestDTO> productCreateRequestDTOList) {
        List<ProductResponseDTO> createdProducts = productService.createBulkProducts(productCreateRequestDTOList);
        ApiResponse<List<ProductResponseDTO>> response = new ApiResponse<>(createdProducts, "Bulk products created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get all products with pagination ->WORKING
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<ProductListDTO>>> getAllProducts(@RequestParam int page, @RequestParam int size) {
        List<ProductListDTO> products = productService.getAllProducts(page, size);
        ApiResponse<List<ProductListDTO>> response = new ApiResponse<>(products, "All products fetched successfully");
        return ResponseEntity.ok(response);
    }

    // Search products by keyword ->WORKING
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<ProductListDTO>>> searchProducts(@RequestParam String keyword, @RequestParam int page, @RequestParam int size) {
        List<ProductListDTO> products = productService.searchProducts(keyword, page, size);
        ApiResponse<List<ProductListDTO>> response = new ApiResponse<>(products, "Products searched successfully");
        return ResponseEntity.ok(response);
    }

    // Get products by category ->NOT WORKING
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/category/{category}")
    public ResponseEntity<ApiResponse<List<ProductListDTO>>> getProductsByCategory(@PathVariable String category, @RequestParam int page, @RequestParam int size) {
        List<ProductListDTO> products = productService.getProductsByCategory(category, page, size);
        ApiResponse<List<ProductListDTO>> response = new ApiResponse<>(products, "Products by category fetched successfully");
        return ResponseEntity.ok(response);
    }

    // Get products by brand ->NOT WORKING
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/brand/{brand}")
    public ResponseEntity<ApiResponse<List<ProductListDTO>>> getProductsByBrand(@PathVariable String brand, @RequestParam int page, @RequestParam int size) {
        List<ProductListDTO> products = productService.getProductsByBrand(brand, page, size);
        ApiResponse<List<ProductListDTO>> response = new ApiResponse<>(products, "Products by brand fetched successfully");
        return ResponseEntity.ok(response);
    }

    // Get products by status ->WORKING
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<ProductListDTO>>> getProductsByStatus(@PathVariable String status, @RequestParam int page, @RequestParam int size) {
        List<ProductListDTO> products = productService.getProductsByStatus(status, page, size);
        ApiResponse<List<ProductListDTO>> response = new ApiResponse<>(products, "Products by status fetched successfully");
        return ResponseEntity.ok(response);
    }

    // Get products by brand and name  -> NOT WORKING
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/brand/{brand}/name/{name}")
    public ResponseEntity<ApiResponse<List<ProductListDTO>>> getProductByBrandAndName(@PathVariable String brand, @PathVariable String name, @RequestParam int page, @RequestParam int size) {
        List<ProductListDTO> products = productService.getProductByBrandAndName(brand, name, page, size);
        ApiResponse<List<ProductListDTO>> response = new ApiResponse<>(products, "Products by brand and name fetched successfully");
        return ResponseEntity.ok(response);
    }

    // Update product status -> WORKING
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> updateProductStatus(@PathVariable Long id, @RequestParam String status) {
        ProductResponseDTO updatedProduct = productService.updateProductStatus(id, status);
        ApiResponse<ProductResponseDTO> response = new ApiResponse<>(updatedProduct, "Product status updated successfully");
        return ResponseEntity.ok(response);
    }

    // -> WORKING

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse<List<ProductResponseDTO>>> getProductsByName(@PathVariable String name) {
        List<ProductResponseDTO> products = productService.getProductByName(name);
        ApiResponse<List<ProductResponseDTO>> response = new ApiResponse<>(products, "Products fetched by name successfully");
        return ResponseEntity.ok(response);
    }

    // -> WORKING
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/category/{category}/status/{status}")
    public ResponseEntity<ApiResponse<List<ProductListDTO>>> getProductsByCategoryAndStatus(
            @PathVariable String category,
            @PathVariable String status,
            @RequestParam int page,
            @RequestParam int size) {
        List<ProductListDTO> products = productService.getProductByCategoryAndStatus(category, status, page, size);
        ApiResponse<List<ProductListDTO>> response = new ApiResponse<>(products, "Products fetched by category and status successfully");
        return ResponseEntity.ok(response);
    }

    // -> NOT  WORKING

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/category/{category}/brand/{brand}")
    public ResponseEntity<ApiResponse<List<ProductListDTO>>> getProductsByCategoryAndBrand(
            @PathVariable String category,
            @PathVariable String brand,
            @RequestParam int page,
            @RequestParam int size) {
        List<ProductListDTO> products = productService.getProductByCategoryAndBrand(category, brand, page, size);
        ApiResponse<List<ProductListDTO>> response = new ApiResponse<>(products, "Products fetched by category and brand successfully");
        return ResponseEntity.ok(response);
    }




    // Delete product -> WORKING
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        ApiResponse<Void> response = new ApiResponse<>(null, "Product deleted successfully");
        return ResponseEntity.noContent().build();
    }

    // Check product availability -> DID NOT TEST
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}/availability")
    public ResponseEntity<ApiResponse<Boolean>> checkProductAvailability(@PathVariable Long id, @RequestParam int quantity) {
        boolean isAvailable = productService.checkProductAvailability(id, quantity);
        ApiResponse<Boolean> response = new ApiResponse<>(isAvailable, "Product availability checked successfully");
        return ResponseEntity.ok(response);
    }
}
