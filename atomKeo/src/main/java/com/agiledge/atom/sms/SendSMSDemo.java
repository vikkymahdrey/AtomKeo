package com.agiledge.atom.sms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SendSMSDemo implements Runnable, SendSMS {

	private String message;
	private String[] messages;
	int sleepTime;
	// ------- send to single
	String postData = "";
	String retval = "";
	// String User = "ATOm1";
	// String passwd = "atom9886021161!";
	String mobilenumber;
	String[] mobilenumbers;
	String sid = "AGEDGE";
	String templateid = "174";
	String working_key = "034p220h9w0hr3n3lx0b878r8byk8om3";
	
	
	public void send(String mobileNo, String message) {
		try {
			Thread t = new Thread(this);
			this.message = message;
			this.mobilenumber = mobileNo;
			t.start();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("aaaaaaaaaaaaaaaaaerror1" + e);
		}
	}
	
	public void send(String[] mobileNo, String message) {
		try {
			Thread t = new Thread(this);
			this.message = message;
			this.mobilenumbers = mobileNo;						
			t.start();
		} catch (Exception e) {
			System.out.println("aaaaaaaaaaaaaaaaaaaaaaerror2" + e);

		}

	}
	
	
	public void send(String mobileNo[], String[] messages) {
		try {
			Thread t = new Thread(this);
			this.messages = messages;
			//this.mobilenumbers = mobileNo;
			this.mobilenumbers = mobileNo;
 
						t.start();
 
		} catch (Exception e) {
			System.out.println("aaaaaaaaerror3" + e);

		}
	}
	
	public void run() {
		try {
			if (mobilenumbers == null || mobilenumbers.length < 1) {
				mobilenumbers=new String[1];
				mobilenumbers[0] = mobilenumber;
			}
			if (messages == null || messages.length < 1) {
				messages=new String[1];
				messages[0] = message;
			}
			for (int i = 0; i < mobilenumbers.length; i++) {
				this.mobilenumber = mobilenumbers[i];
				if(messages.length==1)
				this.message = messages[0];
				else
					this.message = messages[i];
				postData += "?workingkey=" + working_key + "&to="
						+ mobilenumber + "&sender=" + sid + "&message="
						+ URLEncoder.encode(message, "UTF-8");
				System.out
						.println("http://sms.variformsolutions.co.in/api/web2sms.php"
								+ postData);
				URL url = new URL(
						"http://sms.variformsolutions.co.in/api/web2sms.php"
								+ postData);
				HttpURLConnection urlconnection = (HttpURLConnection) url
						.openConnection();
				urlconnection.setRequestMethod("GET");
				urlconnection.setDoOutput(true);
				OutputStreamWriter out = new OutputStreamWriter(
						urlconnection.getOutputStream());
				out.write(postData);
				out.close();
				BufferedReader in = new BufferedReader(new InputStreamReader(
						urlconnection.getInputStream()));
				String decodedString;
				while ((decodedString = in.readLine()) != null) {
					retval = decodedString;
				}
				in.close();
				System.out.println(retval);
			}
		} catch (Exception e) {
 
			System.out.println("Error in runnnnnnnnnnnnn" + e);
 
		}
	}

	
}
