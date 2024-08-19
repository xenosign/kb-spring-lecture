package org.example.repository.book;

import org.apache.ibatis.annotations.Mapper;
import org.example.domain.BookMybatis;

import java.util.List;

@Mapper
public interface BookMapper {
    public List<BookMybatis> findAll();
    public int save(BookMybatis newBookMybatis);
    public int delete(Long id);
}
