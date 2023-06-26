package com.ss.smartoffice.sonotificationservice.JobEvent;

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
import com.ss.smartoffice.sonotificationservice.model.Partner;

@Service
public class JobConfirmationEmailService {
	@Autowired
	SmsHelper smsHelper;
	@Autowired
	CommonUtils commonUtils;

	@Value("${smartoffice.url}")
	private String smartofficeUrl;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private TemplateEngine htmlTemplateEngine;

	private static final String EMAIL_JOB_CONFIRMATION_TEMPLATE_NAME = "html/job-confirmation";

	/*
	 * Send HTML mail with attachment.
	 */
	public void sendMailNewUser(Partner partner) throws MessagingException {
		try {
			final Context ctx = new Context(Locale.ENGLISH);
			ctx.setVariable("clientName", partner.getClientName());
			ctx.setVariable("smartofficeUrl", smartofficeUrl);
			final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
			final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true /* multipart */, "UTF-8");
			message.setSubject(" Job Creation Successfull");
			message.setFrom(commonUtils.getSenderEmail());
			message.setTo(partner.getEmailId());
			final String htmlContent = this.htmlTemplateEngine.process(EMAIL_JOB_CONFIRMATION_TEMPLATE_NAME, ctx);
			message.setText(htmlContent, true /* isHtml */);
			this.mailSender.send(mimeMessage);
			String smsSessage = "Hi " + partner.getClientName() + " .Job Has been Created Successfully"
					+ ".  Please logon to " + smartofficeUrl + "for further information.";
			smsHelper.sendSms(partner.getMobileNo(), smsSessage);

		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e.getLocalizedMessage());
		}
	}
}
