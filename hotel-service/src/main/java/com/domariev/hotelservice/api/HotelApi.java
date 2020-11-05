package com.domariev.hotelservice.api;

import com.domariev.hotelservice.dto.HotelDto;
import com.domariev.hotelservice.dto.validation.OnCreate;
import com.domariev.hotelservice.dto.validation.OnUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Hotel management REST API")

@RequestMapping("/api/v1/hotel")
public interface HotelApi {

    @ApiOperation("Create hotel")
    @ApiResponse(code = 200, message = "CREATED", response = HotelDto.class)
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    HotelDto addHotel(@RequestBody @Validated(OnCreate.class) HotelDto hotelDto);

    @ApiOperation("Get hotel by id")
    @ApiResponse(code = 200, message = "OK", response = HotelDto.class)
    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    HotelDto getHotelById(@PathVariable Long id);

    @ApiOperation("Get all hotels")
    @ApiResponse(code = 200, message = "OK", response = List.class)
    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    List<HotelDto> getAllHotels();

    @ApiOperation("Update hotel")
    @ApiResponse(code = 200, message = "OK", response = HotelDto.class)
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    HotelDto updateHotel(@RequestBody @Validated(OnUpdate.class) HotelDto hotelDto);

    @ApiOperation("Delete hotel")
    @ApiResponse(code = 200, message = "No content")
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteHotel(@PathVariable Long id);
}
