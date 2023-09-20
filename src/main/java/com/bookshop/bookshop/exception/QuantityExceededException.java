package com.bookshop.bookshop.exception;

public class QuantityExceededException extends RuntimeException { 

    public QuantityExceededException() {
        super("the quantity of product you need exceeds our resources");
    }
    
}