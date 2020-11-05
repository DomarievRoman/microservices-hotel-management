package com.domariev.securitygatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class SecurityGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityGatewayServiceApplication.class, args);
	}

}
