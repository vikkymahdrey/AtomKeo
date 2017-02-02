package com.agiledge.atom.dto;

import java.sql.Timestamp;

public class ShuttleTripDto {
	private String vehicleNo;
	private String totaldistance;
	private Timestamp tripDate;
	
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public String getTotaldistance() {
		return totaldistance;
	}
	public void setTotaldistance(String totaldistance) {
		this.totaldistance = totaldistance;
	}
	public Timestamp getTripDate() {
		return tripDate;
	}
	public void setTripDate(Timestamp timestamp) {
		this.tripDate = timestamp;
	}
}
