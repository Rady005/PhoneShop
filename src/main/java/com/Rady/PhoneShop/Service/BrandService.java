package com.Rady.PhoneShop.Service;

import com.Rady.PhoneShop.Enitity.Brand;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface BrandService {
    Brand create(Brand brand);

    Brand getBrandById(Integer id);
    Brand update(Integer id, Brand brand);
/*    List<Brand> getBrands();
    List<Brand>getBrands(String brandName);*/
    /*List<Brand> getBrands(Map<String,String> params);*/
    Page<Brand> getBrands(Map<String,String> params);
}
