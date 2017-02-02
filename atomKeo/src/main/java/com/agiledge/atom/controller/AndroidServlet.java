
package com.agiledge.atom.controller;



import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agiledge.atom.service.intfc.DeviceService;





@Controller
public class AndroidServlet {
	private static final Logger logger = Logger.getLogger(AndroidServlet.class);
	
	
	@Autowired
	DeviceService deviceService;
	
	@RequestMapping(value={"androidservlet"})
	public @ResponseBody
	String androidServlet(@RequestBody String received) {
	logger.debug("IN device Controller"+received);
		JSONObject rObj=new JSONObject();
		try{
		JSONObject obj=new JSONObject();
		obj=(JSONObject)new JSONParser().parse(received);
		//String  nuance=obj.get("NUANCE").toString();
		
		System.out.println(obj.get("ACTION").toString());
		if (obj.get("ACTION").equals("INTERNET_CHECK")) {
			
			rObj = obj;
			 rObj.put("result", "true"); 
			
		}
		else if (obj.get("ACTION").toString().equals("bulkinsert")) {
			 rObj = deviceService.bulkInsert(obj); 
		
		} 
		else if (obj.get("ACTION").toString().equals("LOGINOTP")) {
		System.out.println("loginotp");
			rObj =deviceService.ValidateUserOTP( obj.get("password").toString(),obj.get("IMEI").toString());
			
		}
		else if (obj.get("ACTION").equals("login")) {
			
			rObj =deviceService.ValidateUserOTP( obj.get("password").toString(),obj.get("IMEI").toString());
			//rObj = deviceService.ValidateUser(obj.get("userName").toString(), obj.get("password").toString(),	obj.get("IMEI").toString());
		} 
		/*else if (obj.get("ACTION").equals("gettrips") ){
			
			rObj =deviceService.getTrips(obj.get("IMEI").toString(),obj.get("date").toString());
		
		
		}*/
		else if (obj.get("ACTION").equals("gettrip") ) {
			
			rObj =deviceService.getTripSheet(obj.get("IMEI").toString() );
		
		} else if (obj.get("ACTION").equals("vehiclePosition")) {
			
			rObj = deviceService.updateGeoCode(obj.get("IMEI").toString(),obj.get("tripId").toString(),Double.parseDouble( obj.get("latitude").toString()),Double.parseDouble(obj.get("longitude").toString()),Double.parseDouble(obj.get("distanceCovered").toString()),Long.parseLong(obj.get("timeElapsed").toString()), Long.parseLong(obj.get("NUANCE").toString()));
		
		} else if (obj.get("ACTION").equals("fullVehiclePosition")) {

			rObj = deviceService.updateGeoCodePassive(obj.get("IMEI").toString(),Double.parseDouble( obj.get("latitude").toString()),Double.parseDouble(obj.get("longitude").toString()), Long.parseLong(obj.get("NUANCE").toString()));
			
		
		} else if (obj.get("ACTION").equals("employeeGetIn") ) {
			
			rObj = deviceService.employeeCheckIn(obj.get("IMEI").toString(),obj.get("tripId").toString(), obj.get("empCode").toString(),Double.parseDouble( obj.get("latitude").toString()),Double.parseDouble(obj.get("longitude").toString()), Long.parseLong(obj.get("NUANCE").toString()));
		
		} else if (obj.get("ACTION").equals("employeeGetOut") ) {
			
			rObj = deviceService.employeeCheckOut(obj.get("IMEI").toString(),obj.get("tripId").toString(), obj.get("empCode").toString(),Double.parseDouble( obj.get("latitude").toString()),Double.parseDouble(obj.get("longitude").toString()), Long.parseLong(obj.get("NUANCE").toString()));
		
			
		} else if (obj.get("ACTION").equals("startTrip")) {
			
			rObj = deviceService.startTrip(obj.get("tripId").toString(),obj.get("IMEI").toString());
			
		} else if (obj.get("ACTION").equals("stopTrip")	) {

			rObj = deviceService.stopTrip(obj.get("tripId").toString());
	
		} else if (obj.get("ACTION").equals("forceStopTrip")) {
			
			rObj = deviceService.forceStopTrip(obj.get("tripId").toString());
		}
		else if (obj.get("ACTION").equals("logout")) {
			
			rObj =  deviceService.logout(obj.get("IMEI").toString());
		}
		else if(obj.get("ACTION").equals("downloadSettings")){
			  rObj.put("result", "true");
			  rObj.put("ACTION", "downloadSettings");
			  rObj.put("versionNo",  "1.0");
			  rObj.put("configurationType", "password type");
		}

		// currently for keonics this panic setup  is not done
	      
    	else if (obj.get("ACTION").equals("alarm")) {
		String time = "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int hour = cal.get(Calendar.HOUR);
		int minute = cal.get(Calendar.MINUTE);
		time = "" + (hour < 10 ? "0" + hour : hour);
		time = time + ":" + (minute < 10 ? "0" + minute : minute);
		rObj = deviceService.panicAlarm(obj.get("IMEI").toString(),time);
	
	}   
		
		/*
       // currently for keonics this module is not necessary
        
     		else if (obj.get("ACTION").equals("updateTime")) {
			String time = "";
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			int hour = cal.get(Calendar.HOUR);
			int minute = cal.get(Calendar.MINUTE);
			time = "" + (hour < 10 ? "0" + hour : hour);
			time = time + ":" + (minute < 10 ? "0" + minute : minute);
			rObj = deviceService.updateTime(obj.get("tripId").toString(), time,obj.get("userName").toString(), obj.get("password").toString());
		}
		
	 */ 	
		
		
		
		
		
	}catch(Exception e){e.printStackTrace();}
	 rObj.put("NUANCE", new Date().getTime());
	return rObj.toJSONString();
	}
	
	
}
