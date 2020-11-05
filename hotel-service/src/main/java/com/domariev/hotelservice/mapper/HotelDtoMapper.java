package com.domariev.hotelservice.mapper;

import com.domariev.hotelservice.dto.HotelDto;
import com.domariev.hotelservice.model.Hotel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface HotelDtoMapper {

    HotelDto hotelToHotelDto(Hotel hotel);

    Hotel hotelDtoToHotel(HotelDto hotelDto);

    List<HotelDto> hotelListToDto(List<Hotel> hotelList);
}
