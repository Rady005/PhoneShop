    package com.Rady.PhoneShop.Enitity;


    import jakarta.persistence.*;
    import lombok.Data;

    import java.math.BigDecimal;
    import java.time.LocalDateTime;

    @Entity
    @Data
    @Table (name = "saleDetails")
    public class SaleDetail {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "sale_id")
        private Sale sale;

        @ManyToOne
        @JoinColumn(name = "product_id")
        private Product product;

        @Column(name = "amount")
        private BigDecimal amount;

        private Integer unit;



    }
