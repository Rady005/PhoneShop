package com.Rady.PhoneShop.Service;

import com.Rady.PhoneShop.Dto.ProductImportDto;
import com.Rady.PhoneShop.Enitity.Product;
import com.Rady.PhoneShop.exception.UploadResponse;
import org.springframework.web.multipart.MultipartFile;


import java.math.BigDecimal;

public interface ProductService {
    Product create(Product product);
    Product getById(Integer id);
    Product getByModelIdAndColorId(Long modelId, Long colorId);

    void importProducts(ProductImportDto productImportDto);
    void setSalePrice(Integer id, BigDecimal salePrice);

    void validateStock(Integer productId,Integer number);
    UploadResponse uploadProduct
            (MultipartFile file);


}
