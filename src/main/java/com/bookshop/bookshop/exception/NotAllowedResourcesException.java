package com.bookshop.bookshop.exception;

public class NotAllowedResourcesException extends RuntimeException { 

    public NotAllowedResourcesException() {
        super("User is not allowed to perform this type of action");
    }
    
}