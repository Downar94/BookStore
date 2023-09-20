package com.bookshop.bookshop;

import java.util.Arrays;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bookshop.bookshop.exception.ApplicationException;
import com.bookshop.bookshop.exception.EntityNotFoundException;
import com.bookshop.bookshop.exception.ErrorResponse;
import com.bookshop.bookshop.exception.InvalidAccountStatusException;
import com.bookshop.bookshop.exception.NotAllowedResourcesException;
import com.bookshop.bookshop.exception.OrderForUserNotFoundException;
import com.bookshop.bookshop.exception.ProductNotFoundException;
import com.bookshop.bookshop.exception.ProductOrderAlreadyExistException;
import com.bookshop.bookshop.exception.QuantityExceededException;
import com.bookshop.bookshop.exception.ReviewNotFoundException;
import com.bookshop.bookshop.exception.UserNotFoundException;
import com.bookshop.bookshop.exception.UserWithNameNotFoundException;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class BookshopExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> ConstraintViolationExceptionHandler(ConstraintViolationException exception) {
        ErrorResponse error = new ErrorResponse(Arrays.asList("Constraint Violation: " + exception.getMessage()));  
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotAllowedResourcesException.class, ProductOrderAlreadyExistException.class, QuantityExceededException.class, InvalidAccountStatusException.class})
    public ResponseEntity<Object> NotAllowedResourcesExceptionHandler(RuntimeException exception) {
        ErrorResponse error = new ErrorResponse(Arrays.asList(exception.getMessage()));  
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> DataIntegrityViolationExceptionHandler(DataIntegrityViolationException exception) {
        ErrorResponse error = new ErrorResponse(Arrays.asList("Data Integrity Violation"));  
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> ResultDataAccessExceptionHandler(EmptyResultDataAccessException exception) {
        ErrorResponse error = new ErrorResponse(Arrays.asList("Resource does not exist"));  
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({EntityNotFoundException.class, UserNotFoundException.class, ProductNotFoundException.class, ReviewNotFoundException.class, UserWithNameNotFoundException.class, OrderForUserNotFoundException.class})
    public ResponseEntity<Object> NotFoundExceptionHandler(RuntimeException exception) {
        ErrorResponse error = new ErrorResponse(Arrays.asList(exception.getMessage()));  
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> AccessDeniedExceptionHandler(AccessDeniedException exception, WebRequest webRequest) {
        ErrorResponse error = new ErrorResponse(Arrays.asList("Access danied for these resources")); 
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Object> ApplicationExceptionHandler(ApplicationException exception, WebRequest webRequest){
        ErrorResponse error = new ErrorResponse(Arrays.asList(exception.getMessage()));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
