package com.idea.readingisgood.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.idea.readingisgood.domain.response.BaseResponse;
import com.idea.readingisgood.dto.StockDTO;
import com.idea.readingisgood.service.StockService;

@RestController
@RequestMapping("/stock")
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getStock(@PathVariable String stockId) {
        return stockService.fetchOneById(stockId);
    }

    @GetMapping(path = "/book")
    public ResponseEntity<BaseResponse> getStockOfBook(@PathVariable String bookId) {
        return stockService.findWithBookId(bookId);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<BaseResponse> getListStock(
        @RequestParam(name = "startIndex", required = false, defaultValue = "0") Integer startIndex,
        @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {
        return stockService.fetchAll(startIndex, size);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createStock(@RequestBody @Valid StockDTO stockDTO) {
        return stockService.save(stockDTO);
    }

    @PutMapping
    public ResponseEntity<BaseResponse> updateStock(@RequestBody @Valid StockDTO stockDTO) {
        return stockService.update(stockDTO);
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse> deleteStock(@PathVariable String stockId) {
        return stockService.deleteOneById(stockId);
    }

}
