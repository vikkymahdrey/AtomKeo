package com.agiledge.atom.controller;

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
public class DeviceController {
	private static final Logger logger = Logger.getLogger(DeviceController.class);
	
	@Autowired
	DeviceService deviceService;
	
	@RequestMapping(value={"devicecommunicator"})
	public @ResponseBody
	String deviceCommunicator(@RequestBody String received) throws Exception {
	logger.debug("IN device Controller");
		JSONObject returnjson=new JSONObject();
		try{
		JSONObject json=new JSONObject();
		json=(JSONObject)new JSONParser().parse(received);
		System.out.println("rec"+received);
		if(json.get("ACTION").equals("START_TRIP")){
			returnjson=deviceService.checkpassword(json);
		}
		else if(json.get("ACTION").equals("VEHICLE_TRACKING")){
			returnjson=deviceService.inserttrackingdata(json);
		}
		else if(json.get("ACTION").equals("STOP_TRIP")){
			returnjson=deviceService.stoptrip(json);
		}
		
	}catch(Exception e){}
	return returnjson.toJSONString();
	}

}
