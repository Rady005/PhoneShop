package com.Rady.PhoneShop.Service;

import com.Rady.PhoneShop.Dto.ProductReportDto;
import com.Rady.PhoneShop.Service.Projection.ProductSold;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {

    List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate);
    List<ProductReportDto> getProductReport(LocalDate startDate, LocalDate endDate);


}
