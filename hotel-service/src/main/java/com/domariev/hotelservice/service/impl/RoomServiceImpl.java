package com.domariev.hotelservice.service.impl;

import com.domariev.hotelservice.dto.RoomDto;
import com.domariev.hotelservice.exception.HotelNotFoundException;
import com.domariev.hotelservice.exception.RoomNotFoundException;
import com.domariev.hotelservice.mapper.RoomDtoMapper;
import com.domariev.hotelservice.model.Hotel;
import com.domariev.hotelservice.model.Room;
import com.domariev.hotelservice.repository.HotelRepository;
import com.domariev.hotelservice.repository.RoomRepository;
import com.domariev.hotelservice.service.RoomService;
import com.domariev.hotelservice.transaction.OrderDto;
import com.domariev.hotelservice.transaction.TransactionalEntityRequest;
import com.domariev.hotelservice.transaction.TransactionalEntityResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    private final HotelRepository hotelRepository;

    private final RestTemplate restTemplate;

    private final RoomDtoMapper mapper = Mappers.getMapper(RoomDtoMapper.class);

    @Transactional
    @Override
    public RoomDto addRoom(RoomDto roomDto, Long hotelId) {
        Room room = mapper.roomDtoToRoom(roomDto);
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new HotelNotFoundException("Hotel with id: " + hotelId + " not found"));
        log.info("addRoom() - hotel with id {} founded", hotelId);
        room.setHotel(hotel);
        room = roomRepository.save(room);
        if (roomRepository.findById(room.getId()).isPresent()) {
            log.info("addRoom() - room with id {} saved", room.getId());
        } else {
            log.info("addRoom() - failed to save room");
            throw new RoomNotFoundException("Failed to create room");
        }
        return mapper.roomToRoomDto(room);
    }

    @Override
    public TransactionalEntityResponse bookRoom(TransactionalEntityRequest requestTransactionalEntityRequest, Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(()->new RoomNotFoundException("Room not found"));
        OrderDto orderDto = requestTransactionalEntityRequest.getOrderDto();
        orderDto.setRoomId(room.getId());
        OrderDto orderDtoResponse =restTemplate.
                postForObject("http://localhost:8002/order/doOrder", orderDto, OrderDto.class);
        return new TransactionalEntityResponse(room,orderDtoResponse);
    }
}
