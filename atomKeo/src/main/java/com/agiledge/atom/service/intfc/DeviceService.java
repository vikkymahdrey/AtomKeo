package com.agiledge.atom.service.intfc;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.agiledge.atom.dto.EmployeeICEDTO;
import com.agiledge.atom.entities.Employee;
import com.agiledge.atom.entities.EmployeeSchedule;
import com.agiledge.atom.entities.TripDetail;

public interface DeviceService {

	public JSONObject checkpassword(JSONObject json);

	public JSONObject inserttrackingdata(JSONObject json);

	public JSONObject stoptrip(JSONObject json);

	public JSONObject updateGeoCode(String imei, String tripId,
			double latitude, double longitude, double distanceCovered,
			long timeElapsed, long nuance);

	public JSONObject updateGeoCodePassive(String imei, double latitude,
			double longitude, long nuance);

	public JSONObject employeeCheckIn(String imei, String tripId,
			String empCode, double latitude, double longitude, long nuance);

	public JSONObject employeeCheckOut(String imei, String tripId,
			String empCode, double latitude, double longitude, long nuance);

	public JSONObject startTrip(String tripId, String imei);

	public JSONObject stopTrip(String tripId);

	public JSONObject forceStopTrip(String tripId);

	public JSONObject logout(String imei);

	public JSONObject ValidateUserOTP(String password, String IMEI);

	public JSONObject getTripSheet(String IMEI);

	public JSONObject bulkInsert(JSONObject obj);

	public JSONObject panicAlarm(String imei, String time);

	// employee app starts here

	public String mobileNocheck(String mobileno);

	public String passwordgenerator();

	public String insertOTP(String mobileNo, String Imei, String otp);

	public Employee getEmployeeDetails(String Imei);

	public TripDetail getTripDetails(String imei, String auth);

	public String checkImei(String Imei);

	public JSONObject getVehiclePosition(String tripid);

	public JSONObject getVehiclePosition1();

	public JSONObject getEmpsBoarded(String tripid);

	public int insertICEdetails(EmployeeICEDTO dto);

	public EmployeeICEDTO getICEdetailsbyID(String empid);

	public int panicactivated(String imei, String empid, String tripid,
			String Lat, String Long, String time);

	public JSONObject getLiveTrips();

	/*public int panicdeactivated(String imei, String empid, String tripid);*/

	public List<EmployeeSchedule>  scheduleList(String imei);
	
	public JSONArray getActiveLog(String log);
	
	public String requestScheduleAlter(int scheduleid, String date,
			String login, String logout);

	public String transportrequest(String imei, String fromDate,
			String toDate, String LogIn, String LogOut);
	
	
	
	
}