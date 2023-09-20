package com.bookshop.bookshop.exception;

public class InvalidAccountStatusException extends RuntimeException { 

    public InvalidAccountStatusException(String accountStatus) {
        super("Your account has status:'" + accountStatus + "' please contact administrator for more informations");
    }
    
}