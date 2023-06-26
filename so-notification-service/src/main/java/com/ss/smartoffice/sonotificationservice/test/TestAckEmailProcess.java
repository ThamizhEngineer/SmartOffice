//package com.ss.smartoffice.sonotificationservice.test;
//
//import java.util.Locale;
//import java.util.Map;
//
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.Context;
//
//import com.ss.smartoffice.shared.model.Event;
//
//@Service
//public class TestAckEmailProcess {
//	
//	
//	@Autowired
//	private JavaMailSender mailSender;
//
//	@Autowired
//	private TemplateEngine htmlTemplateEngine;
//	
//	private static final String TEST_ACK_COMPLETE_TEMPLATE = "html/test-ack-email";
//	
//	public void ackProcess(Event event) {
//		
//		if(event.getKeyValues()!=null) {
//			Map<String,String> keyValues= event.getKeyValues();
//			String emailSubject=keyValues.get("email-subject");
//			String emaiId=keyValues.get("email-id");
//			String applicantname=keyValues.get("applicant-name");
//			String testResult=keyValues.get("test-result");
//			
//			
//			try {
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
//			ctx.setVariable("testResult", testResult);
//			// Create the HTML body using Thymeleaf
//			final String htmlContent1 = this.htmlTemplateEngine.process(TEST_ACK_COMPLETE_TEMPLATE, ctx);
//			message.setText(htmlContent1, true /* isHtml */);
//
//			
//
//			// Send mail
//			this.mailSender.send(mimeMessage);
//			
//			} catch (MessagingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			
//			
//		}
//	}
//
//
//}
