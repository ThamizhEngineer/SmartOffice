package com.ss.smartoffice.soservice.event.employee;
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
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.soservice.kafkaConsumer.SmsHelper;


@Service
public class NewEmailService {
	@Autowired
	SmsHelper smsHelper;

	private static final String EMAIL_NEW_USER_TEMPLATE_NAME = "html/new-user-event";

	@Autowired
	CommonUtils commonUtils;
	
	@Value("${smartoffice.url}")
	private String smartofficeUrl;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private TemplateEngine htmlTemplateEngine;

	
	/*
	 * Send HTML mail with attachment.
	 */
	public void sendMailNewUser(memployee employee) throws MessagingException {

		try {
			final Context ctx = new Context(Locale.ENGLISH);

			String password = employee.getDob();
			System.out.println("employee");
			System.out.println(employee);
			ctx.setVariable("empName", employee.getEmpName());
			ctx.setVariable("userName", employee.getContactMobileNo());
			ctx.setVariable("password", password);
			ctx.setVariable("smartofficeUrl", smartofficeUrl);
			final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
			final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true /* multipart */, "UTF-8");
			message.setSubject("Registration Successfull");
			message.setFrom(commonUtils.getSenderEmail());
			message.setTo(employee.getEmailId());
			System.out.println(message);

			final String htmlContent = this.htmlTemplateEngine.process(EMAIL_NEW_USER_TEMPLATE_NAME, ctx);
		message.setText(htmlContent, true /* isHtml */);

			// Send mail
			this.mailSender.send(mimeMessage);

			String smsSessage = "Hi " + employee.getEmpName()
					+ " your registration is successfull.  Your login user name " + employee.getContactMobileNo()
					+ " and password " + password
					+ ".  Please logon to "+smartofficeUrl+"for further information.";
			smsHelper.sendSms(employee.getContactMobileNo(), smsSessage);

		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e.getLocalizedMessage());
		}

	}
}
