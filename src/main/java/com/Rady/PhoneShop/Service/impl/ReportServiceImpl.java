package com.Rady.PhoneShop.Service.impl;

import com.Rady.PhoneShop.Repository.SaleRepository;
import com.Rady.PhoneShop.Service.Projection.ProductSold;
import com.Rady.PhoneShop.Service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final SaleRepository saleRepository;

    @Override
    public List<ProductSold> getProductSold (LocalDate startDate, LocalDate endDate) {
        return saleRepository.findProductSold(startDate,endDate);
    }
}
