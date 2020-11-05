package com.domariev.hotelservice.controller;

import com.domariev.hotelservice.api.HotelApi;
import com.domariev.hotelservice.dto.HotelDto;
import com.domariev.hotelservice.dto.validation.OnCreate;
import com.domariev.hotelservice.dto.validation.OnUpdate;
import com.domariev.hotelservice.service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class HotelController implements HotelApi {

    private final HotelService hotelService;

    @Override
    public HotelDto addHotel(@RequestBody @Validated(OnCreate.class) HotelDto hotelDto) {
        log.info("addHotel() - about to add hotel {}", hotelDto);
        return hotelService.upsert(hotelDto);
    }

    @Override
    public HotelDto getHotelById(@PathVariable Long id) {
        log.info("getHotelById() - about to retrieve hotel by id {}", id);
        return hotelService.get(id);
    }

    @Override
    public List<HotelDto> getAllHotels() {
        log.info("getAllHotels() - about to retrieve all hotels");
        return hotelService.getAll();
    }

    @Override
    public HotelDto updateHotel(@RequestBody @Validated(OnUpdate.class) HotelDto hotelDto) {
        log.info("updateHotel() - about to update hotel with id {}", hotelDto.getId());
        return hotelService.upsert(hotelDto);
    }


    @Override
    public void deleteHotel(@PathVariable Long id) {
        log.info("deleteHotel() - about to delete hotel with id {}", id);
        hotelService.delete(id);
    }
}
