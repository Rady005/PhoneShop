package com.Rady.PhoneShop.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorException {
    private HttpStatus status;
    private String message;


}
