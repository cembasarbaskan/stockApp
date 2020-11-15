package com.idea.readingisgood.mapper;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.idea.readingisgood.dto.OrderedBookDTO;
import com.idea.readingisgood.entity.Book;
import com.idea.readingisgood.entity.Order;
import com.idea.readingisgood.entity.OrderedBook;
import com.idea.readingisgood.repository.BookRepository;
import com.idea.readingisgood.repository.CustomerRepository;
import com.idea.readingisgood.repository.OrderRepository;

@Component
public class OrderedBookMapper implements BaseMapper<OrderedBookDTO, OrderedBook> {
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderedBookMapper(BookRepository bookRepository, OrderRepository orderRepository,
        CustomerRepository customerRepository) {
        this.bookRepository = bookRepository;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public OrderedBookDTO entityToDTO(OrderedBook entity) {
        return new OrderedBookDTO(entity.getBook().getId(), entity.getOrder().getId(), entity.getPiece());
    }

    // Only use for updating Order
    @Override
    public OrderedBook dtoToEntity(OrderedBookDTO dto) {
        Optional<Book> optionalBook = bookRepository.findById(dto.getBookId());
        Order order = orderRepository.getOne(dto.getOrderCode());
        if (optionalBook.isEmpty() || order == null) {
            throw new NoSuchElementException("The book in the order was not matched");
        }

        return new OrderedBook(optionalBook.get(), order, dto.getPiece());
    }

}
