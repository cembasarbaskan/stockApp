package com.idea.readingisgood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idea.readingisgood.entity.Customer;
import com.idea.readingisgood.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findAllByCustomer(Customer customer);

    Order findByCode(String code);
}
