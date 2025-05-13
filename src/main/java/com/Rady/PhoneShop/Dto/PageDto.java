package com.Rady.PhoneShop.Dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
@Data
public class PageDto {
    private List<?> list;
    private PaginationDto pagination;
    public PageDto(Page<?>page) {
        this.list = page.getContent();
        this.pagination = PaginationDto.builder()
                .pageSize(page.getSize())
                .pageNumber(page.getNumber()+1)
                .totalPage(page.getTotalPages())
                .totalElements((long) page.getTotalElements())
                .numberOfElements(page.getNumberOfElements())
                .last(page.isLast())
                .first(page.isFirst())
                .empty(page.isEmpty())
                .build();
    }
}
