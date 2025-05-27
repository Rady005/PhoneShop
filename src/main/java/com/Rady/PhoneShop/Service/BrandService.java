package com.Rady.PhoneShop.Service;

import com.Rady.PhoneShop.Enitity.Brand;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.dialect.LobMergeStrategy;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface BrandService {
    Brand create(Brand brand);

    Brand getBrandById(Integer id);
    Brand update(Integer id, Brand brand);
    Page<Brand> getBrands(Map<String,String> params);
}
