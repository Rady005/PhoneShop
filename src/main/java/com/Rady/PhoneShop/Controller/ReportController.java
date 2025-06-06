package com.Rady.PhoneShop.Controller;


import com.Rady.PhoneShop.Service.Projection.ProductSold;
import com.Rady.PhoneShop.Service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping ("/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;
    @GetMapping("{startDate}/{endDate}")
    public ResponseEntity<?> productSold(@DateTimeFormat(pattern = "yyyy-mm-dd") @PathVariable LocalDate startDate, @DateTimeFormat(pattern = "yyyy-mm-dd") @PathVariable LocalDate endDate){
        List<ProductSold> productSold=reportService.getProductSold(startDate,endDate);
        return ResponseEntity.ok(productSold);

    }


}
