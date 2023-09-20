package com.bookshop.bookshop.exception;

public class ReviewNotFoundException extends RuntimeException { 

    public ReviewNotFoundException(Long reviewId) {
        super("The review with id: '" + reviewId + "' does not exist");
    }
    
}