package com.Rady.PhoneShop.Controller;


import com.Rady.PhoneShop.Dto.ProductDto;
import com.Rady.PhoneShop.Enitity.Product;
import com.Rady.PhoneShop.Mapper.ProductMapper;

import com.Rady.PhoneShop.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/products")
public class ProductController {

    private final ProductMapper productMapper;
    private final ProductService productService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody ProductDto productDto){

        Product product=productMapper.toProduct(productDto);
        product=productService.create(product);
        return ResponseEntity.ok(product);
    }


}
