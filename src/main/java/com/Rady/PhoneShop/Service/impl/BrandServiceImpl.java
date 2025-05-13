package com.Rady.PhoneShop.Service.impl;

import com.Rady.PhoneShop.Enitity.Brand;
import com.Rady.PhoneShop.Repository.BrandRepository;
import com.Rady.PhoneShop.Service.BrandService;

import com.Rady.PhoneShop.Service.util.PageUtil;
import com.Rady.PhoneShop.Service.util.spec.BrandFilter;
import com.Rady.PhoneShop.Service.util.spec.BrandSpec;
import com.Rady.PhoneShop.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

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

    @Override
    public Page<Brand> getBrands(Map<String, String> params)
    {
        BrandFilter brandFilter=new BrandFilter();
        if(params.containsKey("brandName"))
        {
           String name= params.get("brandName");
           brandFilter.setBrandName(name);
        }
        if (params.containsKey("id"))
        {
            String id=params.get("id");
            brandFilter.setId(Integer.parseInt(id));
        }

        //todo at to a function
        int pageLimit=PageUtil.DEFAULT_PAGE_LIMIT;
        if(params.containsKey(PageUtil.PAGE_LIMIT)){
            pageLimit=Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
        }
        int pageNumber=PageUtil.DEFAULT_PAGE_NUMBER;
        if(params.containsKey(PageUtil.PAGE_NUMBER)){
            pageNumber=Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
        }
        Pageable pageable= PageUtil.getPageable(pageNumber,pageLimit);

        BrandSpec brandSpec=new BrandSpec(brandFilter);



        Page<Brand> page = brandRepository.findAll(brandSpec,pageable);
         return page;
    }



/*    @Override
    public List<Brand> getBrands(Map<String, String> params)
    {
        BrandFilter brandFilter=new BrandFilter();
        if(params.containsKey("brandName"))
        {
            String name= params.get("brandName");
            brandFilter.setBrandName(name);
        }
        if (params.containsKey("id"))
        {
            String id=params.get("id");
            brandFilter.setId(Integer.parseInt(id));
        }


        BrandSpec brandSpec=new BrandSpec(brandFilter);



        return brandRepository.findAll(brandSpec);
    }*/
/*
    @Override
    public List<Brand> getBrands() {
        return brandRepository.findAll();
    }
*/

/*
    @Override
    public List<Brand> getBrands(String brandName) {
      */
/*  return brandRepository.findByBrandNameLike("%"+brandName+"%");*//*

        return brandRepository.findByBrandNameContaining(brandName);

    }
*/

}
