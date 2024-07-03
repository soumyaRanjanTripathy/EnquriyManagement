package com.demo.Utils;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;
@Component
public class EmailUtils {
	private  JavaMailSender mailSender;
	public EmailUtils(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public boolean sendEmail(String to, String subject, String body) throws Exception {
		
		boolean isSent=false;
		try {
		        MimeMessage mimeMsg = mailSender.createMimeMessage();
		        MimeMessageHelper helper = new MimeMessageHelper(mimeMsg, true);


		        helper.setTo(to);
		        helper.setSubject(subject);
		        helper.setText(body, true);

		        mailSender.send(mimeMsg);
		        isSent =true; // Email sent successfully
		    } catch (Exception e) {
		    	e.printStackTrace();
		     		 // Failed to send email
		    }
	
	return isSent;
}}
