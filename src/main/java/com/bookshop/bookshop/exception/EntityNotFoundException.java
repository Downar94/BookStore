package com.bookshop.bookshop.exception;

public class EntityNotFoundException extends RuntimeException { 

    public EntityNotFoundException(Class<?> entity) { 
            super(entity.getSimpleName().toLowerCase() + "' does not exist");
    }

}