package com.agiledge.atom.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.agiledge.atom.service.intfc.MailSenderService;


@Service("mailSenderService")
public class MailSenderServiceImpl extends Thread implements MailSenderService{
	
	
	
	@Autowired
	private JavaMailSenderImpl mailSender;
	 
	 public void sendMail(String from, String to, List<String> ccs, List<String> bccs, String subject, String msg)  {
 
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);	
	 }
	
	 
	 public void sendMailWithAttachemnt(String from, String to, List<String> ccs, List<String> bccs, String subject, String msg, String attachmentName) throws IOException {
		 
		 MimeMessage message = mailSender.createMimeMessage();
		   try{
				MimeMessageHelper helper = new MimeMessageHelper(message, true);
				helper.setFrom(from);
				helper.setTo(to);
				helper.setSubject(subject);
				helper.setText("Please  find the bug report in the attachment");
				File attachment = new File(attachmentName);
				FileUtils.writeStringToFile(attachment, msg);
				// FileSystemResource file = new FileSystemResource(new File(msg));
				helper.addAttachment(subject, attachment);
		    }catch (MessagingException e) {
		    	throw new MailParseException(e);
		    }
		 mailSender.send(message);
	 }
	 
	 @Async
	 public void sendMailWithHtml(String from, String to, List<String> ccs, List<String> bccs, String subject, String msg) throws MessagingException  {
		 
		 MimeMessage mimeMessage = mailSender.createMimeMessage();
		 MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
		 mimeMessage.setContent(msg, "text/html");
		 // helper.setFrom(from);
		 helper.setTo(to);
		 for(String cc : ccs)
			 helper.setCc(cc);
		 for(String bcc : bccs)
			 helper.setBcc(bcc);
		 helper.setSubject(subject);
		 
		 
			mailSender.send(mimeMessage);	
		 }

	
			
	 
}
