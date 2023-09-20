package com.bookshop.bookshop.exception;

public class ContactNotFoundException extends RuntimeException { 

    public ContactNotFoundException(Long userId) {
        super("The for user with id: '" + userId + "' does not exist");
    }
    
}