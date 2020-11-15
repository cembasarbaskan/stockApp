package com.idea.readingisgood;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import com.idea.readingisgood.entity.Book;
import com.idea.readingisgood.entity.Customer;
import com.idea.readingisgood.entity.Order;
import com.idea.readingisgood.entity.OrderedBook;
import com.idea.readingisgood.entity.Stock;
import com.idea.readingisgood.entity.enums.EnumGenre;
import com.idea.readingisgood.entity.enums.EnumOrderStatus;

public class AbstractTest {
    public Customer createCustomer() {
        Customer customer = new Customer();
        customer.setAddress("asdasd");
        customer.setEmail("asdasdadasd");
        customer.setName("asda");
        customer.setLastName("qadasdas");
        customer.setTelephone("111");
        customer.setYearOfBirth(1993);
        return customer;
    }

    public Book createBook() {
        return Book.builder()
            .author("asd")
            .name("sdasda")
            .publisher("azxaz")
            .genre(Arrays.asList(EnumGenre.HEALTH, EnumGenre.ART))
            .publishDate(Calendar.getInstance().getTime())
            .build();
    }

    public Stock createStock(Book book) {
        Stock stock = new Stock();
        stock.setBook(book);
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

    public Order setOrderAttributes(Order order, Customer customer, List<OrderedBook> orderedBooks) {
        order.setStatus(EnumOrderStatus.PREPARING);
        order.setCustomer(customer);
        order.setOrderedBooks(orderedBooks);
        return order;
    }

}
