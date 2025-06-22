package com.Rady.PhoneShop.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class UploadResponse
{
    private String message;
    private List<RowError>errors;

    public UploadResponse(String message, List<RowError> errors) {
        this.message = message;
        this.errors = errors;
    }   
}
