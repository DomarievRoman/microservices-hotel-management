package com.domariev.hotelservice.dto;

import com.domariev.hotelservice.dto.validation.OnCreate;
import com.domariev.hotelservice.dto.validation.OnUpdate;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
public class RoomDto {

    @Null(groups = OnCreate.class)
    @NotNull(groups = OnUpdate.class)
    private Long id;

    @NotBlank(groups = OnCreate.class, message = "Room type cannot be blank")
    private String type;

    @NotNull(groups = OnCreate.class, message = "Amount of sleeping places cannot be null")
    private int sleepingPlacesAmount;

    @NotNull(groups = OnCreate.class, message = "Room's floor cannot be null")
    private int floor;

    private boolean available;

    @Positive
    private BigDecimal price;

    private HotelDto hotel;
}
