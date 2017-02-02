package com.agiledge.atom.dto;

public class VehicleDto {
private String id;	
private String vehicleType;
private String vehicleNo;
private String doneBy;
private int seat;
private int sit_cap;
private String vehicleTypeId;
private String lattitude;
private String longitude;
private String status;
private String tripId;
private String ladyIn;
private String vendor;
private String driver;
private String dateTime;
private String traveltime;
private String childpickuptime[];

public String[] getChildpickuptime() {
	return childpickuptime;
}
public void setChildpickuptime(String[] childpickuptime) {
	this.childpickuptime = childpickuptime;
}
public String getTraveltime() {
	return traveltime;
}
public void setTraveltime(String traveltime) {
	this.traveltime = traveltime;
}
public String getTravellingtime() {
	return travellingtime;
}
public void setTravellingtime(String travellingtime) {
	this.travellingtime = travellingtime;
}
private String travellingtime;

public String getVehicleType() {
	return vehicleType;
}
public void setVehicleType(String vehicleType) {
	this.vehicleType = vehicleType;
}
public String getVehicleNo() {
	return vehicleNo;
}
public void setVehicleNo(String vehicleNo) {
	this.vehicleNo = vehicleNo;
}
public String getDoneBy() {
	return doneBy;
}
public void setDoneBy(String doneBy) {
	this.doneBy = doneBy;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public int getSeat() {
	return seat;
}
public void setSeat(int seat) {
	this.seat = seat;
}
public int getSit_cap() {
	return sit_cap;
}
public void setSit_cap(int sit_cap) {
	this.sit_cap = sit_cap;
}
public String getVehicleTypeId() {
	return vehicleTypeId;
}
public void setVehicleTypeId(String vehicleTypeId) {
	this.vehicleTypeId = vehicleTypeId;
}
public String getLongitude() {
	return longitude;
}
public void setLongitude(String longitude) {
	this.longitude = longitude;
}
public String getLattitude() {
	return lattitude;
}
public void setLattitude(String lattitude) {
	this.lattitude = lattitude;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getTripId() {
	return tripId;
}
public void setTripId(String tripId) {
	this.tripId = tripId;
}
public String getLadyIn() {
	return ladyIn;
}
public void setLadyIn(String ladyIn) {
	this.ladyIn = ladyIn;
}
public String getVendor() {
	return vendor;
}
public void setVendor(String vendor) {
	this.vendor = vendor;
}
public String getDriver() {
	return driver;
}
public void setDriver(String driver) {
	this.driver = driver;
}
public String getDateTime() {
	return dateTime;
}
public void setDateTime(String dateTime) {
	this.dateTime = dateTime;
}
}
