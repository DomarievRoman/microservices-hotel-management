package com.domariev.securitygatewayservice.service.impl;

import com.domariev.securitygatewayservice.dto.AuthenticationRequestDto;
import com.domariev.securitygatewayservice.dto.UserDto;
import com.domariev.securitygatewayservice.mapper.UserDtoMapper;
import com.domariev.securitygatewayservice.model.Role;
import com.domariev.securitygatewayservice.model.User;
import com.domariev.securitygatewayservice.model.enums.Status;
import com.domariev.securitygatewayservice.repository.RoleRepository;
import com.domariev.securitygatewayservice.repository.UserRepository;
import com.domariev.securitygatewayservice.security.jwt.JwtTokenProvider;
import com.domariev.securitygatewayservice.service.AuthenticationService;
import com.domariev.securitygatewayservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    private UserDtoMapper mapper = Mappers.getMapper(UserDtoMapper.class);

    @Override
    public UserDto signUp(UserDto userDto) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        Date date = new Date();
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);
        User user = mapper.userDtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreated(date);
        user.setUpdated(date);
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);
        User registeredUser = userRepository.save(user);
        log.info("register() - user: {} successfully registered", registeredUser);
        return mapper.userToUserDto(registeredUser);
    }

    @Override
    public AuthenticationRequestDto signIn(AuthenticationRequestDto requestDto) {
        try {
            String email = requestDto.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, requestDto.getPassword()));
            User user = userService.findByEmail(email);
            if (user == null) {
                throw new UsernameNotFoundException("User with email " + email + "not found");
            }
            String token = jwtTokenProvider.createToken(email, user.getRoles());
            return new AuthenticationRequestDto(email, token);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email or password");
        }
    }
}
