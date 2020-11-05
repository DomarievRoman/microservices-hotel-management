package com.domariev.securitygatewayservice.service;

import com.domariev.securitygatewayservice.dto.UserDto;
import com.domariev.securitygatewayservice.model.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User findByEmail(String email);

    UserDto findById(Long id);

    void delete(Long id);

}
