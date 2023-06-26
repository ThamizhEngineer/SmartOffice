package com.ss.smartoffice.soservice;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.ss.smartoffice.shared.interceptor.AuthInterceptor;;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "com.ss.smartoffice" })
@EntityScan(basePackages = { "com.ss.smartoffice" })
@ComponentScan(basePackages = { "com.ss.smartoffice" })
@EnableAsync
@EnableEurekaClient
public class SoServiceApplication implements WebMvcConfigurer {

	private static final String[] REQUEST_METHOD_SUPPORTED = { "GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS",
			"HEAD" };
	public static final String EMAIL_TEMPLATE_ENCODING = "UTF-8";

	public static void main(String[] args) {
		SpringApplication.run(SoServiceApplication.class, args);
	}

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

	@Configuration
	public class AppConfig extends WebMvcConfigurerAdapter {

		@Autowired
		AuthInterceptor authInterceptor;

		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(authInterceptor);
		}

	}

	@Bean("asyncThreadPoolTaskExecutor")
	public TaskExecutor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(20);
		executor.setMaxPoolSize(1000);
		executor.setWaitForTasksToCompleteOnShutdown(true);
		executor.setThreadNamePrefix("Async-");
		return executor;
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
