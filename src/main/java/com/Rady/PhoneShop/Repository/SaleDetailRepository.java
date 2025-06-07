package com.Rady.PhoneShop.Repository;

import com.Rady.PhoneShop.Enitity.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface SaleDetailRepository extends JpaRepository <SaleDetail,Integer>, JpaSpecificationExecutor<SaleDetail>
{

    List<SaleDetail> findBySaleId(Integer saleId);


}
