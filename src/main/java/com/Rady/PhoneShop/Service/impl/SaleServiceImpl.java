package com.Rady.PhoneShop.Service.impl;

import com.Rady.PhoneShop.Dto.ProductSoldDto;
import com.Rady.PhoneShop.Dto.SaleDto;
import com.Rady.PhoneShop.Enitity.Product;
import com.Rady.PhoneShop.Enitity.Sale;
import com.Rady.PhoneShop.Enitity.SaleDetail;
import com.Rady.PhoneShop.Repository.ProductRepository;
import com.Rady.PhoneShop.Repository.SaleDetailRepository;
import com.Rady.PhoneShop.Repository.SaleRepository;
import com.Rady.PhoneShop.Service.ProductService;
import com.Rady.PhoneShop.Service.SaleService;
import com.Rady.PhoneShop.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;
    private void validate(SaleDto saleDto) {
/*        List<Integer>productIds = saleDto.getProductSoldDtos().stream()
                .map(ProductSoldDto::getProductId)
                .toList();
        //validate that the productIds are not empty
        productIds.forEach(productService::getById);

        List<Product>products= productRepository.findAllById(productIds);
        Map<Integer,Product>productMap= products.stream().collect(Collectors.toMap(Product::getId, Function.identity()))
        //validate that the products are available in stock
        saleDto.getProducts()
                .forEach(ps->{
                   Product product= productMap.get(ps.getProductId());
                    if(product.getAvailableUnits()<ps.getNumberOfUnit()){
                        throw new ApiException(HttpStatus.BAD_REQUEST, "Product with id " + ps.getProductId() + " is not available in stock. Available units: " + product.getAvailableUnits());
                    }
                });*/

        saleDto.getProducts().forEach(ps->{
            Product product= productService.getById(ps.getProductId());
            if(product.getAvailableUnits()<ps.getNumberOfUnit()){
                throw new ApiException(HttpStatus.BAD_REQUEST, "Product with id " + ps.getProductId() + " is not available in stock. Available units: " + product.getAvailableUnits());
            }
        });


    }

    public void saveSale(SaleDto saleDto) {

        Sale sale=new Sale();
        sale.setSoldDate(saleDto.getSoldDate());
        saleRepository.save(sale);

        saleDto.getProducts().forEach(ps->{
            SaleDetail saleDetail=new SaleDetail();
            saleDetail.setAmount(null);
        });
        SaleDetail saleDetail=new SaleDetail();
        saleDetail.setSale(sale);
        saleDetailRepository.save(saleDetail);



    }

    @Override
    public void sell (SaleDto saleDto) {
        //validate the saleDto
//        validate(saleDto);
        //save the saleDto to the database

        List<Integer>productIds=saleDto.getProducts().stream()
                .map(ProductSoldDto ::getProductId)
                .toList();

        productIds.forEach(productService::getById);
        List<Product>products= productRepository.findAllById(productIds);
        Map<Integer,Product> productMap= products.stream().collect(Collectors.toMap(Product::getId, Function.identity()));


        Sale sale=new Sale();
        sale.setActive(true);
        sale.setSoldDate(saleDto.getSoldDate());
        saleRepository.save(sale);

        System.out.println("Product ids: "+productIds);
        System.out.println("sale id: "+sale.getId());
        saleDto.getProducts().forEach(ps->{
            Product product= productMap.get(ps.getProductId());
            if (product.getSalePrice() == null) {
                throw new ApiException(HttpStatus.BAD_REQUEST,
                        "Product with id " + ps.getProductId() + " has no sale price set");
            }

            SaleDetail saleDetail=new SaleDetail();

            System.out.println("Product Id in loop:  "+ps.getProductId());
            System.out.println("Product price in loop : "+productMap.get(ps.getProductId()).getSalePrice());
            saleDetail.setAmount(productMap.get(ps.getProductId()).getSalePrice().multiply(new java.math.BigDecimal(ps.getNumberOfUnit())));
            saleDetail.setProduct(product);
            saleDetail.setSale(sale);
            saleDetail.setUnit(ps.getNumberOfUnit());
            saleDetailRepository.save(saleDetail);

            //update the product available units in stock

            Integer availableUnits=product.getAvailableUnits()-ps.getNumberOfUnit();
            product.setAvailableUnits(availableUnits);
            productRepository.save(product);
        });

    }
    @Override
    public Sale getById (Integer id) {
        return saleRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Sale with id " + id + " not found"));
    }

    @Override
    public void cancelSale (Integer saleId) {


        //update sale to status
        Sale sale =getById(saleId);
        if (!sale.isActive()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Sale with id " + saleId + " is already cancelled");
        }
        sale.setActive(false);
        saleRepository.save(sale);

        //update stock
        List<SaleDetail> saleDetails = saleDetailRepository.findBySaleId(saleId);
        List<Integer> productIds = saleDetails.stream()
                .map(sd -> sd.getProduct().getId()).toList();

        List<Product>products= productRepository.findAllById(productIds);
        Map<Integer,Product> productMap= products.stream().collect(Collectors.toMap(Product::getId, Function.identity()));

        saleDetails.forEach(sd->{
            Product product = productMap.get(sd.getProduct().getId());
            product.setAvailableUnits(product.getAvailableUnits() + sd.getUnit());
            productRepository.save(product);

        });
    }

}