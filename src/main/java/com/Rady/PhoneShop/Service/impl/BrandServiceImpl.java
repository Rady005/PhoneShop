package com.Rady.PhoneShop.Service.impl;

import com.Rady.PhoneShop.Enitity.Brand;
import com.Rady.PhoneShop.Repository.BrandRepository;
import com.Rady.PhoneShop.Service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;
    @Override
    public Brand create(Brand brand) {
        return  brandRepository.save(brand);
    }
}
