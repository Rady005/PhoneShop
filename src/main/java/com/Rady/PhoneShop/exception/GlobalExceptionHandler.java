package com.Rady.PhoneShop.exception;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler( ApiException.class)
    public ResponseEntity<?> handleException(ApiException e){
       ErrorException errorException=new ErrorException(e.getStatus(),e.getMessage());
        return ResponseEntity.status(e.getStatus())
                .body(errorException);

    }
}
