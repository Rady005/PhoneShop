package com.Rady.PhoneShop.Service;

import com.Rady.PhoneShop.Dto.ProductImportDto;
import com.Rady.PhoneShop.Enitity.Product;

import java.math.BigDecimal;

public interface ProductService {
    Product create(Product product);
    Product getById(Integer id);

    void importProducts(ProductImportDto productImportDto);
    void setSalePrice(Integer id, BigDecimal salePrice);

    void validateStock(Integer productId,Integer number);

}
