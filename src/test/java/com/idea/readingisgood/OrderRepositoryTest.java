package com.idea.readingisgood;

import java.util.Collections;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.idea.readingisgood.entity.Book;
import com.idea.readingisgood.entity.Customer;
import com.idea.readingisgood.entity.Order;
import com.idea.readingisgood.entity.OrderedBook;
import com.idea.readingisgood.entity.Stock;
import com.idea.readingisgood.repository.BookRepository;
import com.idea.readingisgood.repository.CustomerRepository;
import com.idea.readingisgood.repository.OrderRepository;
import com.idea.readingisgood.repository.StockRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderRepositoryTest extends AbstractTest {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @Transactional
    void saveTest() {
        Order order = new Order();

        // create Customer
        Customer customer = customerRepository.save(createCustomer());

        // create Book
        Book book = bookRepository.save(createBook());

        // create Stock
        Stock stock = stockRepository.save(createStock(book));

        // create OrderedBook
        OrderedBook orderedBook = createOrderedBook(book, order);

        // set & save Order
        order = orderRepository.save(setOrderAttributes(order, customer, Collections.singletonList(orderedBook)));

        assert orderRepository.findById(order.getId()).isPresent();
    }
}
