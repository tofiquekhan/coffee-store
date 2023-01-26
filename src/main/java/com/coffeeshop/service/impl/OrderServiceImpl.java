package com.coffeeshop.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coffeeshop.dto.OrderDTO;
import com.coffeeshop.entity.Order;
import com.coffeeshop.exception.OrderNotFoundException;
import com.coffeeshop.repository.OrderRepository;
import com.coffeeshop.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

public class OrderServiceImpl implements OrderService{
	
	@Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OrderDTO saveUpdateOrder(OrderDTO orderDTO) {
        log.info("saveUpdateOrder method started Order name :: "+orderDTO.getName());
        Order order = modelMapper.map(orderDTO,Order.class);
          Order savedOrder = orderRepository.saveAndFlush(order);
          OrderDTO savedOrderDTO = modelMapper.map(savedOrder,OrderDTO.class);
        log.info("Order Saved Successfully orderId :: "+savedOrder.getId());
        return savedOrderDTO;
    }

    @Override
    public List<OrderDTO> getOrders() {
        log.info("getOrders method started.");
        List<Order> orders = (List<Order>) orderRepository.findAll();
        List<OrderDTO> orderDTOS = orders.stream().map(order -> modelMapper.map(order, OrderDTO.class)).collect(Collectors.toList());
        log.info("getOrder method completed total orders :: "+orders.size());
        return orderDTOS;
    }

    @Override
    public void removeOrderDetailsById(Long id) {
        log.info("removeOrder method started order id ::"+id);
        getOrderDetailsById(id);
        orderRepository.deleteById(id);
        log.info("removeOrder method ended");
    }
    @Override
    public OrderDTO getOrderDetailsById(Long id) {
        log.info("getOrder method started order id ::"+id);
        Optional<Order> orderOptional = orderRepository.findById(id);
        if(orderOptional.isEmpty()){
            log.error("getOrder Order not found with id ::"+id);
            throw new OrderNotFoundException("Order not found with id ::"+id);
        }
        Order order = orderOptional.get();
        OrderDTO orderDTO = modelMapper.map(order,OrderDTO.class);
        log.info("getOrder method ended");
        return orderDTO;
    }


}
