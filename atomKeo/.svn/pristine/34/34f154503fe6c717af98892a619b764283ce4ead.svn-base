package com.agiledge.atom.config.files;

import com.agiledge.atom.constants.SettingsConstant;
import com.agiledge.atom.mail.SendMail;

public class SendMailFactory {
 
	public static String mailFlag = "gss";
 

	private SendMailFactory() {

	}

	public static SendMail getMailInstance() {
		if (mailFlag.equalsIgnoreCase("gmail")) {

			return (SendMail) new SendGmail();
		}
		else if (mailFlag.equalsIgnoreCase("gss")) {
System.out.println("Gmail sender "+mailFlag);
			return (SendMail) new SendGmail();
		}
		else if (mailFlag.equalsIgnoreCase("CGI")) {

			return (SendMail) new SendMailLogica();
		} else if (mailFlag.equalsIgnoreCase("cd")) {

			return (SendMail) new SendMailCrossDomain();

		}else
		{
			return (SendMail) new SendMailUAT();
		}


	}

}
