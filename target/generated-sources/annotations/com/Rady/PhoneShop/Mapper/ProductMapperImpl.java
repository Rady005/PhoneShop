package com.Rady.PhoneShop.Mapper;

import com.Rady.PhoneShop.Dto.ProductDto;
import com.Rady.PhoneShop.Dto.ProductImportDto;
import com.Rady.PhoneShop.Enitity.Product;
import com.Rady.PhoneShop.Enitity.ProductImportHistory;
import com.Rady.PhoneShop.Service.ColorService;
import com.Rady.PhoneShop.Service.ModelService;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-28T13:13:55+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Autowired
    private ModelService modelService;
    @Autowired
    private ColorService colorService;

    @Override
    public Product toProduct(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setModel( modelService.getById( productDto.getModelId() ) );
        product.setColor( colorService.getById( productDto.getColorId() ) );

        return product;
    }

    @Override
    public ProductImportHistory toProductImportHistory(ProductImportDto productImportDto, Product product) {
        if ( productImportDto == null && product == null ) {
            return null;
        }

        ProductImportHistory productImportHistory = new ProductImportHistory();

        if ( productImportDto != null ) {
            if ( productImportDto.getImportDate() != null ) {
                productImportHistory.setDateImport( productImportDto.getImportDate().atStartOfDay() );
            }
            productImportHistory.setPricePerUnit( productImportDto.getImportPrice() );
            if ( productImportDto.getImportUint() != null ) {
                productImportHistory.setImportUnit( String.valueOf( productImportDto.getImportUint() ) );
            }
        }
        productImportHistory.setProduct( product );

        return productImportHistory;
    }
}
