package com.ss.smartoffice.sonotificationservice.applicant;

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
import com.ss.smartoffice.shared.model.applicant.Applicant;

@Service
public class NewApplicantEmailService {

	private static final String EMAIL_APP_USER_TEMPLATE_NAME = "html/new-app-event";

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
	public void sendMailApplicantUser(Applicant applicant) throws MessagingException {

		try {
			final Context ctx = new Context(Locale.ENGLISH);

			String password = applicant.getDob().replaceAll("-", "");
			System.out.println("applicant");
			System.out.println(applicant);
			ctx.setVariable("empName", applicant.getFirstName());
			ctx.setVariable("userName", applicant.getFirstName());
			ctx.setVariable("password", password);
			ctx.setVariable("smartofficeUrl", smartofficeUrl);
			final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
			final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true /* multipart */, "UTF-8");
			message.setSubject("Registration Successfull");
			message.setFrom(commonUtils.getSenderEmail());
			message.setTo(applicant.getContactEmailId());
			System.out.println(message);

			final String htmlContent = this.htmlTemplateEngine.process(EMAIL_APP_USER_TEMPLATE_NAME, ctx);
			message.setText(htmlContent, true /* isHtml */);

			// Send mail
			this.mailSender.send(mimeMessage);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e.getLocalizedMessage());
		}

	}

	
}
