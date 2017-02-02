package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the vendor_trip_sheet database table.
 * 
 */
@Entity
@Table(name="vendor_trip_sheet")
@NamedQuery(name="VendorTripSheet.findAll", query="SELECT v FROM VendorTripSheet v")
public class VendorTripSheet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String curStatus;

	private BigInteger insertedOrder;

	private String inTime;

	private String isCorrectPos;

	private String keypin;

	private double latitude;

	private double longitude;

	private String noShowReason;

	private String outTime;

	private String showStatus;

	//bi-directional many-to-one association to Employee
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employeeId")
	private Employee employee;

	//bi-directional many-to-one association to TripDetail
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tripId")
	private TripDetail tripDetail;

	public VendorTripSheet() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCurStatus() {
		return this.curStatus;
	}

	public void setCurStatus(String curStatus) {
		this.curStatus = curStatus;
	}

	public BigInteger getInsertedOrder() {
		return this.insertedOrder;
	}

	public void setInsertedOrder(BigInteger insertedOrder) {
		this.insertedOrder = insertedOrder;
	}

	public String getInTime() {
		return this.inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String getIsCorrectPos() {
		return this.isCorrectPos;
	}

	public void setIsCorrectPos(String isCorrectPos) {
		this.isCorrectPos = isCorrectPos;
	}

	public String getKeypin() {
		return this.keypin;
	}

	public void setKeypin(String keypin) {
		this.keypin = keypin;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getNoShowReason() {
		return this.noShowReason;
	}

	public void setNoShowReason(String noShowReason) {
		this.noShowReason = noShowReason;
	}

	public String getOutTime() {
		return this.outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	public String getShowStatus() {
		return this.showStatus;
	}

	public void setShowStatus(String showStatus) {
		this.showStatus = showStatus;
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

}