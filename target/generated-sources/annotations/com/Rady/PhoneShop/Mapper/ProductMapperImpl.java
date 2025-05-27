package com.Rady.PhoneShop.Mapper;

import com.Rady.PhoneShop.Dto.ProductDto;
import com.Rady.PhoneShop.Enitity.Product;
import com.Rady.PhoneShop.Service.ColorService;
import com.Rady.PhoneShop.Service.ModelService;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-27T09:07:58+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24 (Oracle Corporation)"
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
}
