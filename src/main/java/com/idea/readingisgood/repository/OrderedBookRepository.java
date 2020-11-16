package com.idea.readingisgood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idea.readingisgood.domain.OrderedBook;

@Repository
public interface OrderedBookRepository extends JpaRepository<OrderedBook, String> {
}
