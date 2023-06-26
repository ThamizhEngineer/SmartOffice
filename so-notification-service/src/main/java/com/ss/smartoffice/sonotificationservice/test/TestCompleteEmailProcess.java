package com.ss.smartoffice.sonotificationservice.test;


import java.util.Locale;
import java.util.Map;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.ss.smartoffice.sonotificationservice.transaction.event.Event;



@Service
public class TestCompleteEmailProcess {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private TemplateEngine htmlTemplateEngine;
	
	private static final String TEST_COMPLETE_TEMPLATE = "html/test-complete-email";
	
	public void process(Event event) {
		
		if(event.getAttachments()!=null) {
		
//			String emailSubject=event.getAttachments().get(0).getEmailSubject();
//			String emaiId=event.getAttachments().get(0).getEmailId();
//			String applicantname=event.getAttachments().get(0).getApplicantName();
//			
			
			try {
//			 MimeMessage mimeMessage = this.mailSender.createMimeMessage();
//			 String recipient = emaiId;
//				String[] recipientList = recipient.split(",");
//				InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
//				int counter = 0;
//				for (String recipient1 : recipientList) {
//				    recipientAddress[counter] = new InternetAddress(recipient1.trim());
//				    counter++;
//				}
//				mimeMessage.setRecipients(Message.RecipientType.TO, recipientAddress);
//			 MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true /* multipart */, "UTF-8");
//			message.setSubject( emailSubject);
//		
//			
//			
//			
//			
//			final Context ctx = new Context(Locale.ENGLISH);
//			ctx.setVariable("applicantname", applicantname);
//			// Create the HTML body using Thymeleaf
//			final String htmlContent1 = this.htmlTemplateEngine.process(TEST_COMPLETE_TEMPLATE, ctx);
//			message.setText(htmlContent1, true /* isHtml */);
//
//			
//
//			// Send mail
//			this.mailSender.send(mimeMessage);
			
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
	}

}
