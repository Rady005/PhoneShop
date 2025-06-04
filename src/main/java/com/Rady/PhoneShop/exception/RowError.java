package com.Rady.PhoneShop.exception;

import lombok.Data;

@Data
public class RowError {
    private int row;
    private String errorMessage;

    public RowError(int row, String errorMessage) {
        this.row = row;
        this.errorMessage = errorMessage;
    }
}
