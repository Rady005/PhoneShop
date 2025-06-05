package com.Rady.PhoneShop.Controller;

import com.Rady.PhoneShop.Dto.SaleDto;
import com.Rady.PhoneShop.Service.ProductService;
import com.Rady.PhoneShop.Service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("{saleId}/cancel")
    public ResponseEntity<?> cancelSale(@PathVariable Integer saleId){
        saleService.cancelSale(saleId);

        return ResponseEntity.ok().build();
    }

}
