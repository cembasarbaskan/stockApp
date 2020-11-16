package com.idea.readingisgood.service;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.idea.readingisgood.AbstractTest;
import com.idea.readingisgood.domain.response.BaseResponse;
import com.idea.readingisgood.domain.response.SuccessResponse;
import com.idea.readingisgood.dto.BookDTO;
import com.idea.readingisgood.dto.CustomerDTO;
import com.idea.readingisgood.dto.OrderDTO;
import com.idea.readingisgood.dto.OrderedBookDTO;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderServiceTest extends AbstractTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BookService bookService;

    @Autowired
    private StockService stockService;

    @Autowired
    private OrderService orderService;

    @org.junit.jupiter.api.Order(1)
    @Test
    public void makeOrder() {
        OrderDTO orderDTO = new OrderDTO();
        ResponseEntity<BaseResponse> customerResponse = customerService.save(createCustomerDTO());

        SuccessResponse<CustomerDTO> customerDTOSuccessResponse =
            (SuccessResponse<CustomerDTO>) customerResponse.getBody();
        CustomerDTO customerDTO = customerDTOSuccessResponse.getData();

        ResponseEntity<BaseResponse> bookResponse = bookService.save(createBookDTO());

        BookDTO bookDTO = ((SuccessResponse<BookDTO>) bookResponse.getBody()).getData();
        stockService.save(createStockDTO(bookDTO));

        Set<OrderedBookDTO> orderedBookDTOS = new HashSet<>();
        orderedBookDTOS.add(createOrderedBookDTO(bookDTO, orderDTO));
        orderDTO = setOrderDTOAttributes(orderDTO, customerDTO, orderedBookDTOS);

        SuccessResponse<OrderDTO> orderDTOSuccessResponse =
            (SuccessResponse<OrderDTO>) orderService.save(orderDTO).getBody();

        assert orderDTOSuccessResponse.getData().getId() != null;
    }

}
