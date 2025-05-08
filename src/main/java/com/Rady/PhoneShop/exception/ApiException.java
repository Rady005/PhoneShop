package com.Rady.PhoneShop.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
public class ApiException extends RuntimeException{


    private final HttpStatus status;
    private final String message;

}
