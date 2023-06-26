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
import com.ss.smartoffice.sonotificationservice.common.SmsHelper;
import com.ss.smartoffice.sonotificationservice.model.Employee;
@Service
public class EmployeeLeaveApplyingService {
	
	private static final String EMAIL_LEAVE_APPLY_TEMPLATE_NAME="html/employee-leave-apply";
	@Autowired
	SmsHelper smsHelper;
	
	@Autowired
	CommonUtils commonUtils;

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private TemplateEngine htmlTemplateEngine;
	
	/*
	 * Send HTML mail with attachment.
	 */
	public void sendEmployeeLeaveApply(Employee employee)throws SmartOfficeException,MessagingException{
		try {
			final Context ctx = new Context(Locale.ENGLISH);
			ctx.setVariable("empName", employee.getEmpName());
			final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
			final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true /* multipart */, "UTF-8");
			message.setSubject("Hi"+employee.getEmpName()+"Your Leave Has Been Applied Successfully.");
			message.setFrom(commonUtils.getSenderEmail());
			message.setTo(employee.getContactEmailId());
			final String htmlContent= this.htmlTemplateEngine.process(EMAIL_LEAVE_APPLY_TEMPLATE_NAME, ctx);
			message.setText(htmlContent, true /* isHtml */);
			
			// Send mail
			this.mailSender.send(mimeMessage);
			
			//Send Sms To memployee
			
			String smsSessage="Hi"+ employee.getEmpName() + "Your Leave Has Been Applied SuccessFully. Please Logon to http://35.198.238.141/smart-office/login for Further Information.";
			smsHelper.sendSms(employee.getContactMobileNo(),smsSessage);	
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e.getMessage());
		}
		
		
		
		
	}

}
