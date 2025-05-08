package com.Rady.PhoneShop.Service.impl;

import com.Rady.PhoneShop.Enitity.Brand;
import com.Rady.PhoneShop.Repository.BrandRepository;
import com.Rady.PhoneShop.Service.BrandService;

import com.Rady.PhoneShop.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;
    @Override
    public Brand create(Brand brand) {
        return  brandRepository.save(brand);
    }
    @Override
    public Brand getBrandById(Integer id) {
        return brandRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Brand",id));
    }

    @Override
    public Brand update(Integer id,Brand brandUpdate) {
        Brand brand=getBrandById(id);
        brand.setBrandName(brandUpdate.getBrandName());
        return brandRepository.save(brand);
    }
}
