package com.Rady.PhoneShop.Controller;


import com.Rady.PhoneShop.Dto.ExpenseReportDto;
import com.Rady.PhoneShop.Dto.ProductReportDto;

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


/*use query method to get product sold
    @GetMapping("{startDate}/{endDate}")
    public ResponseEntity<?> productSold(@DateTimeFormat(pattern = "yyyy-mm-dd") @PathVariable LocalDate startDate, @DateTimeFormat(pattern = "yyyy-mm-dd") @PathVariable LocalDate endDate){
        List<ProductSold> productSold=reportService.getProductSold(startDate,endDate);
        return ResponseEntity.ok(productSold);
    }
*/

    @GetMapping("{startDate}/{endDate}")
    public ResponseEntity<?> productSold(@DateTimeFormat(pattern = "yyyy-mm-dd") @PathVariable LocalDate startDate, @DateTimeFormat(pattern = "yyyy-mm-dd") @PathVariable LocalDate endDate){
        List<ProductReportDto> productSold=reportService.getProductReport(startDate,endDate);
        return ResponseEntity.ok(productSold);
    }
    @GetMapping("expense/{startDate}/{endDate}")
    public ResponseEntity<?> expenseReport(@DateTimeFormat(pattern = "yyyy-mm-dd") @PathVariable LocalDate startDate, @DateTimeFormat(pattern = "yyyy-mm-dd") @PathVariable LocalDate endDate){
        List<ExpenseReportDto> expenseReport = reportService.getExpenseReport(startDate, endDate);
        return ResponseEntity.ok(expenseReport);
    }




}
