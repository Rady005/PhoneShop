package com.Rady.PhoneShop.Repository;

import com.Rady.PhoneShop.Enitity.Brand;
import com.Rady.PhoneShop.Enitity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>, JpaSpecificationExecutor<Product> {


}
