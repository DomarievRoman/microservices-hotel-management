package com.domariev.securitygatewayservice.service;

import com.domariev.securitygatewayservice.dto.AuthenticationRequestDto;
import com.domariev.securitygatewayservice.dto.UserDto;

public interface AuthenticationService {

    UserDto signUp(UserDto userDto);

    AuthenticationRequestDto signIn(AuthenticationRequestDto requestDto);
}
