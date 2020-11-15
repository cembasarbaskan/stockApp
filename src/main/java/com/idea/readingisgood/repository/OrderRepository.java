package com.idea.readingisgood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idea.readingisgood.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    Order findByCustomerId(String customerId);
}
