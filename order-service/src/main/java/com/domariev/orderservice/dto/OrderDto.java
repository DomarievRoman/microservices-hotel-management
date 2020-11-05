package com.domariev.orderservice.dto;

import com.domariev.orderservice.dto.validation.OnCreate;
import com.domariev.orderservice.model.BankCard;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class OrderDto {

    @Null(groups = OnCreate.class)
    private Long id;

    @NotBlank(groups = OnCreate.class, message = "Order number cannot be blank")
    private String orderNum;

    private BankCard bankCard;

    @NotNull(message = "Room id cannot be null")
    private Long roomId;
}
