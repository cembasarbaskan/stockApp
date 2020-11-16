package com.idea.readingisgood.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.idea.readingisgood.base.AbstractTest;
import com.idea.readingisgood.domain.response.BaseResponse;
import com.idea.readingisgood.domain.response.SuccessResponse;
import com.idea.readingisgood.dto.CustomerDTO;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerServiceTest extends AbstractTest {
    @Autowired
    private CustomerService customerService;

    @Test
    public void test01() {
        assert customerService.save(createCustomerDTO()).getStatusCode().equals(HttpStatus.OK);
    }

    @Test
    public void test02() {
        ResponseEntity<BaseResponse> responseResponseEntity = customerService.save(createCustomerDTO());
        SuccessResponse<CustomerDTO> successResponse = (SuccessResponse<CustomerDTO>) responseResponseEntity.getBody();
        assert customerService.deleteOneById(successResponse.getData().getId()).getStatusCode().equals(HttpStatus.OK);
    }

    @Test
    public void test03() {
        ResponseEntity<BaseResponse> responseResponseEntity = customerService.save(createCustomerDTO());
        SuccessResponse<CustomerDTO> successResponse = (SuccessResponse<CustomerDTO>) responseResponseEntity.getBody();
        assert customerService.fetchOneById(successResponse.getData().getId()).getStatusCode().equals(HttpStatus.OK);
    }
}
