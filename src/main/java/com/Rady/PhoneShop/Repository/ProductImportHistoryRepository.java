package com.Rady.PhoneShop.Repository;

import com.Rady.PhoneShop.Enitity.ProductImportHistory;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImportHistoryRepository extends JpaRepository <ProductImportHistory, Integer>
{

}
