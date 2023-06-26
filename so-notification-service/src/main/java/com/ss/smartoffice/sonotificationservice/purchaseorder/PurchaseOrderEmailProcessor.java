package com.ss.smartoffice.sonotificationservice.purchaseorder;

import java.io.File;
import java.util.Locale;
import java.util.Map;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.ss.smartoffice.shared.dm.DocumentManagementHelper;
import com.ss.smartoffice.sonotificationservice.transaction.event.Event;

@Service
public class PurchaseOrderEmailProcessor {
//	
//
//	@Autowired
//	private JavaMailSender mailSender;
//
//	@Autowired
//	private TemplateEngine htmlTemplateEngine;
//	
//	@Autowired
//	private DocumentManagementHelper documentManagementHelper;
//	
//	private static final String PURCHASE_ORDER_TEMPLATE_NAME = "html/purchase-order-email-with-attachment";
//	
	public void process(Event event) {
//	
//		if(event.getNotificationKeys()!=null) {
//		
////			String emailBody=event.getAttachments().get(0).getEmailBody();
////			String emailSubject=event.getAttachments().get(0).getEmailSubject();
////			String emaiId=event.getAttachments().get(0).getEmailId();
////			String purchaseOrderId=event.getAttachments().get(0).getPurchaseOrderId();
////			String vendorName=event.getAttachments().get(0).getVendorName();
////			String docId=event.getAttachments().get(0).getDocId1();
//			
//			try {
//			 MimeMessage mimeMessage = this.mailSender.createMimeMessage();
////			 String recipient = emaiId;
////				String[] recipientList = recipient.split(",");
////				InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
////				int counter = 0;
////				for (String recipient1 : recipientList) {
////				    recipientAddress[counter] = new InternetAddress(recipient1.trim());
////				    counter++;
////				}
////				mimeMessage.setRecipients(Message.RecipientType.TO, recipientAddress);
////			 MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true /* multipart */, "UTF-8");
//////			message.setSubject( emailSubject);
////		
////			
////			
////			
////			
////			final Context ctx = new Context(Locale.ENGLISH);
//////			ctx.setVariable("emailBody", emailBody);
//////			ctx.setVariable("vendorName", vendorName);
////			// Create the HTML body using Thymeleaf
////			final String htmlContent1 = this.htmlTemplateEngine.process(PURCHASE_ORDER_TEMPLATE_NAME, ctx);
////			message.setText(htmlContent1, true /* isHtml */);
////
////			// Add the attachment
////
//////			File file=documentManagementHelper.getDocByDocId(docId);
////			FileSystemResource fileSystemResource = new FileSystemResource(file);
////			message.addAttachment("Purchase order.pdf", fileSystemResource, "application/pdf");
////
////			// Send mail
////			this.mailSender.send(mimeMessage);
////			
////			} catch (MessagingException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}catch (Exception e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
//			
//			
//			
//		}
	}
//
}
