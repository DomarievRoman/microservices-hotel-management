package com.domariev.orderservice.mapper;

import com.domariev.orderservice.dto.OrderDto;
import com.domariev.orderservice.model.Order;
import org.mapstruct.Mapper;

@Mapper
public interface OrderDtoMapper {

    OrderDto orderToOrderDto(Order order);

    Order orderDtoToOrder(OrderDto orderDto);
}
