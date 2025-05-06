package com.Rady.PhoneShop.Repository;

import com.Rady.PhoneShop.Enitity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository <Brand, Integer> {


}
