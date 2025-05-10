package com.Rady.PhoneShop.Service;

import com.Rady.PhoneShop.Enitity.Brand;

import java.util.List;

public interface BrandService {
    Brand create(Brand brand);

    Brand getBrandById(Integer id);
    Brand update(Integer id, Brand brand);
    List<Brand> getBrands();
    List<Brand>getBrands(String brandName);
}
