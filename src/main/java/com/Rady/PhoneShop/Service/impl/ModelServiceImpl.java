package com.Rady.PhoneShop.Service.impl;

import com.Rady.PhoneShop.Enitity.Model;
import com.Rady.PhoneShop.Repository.ModelRepository;
import com.Rady.PhoneShop.Service.BrandService;
import com.Rady.PhoneShop.Service.ModelService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class ModelServiceImpl implements ModelService {

    private ModelRepository modelRepository;

    private BrandService brandService;
    @Override
    public Model save(Model model) {
        Integer brandId = model.getBrand().getId();
        brandService.getBrandById(brandId);
        return modelRepository.save(model);
    }



    @Override
    public List<Model> getModelByBrand (Integer brandId) {
        return modelRepository.findByBrandId(brandId);
    }

    @Override
    public Model getById (Integer id) {
        return modelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Model not found with id: " + id));
    }


}
