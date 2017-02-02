package com.agiledge.atom.service.intfc;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;


public interface MailSenderService   {
	public void sendMail(String from, String to, List<String> ccs, List<String> bccs, String subject, String msg) ;
	public void sendMailWithHtml(String from, String to, List<String> ccs, List<String> bccs, String subject, String msg) throws MessagingException;
	public void sendMailWithAttachemnt(String from, String to, List<String> ccs, List<String> bccs, String subject, String msg, String attachmentName) throws IOException;
}
