package com.domariev.orderservice.service.impl;


import com.domariev.orderservice.dto.OrderDto;
import com.domariev.orderservice.exception.BankCardNotFoundException;
import com.domariev.orderservice.mapper.OrderDtoMapper;
import com.domariev.orderservice.model.BankCard;
import com.domariev.orderservice.model.Order;
import com.domariev.orderservice.repository.BankCardRepository;
import com.domariev.orderservice.repository.OrderRepository;
import com.domariev.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    public final OrderRepository orderRepository;

    public final BankCardRepository bankCardRepository;

    private final OrderDtoMapper orderDtoMapper = Mappers.getMapper(OrderDtoMapper.class);

    @Override
    public OrderDto create(OrderDto orderDto) {
        Order order = orderDtoMapper.orderDtoToOrder(orderDto);
        order.setOrderNum(UUID.randomUUID().toString());
        BankCard bankCard = bankCardRepository.save(order.getBankCard());
        if (!bankCardRepository.findById(bankCard.getId()).isPresent()) {
            throw new BankCardNotFoundException("Bank card not found");
        }
        order.setBankCard(bankCard);
        orderRepository.save(order);
        return orderDtoMapper.orderToOrderDto(order);
    }
}
