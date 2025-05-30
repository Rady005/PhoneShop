package com.Rady.PhoneShop.Dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaleDto {
    @NonNull
    private List<ProductSoldDto> products;
    private LocalDate soldDate;


}
