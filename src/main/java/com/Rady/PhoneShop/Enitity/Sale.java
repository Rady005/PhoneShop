    package com.Rady.PhoneShop.Enitity;
    import jakarta.persistence.*;
    import lombok.Data;
    import java.time.LocalDateTime;

    @Entity
    @Data
    @Table(name = "sales")
    public class Sale {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(name = "sold_date")
        private LocalDateTime soldDate;

        private boolean active;



    }
