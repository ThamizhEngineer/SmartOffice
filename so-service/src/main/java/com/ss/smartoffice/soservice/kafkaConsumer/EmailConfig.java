// package com.ss.smartoffice.soservice.kafkaConsumer;

// import java.io.IOException;
// import java.util.Collections;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.Properties;

// import org.springframework.beans.BeansException;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
// import org.springframework.context.ApplicationContext;
// import org.springframework.context.ApplicationContextAware;
// import org.springframework.context.EnvironmentAware;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.PropertySource;
// import org.springframework.core.env.Environment;
// import org.springframework.mail.javamail.JavaMailSender;
// import org.springframework.mail.javamail.JavaMailSenderImpl;
// import org.thymeleaf.templatemode.TemplateMode;
// import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
// import org.thymeleaf.templateresolver.ITemplateResolver;

// import com.ss.smartoffice.shared.busconfig.ConfigBusHelper;
// import com.ss.smartoffice.shared.model.Config;

// @Configuration
// @EnableAutoConfiguration
// @PropertySource("classpath:mail/emailconfig.properties")
// public class EmailConfig implements ApplicationContextAware, EnvironmentAware {
	

// 	@Autowired
// 	ConfigBusHelper configBusHelper;
	
	
	
// 	  public static final String EMAIL_TEMPLATE_ENCODING = "UTF-8";

// 	    private static final String JAVA_MAIL_FILE = "classpath:mail/javamail.properties";

// 	    private static final String HOST = "mail.server.host";
// 	    private static final String PORT = "mail.server.port";
// 	    private static final String PROTOCOL = "mail.server.protocol";

// 	    private static final String USERNAME = "mail.username";
// 	    private static final String PASSWORD = "mail.password";

// 	    private ApplicationContext applicationContext;
//         private Environment environment;

//         @Override
//         public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
//             this.applicationContext = applicationContext;
//         }

//         @Override
//         public void setEnvironment(final Environment environment) {
//             this.environment = environment;
//         }
// 	 /*
//      * SPRING + JAVAMAIL: JavaMailSender instance, configured via .properties files.
//      */
//     @Bean
//     public JavaMailSender mailSender() throws IOException {

//         final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        
//         Map<String,String> senderEmail = senderEmailDetails();

//         // Basic mail sender configuration, based on emailconfig.properties
//         mailSender.setHost(this.environment.getProperty(HOST));
//         mailSender.setPort(Integer.parseInt(this.environment.getProperty(PORT)));
//         mailSender.setProtocol(this.environment.getProperty(PROTOCOL));
// //        mailSender.setUsername(senderEmail.get("sender-email-id"));
// //        mailSender.setPassword(senderEmail.get("sender-email-password"));
//         mailSender.setUsername(this.environment.getProperty(USERNAME));
//         mailSender.setPassword(this.environment.getProperty(PASSWORD));
      
//         // JavaMail-specific mail sender configuration, based on javamail.properties
//         final Properties javaMailProperties = new Properties();
//         javaMailProperties.load(this.applicationContext.getResource(JAVA_MAIL_FILE).getInputStream());
//         mailSender.setJavaMailProperties(javaMailProperties);

//         return mailSender;

//     }
    
//     @Bean
//     public ITemplateResolver htmlTemplateResolver() {
//         final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//         templateResolver.setOrder(Integer.valueOf(2));
//         templateResolver.setResolvablePatterns(Collections.singleton("html/*"));
//         templateResolver.setPrefix("/mail/");
//         templateResolver.setSuffix(".html");
//         templateResolver.setTemplateMode(TemplateMode.HTML);
//         templateResolver.setCharacterEncoding(EMAIL_TEMPLATE_ENCODING);
//         templateResolver.setCacheable(false);
//         return templateResolver;
//     }
    
//     public Map<String,String> senderEmailDetails(){
// 		Map<String,String> senderEmail =  new HashMap<String,String>();
// 		String senderEmailId="";
// 		String senderEmailPassword="";
// 		//configUrl = configUrl+"?configDtlCode=SENDER-EMAIL";
// 		Iterable<Config> respConfig = configBusHelper.getConfig("SENDER-EMAIL", null);
		
// 		for(Config config:respConfig) {
	
// 			if(config.getConfigDtlName().equals("Sender Email Id")) {
// 				senderEmailId = config.getConfigDtlValue();
// 			}
// 			if(config.getConfigDtlName().equals("Sender Email Password")) {
// 				senderEmailPassword = config.getConfigDtlValue();
// 			}
// 			break; //use only first record
			
// 		}
		
// 		senderEmail.put("sender-email-id", senderEmailId);
// 		senderEmail.put("sender-email-password", senderEmailPassword);

			
// 		return senderEmail;
// 	}

// }
