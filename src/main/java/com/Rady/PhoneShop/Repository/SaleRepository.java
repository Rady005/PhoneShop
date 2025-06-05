package com.Rady.PhoneShop.Repository;

import com.Rady.PhoneShop.Enitity.Sale;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface SaleRepository extends JpaRepository <Sale,Integer>{
    Optional<Sale> findById(Integer id);


}
