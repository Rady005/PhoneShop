package com.Rady.PhoneShop.Enitity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table (name = "colors")
public class Color {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String colorName;

}
