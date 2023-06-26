package com.ss.smartoffice.sonotificationservice.common;

import java.io.IOException;
import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.notifn.Email;
import com.ss.smartoffice.sonotificationservice.kafkaConsumer.KafkaEmailTopicConsumer;
import com.ss.smartoffice.sonotificationservice.transaction.event.Event;

@RestController
@RequestMapping(path ="notfn/")
public class EmailHelper {

	@Autowired
	CommonUtils commonUtils;
	@Autowired
	private JavaMailSender mailSender;


	private static Logger log = LoggerFactory.getLogger(KafkaEmailTopicConsumer.class);


	@PostMapping("/send-event-by-email")
	public String sendEventByEmail(@RequestBody Event event)throws SmartOfficeException {
		try {

			Email email = new Email();
			email.setEmailSubject(event.getNotificationKeys().get(0).getEmailSubject());
			email.setEmailBody(event.getNotificationKeys().get(0).getEmailMessage());
			email.setFromEmailId(commonUtils.getSenderEmail());
			email.setToEmailId(event.getNotificationKeys().get(0).getEmailId());
			return sendEmail(email);
			
		} catch (Exception e) {
			throw new SmartOfficeException(e);
		}
	}
	
	@PostMapping("/send-by-email")
	public String sendByEmail(@RequestBody Email email)throws SmartOfficeException {
		try {
			System.out.println(email);
			return sendEmail(email);
			
		} catch (Exception e) {
			throw new SmartOfficeException(e);
		}
	}
	
	private String sendEmail(Email email) {
		String result = "Success";
		try {
			if(email.getFromEmailId()== null || email.getFromEmailId().isEmpty()) {
				email.setFromEmailId(commonUtils.getSenderEmail());
			}
			final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
			final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true /* multipart */,
					"UTF-8");
			mimeMessageHelper.setSubject(email.getEmailSubject());
			mimeMessageHelper.setFrom(email.getFromEmailId());
			mimeMessageHelper.setTo(email.getToEmailId());
			mimeMessageHelper.setText(email.getEmailBody(), true);
			// send mail
			this.mailSender.send(mimeMessage);
			log.info("Email to "+email.getToEmailId()+" with subject("+email.getEmailSubject()+")","Email Sent Succesfully at "+ new Date());
		} catch (Exception e) {
			result = "Failure - Email to "+email.getToEmailId()+" with subject("+email.getEmailSubject()+") at "+ new Date(); 
			log.error(result, e); 
		}
		return result;
	}
}
