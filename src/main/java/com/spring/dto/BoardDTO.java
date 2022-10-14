package com.spring.dto;


import com.spring.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BoardDTO {


    private Long seq;
    private String title;
    private String content;
    private int cnt;
    private Member member;

    private LocalDateTime regDate;
    private LocalDateTime modDate;

}
