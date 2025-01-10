package com.Ecommerce.dhruvzon.exception;

import com.Ecommerce.dhruvzon.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleUserNotFoundException(UserNotFoundException e) {
        ApiResponse<String> apiResponse = new ApiResponse<>(null, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<String>> handleEmailAlreadyExistsException(EmailAlreadyExistsException e) {
        ApiResponse<String> apiResponse = new ApiResponse<>(null, e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleProductNotFoundException(ProductNotFoundException e) {
        ApiResponse<String> apiResponse = new ApiResponse<>(null, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleCategoryNotFoundException(CategoryNotFoundException e) {
        ApiResponse<String> apiResponse = new ApiResponse<>(null, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ApiResponse<String>> handleInvalidRequestException(InvalidRequestException e) {
        ApiResponse<String> apiResponse = new ApiResponse<>(null, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(ImageNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleImageNotFoundException(ImageNotFoundException e) {
        ApiResponse<String> apiResponse = new ApiResponse<>(null, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<String>> handleProductAlreadyExistsException(ProductAlreadyExistsException e) {
        ApiResponse<String> apiResponse = new ApiResponse<>(null, e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleReviewNotFoundException(ReviewNotFoundException e) {
        ApiResponse<String> apiResponse = new ApiResponse<>(null, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse<String>> handleUnauthorizedException(UnauthorizedException e) {
        ApiResponse<String> apiResponse = new ApiResponse<>(null, e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);
    }

    @ExceptionHandler(CartItemNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleCartItemNotFoundException(CartItemNotFoundException e) {
        ApiResponse<String> apiResponse = new ApiResponse<>(null, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleCartNotFoundException(CartNotFoundException e) {
        ApiResponse<String> apiResponse = new ApiResponse<>(null, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<String>> handleIllegalArgumentException(IllegalArgumentException e) {
        ApiResponse<String> apiResponse = new ApiResponse<>(null, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }


}
