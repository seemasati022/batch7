package com.tejait.batch7.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalException {
/*
* create a custom exception class and extend to runtime exception
* create errrodetails class and create required args constructor
* create global exception class and annotate with @rest-controllerAdvice
* now sb will take care to handle all exceptions in the project using this class
* create methods in global class and annotate with @ExceptionHandler(T) and provide the class
* if our custom class is provided -> specific exception is called
* if exception class is provided in T -> common exception is called.
*
* */
    @ExceptionHandler(UserNotAvailableException.class)
    public ResponseEntity<ErrorDetails> userNotFound(){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),309,"no emp found with given details");
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> invalidInput(){
        return new ResponseEntity<>(
                new ErrorDetails(new Date(),490,"please check the data"),
                HttpStatus.BAD_REQUEST
        );
    }
}
