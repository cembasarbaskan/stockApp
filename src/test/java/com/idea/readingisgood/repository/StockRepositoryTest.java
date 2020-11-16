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
public class StockRepositoryTest extends AbstractTest {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    @Transactional
    @org.junit.jupiter.api.Order(1)
    void saveTest() {
        Stock stock = createAndSaveStock();
        assert stockRepository.findById(stock.getId()).isPresent();
    }

    @org.junit.jupiter.api.Order(2)
    @Test
    @Transactional
    void updateTest() {
        //create
        Stock stock = createAndSaveStock();

        //update
        stock.setPiece(1919);
        stockRepository.save(stock);

        assert stockRepository.getOne(stock.getId()).getPiece().equals(1919);
    }

    @org.junit.jupiter.api.Order(2)
    @Test
    @Transactional
    void deleteTest() {
        Stock stock = createAndSaveStock();
        String id = stock.getId();
        stockRepository.deleteById(id);

        assert stockRepository.findById(id).isEmpty();
    }

    private Stock createAndSaveStock() {
        Book book = createBook();
        book = bookRepository.save(book);
        return stockRepository.save(createStock(book));
    }
}
