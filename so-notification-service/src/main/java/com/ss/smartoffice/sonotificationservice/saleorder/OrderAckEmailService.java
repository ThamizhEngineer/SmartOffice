package com.ss.smartoffice.sonotificationservice.saleorder;

import java.util.Locale;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.sonotificationservice.common.SmsHelper;
import com.ss.smartoffice.sonotificationservice.model.Customer;
import com.ss.smartoffice.sonotificationservice.model.SaleOrder;



@Service
public class OrderAckEmailService {
	@Autowired
	SmsHelper smsHelper;

	private static final String EMAIL_NEW_USER_TEMPLATE_NAME = "html/order-ack-processor";
	
	@Autowired
	CommonUtils commonUtils;
	
	@Value("${smartoffice.url}")
	private String smartofficeUrl;
	
	

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private TemplateEngine htmlTemplateEngine;
	
	public void sendMailUser(SaleOrder saleOrder, String purchaseOrderId, String saleOrderCode) throws MessagingException {

		try {
			final Context ctx = new Context(Locale.ENGLISH);

			String password = saleOrder.getPartnerName();
			System.out.println("customer");
	
			ctx.setVariable("cusName", saleOrder.getPartnerName());
			ctx.setVariable("userName", saleOrder.getPartnerName());
			ctx.setVariable("password", password);
			ctx.setVariable("purchaseOrderId", purchaseOrderId);
			ctx.setVariable("saleOrderCode", saleOrderCode);
			ctx.setVariable("smartofficeUrl", smartofficeUrl);
			final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
			final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true /* multipart */, "UTF-8");
			message.setSubject("Sale Order Generated Successfully");
			message.setFrom(commonUtils.getSenderEmail());
			message.setTo(saleOrder.getEmailId());
			System.out.println(saleOrder.getEmailId());

			final String htmlContent = this.htmlTemplateEngine.process(EMAIL_NEW_USER_TEMPLATE_NAME, ctx);
			message.setText(htmlContent, true /* isHtml */);

			// Send mail
		this.mailSender.send(mimeMessage);
//
//			String smsSessage = "Hi " + customer.getClientName()
//					+ "  Sale Order Generated Successfully : - SO.Number " + saleOrderCode
//					+ " in reference to the  P.O given - PO.Number" + purchaseOrderId;
//			       
//			       
//			smsHelper.sendSms(customer.getMobileNo(), smsSessage);

		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e.getLocalizedMessage());
		}

	}



}
