package com.idea.readingisgood.repository;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.idea.readingisgood.AbstractTest;
import com.idea.readingisgood.domain.Book;
import com.idea.readingisgood.domain.Stock;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookRepositoryTest extends AbstractTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    @Transactional
    @org.junit.jupiter.api.Order(1)
    void saveTest() {
        Book book = createAndSaveStock();
        assert bookRepository.findById(book.getId()).isPresent();
    }

    @org.junit.jupiter.api.Order(2)
    @Test
    @Transactional
    void updateTest() {
        //create
        Book book = createAndSaveStock();

        //update
        book.setName("getir");
        bookRepository.save(book);

        assert bookRepository.getOne(book.getId()).getName().equals("getir");
    }

    @org.junit.jupiter.api.Order(2)
    @Test
    @Transactional
    void deleteTest() {
        Book book = createAndSaveStock();
        String id = book.getId();
        bookRepository.deleteById(id);

        assert bookRepository.findById(id).isEmpty();
    }

    private Book createAndSaveStock() {
        return bookRepository.save(createBook());
    }
}
