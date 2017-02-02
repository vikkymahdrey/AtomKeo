package com.agiledge.atom.entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the vendor_trip_sheet_parent database table.
 * 
 */
@Entity
@Table(name="vendor_trip_sheet_parent")
@NamedQuery(name="VendorTripSheetParent.findAll", query="SELECT v FROM VendorTripSheetParent v")
public class VendorTripSheetParent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String approvalStatus;

	private int authenticatedBy;

	private String comment;

	private double distanceCovered;

	private String downloadStatus;

	private String driverContact;

	private String escort;

	private String escortClock;
	
	private String vehicleNo;

	private BigInteger insertedBy;

	private String logTime;
    
	private double manualDistance;

	private String onTimeStatus;

	private String startTime;

	private String status;

	private String stopTime;

	private BigInteger timeElapsed;

	@Column(name="vehicle_imei")
	private String vehicleImei;

	//bi-directional many-to-one association to Employee
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="updatedBy")
	private Employee employee;

	//bi-directional many-to-one association to TripDetail
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tripId")
	private TripDetail tripDetail;

	
	public VendorTripSheetParent() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApprovalStatus() {
		return this.approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public int getAuthenticatedBy() {
		return this.authenticatedBy;
	}

	public void setAuthenticatedBy(int authenticatedBy) {
		this.authenticatedBy = authenticatedBy;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public double getDistanceCovered() {
		return this.distanceCovered;
	}

	public void setDistanceCovered(double distanceCovered) {
		this.distanceCovered = distanceCovered;
	}

	public String getDownloadStatus() {
		return this.downloadStatus;
	}

	public void setDownloadStatus(String downloadStatus) {
		this.downloadStatus = downloadStatus;
	}

	public String getDriverContact() {
		return this.driverContact;
	}

	public void setDriverContact(String driverContact) {
		this.driverContact = driverContact;
	}

	public String getEscort() {
		return this.escort;
	}

	public void setEscort(String escort) {
		this.escort = escort;
	}

	public String getEscortClock() {
		return this.escortClock;
	}

	public void setEscortClock(String escortClock) {
		this.escortClock = escortClock;
	}

	public BigInteger getInsertedBy() {
		return this.insertedBy;
	}

	public void setInsertedBy(BigInteger insertedBy) {
		this.insertedBy = insertedBy;
	}

	public String getLogTime() {
		return this.logTime;
	}

	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}

	public double getManualDistance() {
		return this.manualDistance;
	}

	public void setManualDistance(double manualDistance) {
		this.manualDistance = manualDistance;
	}

	public String getOnTimeStatus() {
		return this.onTimeStatus;
	}

	public void setOnTimeStatus(String onTimeStatus) {
		this.onTimeStatus = onTimeStatus;
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

	public String getStopTime() {
		return this.stopTime;
	}

	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}

	public BigInteger getTimeElapsed() {
		return this.timeElapsed;
	}

	public void setTimeElapsed(BigInteger timeElapsed) {
		this.timeElapsed = timeElapsed;
	}

	public String getVehicleImei() {
		return this.vehicleImei;
	}

	public void setVehicleImei(String vehicleImei) {
		this.vehicleImei = vehicleImei;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public TripDetail getTripDetail() {
		return this.tripDetail;
	}

	public void setTripDetail(TripDetail tripDetail) {
		this.tripDetail = tripDetail;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	

}