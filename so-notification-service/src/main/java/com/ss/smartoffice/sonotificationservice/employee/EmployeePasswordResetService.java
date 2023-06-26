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
import com.ss.smartoffice.sonotificationservice.model.User;

@Service
public class EmployeePasswordResetService {
	@Autowired
	SmsHelper smsHelper;
	private static final String PASSWORD_NEW_USER_TEMPLATE_NAME = "html/employee-update-password";
	@Autowired
	CommonUtils commonUtils;
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private TemplateEngine htmlTemplateEngine;

	/*
	 * Send HTML mail with attachment.
	 */
	public void sendPasswordEmployeeUpdate(User user) throws SmartOfficeException, MessagingException {
		try {
			final Context ctx = new Context(Locale.ENGLISH);

			ctx.setVariable("userName", user.getFirstName());
			ctx.setVariable("password", user.getPassword());
			ctx.setVariable("Phone-Number", user.getUserName());

			final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
			final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true /* multipart */, "UTF-8");
			message.setSubject("Password Has Been Changed");
			message.setFrom(commonUtils.getSenderEmail());
			message.setTo(user.getEmailId());
			final String htmlContent = this.htmlTemplateEngine.process(PASSWORD_NEW_USER_TEMPLATE_NAME, ctx);
			message.setText(htmlContent, true /* isHtml */);

			// Send mail
			this.mailSender.send(mimeMessage);

			// Send Sms To memployee

			String smsSessage = "Hi " + user.getFirstName() + "  Your Password Has Been Changed.  Your NewPassword is "
					+ user.getPassword()
					+ " Please logon to http://35.198.238.141/smart-office/login for Further Information.";

			smsHelper.sendSms(user.getUserName(), smsSessage);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e.getMessage());
		}

	}

}
