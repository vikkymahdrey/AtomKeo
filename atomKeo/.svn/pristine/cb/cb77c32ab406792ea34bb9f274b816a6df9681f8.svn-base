package com.agiledge.atom.dao.intfc;

import java.util.ArrayList;
import java.util.List;

import com.agiledge.atom.dto.DriverVehicleDto;
import com.agiledge.atom.dto.VehicleDto;
import com.agiledge.atom.dto.VehicleTypeDto;
import com.agiledge.atom.entities.Vehicle;
import com.agiledge.atom.entities.VehicleType;


public interface VehicleTypeDao {

	List<VehicleType> getAllVehicleType() throws Exception;
	VehicleType getVehicleTypeById(int vehicleTypeId) throws Exception;
	VehicleType insertVehicleType(VehicleType vt) throws Exception;
	ArrayList<VehicleTypeDto> getAllVehicleTypeBySite(int siteId) throws Exception;
	void getAllVehicleTypeByToAppend(ArrayList<VehicleTypeDto> vehicleType) throws Exception;
	ArrayList<VehicleDto> getVehicleTrackInInterval(String tripId) throws Exception;
	ArrayList<VehicleDto> vehicleTrack(String tripId) throws Exception;
	ArrayList<DriverVehicleDto> getVehicle(String vehicleId) throws Exception;
	Vehicle getVehicleById(String vehicleId) throws Exception;
	List<Vehicle> getAllVehicle()throws Exception;
	Vehicle getVehicleByIdNotStatus(String vehicleId) throws Exception;
	Vehicle updateVehicleStatus(Vehicle v)throws Exception;
	Vehicle addVehicle(Vehicle v)throws Exception;
	VehicleType setSiteVehicle(VehicleType vt)throws Exception;
	List<Vehicle> getVehiclesByIdOrNot(String vehicleId) throws Exception;
	String getVehicleByImei(String imei)throws Exception;
	String getDistance(String imei,String fromDate,String toDate);
	
}
