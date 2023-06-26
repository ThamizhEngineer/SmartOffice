package com.ss.smartoffice.sonotificationservice;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.ss.smartoffice.shared.interceptor.AuthInterceptor;




@SpringBootApplication
@EnableJpaRepositories(basePackages = { "com.ss.smartoffice" })
@EntityScan(basePackages = { "com.ss.smartoffice" })
@ComponentScan(basePackages = { "com.ss.smartoffice" })
@EnableEurekaClient
public class SoNotificationServiceApplication implements WebMvcConfigurer {
	private static final String[] REQUEST_METHOD_SUPPORTED = { "GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS",
			"HEAD" };
	public static final String EMAIL_TEMPLATE_ENCODING = "UTF-8";

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedHeaders("*")
						.allowedMethods(REQUEST_METHOD_SUPPORTED);
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SoNotificationServiceApplication.class, args);
	}

	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
	}

	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
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
	

    @Bean
    public ITemplateResolver htmlTemplateResolver() {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setOrder(Integer.valueOf(2));
        templateResolver.setResolvablePatterns(Collections.singleton("html/*"));
        templateResolver.setPrefix("/mail/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding(EMAIL_TEMPLATE_ENCODING);
        templateResolver.setCacheable(false);
        return templateResolver;
    }
    
}
