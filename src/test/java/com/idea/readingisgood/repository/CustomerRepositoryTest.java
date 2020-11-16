package com.idea.readingisgood.repository;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.idea.readingisgood.base.AbstractTest;
import com.idea.readingisgood.domain.Customer;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerRepositoryTest extends AbstractTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @Transactional
    @org.junit.jupiter.api.Order(1)
    void saveTest() {
        Customer customer = createCustomer();
        customer = customerRepository.save(customer);
        assert customerRepository.findById(customer.getId()).isPresent();
    }

    @org.junit.jupiter.api.Order(2)
    @Test
    @Transactional
    void updateTest() {
        //create
        Customer customer = createCustomer();
        customer = customerRepository.save(customer);

        //update
        customer.setName("getir");
        customerRepository.save(customer);

        assert customerRepository.getOne(customer.getId()).getName().equals("getir");
    }

    @org.junit.jupiter.api.Order(2)
    @Test
    @Transactional
    void deleteTest() {
        //create
        Customer customer = createCustomer();
        customer = customerRepository.save(customer);
        String id = customer.getId();
        customerRepository.deleteById(id);

        assert customerRepository.findById(id).isEmpty();
    }

}
