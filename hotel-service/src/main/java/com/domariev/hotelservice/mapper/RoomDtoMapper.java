package com.domariev.hotelservice.mapper;

import com.domariev.hotelservice.dto.RoomDto;
import com.domariev.hotelservice.model.Room;
import org.mapstruct.Mapper;

@Mapper
public interface RoomDtoMapper {

    RoomDto roomToRoomDto(Room room);

    Room roomDtoToRoom(RoomDto roomDto);
}
