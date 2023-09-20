package com.bookshop.bookshop.exception;

public class OrderForUserNotFoundException extends RuntimeException { 

    public OrderForUserNotFoundException(Long orderId, Long userId) {
        super("The order with id: '" + orderId + "' does not exist for user with id:" + userId);
    }
    
}