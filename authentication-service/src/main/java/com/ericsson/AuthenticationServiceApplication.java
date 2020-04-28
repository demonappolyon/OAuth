package com.ericsson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.ericsson.config.AppProperties;

@SpringBootApplication(scanBasePackages = {"com.ericsson.repository"})
@EnableConfigurationProperties(AppProperties.class)
public class AuthenticationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServiceApplication.class, args);
	}

}
