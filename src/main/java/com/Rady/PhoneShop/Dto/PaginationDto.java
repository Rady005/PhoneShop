package com.Rady.PhoneShop.Dto;

import lombok.*;


@Builder
@Data
public class PaginationDto {
    private int pageSize;
    private int pageNumber;
    private int totalPage;
    private long totalElements;
    private long numberOfElements;


    private boolean last;
    private boolean first;
    private boolean empty;
}
