package com.Rady.PhoneShop.Service;

import com.Rady.PhoneShop.Enitity.Brand;

public interface BrandService {
    Brand create(Brand brand);

    Brand getBrandById(Integer id);
    Brand update(Integer id, Brand brand);
}
