package com.idea.readingisgood.mapper;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.idea.readingisgood.dto.StockDTO;
import com.idea.readingisgood.domain.Book;
import com.idea.readingisgood.domain.Stock;
import com.idea.readingisgood.repository.BookRepository;

@Component
public class StockMapper implements BaseMapper<StockDTO, Stock> {
    private final BookRepository bookRepository;

    public StockMapper(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public StockDTO entityToDTO(Stock entity) {
        return new StockDTO(entity.getId(), entity.getBook().getId(), entity.getPiece());
    }

    @Override
    public Stock dtoToEntity(StockDTO dto) {
        Optional<Book> book = bookRepository.findById(dto.getBookId());
        if (book.isEmpty()) {
            throw new NoSuchElementException("No book in shelf!");
        }

        return new Stock(dto.getId(),book.get(), dto.getPiece());
    }
}
