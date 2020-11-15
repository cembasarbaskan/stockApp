package com.idea.readingisgood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idea.readingisgood.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {
    Stock findByBookId(String bookId);
}
