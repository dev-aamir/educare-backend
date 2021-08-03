package com.educare.utility;

import javax.mail.MessagingException;

import com.educare.model.Mail;
import com.educare.model.Student;

public class Tester {

	public static void main(String[] args) {
		testHtmlMail();

	}
	
	public static void testHtmlMail() {
		Student s = new Student();
		s.setStudentFirstName("Adam");
		s.setStudentUsername("Adam");
		s.setStudentPassword("Adam123");
		s.setStudentEmail("aamirs406@gmail.com");
		
		Mail mailContent = new Mail();
		mailContent.setMailSubject("Welcome to Hayat Educare!");
		mailContent.setMailBody("Hi <h3>"+s.getStudentFirstName()+"</h3>,<br> I <strong>Ajaj Shaikh, CEO</strong> of Hayat Educare welcome you to "
				+ "your wonderful journey of learning. Hope you will have a great learning ahead.<br>"
				+ "Your credentials are :<br>"
				+ "Username :" + s.getStudentUsername() + " <br>"
				+ "Password :" + s.getStudentPassword());
		
		MailerUtility mailer = new MailerUtility();
		
		try {
			mailer.sendHtmlMail(mailContent,s.getStudentEmail());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
