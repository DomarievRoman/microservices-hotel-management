package com.domariev.hotelservice.service;

import com.domariev.hotelservice.dto.RoomDto;
import com.domariev.hotelservice.transaction.TransactionalEntityRequest;
import com.domariev.hotelservice.transaction.TransactionalEntityResponse;

public interface RoomService {

    RoomDto addRoom(RoomDto roomDto, Long hotelId);

    TransactionalEntityResponse bookRoom(TransactionalEntityRequest transactionalEntityRequest, Long roomId);
}
