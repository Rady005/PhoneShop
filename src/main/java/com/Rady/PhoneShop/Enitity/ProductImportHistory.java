package com.Rady.PhoneShop.Enitity;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table (name = "productImportHistory")
public class ProductImportHistory
{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_import")
    private LocalDateTime dateImport;

    @Column(name = "import_unit")
    private String importUnit;

    @Column(name = "price_per_unit")
    private BigDecimal pricePerUnit;

    @ManyToOne
    @JoinColumn (name = "product_id")
    private Product product;
}
