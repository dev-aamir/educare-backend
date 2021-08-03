package com.educare.utility;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.educare.model.Mail;

@Service
public class MailerUtility {

	 @Autowired
	 private JavaMailSender javaMailSender;
	 
	 public void sendEmail(Mail content, String receiverEmail) {

	        SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setTo(receiverEmail);

	        msg.setSubject(content.getMailSubject());
	        msg.setText(content.getMailBody());

	        javaMailSender.send(msg);

	  }
	 
	 public void sendHtmlMail(Mail content, String receiverEmail) throws MessagingException {
			
		MimeMessage mail = javaMailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(mail, true);
		
		helper.setTo(receiverEmail);
		helper.setSubject(content.getMailSubject());
		helper.setText(content.getMailBody(), true);
		javaMailSender.send(mail);


	}
	
	
}
