package com.idea.readingisgood;

import java.util.Arrays;
import java.util.Calendar;
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
import com.idea.readingisgood.entity.enums.EnumGenre;
import com.idea.readingisgood.entity.enums.EnumOrderStatus;
import com.idea.readingisgood.mapper.BookMapper;
import com.idea.readingisgood.mapper.StockMapper;
import com.idea.readingisgood.repository.BookRepository;
import com.idea.readingisgood.repository.CustomerRepository;
import com.idea.readingisgood.repository.OrderRepository;
import com.idea.readingisgood.repository.StockRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderServiceTest {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @Transactional
    void saveTest() {
        Order order = new Order();

        // create customer
        Customer customer = new Customer();
        customer.setAddress("asdasd");
        customer.setEmail("asdasdadasd");
        customer.setName("asda");
        customer.setLastName("qadasdas");
        customer.setTelephone("111");
        customer.setYearOfBirth(1993);
        customer = customerRepository.save(customer);

        // create book
        Book book = Book.builder()
            .author("asd")
            .name("sdasda")
            .publisher("azxaz")
            .genre(Arrays.asList(EnumGenre.HEALTH, EnumGenre.ART))
            .publishDate(Calendar.getInstance().getTime())
            .build();
        book = bookRepository.save(book);

        // create stock
        Stock stock = new Stock();
        stock.setBook(book);
        stock.setPiece(1000);
        stock = stockRepository.save(stock);

        // create OrderedBook
        OrderedBook orderedBook = new OrderedBook();
        orderedBook.setPiece(1);
        orderedBook.setBook(book);
        orderedBook.setOrder(order);

        order.setStatus(EnumOrderStatus.PREPARING);
        order.setCustomer(customer);
        order.setOrderedBooks(Collections.singletonList(orderedBook));

        order = orderRepository.save(order);

        assert order.getId() != null;
    }
}
