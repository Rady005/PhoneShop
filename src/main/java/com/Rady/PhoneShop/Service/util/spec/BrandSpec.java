package com.Rady.PhoneShop.Service.util.spec;

import com.Rady.PhoneShop.Enitity.Brand;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.springframework.beans.factory.config.ListFactoryBean;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


@Data
public class BrandSpec implements Specification<Brand> {
    private final BrandFilter brandFilter;
    List<Predicate> predicates=new ArrayList<>();

    @Override
    public Predicate toPredicate(Root<Brand> brandRoot, CriteriaQuery<?> query, CriteriaBuilder cb)
    {
        if(brandFilter.getBrandName()!=null)
        {
            /*Predicate name= brandRoot.get("brandName").in(brandFilter.getBrandName());*/
            /*predicates.add(name);*/
            Predicate name= cb.like(brandRoot.get("brandName"),"%"+brandFilter.getBrandName()+"%");
/*            //still the same
            Predicate name=cb.like(cb.upper(brandRoot.get("brandName")),brandFilter.getBrandName().toUpperCase()+"%");*/
            predicates.add(name);

        }
        if(brandFilter.getId()!=null)
        {
            Predicate id=brandRoot.get("id").in(brandFilter.getId());
            predicates.add(id);
        }

        

        return cb.and(predicates.toArray(Predicate[]::new));
    }
}
