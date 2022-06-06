package com.delivery.delivery.controller;


import com.delivery.delivery.dto.OrderDto;
import com.delivery.delivery.dto.OrderRequestDto;
import com.delivery.delivery.model.Order;
import com.delivery.delivery.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order/request")
    public OrderDto registerOrder(@RequestBody OrderRequestDto orderRequestDto){
        return orderService.registerOrder(orderRequestDto);
    }

    @GetMapping("/orders")
    public List<Order> getOrder() {
        List<Order> orderList = orderService.getOrder();
        return orderList;
    }
}
