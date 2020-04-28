package com.ericsson.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	private final long Max_AGE_SECS = 3600;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("*")
		.allowedMethods("GET","PUT","POST","PATCH","DELETE","OPTIONS")
		.allowedHeaders("*")
		.allowCredentials(true)
		.maxAge(Max_AGE_SECS);
	}
}
