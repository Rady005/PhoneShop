package com.Rady.PhoneShop.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApiException{

    public ResourceNotFoundException( String resource ,Integer id) {
        super(HttpStatus.NOT_FOUND, String.format("%s with id %d not found",resource,id));
    }
}
