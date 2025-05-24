package com.Rady.PhoneShop.Mapper;


import com.Rady.PhoneShop.Dto.ModelDto;

import com.Rady.PhoneShop.Enitity.Model;
import com.Rady.PhoneShop.Service.BrandService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",uses={BrandService.class})
public interface ModelMapper {
    ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);
    @Mapping(target = "brand", source = "brandId")
    Model toModel(ModelDto Dto);

    @Mapping(target = "brandId", source = "brand.id")
    ModelDto toModelDto(Model model);

/*    default Brand toBrand(Integer brId){
        Brand brand=new Brand();
        brand.setId(brId);
        return brand;
    }*/

}
