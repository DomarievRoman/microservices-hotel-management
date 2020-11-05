package com.domariev.hotelservice.controller;

import com.domariev.hotelservice.api.RoomApi;
import com.domariev.hotelservice.dto.RoomDto;
import com.domariev.hotelservice.dto.validation.OnCreate;
import com.domariev.hotelservice.service.RoomService;
import com.domariev.hotelservice.transaction.TransactionalEntityRequest;
import com.domariev.hotelservice.transaction.TransactionalEntityResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RoomController implements RoomApi {

    private final RoomService roomService;

    @Override
    public RoomDto addRoom(@RequestBody @Validated(OnCreate.class) RoomDto roomDto, @PathVariable Long hotelId) {
        log.info("addRoom() - about to add room  in hotel №{}", hotelId);
        return roomService.addRoom(roomDto, hotelId);
    }

    public TransactionalEntityResponse bookRoom(@RequestBody TransactionalEntityRequest transactionalEntityRequest, @PathVariable Long roomId) {
        return roomService.bookRoom(transactionalEntityRequest, roomId);
    }

}
