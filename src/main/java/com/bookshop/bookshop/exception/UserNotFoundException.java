package com.bookshop.bookshop.exception;

public class UserNotFoundException extends RuntimeException { 

    public UserNotFoundException(Long userId) {
        super("The user with id: '" + userId + "' does not exist");
    }
    
}