package com.domariev.securitygatewayservice.config;

import com.domariev.securitygatewayservice.security.jwt.JwtConfigurer;
import com.domariev.securitygatewayservice.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String ADMIN_ENDPOINTS = "/api/v1/admin/**";
    private static final String LOGIN_ENDPOINTS = "/api/v1/auth/**";
    private static final String HOTEL_SERVICE_ADMIN_ENDPOINTS = "/hotel/**";
    private static final String HOTEL_SERVICE_USER_ENDPOINTS = "/hotel/get/**";

    private final JwtTokenProvider jwtTokenProvider;

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(LOGIN_ENDPOINTS).permitAll()
                .antMatchers(HOTEL_SERVICE_USER_ENDPOINTS).hasRole("USER")
                .antMatchers(HOTEL_SERVICE_ADMIN_ENDPOINTS).hasRole("ADMIN")
                .antMatchers(ADMIN_ENDPOINTS).hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
