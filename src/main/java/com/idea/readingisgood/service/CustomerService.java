package com.idea.readingisgood.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.idea.readingisgood.dto.CustomerDTO;
import com.idea.readingisgood.entity.Customer;
import com.idea.readingisgood.entity.response.BaseResponse;
import com.idea.readingisgood.entity.response.SuccessResponse;
import com.idea.readingisgood.mapper.CustomerMapper;
import com.idea.readingisgood.repository.CustomerRepository;
import com.idea.readingisgood.validator.SavingItemIdCheck;

@Service
public class CustomerService extends BaseService<Customer, CustomerDTO> {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public ResponseEntity<BaseResponse> fetchAll(int start, int size) {
        var customers = customerRepository.findAll(createPageRequest(start, size, "changeTime"));
        var listOfCustomerDTO = customers.stream().map(customerMapper::entityToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(
            SuccessResponse.<List<CustomerDTO>>builder().data(listOfCustomerDTO).status(HttpStatus.OK).build());
    }

    @Override
    public ResponseEntity<BaseResponse> fetchOneById(String id) {
        Optional<Customer> optCustomer = customerRepository.findById(id);
        return optCustomer.<ResponseEntity<BaseResponse>>map(customer -> ResponseEntity.ok(
            SuccessResponse.<CustomerDTO>builder().data(customerMapper.entityToDTO(customer)).build())).orElseThrow();
    }

    @Override
    public ResponseEntity<BaseResponse> deleteOneById(String id) {
        return ResponseEntity.ok(
            SuccessResponse.<String>builder().message("Deletion was successful").status(HttpStatus.OK).build());
    }

    @Override
    public ResponseEntity<BaseResponse> save(@SavingItemIdCheck(propName = Customer.class) CustomerDTO customerDTO) {
        Customer customer = customerRepository.save(customerMapper.dtoToEntity(customerDTO));
        return ResponseEntity.ok(SuccessResponse.<CustomerDTO>builder().data(customerMapper.entityToDTO(customer))
            .status(HttpStatus.OK)
            .build());
    }

    @Override
    public ResponseEntity<BaseResponse> update(CustomerDTO dto) {
        var customer = customerRepository.findByEmail(dto.getEmail());
        if (customer == null) {
            throw new NoSuchElementException("There was not any matched customer for update");
        }

        customer = customerRepository.save(customerMapper.dtoToEntity(dto));
        return ResponseEntity.ok(SuccessResponse.builder().data(customerMapper.entityToDTO(customer)).build());
    }
}
