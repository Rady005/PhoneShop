package com.Rady.PhoneShop.Repository;

import com.Rady.PhoneShop.Enitity.Sale;
import com.Rady.PhoneShop.Service.Projection.ProductSold;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;


import java.time.LocalDate;

import java.util.List;
import java.util.Optional;


@Repository
public interface SaleRepository extends JpaRepository <Sale,Integer>{
   Optional<Sale> findById(Integer id);


    @Query(value = "select " +
            "p.id as productId, " +
            "p.product_name as productName, " +
            "sum(sd.unit) as numberOfUnit, " +
            "sum(sd.unit*sd.amount) as totalAmount " +
            "from sale_details sd\n" +
            "inner join sales s on sd.sale_id = s.id\n" +
            "inner join products p on p.id=sd.product_id\n" +
            "where date(s.sold_date) >= :startDate and date(s.sold_date)<=:endDate\n" +
            "group by p.id,p.product_name", nativeQuery = true)
    List<ProductSold> findProductSold(LocalDate startDate, LocalDate endDate);
 
}
