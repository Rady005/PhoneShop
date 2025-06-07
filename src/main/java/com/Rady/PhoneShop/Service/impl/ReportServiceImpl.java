package com.Rady.PhoneShop.Service.impl;

import com.Rady.PhoneShop.Dto.ProductReportDto;
import com.Rady.PhoneShop.Enitity.Product;
import com.Rady.PhoneShop.Enitity.SaleDetail;
import com.Rady.PhoneShop.Repository.ProductRepository;
import com.Rady.PhoneShop.Repository.SaleDetailRepository;
import com.Rady.PhoneShop.Repository.SaleRepository;
import com.Rady.PhoneShop.Service.Projection.ProductSold;
import com.Rady.PhoneShop.Service.ReportService;
import com.Rady.PhoneShop.Service.util.spec.SaleDetailFilter;
import com.Rady.PhoneShop.Service.util.spec.SaleDetailSpec;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import java.util.stream.Collectors;


@Data
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;
    private final ProductRepository productRepository;

    @Override
    public List<ProductSold> getProductSold (LocalDate startDate, LocalDate endDate) {
        return saleRepository.findProductSold(startDate,endDate);
    }

    @Override
    public List<ProductReportDto> getProductReport (LocalDate startDate, LocalDate endDate) {
        List<ProductReportDto>list=new ArrayList<>();

        SaleDetailFilter saleDetailFilter=new SaleDetailFilter();
        saleDetailFilter.setStartDate(startDate);
        saleDetailFilter.setEndDate(endDate);
        Specification<SaleDetail>spec=new SaleDetailSpec(saleDetailFilter);
        List<SaleDetail>saleDetails= saleDetailRepository.findAll(spec);

        List<Integer> productIds = saleDetails.stream().map(sd -> sd.getProduct().getId()).toList();

        Map<Integer, Product> productMap = productRepository.findAllById(productIds).stream().collect(Collectors.toMap(Product :: getId, Function.identity()));

        Map<Product,List<SaleDetail>>sellDetailMap= saleDetails.stream().
                                                    collect(Collectors.groupingBy(SaleDetail::getProduct));
       for(var entry:sellDetailMap.entrySet()){
           Product product = productMap.get(entry.getKey().getId());
           List<SaleDetail> sdList = entry.getValue();


           Integer unit = sdList.stream().map(SaleDetail :: getUnit).reduce(0, Integer :: sum);

/*
           Double reduce = sdList.stream().
                   map(sd -> sd.getUnit() * sd.getAmount().doubleValue())
                   .reduce(0d, Double :: sum);

*/
           double totalAmount = sdList.stream()
                   .mapToDouble(sd -> sd.getUnit() * sd.getAmount().doubleValue()).sum();


           ProductReportDto productReportDto=new ProductReportDto();
            productReportDto.setProductId(entry.getKey().getId());
            productReportDto.setNumberOfUnit(unit);

            productReportDto.setProductName(product.getProductName());
            productReportDto.setAmount(BigDecimal.valueOf(totalAmount));
            list.add(productReportDto);

       }

        return list;
    }
}
