package com.domariev.securitygatewayservice.controller;

import com.domariev.securitygatewayservice.dto.AuthenticationRequestDto;
import com.domariev.securitygatewayservice.dto.UserDto;
import com.domariev.securitygatewayservice.dto.validation.OnSignIn;
import com.domariev.securitygatewayservice.dto.validation.OnSignUp;
import com.domariev.securitygatewayservice.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @RequestMapping("signin")
    public AuthenticationRequestDto signIn(@RequestBody @Validated(OnSignIn.class) AuthenticationRequestDto requestDto) {
        return authenticationService.signIn(requestDto);
    }

    @RequestMapping("signup")
    public UserDto signUp(@RequestBody @Validated(OnSignUp.class) UserDto userDto) {
        return authenticationService.signUp(userDto);
    }
}
