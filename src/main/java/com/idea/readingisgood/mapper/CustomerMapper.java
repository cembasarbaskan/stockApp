package com.idea.readingisgood.mapper;

import org.springframework.stereotype.Component;

import com.idea.readingisgood.dto.CustomerDTO;
import com.idea.readingisgood.entity.Customer;

@Component
public class CustomerMapper implements BaseMapper<CustomerDTO, Customer> {

    @Override
    public CustomerDTO entityToDTO(Customer customer) {
        return CustomerDTO.builder()
            .id(customer.getId())
            .name(customer.getName())
            .lastName(customer.getLastName())
            .email(customer.getEmail())
            .address(customer.getAddress())
            .telephone(customer.getTelephone())
            .yearOfBirth(customer.getYearOfBirth())
            .build();
    }

    @Override
    public Customer dtoToEntity(CustomerDTO dto) {
        return Customer.builder()
            .id(dto.getId())
            .name(dto.getName())
            .lastName(dto.getLastName())
            .email(dto.getEmail())
            .address(dto.getAddress())
            .telephone(dto.getTelephone())
            .yearOfBirth(dto.getYearOfBirth())
            .build();
    }
}
