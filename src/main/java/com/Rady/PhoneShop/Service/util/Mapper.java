package com.Rady.PhoneShop.Service.util;

import com.Rady.PhoneShop.Dto.BrandDto;
import com.Rady.PhoneShop.Enitity.Brand;

public class Mapper {
    public static Brand toBrand(BrandDto brandDto){
        Brand brand = new Brand();
   /*     brand.setId(brandDto.getId());*/
        brand.setBrandName(brandDto.getBrandName());
        return brand;
    }

    public static BrandDto toBrandDto(Brand brand){
        BrandDto brandDto = new BrandDto();
        brandDto.setBrandName(brand.getBrandName());
        return brandDto;
    }
}
