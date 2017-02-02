package com.agiledge.atom.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agiledge.atom.dto.EmployeeICEDTO;
import com.agiledge.atom.entities.Employee;
import com.agiledge.atom.entities.EmployeeSchedule;
import com.agiledge.atom.entities.EmployeeScheduleAlter;
import com.agiledge.atom.entities.TripDetail;
import com.agiledge.atom.entities.TripDetailsChild;
import com.agiledge.atom.service.intfc.DeviceService;

@Controller
public class EmployeeAppServlet {
	private static final Logger logger = Logger
			.getLogger(EmployeeAppServlet.class);

	@Autowired
	DeviceService deviceService;

	@RequestMapping(value = { "employeeappservlet" })
	public @ResponseBody String employeeappservlet(@RequestBody String received) {
		logger.debug("IN device Controller" + received);
		JSONObject rObj = new JSONObject();
		try {

			JSONObject obj = new JSONObject();
			obj = (JSONObject) new JSONParser().parse(received);
			String otp = null;
			String result = "false";

			if (obj.get("ACTION").equals("INTERNET_CHECK")) {

				rObj = obj;
				rObj.put("result", "true");
			}
			else if (obj.get("ACTION").toString().equals("INITIAL_LOGIN")) {
				
				result = deviceService.mobileNocheck(obj.get("MOBILE_NUMBER").toString());
				if (result.equalsIgnoreCase("true")) {
					otp = deviceService.passwordgenerator();
					otp = deviceService.insertOTP(obj.get("MOBILE_NUMBER").toString(),obj.get("IMEI_NUMBER").toString(),otp);
						
					if (otp != null && !otp.equalsIgnoreCase("")) {
						
						Employee emp=deviceService.getEmployeeDetails(obj.get("IMEI_NUMBER").toString());
						String usertype="emp";
						int roleid=emp.getRole().getId();
						if(roleid==2 || roleid==5)
						{
							usertype = "admin";
						}
						if(roleid==4 || roleid==7)
						{
							usertype = "manager";
						}
						

						rObj.put("result", result);
						rObj.put("EMP_NAME", emp.getDisplayname());
						rObj.put("EMP_PERSONNELNO", emp.getPersonnelNo());
						rObj.put("EMP_GENDER",emp.getGender());
						rObj.put("EMP_EMAIL", emp.getEmailAddress());
						rObj.put("EMP_SITE", emp.getSiteBean().getId());
						rObj.put("EMP_ID", emp.getId());
						rObj.put("otp", otp);
						rObj.put("user_type",usertype);
				} else {
						rObj.put("result", "false");
					}
				} else {
					rObj.put("result", "false");
				}

			}
			
			
			
			 else if (obj.get("ACTION").toString().equalsIgnoreCase("TRIP_DETAILS")) {
					
					rObj.put("result", "true");
					
					

					TripDetail td =deviceService.getTripDetails(obj.get("IMEI_NUMBER").toString(), "Yes");
					
					
					Employee emp=deviceService.getEmployeeDetails(obj.get("IMEI_NUMBER").toString());
					String usertype="emp";
					int roleid=emp.getRole().getId();
					if(roleid==2 || roleid==5)
					{
						usertype = "admin";
					}
					if(roleid==4 || roleid==7)
					{
						usertype = "manager";
					}
					
					
					
					
					if (td != null ) {
						String escortname="";
						String escortcontact="";
						if(td.getEscort()!=null)
						{
							escortname=td.getEscort().getName();
							escortcontact=td.getEscort().getPhone();
							
						}
						rObj.put("TRIP_ID", td.getId());
						rObj.put("EMP_ID", emp.getId());
						rObj.put("TRIP_CODE", td.getTripCode());
						rObj.put("TRIP_TIME", td.getTripTime());
						rObj.put("TRIP_DATE", td.getTripDate());
						rObj.put("TRIP_LOG", td.getTripLog());
						rObj.put("SECURITY", td.getSecurityStatus());
						rObj.put("REG_NO", td.getVehicleBean().getRegNo());
						rObj.put("DRIVER_NAME", td.getDriver().getName());
						rObj.put("DRIVER_CONTACT",  td.getDriver().getContact());
						rObj.put("ESCORT_NAME",td.getEscort());
						rObj.put("ESCORT_CONTACT",escortname);
						rObj.put("EMPS_COUNT", td.getTripDetailsChilds().size());
						rObj.put("user_type", usertype); // for admin app

						if (td.getTripDetailsChilds()!=null) {
							int i = 1;
							for (TripDetailsChild tdchild : td.getTripDetailsChilds()) {
								rObj.put("EMP_ID" + i,
										tdchild.getEmployee().getId());
								rObj.put("PERSONNEL_NO" + i,
										tdchild.getEmployee().getPersonnelNo());
								rObj.put("EMP_NAME" + i,
										tdchild.getEmployee().getDisplayname());
								rObj.put("GENDER" + i,
										tdchild.getEmployee().getGender());
								rObj.put("EMP_CONTACT" + i,
										tdchild.getEmployee().getContactNumber1());
								i++;
							}
						}
					} else {
						System.out.println("here in else");
						rObj.put("EMP_ID", "");
						rObj.put("TRIP_ID", "");
						rObj.put("TRIP_CODE", "");
						rObj.put("TRIP_TIME", "");
						rObj.put("TRIP_DATE", "");
						rObj.put("TRIP_LOG", "");
						rObj.put("SECURITY", "");
						rObj.put("REG_NO", "");
						rObj.put("DRIVER_NAME", "");
						rObj.put("DRIVER_CONTACT", "");
						rObj.put("ESCORT_NAME", "");
						rObj.put("ESCORT_CONTACT", "");
						rObj.put("EMPS_COUNT", "0");
						rObj.put("EMP_NAME", "");
						rObj.put("EMP_PERSONNELNO", "");
						rObj.put("EMP_GENDER", "");
						rObj.put("EMP_EMAIL", "");
						rObj.put("EMP_SITE", "");
						rObj.put("EMP_ID", "");
						rObj.put("user_type", usertype);

					}
			 }
			
			 else if (obj.get("ACTION").toString().equals("IMEI_CHECK")) {
					
					
					result =deviceService.checkImei(obj.get("IMEI_NUMBER").toString());

					rObj.put("result", result);
					if (result.equalsIgnoreCase("true")) {
						
						Employee emp=deviceService.getEmployeeDetails(obj.get("IMEI_NUMBER").toString());
						String usertype="emp";
						int roleid=emp.getRole().getId();
						if(roleid==2 || roleid==5)
						{
							usertype = "admin";
						}
						if(roleid==4 || roleid==7)
						{
							usertype = "manager";
						}
						
						TripDetail td =deviceService.getTripDetails(obj.get("IMEI_NUMBER").toString(), "No");
						
						

						if (td != null ) {
							String escortname="";
							String escortcontact="";
							if(td.getEscort()!=null)
							{
								escortname=td.getEscort().getName();
								escortcontact=td.getEscort().getPhone();
								
							}
						
						
							rObj.put("EMP_NAME", emp.getDisplayname());
							rObj.put("EMP_PERSONNELNO", emp.getPersonnelNo());
							rObj.put("EMP_GENDER", emp.getGender());
							rObj.put("EMP_EMAIL", emp.getEmailAddress());
							rObj.put("EMP_SITE", emp.getSiteBean().getId());
							rObj.put("EMP_ID", emp.getId());
							rObj.put("TRIP_ID", td.getId());
							rObj.put("TRIP_CODE", td.getTripCode());
							rObj.put("TRIP_TIME", td.getTripTime());
							rObj.put("TRIP_DATE", td.getTripDate());
							rObj.put("TRIP_LOG", td.getTripLog());
							rObj.put("SECURITY", td.getSecurityStatus());
							rObj.put("REG_NO", td.getVehicleBean().getRegNo());
							rObj.put("DRIVER_NAME", td.getDriver().getName());
							rObj.put("DRIVER_CONTACT",  td.getDriver().getContact());
							rObj.put("ESCORT_NAME",td.getEscort());
							rObj.put("ESCORT_CONTACT",escortname);
							rObj.put("EMPS_COUNT", td.getTripDetailsChilds().size());
							rObj.put("user_type",usertype); // for admin app
							if (td.getTripDetailsChilds()!=null) {
								int i = 1;
								for (TripDetailsChild tdchild : td.getTripDetailsChilds()) {
									rObj.put("EMP_ID" + i,
											tdchild.getEmployee().getId());
									rObj.put("PERSONNEL_NO" + i,
											tdchild.getEmployee().getPersonnelNo());
									rObj.put("EMP_NAME" + i,
											tdchild.getEmployee().getDisplayname());
									rObj.put("GENDER" + i,
											tdchild.getEmployee().getGender());
									rObj.put("EMP_CONTACT" + i,
											tdchild.getEmployee().getContactNumber1());
									i++;
								}
							}
						} else {

							
							rObj.put("EMP_ID", "");
							rObj.put("TRIP_ID", "");
							rObj.put("TRIP_CODE", "");
							rObj.put("TRIP_TIME", "");
							rObj.put("TRIP_DATE", "");
							rObj.put("TRIP_LOG", "");
							rObj.put("SECURITY", "");
							rObj.put("REG_NO", "");
							rObj.put("DRIVER_NAME", "");
							rObj.put("DRIVER_CONTACT", "");
							rObj.put("ESCORT_NAME", "");
							rObj.put("ESCORT_CONTACT", "");
							rObj.put("EMPS_COUNT", "0");
							rObj.put("EMP_NAME", emp.getDisplayname());
							rObj.put("EMP_PERSONNELNO", emp.getPersonnelNo());
							rObj.put("EMP_GENDER", emp.getGender());
							rObj.put("EMP_EMAIL", emp.getEmailAddress());
							rObj.put("EMP_SITE", emp.getSiteBean().getId());
							rObj.put("EMP_ID", emp.getId());
							rObj.put("user_type",usertype);
						}

					
					}

				}

			 else if (obj.get("ACTION").toString().equalsIgnoreCase(
						"VEHICLE_LOCATION")) {
					
					
					rObj =	deviceService.getVehiclePosition(obj.get("TRIP_ID").toString());
					rObj.put("result", "true");

				} else if (obj.get("ACTION").toString().equalsIgnoreCase(
						"VEHICLE_LOCATION1")) {
					
					System.out.println("here in veh loc");
					rObj =	deviceService.getVehiclePosition1();
					rObj.put("result", "true");

				} else if (obj.get("ACTION").toString().equalsIgnoreCase("EMPS_BOARDED")) {
					rObj =	deviceService.getEmpsBoarded(obj
							.get("TRIP_ID").toString());
					rObj.put("result", "true");

				} else if (obj.get("ACTION").toString().equalsIgnoreCase("ICE_REGISTER")) {
					EmployeeICEDTO dto = new EmployeeICEDTO();
					dto.setEmpid(obj.get("EMP_ID").toString());
					dto.setContact_name1(obj.get("CONTACT_NAME1").toString());
					dto.setContact_number1(obj.get("CONTACT_NUMBER1").toString());
					dto.setContact_email1(obj.get("CONTACT_EMAIL1").toString());
					dto.setContact_name2(obj.get("CONTACT_NAME2").toString());
					dto.setContact_number2(obj.get("CONTACT_NUMBER2").toString());
					dto.setContact_email2(obj.get("CONTACT_EMAIL2").toString());
					int returnint = deviceService.insertICEdetails(dto);
					if (returnint > 0 ) {
						rObj.put("result", "true");
					} else {
						rObj.put("result", "false");
					}

				} else if (obj.get("ACTION").toString().equalsIgnoreCase("ICE_DATA")) {
					EmployeeICEDTO dto = deviceService.getICEdetailsbyID(obj.get("EMP_ID").toString());

					if (dto != null && dto.getContact_name1() != null) {
						rObj.put("result", "true");
						rObj.put("CONTACT_NAME1", dto.getContact_name1());
						rObj.put("CONTACT_NUMBER1", dto.getContact_number1());
						rObj.put("CONTACT_EMAIL1", dto.getContact_email1());
						rObj.put("CONTACT_NAME2", dto.getContact_name2());
						rObj.put("CONTACT_NUMBER2", dto.getContact_number2());
						rObj.put("CONTACT_EMAIL2", dto.getContact_email2());
					} else {
						rObj.put("result", "false");
					}

			} else if (obj.get("ACTION").toString()
					.equalsIgnoreCase("PANIC_ACTIVATED")) {
				String time = "";
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
				int hour = cal.get(Calendar.HOUR);
				int minute = cal.get(Calendar.MINUTE);
				time = "" + (hour < 10 ? "0" + hour : hour);
				time = time + ":" + (minute < 10 ? "0" + minute : minute);
				int returnint = deviceService.panicactivated(
						obj.get("IMEI_NUMBER").toString(), obj.get("EMP_ID")
								.toString(), obj.get("TRIP_ID").toString(), obj
								.get("LAT").toString(), obj.get("LONG")
								.toString(), time);
				if (returnint > 0) {

					rObj.put("result", "true");
					rObj.put("PANIC_STATUS", "ACTIVATED");

				} else {
					rObj.put("result", "false");
					rObj.put("PANIC_STATUS", "NOT ACTIVATED");

				}
			} else if (obj.get("ACTION").toString().equalsIgnoreCase("admin_track")) {
			 // for admin app

				rObj =  deviceService.getLiveTrips();
				rObj.put("result", "true");
			}else if(obj.get("ACTION").toString().equals("MANAGEBOOKING")){
				
				JSONArray scheduleid = new JSONArray();
				JSONArray fromdate = new JSONArray();
				JSONArray todate= new JSONArray();
				JSONArray alterdate= new JSONArray();
				JSONArray alterin= new JSONArray();
				JSONArray alterout= new JSONArray();
				List<EmployeeSchedule> list =deviceService.scheduleList(obj.get("IMEI_NUMBER").toString());
				JSONArray inLog=deviceService.getActiveLog("IN");
				JSONArray outLog=deviceService.getActiveLog("OUT");
				if(list.size()>0){
					for(EmployeeSchedule schedule : list){
						scheduleid.add(schedule.getId());
						fromdate.add(schedule.getFromDate());
						todate.add(schedule.getToDate());
						List<EmployeeScheduleAlter> alterlist=schedule.getEmployeeScheduleAlters();
						if(!alterlist.isEmpty()){
							for(EmployeeScheduleAlter esa: alterlist){
								alterdate.add(esa.getDate());
								alterin.add(esa.getLoginTime());
								alterout.add(esa.getLogoutTime());
							}
						}
					}
					rObj.put("result",true);
					rObj.put("SCHEDULE_ID", scheduleid);
					rObj.put("FROM_DATE", fromdate);
					rObj.put("TO_DATE", todate);
					rObj.put("ALTERDATE", alterdate);
					rObj.put("AL_LOG_IN",alterin);
					rObj.put("AL_LOG_OUT", alterout);
				}else{
					JSONArray dummy = new JSONArray();
					rObj.put("result",false);
					rObj.put("SCHEDULE_ID", dummy);
					rObj.put("FROM_DATE", dummy);
					rObj.put("TO_DATE", dummy);
					rObj.put("LOG_IN", dummy);
					rObj.put("LOG_OUT", dummy);
					rObj.put("ALTERDATE", dummy);
					rObj.put("AL_LOG_IN", dummy);
					rObj.put("AL_LOG_OUT", dummy);
				}
				rObj.put("IN_LOGS", inLog);
				rObj.put("OUT_LOGS", outLog);
				
			}else if (obj.get("ACTION").toString().equals("SCHEDULE_ALTER")) {
			
				String Status=deviceService.requestScheduleAlter(Integer.parseInt(obj.get("SCHEDULE_ID").toString()),obj.get("DATE").toString(),obj.get("LOG_IN").toString(),obj.get("LOG_OUT").toString());
				rObj.put("STATUS", Status);
			
			}else if (obj.get("ACTION").toString().equals("SCHEDULE_CANCEL")) {
			
				String Status=deviceService.requestScheduleAlter(Integer.parseInt(obj.get("SCHEDULE_ID").toString()),obj.get("DATE").toString(),obj.get("LOG_IN").toString(),obj.get("LOG_OUT").toString());
				rObj.put("STATUS", Status);
			
			}else if(obj.get("ACTION").toString().equals("BOOKING")){
				String Status=deviceService.transportrequest(obj.get("IMEI").toString(),obj.get("FROM_DATE").toString(),obj.get("TO_DATE").toString(),obj.get("LOG_IN").toString(),obj.get("LOG_OUT").toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return rObj.toJSONString();
	}

}
