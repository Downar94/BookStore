package com.bookshop.bookshop.exception;

public class UserWithNameNotFoundException extends RuntimeException { 

    public UserWithNameNotFoundException(String userName) {
        super("The user with username: '" + userName + "' does not exist");
    }
    
}