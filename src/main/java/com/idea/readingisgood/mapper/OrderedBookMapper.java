package com.idea.readingisgood.mapper;

import org.springframework.stereotype.Component;

import com.idea.readingisgood.dto.OrderedBookDTO;
import com.idea.readingisgood.entity.OrderedBook;

@Component
public class OrderedBookMapper implements BaseMapper<OrderedBookDTO, OrderedBook> {

    @Override
    public OrderedBookDTO entityToDTO(OrderedBook entity) {
        return new OrderedBookDTO(entity.getBook(), entity.getOrder(), entity.getPiece());
    }

    @Override
    public OrderedBook dtoToEntity(OrderedBookDTO dto) {
        return new OrderedBook(dto.getBook(), dto.getOrder(), dto.getPiece());
    }
}
