package com.Rady.PhoneShop.Mapper;

import com.Rady.PhoneShop.Dto.BrandDto;
import com.Rady.PhoneShop.Enitity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandMapper {
    //Mappers.getMapper(BrandMapper.class);
    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);
    Brand toBrand(BrandDto Dto);
    BrandDto toBrandDto(Brand entity);

}
