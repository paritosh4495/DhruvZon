package com.Ecommerce.dhruvzon.exception;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CategoryAlreadyExistsException extends RuntimeException {
    public CategoryAlreadyExistsException(String message) {
        super(message);
    }
}
