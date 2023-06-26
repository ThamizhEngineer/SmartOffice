package com.ss.smartoffice.sonotificationservice.employee;

import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.sonotificationservice.common.SmsHelper;


@Service
public class EmployeeUpdateEmailService {
	@Autowired
	SmsHelper smsHelper;

	private static final String EMAIL_NEW_USER_TEMPLATE_NAME = "html/employee-update-username";

	@Autowired
	CommonUtils commonUtils;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private TemplateEngine htmlTemplateEngine;

	
	/*
	 * Send HTML mail with attachment.
	 */
	public void sendMailEmployeeUpdate(memployee employee) throws MessagingException {
		try {
			final Context ctx = new Context(Locale.ENGLISH);
			
			
			
			ctx.setVariable("empName", employee.getEmpName());
			ctx.setVariable("userName", employee.getContactMobileNo());
		

				final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
				final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true /* multipart */, "UTF-8");
				message.setSubject("User Name Changed");
				message.setFrom(commonUtils.getSenderEmail());
				message.setTo(employee.getContactEmailId());
			
			
				final String htmlContent = this.htmlTemplateEngine.process(EMAIL_NEW_USER_TEMPLATE_NAME, ctx);
				message.setText(htmlContent, true /* isHtml */);

				// Send mail
				this.mailSender.send(mimeMessage);
				
				String smsSessage = "Hi "+ employee.getEmpName()+" your username is changed.  Your new  login user name "+ employee.getContactMobileNo() +".  Please logon to http://35.198.238.141/smart-office/login for further information." ;						
				smsHelper.sendSms(employee.getContactMobileNo(), smsSessage);
				
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e.getMessage());
		}
		
		
	}
	
		
		
	
}
