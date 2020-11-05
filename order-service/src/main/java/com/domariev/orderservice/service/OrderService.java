package com.domariev.orderservice.service;

import com.domariev.orderservice.dto.OrderDto;
import com.domariev.orderservice.model.Order;

public interface OrderService {

    OrderDto create(OrderDto orderDto);
}
