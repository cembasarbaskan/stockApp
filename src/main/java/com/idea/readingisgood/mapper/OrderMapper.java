package com.idea.readingisgood.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.idea.readingisgood.dto.OrderDTO;
import com.idea.readingisgood.dto.OrderedBookDTO;
import com.idea.readingisgood.entity.Order;
import com.idea.readingisgood.entity.OrderedBook;

@Component
public class OrderMapper implements BaseMapper<OrderDTO, Order> {
    @Autowired
    private OrderedBookMapper orderedBookMapper;


    @Override
    public OrderDTO entityToDTO(Order entity) {
        List<OrderedBookDTO> orderedBookDTOS = entity.getOrderedBooks()
            .stream()
            .map(orderedBookMapper::entityToDTO)
            .collect(Collectors.toList());
        return new OrderDTO(entity.getId(), entity.getCustomer(), orderedBookDTOS, entity.getCreateTime(),
            entity.getChangeTime(), entity.getStatus());
    }

    @Override
    public Order dtoToEntity(OrderDTO dto) {
        List<OrderedBook> orderedBooks = dto.getOrderedBooks()
            .stream()
            .map(orderedBookMapper::dtoToEntity)
            .collect(Collectors.toList());
        return new Order(dto.getCustomer(), orderedBooks, dto.getEnumOrderStatus());
    }
}
