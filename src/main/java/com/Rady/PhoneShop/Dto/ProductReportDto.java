package com.Rady.PhoneShop.Dto;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class ProductReportDto {

    Integer productId;
    String productName;
    Integer numberOfUnit;
    BigDecimal amount;
}
