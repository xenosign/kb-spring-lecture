package org.example.repository.book;

import lombok.RequiredArgsConstructor;
import org.example.domain.BookMybatis;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepository {
    private final BookMapper bookMapper;

    public List<BookMybatis> findAll() {
        return bookMapper.findAll();
    }
    public int save(BookMybatis newBookMybatis) {
        return bookMapper.save(newBookMybatis);
    }
    public int delete(Long id) {
        return bookMapper.delete(id);
    }
}
