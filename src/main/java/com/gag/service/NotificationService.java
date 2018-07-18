package com.gag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.gag.model.User;

@Service
public class NotificationService {

	private JavaMailSender javaMailSender;
	
	@Autowired
	public NotificationService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public void sendNotification(User user) throws MailException {
		// send email
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom("testforsuccess526@gmail.com");
		mail.setSubject("Registration code");
		mail.setText(	"Hello, " + user.getUsername() + "\n" + "\n" + 
						"Thank you for the wish to become one of our members! To finish your registration " + "\n"
						+ "you should insert the activation code here: http://localhost:8080/9gag.com/verify" + "\n"
						+ " The code is active in 24h. Afrer that period the account will be deleted."
						+"\n" + "\n" + "Code: " + user.getActivationCode());
		
		javaMailSender.send(mail);
	}
}
