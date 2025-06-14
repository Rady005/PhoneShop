package com.Rady.PhoneShop.Config;


import lombok.*;

@Getter
@AllArgsConstructor (access = AccessLevel.PRIVATE)
public enum PermissionEnum {
    BRAND_WRITE("brand:write"),
    BRAND_READ("brand:read"),
    MODEL_WRITE("model:write"),
    MODEL_READ("model:write");

    private String description;
/*    private PermissionEnum(String desc){
        this.description=desc;
    }*/

}
