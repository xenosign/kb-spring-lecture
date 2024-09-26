package org.example.controller.book;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.BookMybatis;
import org.example.repository.book.mybatis.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(value = "/book/mybatis")
public class MybatisBookController {
    private final BookRepository bookRepository;

    @GetMapping("/show")
    public ResponseEntity<List<BookMybatis>> findAll() {
        System.out.println("@@@@");
        List<BookMybatis> bookMybatis = bookRepository.findAll();
        return ResponseEntity.ok(bookMybatis);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<BookMybatis> findById(@PathVariable Long id) {
        BookMybatis findBook = bookRepository.findById(id);

        if (findBook == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(findBook);
    }

    @PostMapping("/save")
    public ResponseEntity<BookMybatis> saveBook(
            @RequestParam("title") String title,
            @RequestParam("author") String author
    ) {
        BookMybatis newBook = new BookMybatis(null, title, author);
        int affectedRows = bookRepository.save(newBook);
        if (affectedRows == 0) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
        }
    }

    @DeleteMapping(value = "/delete/{id}", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> deleteBook(@PathVariable("id") Long id) {
        int result = bookRepository.delete(id);
        if (result > 0) {
            return ResponseEntity.ok("게시글 삭제 성공");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시글 삭제 실패");
        }
    }
}
