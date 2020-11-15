package com.idea.readingisgood.mapper;

import org.springframework.stereotype.Component;

import com.idea.readingisgood.dto.StockDTO;
import com.idea.readingisgood.entity.Stock;

@Component
public class StockMapper implements BaseMapper<StockDTO, Stock> {
    @Override
    public StockDTO entityToDTO(Stock entity) {
        return new StockDTO(entity.getBook(), entity.getPiece());
    }

    @Override
    public Stock dtoToEntity(StockDTO dto) {
        return new Stock(dto.getBook(), dto.getPiece());
    }
}
