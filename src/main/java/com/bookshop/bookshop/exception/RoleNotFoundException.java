package com.bookshop.bookshop.exception;

public class RoleNotFoundException extends RuntimeException { 

    public RoleNotFoundException(String roleName) {
        super("The role with the name: '" + roleName + "' does not exist");
    }
    
}