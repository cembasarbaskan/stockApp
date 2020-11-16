package com.idea.readingisgood;

import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.UUID;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.jeasy.random.randomizers.range.IntegerRangeRandomizer;
import org.jeasy.random.randomizers.range.LongRangeRandomizer;
import org.junit.jupiter.api.BeforeAll;

import com.idea.readingisgood.domain.Book;
import com.idea.readingisgood.domain.Customer;
import com.idea.readingisgood.domain.Order;
import com.idea.readingisgood.domain.OrderedBook;
import com.idea.readingisgood.domain.Stock;
import com.idea.readingisgood.domain.enums.EnumOrderStatus;
import com.idea.readingisgood.dto.BookDTO;
import com.idea.readingisgood.dto.CustomerDTO;
import com.idea.readingisgood.dto.OrderDTO;
import com.idea.readingisgood.dto.OrderedBookDTO;
import com.idea.readingisgood.dto.StockDTO;

public abstract class AbstractTest {

    public static EasyRandom easyRandom = null;

    @BeforeAll
    public static void initRandom() {
        EasyRandomParameters parameters = new EasyRandomParameters().seed(12345L)
            .objectPoolSize(100)
            .randomizationDepth(100)
            .charset(StandardCharsets.UTF_8)
            .stringLengthRange(3, 99)
            .collectionSizeRange(1, 1)
            .scanClasspathForConcreteTypes(true)
            .overrideDefaultInitialization(false)
            .ignoreRandomizationErrors(true)
            .randomize(Integer.class, new IntegerRangeRandomizer(0, 99999))
            .randomize(Long.class, new LongRangeRandomizer(0L, 99999999L))
            .excludeField(FieldPredicates.named("id"));

        easyRandom = new EasyRandom(parameters);
    }

    public Customer createCustomer() {
        Customer customer = easyRandom.nextObject(Customer.class);
        customer.setEmail(UUID.randomUUID().toString().substring(0, 10) + "@" + "getir.com");
        customer.setTelephone(UUID.randomUUID().toString().substring(0, 8));
        return customer;
    }

    public CustomerDTO createCustomerDTO() {
        CustomerDTO customer = easyRandom.nextObject(CustomerDTO.class);
        customer.setEmail(UUID.randomUUID().toString().substring(0, 10) + "@" + "getir.com");
        customer.setTelephone(UUID.randomUUID().toString().substring(0, 8));
        return customer;
    }

    public Book createBook() {
        Book book = easyRandom.nextObject(Book.class);
        book.setName(UUID.randomUUID().toString().substring(0, 10));
        book.setAuthor(UUID.randomUUID().toString().substring(0, 10));
        book.setPublisher(UUID.randomUUID().toString().substring(0, 10));
        return book;
    }

    public BookDTO createBookDTO() {
        BookDTO book = easyRandom.nextObject(BookDTO.class);
        book.setName(UUID.randomUUID().toString().substring(0, 10));
        book.setAuthor(UUID.randomUUID().toString().substring(0, 10));
        book.setPublisher(UUID.randomUUID().toString().substring(0, 10));
        return book;
    }

    public Stock createStock(Book book) {
        Stock stock = easyRandom.nextObject(Stock.class);
        stock.setBook(book);
        stock.setPiece(Integer.MAX_VALUE);
        return stock;
    }

    public StockDTO createStockDTO(BookDTO book) {
        StockDTO stock = easyRandom.nextObject(StockDTO.class);
        stock.setBookId(book.getId());
        stock.setPiece(Integer.MAX_VALUE);
        return stock;
    }

    public OrderedBook createOrderedBook(Book book, Order order) {
        OrderedBook orderedBook = easyRandom.nextObject(OrderedBook.class);
        orderedBook.setBook(book);
        orderedBook.setOrder(order);
        orderedBook.setPiece(1);
        return orderedBook;
    }

    public OrderedBookDTO createOrderedBookDTO(BookDTO book, OrderDTO order) {
        OrderedBookDTO orderedBook = easyRandom.nextObject(OrderedBookDTO.class);
        orderedBook.setBookId(book.getId());
        orderedBook.setOrderId(order.getId());
        orderedBook.setPiece(1);
        return orderedBook;
    }

    public Order setOrderAttributes(Order order, Customer customer, Set<OrderedBook> orderedBooks) {
        order.setStatus(EnumOrderStatus.PREPARING);
        order.setCustomer(customer);
        order.setOrderedBooks(orderedBooks);
        return order;
    }

    public OrderDTO setOrderDTOAttributes(OrderDTO order, CustomerDTO customer, Set<OrderedBookDTO> orderedBooks) {
        order.setEnumOrderStatus(EnumOrderStatus.PREPARING);
        order.setCustomerId(customer.getId());
        order.setOrderedBooks(orderedBooks);
        return order;
    }
}
