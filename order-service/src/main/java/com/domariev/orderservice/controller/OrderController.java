package com.domariev.orderservice.controller;

import com.domariev.orderservice.dto.OrderDto;
import com.domariev.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping(value = "/doOrder")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto doOrder (@RequestBody OrderDto orderDto) {
        log.info("doOrder() - about to create order: {}", orderDto);
        return orderService.create(orderDto);
    }
}
