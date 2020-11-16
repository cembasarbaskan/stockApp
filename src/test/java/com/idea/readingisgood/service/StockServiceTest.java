package com.idea.readingisgood.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.idea.readingisgood.AbstractTest;
import com.idea.readingisgood.domain.Book;
import com.idea.readingisgood.domain.Stock;
import com.idea.readingisgood.mapper.StockMapper;
import com.idea.readingisgood.repository.BookRepository;
import com.idea.readingisgood.repository.StockRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StockServiceTest extends AbstractTest {
    @Autowired
    private StockService stockService;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockMapper stockMapper;

    @Test
    public void test01() {
        Book book = bookRepository.saveAndFlush(createBook());
        assert stockService.save(stockMapper.entityToDTO(createStock(book))).getStatusCode().equals(HttpStatus.OK);
    }

    @Test
    public void test02() {
        Book book = bookRepository.saveAndFlush(createBook());
        Stock stock = stockRepository.saveAndFlush(createStock(book));
        assert stockService.deleteOneById(stock.getId()).getStatusCode().equals(HttpStatus.OK);
    }

    @Test
    public void test03() {
        Book book = bookRepository.saveAndFlush(createBook());
        Stock stock = stockRepository.saveAndFlush(createStock(book));
        assert stockService.fetchOneById(stock.getId()).getStatusCode().equals(HttpStatus.OK);
    }
}
