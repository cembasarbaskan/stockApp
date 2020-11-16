package com.idea.readingisgood;

import static java.nio.charset.Charset.forName;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.idea.readingisgood.domain.enums.EnumGenre;
import com.idea.readingisgood.domain.enums.EnumOrderStatus;
import com.idea.readingisgood.dto.BookDTO;
import com.idea.readingisgood.dto.CustomerDTO;
import com.idea.readingisgood.dto.OrderDTO;
import com.idea.readingisgood.dto.OrderedBookDTO;
import com.idea.readingisgood.dto.StockDTO;

public abstract class AbstractTest {

    protected static EasyRandom easyRandom = null;

    @BeforeAll
    public static void initRandom() {
        EasyRandomParameters parameters = new EasyRandomParameters().seed(123L)
            .objectPoolSize(100)
            .randomizationDepth(100)
            .charset(forName("UTF-8"))
            .stringLengthRange(6, 10)
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
        Customer customer = new Customer();
        customer.setAddress("asdasd");
        customer.setEmail(easyRandom.nextObject(String.class));
        customer.setName("asda");
        customer.setLastName("qadasdas");
        customer.setTelephone(easyRandom.nextObject(String.class));
        customer.setYearOfBirth(1993);
        return customer;
    }

    public CustomerDTO createCustomerDTO() {
        CustomerDTO customer = new CustomerDTO();
        customer.setAddress("asdasd");
        customer.setEmail(easyRandom.nextObject(String.class));
        customer.setName("asda");
        customer.setLastName("qadasdas");
        customer.setTelephone(easyRandom.nextObject(String.class));
        customer.setYearOfBirth(1993);
        return customer;
    }

    public Book createBook() {
        return Book.builder()
            .author(easyRandom.nextObject(String.class))
            .name(easyRandom.nextObject(String.class))
            .publisher(easyRandom.nextObject(String.class))
            .genre(genres())
            .publishDate(Calendar.getInstance().getTime())
            .build();
    }

    public BookDTO createBookDTO() {
        return BookDTO.builder()
            .author(easyRandom.nextObject(String.class))
            .name(easyRandom.nextObject(String.class))
            .publisher(easyRandom.nextObject(String.class))
            .genre(genres())
            .publishDate(Calendar.getInstance().getTime())
            .build();
    }

    public Stock createStock(Book book) {
        Stock stock = new Stock();
        stock.setBook(book);
        stock.setPiece(1000);
        return stock;
    }

    public StockDTO createStockDTO(BookDTO book) {
        StockDTO stock = new StockDTO();
        stock.setBookId(book.getId());
        stock.setPiece(1000);
        return stock;
    }

    public OrderedBook createOrderedBook(Book book, Order order) {
        OrderedBook orderedBook = new OrderedBook();
        orderedBook.setPiece(1);
        orderedBook.setBook(book);
        orderedBook.setOrder(order);
        return orderedBook;
    }

    public OrderedBookDTO createOrderedBookDTO(BookDTO book, OrderDTO order) {
        OrderedBookDTO orderedBook = new OrderedBookDTO();
        orderedBook.setPiece(1);
        orderedBook.setBookId(book.getId());
        orderedBook.setOrderId(order.getId());
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

    private HashSet<EnumGenre> genres() {
        HashSet<EnumGenre> enumGenres = new HashSet<>();
        enumGenres.add(EnumGenre.ADVENTURE);
        enumGenres.add(EnumGenre.ROMANCE);
        enumGenres.add(EnumGenre.ART);
        return null;
    }

}
