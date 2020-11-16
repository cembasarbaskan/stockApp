package com.idea.readingisgood.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.idea.readingisgood.AbstractTest;
import com.idea.readingisgood.domain.Book;
import com.idea.readingisgood.repository.BookRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookServiceTest extends AbstractTest {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void test01() {
        assert bookService.save(createBookDTO()).getStatusCode().equals(HttpStatus.OK);
    }

    @Test
    public void test02() {
        Book book = bookRepository.saveAndFlush(createBook());
        assert bookService.fetchOneById(book.getId()).getStatusCode().equals(HttpStatus.OK);
    }

    @Test
    public void test03() {
        Book book = bookRepository.saveAndFlush(createBook());
        assert bookService.deleteOneById(book.getId()).getStatusCode().equals(HttpStatus.OK);
    }
}
