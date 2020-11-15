package com.idea.readingisgood.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.idea.readingisgood.dto.OrderDTO;
import com.idea.readingisgood.dto.OrderedBookDTO;
import com.idea.readingisgood.entity.Customer;
import com.idea.readingisgood.entity.Order;
import com.idea.readingisgood.entity.Stock;
import com.idea.readingisgood.entity.response.BaseResponse;
import com.idea.readingisgood.entity.response.SuccessResponse;
import com.idea.readingisgood.mapper.OrderMapper;
import com.idea.readingisgood.repository.BookRepository;
import com.idea.readingisgood.repository.CustomerRepository;
import com.idea.readingisgood.repository.OrderRepository;
import com.idea.readingisgood.validator.SavingItemIdCheck;

@Service
public class OrderService extends BaseService<Order, OrderDTO> {
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final StockService stockService;
    private final CustomerRepository customerRepository;

    public OrderService(OrderMapper orderMapper, OrderRepository orderRepository, StockService stockService,
        CustomerRepository customerRepository, BookRepository bookRepository) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.stockService = stockService;
        this.customerRepository = customerRepository;

    }

    @Override
    public ResponseEntity<BaseResponse> fetchAll(int start, int size) {
        List<Order> orderList = orderRepository.findAll(createPageRequest(start, size, "changeTime")).toList();
        List<OrderDTO> orderDTOList = orderList.stream().map(orderMapper::entityToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(SuccessResponse.<List<OrderDTO>>builder().data(orderDTOList).build());
    }

    @Override
    public ResponseEntity<BaseResponse> fetchOneById(String id) {
        Optional<Order> optOrder = orderRepository.findById(id);
        return optOrder.<ResponseEntity<BaseResponse>>map(order -> ResponseEntity.ok(
            SuccessResponse.<OrderDTO>builder().data(orderMapper.entityToDTO(optOrder.get())).build())).orElseThrow();
    }

    @Override
    public ResponseEntity<BaseResponse> deleteOneById(String id) {
        try {
            orderRepository.deleteById(id);
            return ResponseEntity.ok(
                SuccessResponse.<String>builder().message("Deletion was successful").status(HttpStatus.OK).build());
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchElementException("No order found for deletion", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<BaseResponse> save(@SavingItemIdCheck(propName = Order.class) OrderDTO orderDTO) {
        checkAndUpdateStockWithNewOrder(orderDTO);
        Order savedOrder = orderRepository.save(orderMapper.prepareOrderDTOForCreation(orderDTO));
        return ResponseEntity.ok(SuccessResponse.<OrderDTO>builder().data(orderMapper.entityToDTO(savedOrder))
            .status(HttpStatus.OK)
            .build());
    }

    @Override
    public ResponseEntity<BaseResponse> update(OrderDTO dto) {
        Optional<Order> order = orderRepository.findById(dto.getId());
        if (order.isEmpty()) {
            throw new NoSuchElementException("There is no order for update");
        }
        OrderDTO savedOrder = orderMapper.entityToDTO(orderRepository.save(orderMapper.dtoToEntity(dto)));
        return ResponseEntity.ok(SuccessResponse.<OrderDTO>builder().data(savedOrder).build());
    }

    public ResponseEntity<BaseResponse> fetchWithCustomer(String customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isEmpty()) {
            throw new NoSuchElementException("Customer not found");
        }
        List<Order> orders = orderRepository.findAllByCustomer(customer.get());
        List<OrderDTO> orderDTOS = orders.stream().map(orderMapper::entityToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(SuccessResponse.<List<OrderDTO>>builder().data(orderDTOS).build());
    }

    private void checkAndUpdateStockWithNewOrder(OrderDTO orderDTO) {
        for (OrderedBookDTO orderedBookDTO : orderDTO.getOrderedBooks()) {
            Stock stock = stockService.findStockWithBookId(orderedBookDTO.getBookId());
            if (stock != null && stock.getPiece() >= orderedBookDTO.getPiece()) {
                stock.setPiece(stock.getPiece() - orderedBookDTO.getPiece());
            } else {
                throw new NoSuchElementException(
                    "Book does not found in stock or insufficient book piece in stock" + orderedBookDTO.toString());
            }
        }
    }
}
