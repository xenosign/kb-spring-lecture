package org.example.controller.book;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.Book;
import org.example.repository.book.jpa.JpaBookRepository;
import org.example.repository.book.mybatis.BookRepository;
import org.example.repository.post.JpaPostRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@Transactional
@RequestMapping("/book/jpa")
public class JpaBookController {
    private final JpaBookRepository jpaBookRepository;

    @GetMapping("/show")
    public ResponseEntity<List<Book>> findAll() {
        List<Book> bookList = jpaBookRepository.findAll();
        return ResponseEntity.ok(bookList);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        Book findBook = jpaBookRepository.findById(id);
        if(findBook == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(findBook);
    }

    @PostMapping("/save")
    public ResponseEntity<Book> save(@RequestParam("title") String title, @RequestParam("author") String author) {
        Book newBook = new Book(null, title, author);
        Book addedBook = jpaBookRepository.save(newBook);
        if(addedBook == null) return ResponseEntity.internalServerError().build();
        return ResponseEntity.ok(newBook);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id) {
        Book book = jpaBookRepository.findById(id);
        if(book == null) return ResponseEntity.notFound().build();

        jpaBookRepository.delete(book.getId());
        return ResponseEntity.ok(book);
    }
}

