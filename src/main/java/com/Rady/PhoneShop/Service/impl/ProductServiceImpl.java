package com.Rady.PhoneShop.Service.impl;

import com.Rady.PhoneShop.Dto.ProductImportDto;
import com.Rady.PhoneShop.Enitity.Product;
import com.Rady.PhoneShop.Enitity.ProductImportHistory;
import com.Rady.PhoneShop.Mapper.ProductMapper;
import com.Rady.PhoneShop.Repository.ProductImportHistoryRepository;
import com.Rady.PhoneShop.Repository.ProductRepository;
import com.Rady.PhoneShop.Service.ProductService;
import com.Rady.PhoneShop.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductImportHistoryRepository productImportHistoryRepository;
    private final ProductMapper productMapper;

    @Override
    public Product create (Product product) {
        String name="%s %s".formatted(product.getModel().getModelName(),product.getColor().getColorName());
        product.setProductName(name);
        return productRepository.save(product);
    }

    @Override
    public Product getById (Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, String.format("Product with id %d not found", id)));
    }

    @Override
    public void importProducts (ProductImportDto productImportDto) {
        //update availble products units

        Product product=getById(productImportDto.getId());
        Integer availableUnit=0;
        if(product.getAvailableUnits()!=null){
            availableUnit=product.getAvailableUnits();
        }
        product.setAvailableUnits(availableUnit+ productImportDto.getImportUint());
        productRepository.save(product);


        //save product import history

        ProductImportHistory productImportHistory=productMapper.toProductImportHistory(productImportDto,product);
        productImportHistoryRepository.save(productImportHistory);

    }

    @Override
    public void setSalePrice (Integer id, BigDecimal salePrice) {
        Product product=getById(id);
        product.setSalePrice(salePrice);
        productRepository.save(product);

    }

    @Override
    public void validateStock (Integer productId, Integer number) {

    }
}
