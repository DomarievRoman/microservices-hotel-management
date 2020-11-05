package com.domariev.securitygatewayservice.mapper;

import com.domariev.securitygatewayservice.dto.UserDto;
import com.domariev.securitygatewayservice.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserDtoMapper {

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);
}
