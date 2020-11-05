package com.domariev.hotelservice.service;

import com.domariev.hotelservice.dto.HotelDto;

import java.util.List;

public interface HotelService {

    HotelDto upsert(HotelDto hotelDto);

    HotelDto get(Long id);

    List<HotelDto> getAll();

    void delete(Long id);

}
