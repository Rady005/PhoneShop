package com.Rady.PhoneShop.Mapper;

import com.Rady.PhoneShop.Dto.ProductDto;
import com.Rady.PhoneShop.Enitity.Product;
import com.Rady.PhoneShop.Service.ColorService;
import com.Rady.PhoneShop.Service.ModelService;
import com.Rady.PhoneShop.Service.ProductService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring",uses = {ModelService.class,ColorService.class})
public interface ProductMapper {
    @Mapping(target = "model", source = "modelId")
    @Mapping(target = "color", source = "colorId")
    Product toProduct(ProductDto productDto);


}