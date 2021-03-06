package com.idea.readingisgood.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.idea.readingisgood.dto.OrderDTO;
import com.idea.readingisgood.dto.OrderedBookDTO;
import com.idea.readingisgood.domain.Book;
import com.idea.readingisgood.domain.Order;
import com.idea.readingisgood.domain.OrderedBook;
import com.idea.readingisgood.domain.enums.EnumOrderStatus;
import com.idea.readingisgood.repository.BookRepository;
import com.idea.readingisgood.repository.CustomerRepository;

@Component
public class OrderMapper implements BaseMapper<OrderDTO, Order> {
    private final OrderedBookMapper orderedBookMapper;
    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;

    public OrderMapper(OrderedBookMapper orderedBookMapper, CustomerRepository customerRepository,BookRepository bookRepository) {
        this.orderedBookMapper = orderedBookMapper;
        this.customerRepository = customerRepository;
        this.bookRepository= bookRepository;
    }

    @Override
    public OrderDTO entityToDTO(Order entity) {
        Set<OrderedBookDTO> orderedBookDTOS =
            entity.getOrderedBooks().stream().map(orderedBookMapper::entityToDTO).collect(Collectors.toSet());
        return new OrderDTO(entity.getId(), entity.getCustomer().getId(), orderedBookDTOS,
            entity.getCreateTime(), entity.getChangeTime(), entity.getStatus());
    }

    @Override
    public Order dtoToEntity(OrderDTO dto) {
        Set<OrderedBook> orderedBooks =
            dto.getOrderedBooks().stream().map(orderedBookMapper::dtoToEntity).collect(Collectors.toSet());
        return new Order(dto.getId(), customerRepository.getOne(dto.getCustomerId()), orderedBooks, dto.getEnumOrderStatus());
    }


    public Order prepareOrderDTOForCreation(OrderDTO orderDTO) {
        Order order = new Order();
        order.setStatus(EnumOrderStatus.PREPARING);
        order.setCustomer(customerRepository.getOne(orderDTO.getCustomerId()));

        Set<OrderedBookDTO> orderedBookDTOS = orderDTO.getOrderedBooks();

        Set<OrderedBook> orderedBooks = new HashSet<>();
        for (OrderedBookDTO orderedBookDTO : orderedBookDTOS) {
            OrderedBook orderedBook = new OrderedBook();
            Optional<Book> optionalBook= bookRepository.findById(orderedBookDTO.getBookId());
            if(optionalBook.isEmpty()){
                throw new NoSuchElementException("The book in the order was not matched");
            }
            orderedBook.setBook(optionalBook.get());
            orderedBook.setOrder(order);
            orderedBook.setPiece(orderedBookDTO.getPiece());
            orderedBooks.add(orderedBook);
        }
        order.setOrderedBooks(orderedBooks);
        return order;
    }
}
