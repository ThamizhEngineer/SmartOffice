package com.ss.smartoffice.healthcheck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class SoHealthCheckApplication implements WebMvcConfigurer {	
	
	private static final String[] REQUEST_METHOD_SUPPORTED = { "GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD" };
	
	public static void main(String[] args) {
		SpringApplication.run(SoHealthCheckApplication.class, args);
	}
	
	@Bean
	 public WebMvcConfigurer corsConfigurer() {
	     return new WebMvcConfigurer() {
	         @Override
	         public void addCorsMappings(CorsRegistry registry) {
	             registry.addMapping("/**").allowedOrigins("*").allowedHeaders("*").allowedMethods(REQUEST_METHOD_SUPPORTED);
	         }
	     };
	 }
}
