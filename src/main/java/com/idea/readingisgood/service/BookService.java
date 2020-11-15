package com.idea.readingisgood.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.idea.readingisgood.validator.SavingItemIdCheck;
import com.idea.readingisgood.dto.BookDTO;
import com.idea.readingisgood.entity.Book;
import com.idea.readingisgood.entity.response.BaseResponse;
import com.idea.readingisgood.entity.response.SuccessResponse;
import com.idea.readingisgood.mapper.BookMapper;
import com.idea.readingisgood.repository.BookRepository;

@Service
public class BookService extends BaseService<Book, BookDTO> {
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    public BookService(BookMapper bookMapper, BookRepository bookRepository) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
    }

    @Override
    public ResponseEntity<BaseResponse> fetchAll(int start, int size) {
        List<Book> bookList = bookRepository.findAll(createPageRequest(start, size, "changeTime")).toList();
        List<BookDTO> bookDTOList = bookList.stream().map(bookMapper::entityToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(SuccessResponse.<List<BookDTO>>builder().data(bookDTOList).build());
    }

    @Override
    public ResponseEntity<BaseResponse> fetchOneById(String id) {
        Optional<Book> optBook = bookRepository.findById(id);
        return optBook.<ResponseEntity<BaseResponse>>map(book -> ResponseEntity.ok(
            SuccessResponse.<BookDTO>builder().data(bookMapper.entityToDTO(book)).build())).orElseThrow();
    }

    @Override
    public ResponseEntity<BaseResponse> deleteOneById(String id) {
        try {
            bookRepository.deleteById(id);
            return ResponseEntity.ok(
                SuccessResponse.<String>builder().message("Deletion was successful").status(HttpStatus.OK).build());
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchElementException("No book found for deletion", e);
        }
    }

    @Override
    public ResponseEntity<BaseResponse> save(@SavingItemIdCheck(propName = Book.class) BookDTO bookDTO) {
        Book book = bookRepository.findByDetail(bookDTO.getName(), bookDTO.getAuthor(), bookDTO.getPublisher());
        if (book != null) {
            throw new EntityExistsException("same book exists");
        }

        book = bookRepository.save(bookMapper.dtoToEntity(bookDTO));
        return ResponseEntity.ok(
            SuccessResponse.<BookDTO>builder().data(bookMapper.entityToDTO(book)).status(HttpStatus.OK).build());
    }

    @Override
    public ResponseEntity<BaseResponse> update(BookDTO bookDTO) {
        Book savedBook = bookRepository.save(bookMapper.dtoToEntity(bookDTO));
        return ResponseEntity.ok(SuccessResponse.<BookDTO>builder().data(bookMapper.entityToDTO(savedBook)).build());
    }
}
