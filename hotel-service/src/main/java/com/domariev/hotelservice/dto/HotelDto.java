package com.domariev.hotelservice.dto;

import com.domariev.hotelservice.dto.validation.OnCreate;
import com.domariev.hotelservice.dto.validation.OnUpdate;
import com.domariev.hotelservice.model.Room;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelDto {

    @Null(groups = OnCreate.class)
    @NotNull(groups = OnUpdate.class)
    private Long id;

    @NotBlank(groups = OnCreate.class, message = "Name of the hotel cannot be blank")
    @Size(min = 3, message = "Name should contains at least 3 symbols")
    private String name;

    @NotBlank(groups = OnCreate.class, message = "Hotel address cannot be blank")
    private String address;

    @NotBlank(groups = OnCreate.class)
    @Pattern(regexp = "^\\+[0-9]{3}\\((\\d+)\\)\\d{3}-\\d{2}-\\d{2}$")
    private String phoneNumber;

    @NotNull(groups = OnCreate.class, message = "Rating of the hotel cannot be blank")
    @Range(min = 1, max = 5)
    private int rating;

    private List<Room> rooms;
}
