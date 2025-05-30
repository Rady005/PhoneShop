package com.Rady.PhoneShop.Dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDate;


@Data
public class ProductImportDto {
    @NotNull(message = "Product ID cannot be null")
    private Integer id;
    @JsonProperty("import_unit")
    @Min(value = 1, message = "Product ID must be greater than 0")
    private Integer importUint;
    @DecimalMin(value = "0.0000001", message = "Price must be greater than 0")
    private BigDecimal importPrice;

    @NotNull(message = "Import date cannot be null")
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate importDate;
}