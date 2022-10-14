package com.spring.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
@Builder
@Data
public class PageRequestDto {

    private final int DEFAULT_PAGE_VALUE = 1;
    private final int DEFAULT_PAGE_SIZE_VALUE = 10;

    private int page;
    private int size;

    private String type;

    private String keyword;

    public PageRequestDto() {
        this.page = DEFAULT_PAGE_SIZE_VALUE;
        this.size = DEFAULT_PAGE_SIZE_VALUE;
    }

    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page - 1, size, sort);
    }



}
