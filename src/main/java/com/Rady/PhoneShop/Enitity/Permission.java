package com.Rady.PhoneShop.Enitity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "permission")
@Entity
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    private  String name;
}
