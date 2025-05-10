package com.Rady.PhoneShop.Repository;

import com.Rady.PhoneShop.Enitity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository <Brand, Integer> {
    List<Brand> findByBrandNameLike(String brandName);
    List<Brand> findByBrandNameContaining(String brandName);

}
