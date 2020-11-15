package com.idea.readingisgood.mapper;

import org.springframework.stereotype.Component;

import com.idea.readingisgood.dto.OrderedBookDTO;
import com.idea.readingisgood.entity.OrderedBook;

@Component
public class OrderedBookMapper implements BaseMapper<OrderedBookDTO, OrderedBook> {
    private final BookMapper bookMapper;
    private final OrderMapper orderMapper;

    public OrderedBookMapper(BookMapper bookMapper, OrderMapper orderMapper) {
        this.bookMapper = bookMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderedBookDTO entityToDTO(OrderedBook entity) {
        return new OrderedBookDTO(bookMapper.entityToDTO(entity.getBook()), orderMapper.entityToDTO(entity.getOrder()),
            entity.getPiece());
    }

    @Override
    public OrderedBook dtoToEntity(OrderedBookDTO dto) {
        return new OrderedBook(bookMapper.dtoToEntity(dto.getBook()), orderMapper.dtoToEntity(dto.getOrder()),
            dto.getPiece());
    }
}
