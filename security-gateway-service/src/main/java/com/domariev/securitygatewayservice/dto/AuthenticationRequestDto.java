package com.domariev.securitygatewayservice.dto;

import com.domariev.securitygatewayservice.dto.validation.OnSignIn;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class AuthenticationRequestDto {
    @Email
    @NotBlank(groups = OnSignIn.class)
    private String email;
    @NotBlank(groups = OnSignIn.class)
    private String password;
}
