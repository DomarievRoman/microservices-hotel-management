package com.domariev.hotelservice.api;

import com.domariev.hotelservice.dto.RoomDto;
import com.domariev.hotelservice.dto.validation.OnCreate;
import com.domariev.hotelservice.transaction.TransactionalEntityRequest;
import com.domariev.hotelservice.transaction.TransactionalEntityResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Room management REST API")

@RequestMapping("/api/v1/room")
public interface RoomApi {

    @ApiOperation("Create room in hotel")
    @ApiResponse(code = 200, message = "CREATED", response = RoomDto.class)
    @PostMapping("/addRoomInHotel/{hotelId}")
    @ResponseStatus(HttpStatus.CREATED)
    RoomDto addRoom(@RequestBody @Validated(OnCreate.class) RoomDto roomDto, @PathVariable Long hotelId);

    @ApiOperation("Book room in hotel")
    @ApiResponse(code = 200, message = "OK", response = TransactionalEntityResponse.class)
    @PostMapping("/bookRoom/{roomId}")
    @ResponseStatus(HttpStatus.CREATED)
    TransactionalEntityResponse bookRoom(@RequestBody TransactionalEntityRequest transactionalEntityRequest, @PathVariable Long roomId);
}
