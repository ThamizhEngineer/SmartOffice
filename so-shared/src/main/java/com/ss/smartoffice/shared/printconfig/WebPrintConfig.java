package com.ss.smartoffice.shared.printconfig;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import com.ss.smartoffice.shared.printviewresolver.CsvViewResolver;
import com.ss.smartoffice.shared.printviewresolver.ExcelViewResolver;
import com.ss.smartoffice.shared.printviewresolver.JsonViewResolver;
import com.ss.smartoffice.shared.printviewresolver.PdfViewResolver;



@Configuration
@EnableWebMvc
public class WebPrintConfig implements WebMvcConfigurer {
	 @Override
	    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
	        configurer
	        .favorPathExtension(true) 
	        .defaultContentType(MediaType.APPLICATION_JSON);
	    }
	
	  @Override
	    public void configurePathMatch(PathMatchConfigurer configurer) {
	        configurer
	            // ...
	            .setUseSuffixPatternMatch(true);
	    }

	    /*
	     * Configure ContentNegotiatingViewResolver
	     */
	    @Bean
	    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
	        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
	        resolver.setContentNegotiationManager(manager);

	        // Define all possible view resolvers
	        List<ViewResolver> resolvers = new ArrayList<>();
	        resolvers.add(jsonViewResolver());
	        resolvers.add(excelViewResolver());
	        resolvers.add(csvViewResolver());
	        resolvers.add(pdfViewResolver());
	        System.out.println(resolvers);
	        resolver.setViewResolvers(resolvers);
	        return resolver;
	    }


	    @Bean
	    public ViewResolver jsonViewResolver() {
	        return new JsonViewResolver();
	    }
	    /*
	     * Configure View resolver to provide XLS output using Apache POI library to
	     * generate XLS output for an object content
	     */
	    @Bean
	    public ViewResolver excelViewResolver() {
	        return new ExcelViewResolver();
	    }

	    /*
	     * Configure View resolver to provide Csv output using Super Csv library to
	     * generate Csv output for an object content
	     */
	    @Bean
	    public ViewResolver csvViewResolver() {
	        return new CsvViewResolver();
	    }

	    /*
	     * Configure View resolver to provide Pdf output using iText library to
	     * generate pdf output for an object content
	     */
	    @Bean
	    public ViewResolver pdfViewResolver() {
	        return new PdfViewResolver();
	    }

}
