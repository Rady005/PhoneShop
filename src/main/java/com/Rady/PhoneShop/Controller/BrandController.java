package com.Rady.PhoneShop.Controller;


import com.Rady.PhoneShop.Dto.BrandDto;
import com.Rady.PhoneShop.Enitity.Brand;
import com.Rady.PhoneShop.Mapper.BrandMapper;
import com.Rady.PhoneShop.Service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("brands")
public class BrandController {
    @Autowired
    private BrandService brandService;
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
    public ResponseEntity<?> getAllBrands(){


        List<BrandDto> list=brandService.getBrands()
                .stream()
                .map(brand -> BrandMapper.INSTANCE.toBrandDto(brand))
                .collect(Collectors.toList());

        /*return ResponseEntity.ok(brandService.getBrands());*/
        return ResponseEntity.ok(list);
    }

    @GetMapping("filter")
    public ResponseEntity<?> getBrandsByName(@RequestParam("brandName") String brandName){
        List<BrandDto>list  =brandService.getBrands(brandName)
                .stream()
                .map(brand -> BrandMapper.INSTANCE.toBrandDto(brand))
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);


    }




}
