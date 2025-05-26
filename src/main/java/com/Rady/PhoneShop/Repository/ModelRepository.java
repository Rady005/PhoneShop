package com.Rady.PhoneShop.Repository;

import com.Rady.PhoneShop.Enitity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepository extends JpaRepository <Model, Integer> {
    List<Model> findByBrandId(Integer id);



}
