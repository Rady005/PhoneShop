package com.Rady.PhoneShop.Controller;


import com.Rady.PhoneShop.Dto.BrandDto;
import com.Rady.PhoneShop.Dto.ModelDto;
import com.Rady.PhoneShop.Dto.PageDto;
import com.Rady.PhoneShop.Enitity.Brand;
import com.Rady.PhoneShop.Enitity.Model;
import com.Rady.PhoneShop.Mapper.BrandMapper;
import com.Rady.PhoneShop.Mapper.ModelMapper;
import com.Rady.PhoneShop.Service.BrandService;
import com.Rady.PhoneShop.Service.ModelService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("brands")
public class BrandController {

    private final BrandService brandService;
    private final ModelService modelService;
    private final ModelMapper modelMapper;



    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody BrandDto brandDto){
        Brand brand = BrandMapper.INSTANCE.toBrand(brandDto);
        brand=brandService.create(brand);
        return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDto(brand));

    }
    @GetMapping("{id}")
    public ResponseEntity<?> getOneBranch(@PathVariable Integer id){
        Brand brand=brandService.getBrandById(id);
        return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDto(brand));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,
                                    @RequestBody BrandDto brandDTO  ){
        Brand brand=BrandMapper.INSTANCE.toBrand(brandDTO);
        brandService.update(id,brand);
        return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDto(brand));

    }

    @GetMapping
    public ResponseEntity<?> getBrands(@RequestParam Map<String, String> params){
/*        List<BrandDto>list  =brandService.getBrands(params)
                .stream()
                .map(BrandMapper.INSTANCE::toBrandDto)
                .collect(Collectors.toList());*/
        Page<Brand> brandPage=brandService.getBrands(params);
        PageDto pageDto=new PageDto(brandPage);
        return ResponseEntity.ok(pageDto);

    }
    @GetMapping("{id}/models")
    public ResponseEntity<?> getModelsByBrand(@PathVariable("id") Integer brandId){
        List<Model>brands=modelService.getModelByBrand(brandId);
        List<ModelDto>list= brands.stream()
                .map(this.modelMapper::toModelDto)
                .toList();
        return ResponseEntity.ok(list);
    }




    /*    @GetMapping
    public ResponseEntity<?> getAllBrands(){


        List<BrandDto> list=brandService.getBrands()
                .stream()
                .map(brand -> BrandMapper.INSTANCE.toBrandDto(brand))
                .collect(Collectors.toList());

        *//*return ResponseEntity.ok(brandService.getBrands());*//*
        return ResponseEntity.ok(list);
    }*/




}

