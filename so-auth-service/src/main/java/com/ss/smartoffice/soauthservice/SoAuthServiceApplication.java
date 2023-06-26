package com.ss.smartoffice.soauthservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


import com.ss.smartoffice.shared.interceptor.AuthInterceptor;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.ss.smartoffice"})
@EntityScan(basePackages = {"com.ss.smartoffice"})
@ComponentScan(basePackages = {"com.ss.smartoffice"})
@EnableEurekaClient
public class SoAuthServiceApplication  implements WebMvcConfigurer{

	
	private static final String[] REQUEST_METHOD_SUPPORTED = { "GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD" };
	  
	 
	public static void main(String[] args) {
		SpringApplication.run(SoAuthServiceApplication.class, args);
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
	 @Configuration
		public class AppConfig extends WebMvcConfigurerAdapter {
			
			@Autowired
			AuthInterceptor authInterceptor;
			
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				registry.addInterceptor(authInterceptor);
			}

}
	
	 
	 }
	

