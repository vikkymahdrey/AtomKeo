package com.agiledge.atom.config.files;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.agiledge.atom.dao.impl.AbstractDao;
import com.agiledge.atom.service.impl.MobileTripSheetServiceImpl;
import com.agiledge.atom.service.intfc.MobileTripSheetService;

@Repository
public class RandomString extends AbstractDao
{

  private static  char[] symbols = new char[10];

  static {
    for (int idx = 0; idx < 10; ++idx) {
      symbols[idx] = (char) ('0' + idx);
      System.out.println(symbols[idx]);
    }
  
  }

  private final Random random = new Random();

  private  char[] buf;
  /*public RandomString(){
	  buf=null;
  }*/

  public void RandomStr(int length)
  {
    if (length < 1)
      throw new IllegalArgumentException("length < 1: " + length);
    buf = new char[length];
  }

  public String nextString()
  {
    for (int idx = 0; idx < buf.length; ++idx) 
      buf[idx] = symbols[random.nextInt(symbols.length)];
    return new String(buf);
  }
  public String getDifferentPassword(ArrayList<String> pswds) {
	  String returnString="0000";
	  long largest = 0;
	  try {
	  for(String pswd : pswds) {
		  try {
			  long curPswd = Long.parseLong(pswd);
			  if(largest<=curPswd) {
				  largest = curPswd;
			  }
		  }catch(Exception e) {
			  ;
		  }
	  }
	  largest = largest + 1;
	  returnString = String.valueOf(largest);
	  }catch(Exception e) {
		  ;
	  }
	  
	  return returnString;
  }
  
  public String nextDriverString(String tripId) throws Exception
  {
	  ArrayList<String> pswds= getDriverPasswords(tripId,-15, +15);
	     String returnPswd="";
	   
	  boolean contains=true;
	 for(int i = 0; contains&&i < 2; i++) { 
    for (int idx = 0;   idx < buf.length; ++idx) {
    	int randomInt= random.nextInt(symbols.length);
//    	System.out.println("Random : "+ randomInt);
      buf[idx] = symbols[randomInt];
     
    }
    returnPswd=new String(buf); 
	 
    System.out.println("Generated @"+ i+" :"+returnPswd);
    	if(pswds.contains(returnPswd)) {
    		contains=true;
    		System.out.println("contains :true at "+ i );
    	} else {
    		contains=false;
    		System.out.println("contains :false at "+ i );
    	}
	 }
	  System.out.println(contains);
	 if(contains) {
		 returnPswd = getDifferentPassword(pswds);
		 System.out.println("return Pswd in fn : "+ returnPswd);
		 if(returnPswd.equalsIgnoreCase("0000")) {
			 returnPswd = returnPswd + tripId;
		 }
		 
	 }
    return new String(returnPswd);
  }


 
public String nextEscortString(String tripId) throws Exception
  {
	  MobileTripSheetService mts=new MobileTripSheetServiceImpl();
	  ArrayList<String> pswds= mts.getEscortPasswords(tripId,-15, +15);
	  String returnPswd="";
	  boolean contains=true;
	 for(int i = 0; contains&&i < 10; i++) { 
    for (int idx = 0;   idx < buf.length; ++idx) {
    	int randomInt= random.nextInt(symbols.length);
    	System.out.println("Random : "+ randomInt);
      buf[idx] = symbols[randomInt];
     
    }
    returnPswd=new String(buf);
    System.out.println("Generated @"+ i+" :"+returnPswd);
    	if(pswds.contains(returnPswd)) {
    		contains=true;
    	} else {
    		contains=false;
    	}
	 }
	  
	 if(contains) {
		 returnPswd=tripId;
		 for(int ctr=tripId.length()-1;ctr<buf.length;ctr++) {
			 returnPswd+="0";
		 }
		 
	 }
    return new String(returnPswd);
  }

  public ArrayList<String> getDriverPasswords(String tripId, int priorDays, int postDays) throws Exception {
			 	ArrayList<String> passwords = new ArrayList<String>();
			 	String query="select driverPswd from trip_details where trip_date  between date_add(trip_date,  interval "+priorDays+" day ) and date_add(trip_date,  interval "+postDays+" day )  and driverPswd is not null";
				Query q=getEntityManager().createNativeQuery(query);
					
					List<Object> oList=q.getResultList();
					for(Object o : oList) {
						String driverPswd=String.valueOf(o);
                    //driverPswd!=null&&driverPswd.trim().equals("")==false) {
							passwords.add(driverPswd);
					}
													
				return passwords;
	}

}
