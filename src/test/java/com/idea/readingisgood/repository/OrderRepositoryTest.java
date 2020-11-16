package com.idea.readingisgood.repository;

import java.util.HashSet;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.idea.readingisgood.base.AbstractTest;
import com.idea.readingisgood.domain.Book;
import com.idea.readingisgood.domain.Customer;
import com.idea.readingisgood.domain.Order;
import com.idea.readingisgood.domain.OrderedBook;
import com.idea.readingisgood.domain.Stock;
import com.idea.readingisgood.domain.enums.EnumOrderStatus;

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
    @org.junit.jupiter.api.Order(1)
    void saveTest() {
        Order order = new Order();
        order = fillAndSaveOrder(order);
        orderRepository.save(order);
        assert orderRepository.findById(order.getId()).isPresent();
    }

    @org.junit.jupiter.api.Order(2)
    @Test
    @Transactional
    void updateTest() {
        Order check = new Order();
        check = fillAndSaveOrder(check);

        check.setStatus(EnumOrderStatus.SHIPPED);
        orderRepository.save(check);

        Order mate = orderRepository.getOne(check.getId());
        assert mate.getStatus().equals(EnumOrderStatus.SHIPPED);
    }

    @org.junit.jupiter.api.Order(2)
    @Test
    @Transactional
    void deleteTest() {
        Order check = new Order();
        check = fillAndSaveOrder(check);
        check = orderRepository.save(check);
        String id = check.getId();
        orderRepository.deleteById(id);
        assert orderRepository.findById(id).isEmpty();
    }

    private Order fillAndSaveOrder(Order order) {
        // create Customer
        Customer customer = customerRepository.save(createCustomer());

        // create Book
        Book book = bookRepository.save(createBook());

        // create Stock
        Stock stock = stockRepository.save(createStock(book));

        // create OrderedBook
        OrderedBook orderedBook = createOrderedBook(book, order);

        // set & save Order
        HashSet<OrderedBook> orderedBooks = new HashSet<>();
        orderedBooks.add(orderedBook);
        order = orderRepository.saveAndFlush(setOrderAttributes(order, customer, orderedBooks));
        return order;
    }
}
