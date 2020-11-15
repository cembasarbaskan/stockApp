package com.idea.readingisgood.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.idea.readingisgood.entity.Stock;
import com.idea.readingisgood.entity.response.BaseResponse;
import com.idea.readingisgood.repository.StockRepository;

@Service
public class StockService extends BaseService {
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public ResponseEntity<BaseResponse> fetchAll(int start, int size) {
        throw new UnsupportedOperationException("");
    }

    @Override
    public ResponseEntity<BaseResponse> fetchOneById(String id) {
        throw new UnsupportedOperationException("");
    }

    @Override
    public ResponseEntity<BaseResponse> deleteOneById(String id) {
        throw new UnsupportedOperationException("");
    }

    @Override
    public ResponseEntity<BaseResponse> save(Object dto) {
        throw new UnsupportedOperationException("");
    }

    @Override
    public ResponseEntity<BaseResponse> update(Object dto) {
        throw new UnsupportedOperationException("");
    }

    public Stock findStockWithBookId(String bookId) {
        return stockRepository.findByBookId(bookId);
    }

    public boolean checkIfBookExistInStock(String bookId) {
        return findStockWithBookId(bookId) != null;
    }
}
