package com.spring.dto;


import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Data
public class PageResultDto<DTO,EN> {

    private List<DTO> dtoList;


    private int totalPage;  //총 페이지 번호
    private int page;   //현재 페이지
    private int size;
    private int start,end;
    private boolean prev,next;
    private List<Integer> pageList;



    public PageResultDto(Page<EN>result, Function<EN,DTO> fn) {
        dtoList = result.stream().map(fn).collect(Collectors.toList());
        totalPage = result.getTotalPages();
        makePageList(result.getPageable());
        size = result.getSize();

    }


    public void makePageList(Pageable pageable) {

        this.page = pageable.getPageNumber()+1;
        this.size = pageable.getPageSize();

        //realEnd == 실제 끝 페이지
        int realEnd = (int)Math.ceil(page/10.0)*10;
        start = realEnd -9;
        end = totalPage > realEnd ? realEnd:totalPage;

        prev = start >1 ;
        next = totalPage > realEnd;

        pageList = IntStream.rangeClosed(start,realEnd).boxed()
                .collect(Collectors.toList());

    }

}
