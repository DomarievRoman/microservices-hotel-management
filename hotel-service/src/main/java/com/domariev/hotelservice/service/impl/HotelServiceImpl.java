package com.domariev.hotelservice.service.impl;

import com.domariev.hotelservice.dto.HotelDto;
import com.domariev.hotelservice.exception.HotelNotFoundException;
import com.domariev.hotelservice.mapper.HotelDtoMapper;
import com.domariev.hotelservice.model.Hotel;
import com.domariev.hotelservice.repository.HotelRepository;
import com.domariev.hotelservice.service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    private final HotelDtoMapper mapper = Mappers.getMapper(HotelDtoMapper.class);


    @Transactional
    @Override
    public HotelDto upsert(HotelDto hotelDto) {
        Hotel hotel = mapper.hotelDtoToHotel(hotelDto);
        hotel = hotelRepository.save(hotel);
        if (hotelRepository.findById(hotel.getId()).isPresent()) {
            log.info("create() - hotel: {} saved/updated", hotel);
        } else {
            log.info("create() - failed to save/update hotel");
            throw new HotelNotFoundException("Failed to create/update hotel");
        }
        return mapper.hotelToHotelDto(hotel);
    }

    @Override
    public HotelDto get(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException("Hotel with id: " + id + " not found"));
        log.info("get() - hotel with id {} retrieved", id);
        return mapper.hotelToHotelDto(hotel);
    }

    @Override
    public List<HotelDto> getAll() {
        List<com.domariev.hotelservice.model.Hotel> hotelList = hotelRepository.findAll();
        if (!hotelList.isEmpty()) {
            log.info("getAll() - all hotels retrieved");
            return mapper.hotelListToDto(hotelList);
        } else {
            throw new HotelNotFoundException("There are no hotels yet");
        }
    }


    @Transactional
    @Override
    public void delete(Long id) {
        Hotel existingHotel = hotelRepository.findById(id).orElseThrow(() -> new HotelNotFoundException("Hotel with id: " + id + " not found"));
        hotelRepository.delete(existingHotel);
        log.info("delete() - hotel with id {} deleted", id);
    }

}
