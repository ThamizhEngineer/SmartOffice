//package com.ss.smartoffice.sonotificationservice.config;
//
//import java.io.IOException;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.EnvironmentAware;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.thymeleaf.templatemode.TemplateMode;
//import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
//import org.thymeleaf.templateresolver.ITemplateResolver;
//
//import com.ss.smartoffice.shared.busconfig.ConfigBusHelper;
//import com.ss.smartoffice.shared.model.Config;
//
//@Configuration
//@EnableAutoConfiguration
////@PropertySource("classpath:mail/emailconfig.properties")
//public class EmailConfig  {
//	
//
//	@Autowired
//	ConfigBusHelper configBusHelper;
//	
//	
//	
//	  public static final String EMAIL_TEMPLATE_ENCODING = "UTF-8";
//    @Bean
//    public ITemplateResolver htmlTemplateResolver() {
//        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//        templateResolver.setOrder(Integer.valueOf(2));
//        templateResolver.setResolvablePatterns(Collections.singleton("html/*"));
//        templateResolver.setPrefix("/mail/");
//        templateResolver.setSuffix(".html");
//        templateResolver.setTemplateMode(TemplateMode.HTML);
//        templateResolver.setCharacterEncoding(EMAIL_TEMPLATE_ENCODING);
//        templateResolver.setCacheable(false);
//        return templateResolver;
//    }
//     
//
//}
