package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the adhoctrips database table.
 * 
 */
@Entity
@Table(name="adhoctrips")
@NamedQuery(name="Adhoctrip.findAll", query="SELECT a FROM Adhoctrip a")
public class Adhoctrip implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private float actualDistance;

	private String adhoctype;

	private int bookingId;

	private float distance;

	private String employeeId;

	private String endPlace;

	private String endTime;

	private float enteredDistance;

	private String escortApprove;

	private String escortId;

	private String isEscort;

	private String siteId;

	private String startPlace;

	private String startTime;

	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date travelDate;

	private String travelTime;

	private String tripCode;

	private String vehicleNo;

	private String vehicleType;

	private int vendorId;

	public Adhoctrip() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getActualDistance() {
		return this.actualDistance;
	}

	public void setActualDistance(float actualDistance) {
		this.actualDistance = actualDistance;
	}

	public String getAdhoctype() {
		return this.adhoctype;
	}

	public void setAdhoctype(String adhoctype) {
		this.adhoctype = adhoctype;
	}

	public int getBookingId() {
		return this.bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public float getDistance() {
		return this.distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEndPlace() {
		return this.endPlace;
	}

	public void setEndPlace(String endPlace) {
		this.endPlace = endPlace;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public float getEnteredDistance() {
		return this.enteredDistance;
	}

	public void setEnteredDistance(float enteredDistance) {
		this.enteredDistance = enteredDistance;
	}

	public String getEscortApprove() {
		return this.escortApprove;
	}

	public void setEscortApprove(String escortApprove) {
		this.escortApprove = escortApprove;
	}

	public String getEscortId() {
		return this.escortId;
	}

	public void setEscortId(String escortId) {
		this.escortId = escortId;
	}

	public String getIsEscort() {
		return this.isEscort;
	}

	public void setIsEscort(String isEscort) {
		this.isEscort = isEscort;
	}

	public String getSiteId() {
		return this.siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getStartPlace() {
		return this.startPlace;
	}

	public void setStartPlace(String startPlace) {
		this.startPlace = startPlace;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getTravelDate() {
		return this.travelDate;
	}

	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}

	public String getTravelTime() {
		return this.travelTime;
	}

	public void setTravelTime(String travelTime) {
		this.travelTime = travelTime;
	}

	public String getTripCode() {
		return this.tripCode;
	}

	public void setTripCode(String tripCode) {
		this.tripCode = tripCode;
	}

	public String getVehicleNo() {
		return this.vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getVehicleType() {
		return this.vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public int getVendorId() {
		return this.vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

}