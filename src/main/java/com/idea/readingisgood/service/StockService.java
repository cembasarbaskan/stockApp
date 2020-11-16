package com.idea.readingisgood.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.idea.readingisgood.domain.Stock;
import com.idea.readingisgood.domain.response.BaseResponse;
import com.idea.readingisgood.domain.response.SuccessResponse;
import com.idea.readingisgood.dto.StockDTO;
import com.idea.readingisgood.mapper.StockMapper;
import com.idea.readingisgood.repository.StockRepository;

@Service
public class StockService extends BaseService<Stock, StockDTO> {
    private final static Logger logger = LoggerFactory.getLogger(StockService.class);

    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    public StockService(StockRepository stockRepository, StockMapper stockMapper) {
        this.stockRepository = stockRepository;
        this.stockMapper = stockMapper;
    }

    @Override
    public ResponseEntity<BaseResponse> fetchAll(int start, int size) {
        logger.info("[StockService.fetchAll] called with start: {} and size: {} params", start, size);
        List<Stock> stocks = stockRepository.findAll(createPageRequest(start, size, "createTime")).toList();
        List<StockDTO> stockDTOS = stocks.stream().map(stockMapper::entityToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(SuccessResponse.<List<StockDTO>>builder().data(stockDTOS).build());
    }

    @Override
    public ResponseEntity<BaseResponse> fetchOneById(String id) {
        logger.info("[StockService.fetchOneById] called with stockId: {}", id);
        Optional<Stock> optionalStock = stockRepository.findById(id);
        if (optionalStock.isEmpty()) {
            throw new NoSuchElementException("There is no any stock with given id: " + id);
        }
        return ResponseEntity.ok(
            SuccessResponse.<StockDTO>builder().data(stockMapper.entityToDTO(optionalStock.get())).build());
    }

    @Override
    public ResponseEntity<BaseResponse> deleteOneById(String id) {
        logger.info("[StockService.deleteOneById] called with stockId: {}", id);
        stockRepository.deleteById(id);
        return ResponseEntity.ok(SuccessResponse.<String>builder().data("deletion was successful").build());
    }

    @Override
    public ResponseEntity<BaseResponse> save(StockDTO dto) {
        logger.info("[StockService.save] called with stock: {}", dto);
        Stock stock = stockRepository.findByBookId(dto.getBookId());
        if (stock != null) {
            throw new EntityExistsException("Same stock exist");
        }
        stock = stockRepository.save(stockMapper.dtoToEntity(dto));
        return ResponseEntity.ok(SuccessResponse.<StockDTO>builder().data(stockMapper.entityToDTO(stock)).build());
    }

    @Override
    public ResponseEntity<BaseResponse> update(StockDTO dto) {
        logger.info("[StockService.update] called with stock: {}", dto);
        Optional<Stock> optionalStock = stockRepository.findById(dto.getId());
        if (optionalStock.isEmpty()) {
            throw new NoSuchElementException("There is no stock for update");
        }
        Stock updatedStock = stockRepository.save(stockMapper.dtoToEntity(dto));
        return ResponseEntity.ok(
            SuccessResponse.<StockDTO>builder().data(stockMapper.entityToDTO(updatedStock)).build());
    }

    public ResponseEntity<BaseResponse> findWithBookId(String bookId) {
        logger.info("[StockService.findWithBookId] called with bookId: {} ", bookId);
        StockDTO stockDTO = stockMapper.entityToDTO(findStockWithBookId(bookId));
        return ResponseEntity.ok(SuccessResponse.<StockDTO>builder().data(stockDTO).build());
    }

    protected Stock findStockWithBookId(String bookId) {
        logger.info("[StockService.findStockWithBookId] called with bookId: {}", bookId);
        return stockRepository.findByBookId(bookId);
    }

}