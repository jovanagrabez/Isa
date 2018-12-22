package com.example.ProjekatIsa.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.example.ProjekatIsa.model.User;
import com.example.ProjekatIsa.service.UserService;


public class RegistrationListener implements 
ApplicationListener<OnRegistrationCompleteEvent> {
	
	@Autowired
	private UserService service;
  
    @Autowired
    private MessageSource messages;
  
    @Autowired
    private JavaMailSender mailSender;

	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent event) {
		// TODO Auto-generated method stub
		System.out.println("aktivirao se");
        this.confirmRegistration(event);
		
	}
	
	public RegistrationListener(){
    	super();
    }
	
	private void confirmRegistration(OnRegistrationCompleteEvent event) {
    	System.out.println("CONFIRM REGISTRATION USAO");
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        System.out.println("service je:"+service);
        service.createVerificationTokenForUser(user, token);
         
        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl 
          = event.getAppUrl() + "/public/registrationConfirm?token=" + token;
        //String message = messages.getMessage("message.regSucc", null, event.getLocale());
        String message="poruka";
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        //email.setText(message +"\n" + "http://localhost:8000" + confirmationUrl);
        email.setText(message +"\n" + "http://localhost:4200" + confirmationUrl);
        mailSender.send(email);
    }


}
