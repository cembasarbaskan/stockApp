package com.idea.readingisgood.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.idea.readingisgood.dto.OrderDTO;
import com.idea.readingisgood.entity.response.BaseResponse;
import com.idea.readingisgood.service.OrderService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "order")
@Validated
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getOrder(@PathVariable @NotEmpty String orderId) {
        return orderService.fetchOneById(orderId);
    }

    @GetMapping(path = "/customer")
    public ResponseEntity<BaseResponse> getOrdersWithCustomer(@PathVariable @NotEmpty String customerId) {
        return orderService.fetchWithCustomer(customerId);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<BaseResponse> getListOrder(
        @RequestParam(name = "startIndex", required = false) Integer startIndex,
        @RequestParam(name = "size", required = false) Integer size) {
        return orderService.fetchAll(startIndex, size);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createOrder(@RequestBody @Valid OrderDTO orderDTO) {
        return orderService.save(orderDTO);
    }

    @PutMapping
    public ResponseEntity<BaseResponse> updateOrder(@RequestBody @Valid OrderDTO orderDTO) {
        return orderService.update(orderDTO);
    }

}
