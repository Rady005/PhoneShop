package com.Rady.PhoneShop.Mapper;

import com.Rady.PhoneShop.Dto.ProductDto;
import com.Rady.PhoneShop.Dto.ProductImportDto;
import com.Rady.PhoneShop.Enitity.Product;
import com.Rady.PhoneShop.Enitity.ProductImportHistory;
import com.Rady.PhoneShop.Service.ColorService;
import com.Rady.PhoneShop.Service.ModelService;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring",uses = {ModelService.class,ColorService.class})
public interface ProductMapper {
    @Mapping(target = "model", source = "modelId")
    @Mapping(target = "color", source = "colorId")
    Product toProduct(ProductDto productDto);



    @Mapping(target = "dateImport", source = "productImportDto.importDate")
    @Mapping(target = "pricePerUnit", source = "productImportDto.importPrice")
    @Mapping(target = "product", source = "product")
    @Mapping(target = "importUnit", source = "productImportDto.importUint")
    @Mapping(target = "id", ignore = true)
    ProductImportHistory toProductImportHistory(ProductImportDto productImportDto,Product product);
}