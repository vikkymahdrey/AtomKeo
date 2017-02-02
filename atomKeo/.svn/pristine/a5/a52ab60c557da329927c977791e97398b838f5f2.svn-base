package com.agiledge.atom.dao.intfc;

import java.util.List;

import org.json.simple.JSONObject;

import com.agiledge.atom.dto.EmployeeICEDTO;
import com.agiledge.atom.entities.Employee;
import com.agiledge.atom.entities.EmployeeSchedule;
import com.agiledge.atom.entities.TripDetail;

public interface DeviceDao {

	public JSONObject checkpassword(JSONObject json);

	public JSONObject inserttrackingdata(JSONObject json);

	public JSONObject stoptrip(JSONObject json);

	public int startTrip(String tripId, String imei);

	public int stopTrip(String tripId);

	public int forceStopTrip(String tripId);

	public int employeeCheckIn(String tripId, String empCode, double latitude,
			double longitude, long nuance);

	public int employeeCheckOut(String tripId, String empCode, double latitude,
			double longitude, long nuance);

	public int updateGeoCode(String imei, String tripId, double latitude,
			double longitude, long nuance);

	public int updateDistanceCovered(String tripId, double distanceCovered,
			long timeElapsed);

	public int logout(String imei);

	public int checkUserOTP(String password, String imei);

	public TripDetail getTripByDriverImei(String imei);

	public TripDetail getTripById(String tripid);
	
	public int setTripAsDownloaded(String tripid, String imei, String vehicleNo);

	/*
	 * public int updateGeoCodePassive(String imei, double latitude, double
	 * longitude, long nuance);
	 */

	// emp app starts here

	public String mobileNocheck(String mobileno);

	public String insertOTP(String mobileNo, String imei, String otp);

	public Employee getEmployeeDetails(String imei);

	public TripDetail getTripDetails(String imei, String auth);

	public String checkImei(String imei);

	public JSONObject getVehiclePosition(String tripid);

	public JSONObject getVehiclePosition1();

	public JSONObject getEmpsBoarded(String tripid);

	public int insertICEdetails(EmployeeICEDTO dto);

	public EmployeeICEDTO getICEdetailsbyID(String empid);

	public JSONObject getLiveTrips();
	
	public List<EmployeeSchedule> scheduleList(String imei);

	public String requestScheduleAlter(int scheduleid, String date,
			String login, String logout);

	public String transportrequest(String imei, String fromDate, String toDate,
			String logIn, String logOut);

}
