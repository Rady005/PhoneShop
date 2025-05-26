package com.Rady.PhoneShop.Mapper;

import com.Rady.PhoneShop.Dto.ModelDto;
import com.Rady.PhoneShop.Enitity.Brand;
import com.Rady.PhoneShop.Enitity.Model;
import com.Rady.PhoneShop.Service.BrandService;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-26T13:34:46+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24 (Oracle Corporation)"
)
@Component
public class ModelMapperImpl implements ModelMapper {

    @Autowired
    private BrandService brandService;

    @Override
    public Model toModel(ModelDto Dto) {
        if ( Dto == null ) {
            return null;
        }

        Model model = new Model();

        model.setBrand( brandService.getBrandById( Dto.getBrandId() ) );
        model.setModelName( Dto.getModelName() );

        return model;
    }

    @Override
    public ModelDto toModelDto(Model model) {
        if ( model == null ) {
            return null;
        }

        ModelDto modelDto = new ModelDto();

        modelDto.setBrandId( modelBrandId( model ) );
        modelDto.setModelName( model.getModelName() );

        return modelDto;
    }

    private Integer modelBrandId(Model model) {
        Brand brand = model.getBrand();
        if ( brand == null ) {
            return null;
        }
        return brand.getId();
    }
}
