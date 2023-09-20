package com.bookshop.bookshop.exception;

public class ProductNotFoundException extends RuntimeException { 

    public ProductNotFoundException(Long productId) {
        super("The product with id: '" + productId + "' does not exist");
    }
    
}