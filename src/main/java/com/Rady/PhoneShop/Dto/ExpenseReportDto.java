package com.Rady.PhoneShop.Dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExpenseReportDto {
    private Integer productId;
    private String productName;
    private Integer totalNumberOfUnit;
    private BigDecimal totalAmount;
}
