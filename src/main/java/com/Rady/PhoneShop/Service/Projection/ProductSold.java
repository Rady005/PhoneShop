package com.Rady.PhoneShop.Service.Projection;

import java.math.BigDecimal;

public interface ProductSold {

    Integer getProductId();
    String getProductName();
    Integer getNumberOfUnit();
    BigDecimal getTotalAmount();



}
