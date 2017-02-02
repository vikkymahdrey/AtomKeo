package com.agiledge.atom.sms;

import com.agiledge.atom.constants.SettingsConstant;

public class SendSMSFactory {

	public static String smsFlag =SettingsConstant.smsFlag;
	
	private SendSMSFactory() {

	}

	public static SendSMS getSMSInstance() {
		System.out.println("smsFlagggggvalue"+smsFlag);
		if (smsFlag.equalsIgnoreCase("cd")||smsFlag.equalsIgnoreCase("cdt")) {

			return (SendSMS) new SendSMSCD();
		}else if(smsFlag.equalsIgnoreCase("Demo"))
		{
			return (SendSMS) new SendSMSDemo();
		}else if(smsFlag.equalsIgnoreCase("gss"))
		{
			System.out.println("222222@@");
			return (SendSMS) new SendSMSGSS();	
		}else
		{
			return (SendSMS) new SendSMSUAT();
		}


	}
}
