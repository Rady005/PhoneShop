package com.Rady.PhoneShop.Config.Security;


import lombok.*;

@Getter
@AllArgsConstructor (access = AccessLevel.PRIVATE)
public enum PermissionEnum {
    BRAND_WRITE("brand:write"),
    BRAND_READ("brand:read"),
    MODEL_WRITE("model:write"),
    MODEL_READ("model:write"),
    PRODUCT_WRITE("product:write");


    private String description;


}
