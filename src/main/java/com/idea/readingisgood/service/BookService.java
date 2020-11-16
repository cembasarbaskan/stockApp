package com.idea.readingisgood.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.idea.readingisgood.domain.Book;
import com.idea.readingisgood.domain.response.BaseResponse;
import com.idea.readingisgood.domain.response.SuccessResponse;
import com.idea.readingisgood.dto.BookDTO;
import com.idea.readingisgood.mapper.BookMapper;
import com.idea.readingisgood.repository.BookRepository;
import com.idea.readingisgood.validator.SavingItemIdCheck;

@Service
public class BookService extends BaseService<Book, BookDTO> {
    private final static Logger logger = LoggerFactory.getLogger(BookService.class);

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    public BookService(BookMapper bookMapper, BookRepository bookRepository) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
    }

    @Override
    public ResponseEntity<BaseResponse> fetchAll(int start, int size) {
        logger.info("[bookService.fetchOneById] called with start: {} and size: {} params", start, size);
        List<Book> bookList = bookRepository.findAll(createPageRequest(start, size, "changeTime")).toList();
        List<BookDTO> bookDTOList = bookList.stream().map(bookMapper::entityToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(SuccessResponse.<List<BookDTO>>builder().data(bookDTOList).build());
    }

    @Override
    public ResponseEntity<BaseResponse> fetchOneById(String id) {
        logger.info("[bookService.fetchOneById] called with bookId: {}", id);
        Optional<Book> optBook = bookRepository.findById(id);
        return optBook.<ResponseEntity<BaseResponse>>map(book -> ResponseEntity.ok(
            SuccessResponse.<BookDTO>builder().data(bookMapper.entityToDTO(book)).build())).orElseThrow();
    }

    @Override
    public ResponseEntity<BaseResponse> deleteOneById(String id) {
        logger.info("[bookService.deleteOneById] called with bookId: {}", id);
        return ResponseEntity.ok(
            SuccessResponse.<String>builder().message("Deletion was successful").status(HttpStatus.OK).build());
    }

    @Override
    public ResponseEntity<BaseResponse> save(@SavingItemIdCheck(propName = Book.class) BookDTO bookDTO) {
        logger.info("[bookService.save] called with book: {}", bookDTO);
        Book book = bookRepository.save(bookMapper.dtoToEntity(bookDTO));
        return ResponseEntity.ok(
            SuccessResponse.<BookDTO>builder().data(bookMapper.entityToDTO(book)).status(HttpStatus.OK).build());
    }

    @Override
    public ResponseEntity<BaseResponse> update(BookDTO bookDTO) {
        logger.info("[bookService.update] called with book: {}", bookDTO);
        Book savedBook = bookRepository.save(bookMapper.dtoToEntity(bookDTO));
        return ResponseEntity.ok(SuccessResponse.<BookDTO>builder().data(bookMapper.entityToDTO(savedBook)).build());
    }
}
