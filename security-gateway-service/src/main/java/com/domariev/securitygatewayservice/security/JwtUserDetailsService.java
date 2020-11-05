package com.domariev.securitygatewayservice.security;

import com.domariev.securitygatewayservice.model.User;
import com.domariev.securitygatewayservice.security.jwt.JwtUser;
import com.domariev.securitygatewayservice.security.jwt.JwtUserFactory;
import com.domariev.securitygatewayservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User with email " + email + " not found");
        }
        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("loadUserByUsername() - user with email: {} successfully loaded", email);
        return jwtUser;
    }
}
