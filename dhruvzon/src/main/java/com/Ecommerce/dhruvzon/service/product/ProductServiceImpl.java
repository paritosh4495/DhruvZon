package com.Ecommerce.dhruvzon.service.product;

import com.Ecommerce.dhruvzon.dto.product.*;
import com.Ecommerce.dhruvzon.enums.ProductStatus;
import com.Ecommerce.dhruvzon.exception.ProductAlreadyExistsException;
import com.Ecommerce.dhruvzon.exception.ProductNotFoundException;
import com.Ecommerce.dhruvzon.mapper.ProductMapper;
import com.Ecommerce.dhruvzon.model.Category;
import com.Ecommerce.dhruvzon.model.Product;
import com.Ecommerce.dhruvzon.repository.CategoryRepository;
import com.Ecommerce.dhruvzon.repository.ProductRepository;
import com.Ecommerce.dhruvzon.service.category.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;
    @Override
    public ProductResponseDTO createProduct(ProductCreateRequestDTO productCreateRequestDTO) {
        // Check if the product with the same name already exists

        if(productRepository.existsByNameAndBrand(productCreateRequestDTO.getName(), productCreateRequestDTO.getBrand())){
            throw new ProductAlreadyExistsException("Product with the same name already exists");
        }

        // Fetch the category based on the category name

        // Check if the category exists or create a new one using the category name
        Category category = categoryRepository.findByName(productCreateRequestDTO.getCategory())
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setName(productCreateRequestDTO.getCategory());
                    return categoryRepository.save(newCategory); // Save the new category
                });

        Product product = productMapper.toProduct(productCreateRequestDTO);
        product.setCategory(category);
        product.setStatus(ProductStatus.ACTIVE);
        product.setDiscount(BigDecimal.ZERO);

        productRepository.save(product);
        return productMapper.toResponseDTO(product);


    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductUpdateRequestDTO productUpdateRequestDTO) {
        // Fetch the existing product
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        // Check if a product with the same name already exists (for updates)
        if (productUpdateRequestDTO.getName() != null && !productUpdateRequestDTO.getName().equals(existingProduct.getName())) {
            if (productRepository.existsByName(productUpdateRequestDTO.getName())) {
                throw new ProductAlreadyExistsException("Product with the same name already exists");
            }
        }
        // If category is updated, validate and set it
        if (productUpdateRequestDTO.getCategory() != null) {
            Category category = categoryRepository.findByName(productUpdateRequestDTO.getCategory())
                    .orElseGet(() -> {
                        Category newCategory = new Category();
                        newCategory.setName(productUpdateRequestDTO.getCategory());
                        return categoryRepository.save(newCategory); // Save the new category
                    });
            existingProduct.setCategory(category);
        }

        if (productUpdateRequestDTO.getName() != null) {
            existingProduct.setName(productUpdateRequestDTO.getName());
        }

        if (productUpdateRequestDTO.getDescription() != null) {
            existingProduct.setDescription(productUpdateRequestDTO.getDescription());
        }

        if (productUpdateRequestDTO.getPrice() != null) {
            existingProduct.setPrice(productUpdateRequestDTO.getPrice());
        }
        if (productUpdateRequestDTO.getBrand() != null) {
            existingProduct.setBrand(productUpdateRequestDTO.getBrand());
        }

        if (productUpdateRequestDTO.getStockQuantity() != null) {
            existingProduct.setStockQuantity(productUpdateRequestDTO.getStockQuantity());
        }

        if (productUpdateRequestDTO.getDiscount() != null) {
            existingProduct.setDiscount(productUpdateRequestDTO.getDiscount());
        }

        // Save the updated product back to the repository
        Product updatedProduct  = productRepository.save(existingProduct);

        // Map the updated product entity to a response DTO
        return productMapper.toResponseDTO(updatedProduct);

    }

    @Override
    public ProductDetailDTO getProductById(Long id) {
        // Fetch the product by ID
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        return productMapper.toProductDetailDTO(product);
    }

    @Override
    public List<ProductResponseDTO> createBulkProducts(List<ProductCreateRequestDTO> productCreateRequestDTOList) {
        List<Product> productsToSave = new ArrayList<>();

        List<ProductResponseDTO> productResponseDTOList = new ArrayList<>();

        for(ProductCreateRequestDTO dto : productCreateRequestDTOList){
            // Check if the product with the same name and brand already exists
            if (productRepository.existsByNameAndBrand(dto.getName(), dto.getBrand())) {
                throw new ProductAlreadyExistsException("Product with name '" + dto.getName() + "' and brand '" + dto.getBrand() + "' already exists.");
            }

            // Fetch the category based on the category name
            Category category = categoryRepository.findByName(dto.getCategory())
                    .orElseGet(() -> {
                        Category newCategory = new Category();
                        newCategory.setName(dto.getCategory());
                        return categoryRepository.save(newCategory); // Save the new category
                    });
            // Map the DTO to Product entity
            Product product = productMapper.toProduct(dto);
            product.setCategory(category);
            product.setStatus(ProductStatus.ACTIVE); // Set default status to ACTIVE
            product.setDiscount(BigDecimal.ZERO); // Set default discount to 0

            // Add the product to the list of products to be saved
            productsToSave.add(product);
        }
        // Save all products in bulk
        List<Product> savedProducts = productRepository.saveAll(productsToSave);

        // Map saved products to DTOs and add to the response list
        for (Product savedProduct : savedProducts) {
            productResponseDTOList.add(productMapper.toResponseDTO(savedProduct));
        }

        return productResponseDTOList;
    }

    @Override
    public List<ProductListDTO> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Product> products = productRepository.findAll(pageable).getContent();
        return products.stream()
                .map(productMapper::toProductListDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductListDTO> searchProducts(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Product> products = productRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword, pageable);
        return products.stream()
                .map(productMapper::toProductListDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductListDTO> getProductsByCategory(String category, int page, int size) {

        Category cat = categoryService.getCategoryByName(category);
        Pageable pageable = PageRequest.of(page, size);
        List<Product> products = productRepository.findByCategoryName(category, pageable);
        return productMapper.toProductListDTOs(products);
    }

    @Override
    public List<ProductListDTO> getProductsByBrand(String brand, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        List<Product> products = productRepository.findByBrand(brand, pageable);
        System.out.println(products.stream().toList() + " ");
        return productMapper.toProductListDTOs(products);
    }

    @Override
    public List<ProductResponseDTO> getProductByName(String name) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(name);
        return products.stream()
                .map(productMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductListDTO> getProductsByStatus(String status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        ProductStatus productStatus = ProductStatus.valueOf(status.toUpperCase());
        List<Product> products = productRepository.findByStatus(productStatus, pageable);
        return products.stream()
                .map(productMapper::toProductListDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductListDTO> getProductByBrandAndName(String brand, String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Product> products = productRepository.findByBrandAndNameContainingIgnoreCase(brand, name, pageable);
        return productMapper.toProductListDTOs(products);
    }

    @Override
    public List<ProductListDTO> getProductByCategoryAndStatus(String category, String status, int page, int size) {
        Category cat = categoryService.getCategoryByName(category);
        Pageable pageable = PageRequest.of(page, size);
        ProductStatus productStatus = ProductStatus.valueOf(status.toUpperCase());
        List<Product> products = productRepository.findByCategoryNameAndStatus(category, productStatus, pageable);
        return productMapper.toProductListDTOs(products);
    }

    @Override
    public List<ProductListDTO> getProductByCategoryAndBrand(String category, String brand, int page, int size) {
        Category cat = categoryService.getCategoryByName(category);
        Pageable pageable = PageRequest.of(page, size);
        List<Product> products = productRepository.findByCategoryNameAndBrand(category, brand, pageable);
        return productMapper.toProductListDTOs(products);
    }

    @Override
    public ProductResponseDTO updateProductStatus(Long id, String status) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        ProductStatus productStatus = ProductStatus.valueOf(status.toUpperCase());
        product.setStatus(productStatus);

        productRepository.save(product);
        return productMapper.toResponseDTO(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        productRepository.delete(product);
    }

    @Override
    public boolean checkProductAvailability(Long id, Integer quantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        return product.getStockQuantity() >= quantity;
    }


}
