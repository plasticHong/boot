package com.spring.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.spring.dto.GuestBookDTO;
import com.spring.dto.PageRequestDto;
import com.spring.dto.PageResultDto;
import com.spring.entity.GuestBook;
import com.spring.entity.QGuestBook;
import com.spring.repository.GuestBookRepository;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

@Log4j2
@Service
public class GuestBookServiceImp implements GuestBookService {


    @Autowired
    private GuestBookRepository repo;


    @Override
    public Long insert(GuestBookDTO dto) {
        log.info("DTO===============================");
        log.info(dto);

        GuestBook entity = dtoToEntity(dto);
        log.info(entity);

        repo.save(entity);

        Optional<GuestBook> byId = repo.findById(entity.getGno());

        Long id = 0L;
        if(byId.isPresent()){
            id = byId.get().getGno();
        }else {
            throw new RuntimeException();
        }
        return id;
    }


    @Override
    public PageResultDto<GuestBookDTO,GuestBook> getList(PageRequestDto requestDto) {
        Pageable pageable = requestDto.getPageable(Sort.by("gno").descending());


        BooleanBuilder builder = getSearch(requestDto);

        Page<GuestBook> result = repo.findAll(builder,pageable);

        Function<GuestBook,GuestBookDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDto<>(result,fn);
    }


    @Override
    public BooleanBuilder getSearch(PageRequestDto requestDto) {

        String type = requestDto.getType();
        String keyword = requestDto.getKeyword();

        BooleanBuilder builder = new BooleanBuilder();

        QGuestBook qGuestBook = QGuestBook.guestBook;

        BooleanExpression expression = qGuestBook.gno.gt(0L);
        builder.and(expression);

        //null처리

        if(Objects.isNull(type)||type.trim().length() == 0){
            return builder;
        }

        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if(type.contains("t")){
            conditionBuilder.or(qGuestBook.title.contains(keyword));
        }
        if(type.contains("c")){
            conditionBuilder.or(qGuestBook.content.contains(keyword));
        }
        if(type.contains("w")){
            conditionBuilder.or(qGuestBook.writer.contains(keyword));
        }

        builder.and(conditionBuilder);

        return builder;
    }
}
