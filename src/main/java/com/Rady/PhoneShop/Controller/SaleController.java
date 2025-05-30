package com.Rady.PhoneShop.Controller;

import com.Rady.PhoneShop.Dto.SaleDto;
import com.Rady.PhoneShop.Service.ProductService;
import com.Rady.PhoneShop.Service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping ("/sales")
public class SaleController
{
    private final SaleService saleService;
    @PostMapping
    public ResponseEntity<?> create(@RequestBody SaleDto saleDto){
        saleService.sell(saleDto);
        return ResponseEntity.ok().build();

    }

}
