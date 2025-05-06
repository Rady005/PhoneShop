package com.Rady.PhoneShop.Controller;


import com.Rady.PhoneShop.Dto.BrandDto;
import com.Rady.PhoneShop.Enitity.Brand;
import com.Rady.PhoneShop.Service.BrandService;
import com.Rady.PhoneShop.Service.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("brands")
public class BrandController {
    @Autowired
    private BrandService brandService;
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody BrandDto brandDto){
/*        Brand brand = Mapper.toBrand(brandDto);
        brand = brandService.create(brand);*/
        Brand brand=Mapper.toBrand(brandDto);
        brand=brandService.create(brand);


        return ResponseEntity.ok(Mapper.toBrandDto(brand));

    }


}
