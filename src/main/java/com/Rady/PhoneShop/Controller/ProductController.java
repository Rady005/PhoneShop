package com.Rady.PhoneShop.Controller;


import com.Rady.PhoneShop.Dto.PriceDto;
import com.Rady.PhoneShop.Dto.ProductDto;
import com.Rady.PhoneShop.Dto.ProductImportDto;
import com.Rady.PhoneShop.Enitity.Product;
import com.Rady.PhoneShop.Mapper.ProductMapper;

import com.Rady.PhoneShop.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequiredArgsConstructor
@CrossOrigin
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

    @PostMapping("/importProduct")
    public ResponseEntity<?> importProduct(@RequestBody @Valid ProductImportDto  productImportDto){
        productService.importProducts(productImportDto);
        return  ResponseEntity.ok().build();
    }

    @PostMapping("{id}/setSalePrice")
    public ResponseEntity<?>setSalePrice(@PathVariable Integer id,@RequestBody PriceDto priceDto){
        productService.setSalePrice(id,priceDto.getPrice());
        return ResponseEntity.ok().build();
    }
/*    @PostMapping("uploadProduct")
    public ResponseEntity<?>uploadProduct(@RequestParam("file") MultipartFile file){
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file to upload");
        }
        productService.uploadProduct(file);
        return ResponseEntity.ok().build();
    }*/
    @PostMapping("uploadProduct")
    public ResponseEntity<?>uploadProduct(@RequestParam("file") MultipartFile file){
        try {
            productService.uploadProduct(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok().build();
    }


}
