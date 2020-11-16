package com.idea.readingisgood.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.idea.readingisgood.dto.CustomerDTO;
import com.idea.readingisgood.domain.Customer;
import com.idea.readingisgood.domain.response.BaseResponse;
import com.idea.readingisgood.domain.response.SuccessResponse;
import com.idea.readingisgood.mapper.CustomerMapper;
import com.idea.readingisgood.repository.CustomerRepository;

@Service
public class CustomerService extends BaseService<Customer, CustomerDTO> {
    private final static Logger logger = LoggerFactory.getLogger(CustomerService.class);
    
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public ResponseEntity<BaseResponse> fetchAll(int start, int size) {
        logger.info("[CustomerService.fetchAll] called with start: {} and size: {} params", start, size);
        var customers = customerRepository.findAll(createPageRequest(start, size, "changeTime"));
        var listOfCustomerDTO = customers.stream().map(customerMapper::entityToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(
            SuccessResponse.<List<CustomerDTO>>builder().data(listOfCustomerDTO).status(HttpStatus.OK).build());
    }

    @Override
    public ResponseEntity<BaseResponse> fetchOneById(String id) {
        logger.info("[CustomerService.fetchOneById] called with customerId: {}", id);
        Optional<Customer> optCustomer = customerRepository.findById(id);
        return optCustomer.<ResponseEntity<BaseResponse>>map(customer -> ResponseEntity.ok(
            SuccessResponse.<CustomerDTO>builder().data(customerMapper.entityToDTO(customer)).build())).orElseThrow();
    }

    @Override
    public ResponseEntity<BaseResponse> deleteOneById(String id) {
        logger.info("[CustomerService.deleteOneById] called with customerId: {}", id);
        return ResponseEntity.ok(
            SuccessResponse.<String>builder().message("Deletion was successful").status(HttpStatus.OK).build());
    }

    @Override
    public ResponseEntity<BaseResponse> save(CustomerDTO customerDTO) {
        logger.info("[CustomerService.save] called with customer: {}", customerDTO);
        Customer customer = customerRepository.saveAndFlush(customerMapper.dtoToEntity(customerDTO));
        return ResponseEntity.ok(SuccessResponse.<CustomerDTO>builder().data(customerMapper.entityToDTO(customer))
            .status(HttpStatus.OK)
            .build());
    }

    @Override
    public ResponseEntity<BaseResponse> update(CustomerDTO dto) {
        logger.info("[CustomerService.update] called with customer: {}", dto);
        var customer = customerRepository.findByEmail(dto.getEmail());
        if (customer == null) {
            throw new NoSuchElementException("There was not any matched customer for update");
        }

        customer = customerRepository.saveAndFlush(customerMapper.dtoToEntity(dto));
        return ResponseEntity.ok(SuccessResponse.builder().data(customerMapper.entityToDTO(customer)).build());
    }
}
