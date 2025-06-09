package com.Rady.PhoneShop.Service.util.spec;

import com.Rady.PhoneShop.Enitity.ProductImportHistory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
public class ProductImportHistorySpec implements Specification<ProductImportHistory> {
    private ProductImportHistoryFilter productImportHistoryFilter;
    @Override
    public Predicate toPredicate (Root<ProductImportHistory> importHistory, CriteriaQuery<?> query, CriteriaBuilder cb) {

        List<Predicate>predicates=new ArrayList<>();
        if(Objects.nonNull(productImportHistoryFilter.getStartDate())){
            cb.greaterThanOrEqualTo(importHistory.get("dateImport"),productImportHistoryFilter.getStartDate());
        }
        if(Objects.nonNull(productImportHistoryFilter.getEndDate())){
            cb.lessThanOrEqualTo(importHistory.get("dateImport"),productImportHistoryFilter.getEndDate());
        }
        return cb.and(predicates.toArray(Predicate[]::new));

    }
}
