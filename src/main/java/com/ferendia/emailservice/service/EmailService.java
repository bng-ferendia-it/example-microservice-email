package com.ferendia.emailservice.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.ferendia.emailservice.exception.CustomException;
import com.ferendia.emailservice.model.TokenType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;



@Service
public class EmailService {
	@Value("${applicationDNS}")
	private String applicationDNS;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private JavaMailSender javaMailSender;


	public void sendPreparedMail(String to, String subject, String text, Boolean isHtml) {
		try {
			MimeMessage mail = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail, true);
			helper.setFrom("no-reply@exemple.com");
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text, isHtml);
			javaMailSender.send(mail);
		} catch (MailAuthenticationException e) {
			
			throw new CustomException("Problem with sending email", HttpStatus.BAD_REQUEST);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void sendTextMail(String mail, String object, String body) {

		sendPreparedMail(mail, object, body, false);

	}

	public void sendRequestEmail(String email, String requestToken, TokenType requestType) {
		String object = "";
		String body = "";

		final Context context = new Context();
		context.setVariable("dns", applicationDNS);
		context.setVariable("email", email);
		context.setVariable("token", requestToken);

		switch (requestType) {
		case ACTIVATION_EMAIL:
			object = "Activation Email";
			body = templateEngine.process("confirmEmail", context);
			break;
		case RECOVERY_PASSWORD:
			object = "Reset Password Request";
			body = templateEngine.process("resetPassword", context);
			break;
		default:
			throw new CustomException("Token type undefined", HttpStatus.BAD_REQUEST);
		}
		sendPreparedMail(email, object, body, true);
	
		
	}
}
