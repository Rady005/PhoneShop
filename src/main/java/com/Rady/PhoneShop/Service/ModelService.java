package com.Rady.PhoneShop.Service;

import com.Rady.PhoneShop.Enitity.Model;

import java.util.List;

public interface ModelService {
    Model save(Model model);
    List<Model> getModelByBrand(Integer brandId);

}
