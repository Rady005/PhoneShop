package com.Rady.PhoneShop.Repository;

import com.Rady.PhoneShop.Enitity.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SaleDetailRepository extends JpaRepository <SaleDetail,Integer>{

    List<SaleDetail> findBySaleId(Integer saleId);


}
