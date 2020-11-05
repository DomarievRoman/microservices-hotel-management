package com.domariev.hotelservice.transaction;

import com.domariev.hotelservice.model.Room;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionalEntityResponse {

    private Room room;

    private OrderDto orderDto;
}
