package com.bookshop.bookshop.exception;

public class ProductOrderAlreadyExistException extends RuntimeException { 

    public ProductOrderAlreadyExistException(Long orderId, Long productId) {
        super("The product with id: '" + productId + "' already exist for order with id:" + orderId);
    }
    
}