package com.Rady.PhoneShop.Enitity;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.domain.Auditable;

@Data
@Entity
@Table(name = "brands")

public class Brand  extends AuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Brand name can't be empty")
    @Column(name = "brandName",unique = true,nullable = false)
    private String brandName;




}
