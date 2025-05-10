package com.Rady.PhoneShop.Mapper;

import com.Rady.PhoneShop.Dto.BrandDto;
import com.Rady.PhoneShop.Enitity.Brand;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-09T15:10:18+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24 (Oracle Corporation)"
)
public class BrandMapperImpl implements BrandMapper {

    @Override
    public Brand toBrand(BrandDto Dto) {
        if ( Dto == null ) {
            return null;
        }

        Brand brand = new Brand();

        brand.setId( Dto.getId() );
        brand.setBrandName( Dto.getBrandName() );

        return brand;
    }

    @Override
    public BrandDto toBrandDto(Brand entity) {
        if ( entity == null ) {
            return null;
        }

        BrandDto brandDto = new BrandDto();

        brandDto.setId( entity.getId() );
        brandDto.setBrandName( entity.getBrandName() );

        return brandDto;
    }
}
