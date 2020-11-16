package com.idea.readingisgood;

import java.util.Collections;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.idea.readingisgood.dto.BookDTO;
import com.idea.readingisgood.dto.CustomerDTO;
import com.idea.readingisgood.dto.OrderDTO;
import com.idea.readingisgood.domain.response.BaseResponse;
import com.idea.readingisgood.domain.response.SuccessResponse;
import com.idea.readingisgood.service.BookService;
import com.idea.readingisgood.service.CustomerService;
import com.idea.readingisgood.service.OrderService;
import com.idea.readingisgood.service.StockService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderScenarioTest extends AbstractTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BookService bookService;

    @Autowired
    private StockService stockService;

    @Autowired
    private OrderService orderService;

    @Test
    public void test01() {
        OrderDTO orderDTO = new OrderDTO();
        ResponseEntity<BaseResponse> customerResponse = customerService.save(createCustomerDTO());
        SuccessResponse<CustomerDTO> customerDTOSuccessResponse =
            (SuccessResponse<CustomerDTO>) customerResponse.getBody();
        CustomerDTO customerDTO = customerDTOSuccessResponse.getData();

        ResponseEntity<BaseResponse> bookResponse = bookService.save(createBookDTO());
        SuccessResponse<BookDTO> bookSuccessResponse = (SuccessResponse<BookDTO>) bookResponse.getBody();
        BookDTO bookDTO = bookSuccessResponse.getData();

        stockService.save(createStockDTO(bookDTO));

        orderDTO = setOrderDTOAttributes(orderDTO, customerDTO,
            Collections.singletonList(createOrderedBookDTO(bookDTO, orderDTO)));

        ResponseEntity<BaseResponse> response = orderService.save(orderDTO);

        assert response.getStatusCode().equals(HttpStatus.OK);
    }

}
