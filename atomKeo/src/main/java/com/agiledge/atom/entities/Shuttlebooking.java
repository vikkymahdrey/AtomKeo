package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the shuttlebooking database table.
 * 
 */
@Entity
@NamedQuery(name="Shuttlebooking.findAll", query="SELECT s FROM Shuttlebooking s")
public class Shuttlebooking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String approvalStatus;

	@Temporal(TemporalType.TIMESTAMP)
	private Date bookedDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date effectiveDate;

	private String employeeId;

	@Column(name="in_out")
	private String inOut;

	private String inOutTime;

	private String landmark;

	@Temporal(TemporalType.TIMESTAMP)
	private Date reConfirmDate;

	private String routeId;

	private int siteId;

	private String status;

	private int waitingList;

	public Shuttlebooking() {
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

	public Date getBookedDate() {
		return this.bookedDate;
	}

	public void setBookedDate(Date bookedDate) {
		this.bookedDate = bookedDate;
	}

	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getInOut() {
		return this.inOut;
	}

	public void setInOut(String inOut) {
		this.inOut = inOut;
	}

	public String getInOutTime() {
		return this.inOutTime;
	}

	public void setInOutTime(String inOutTime) {
		this.inOutTime = inOutTime;
	}

	public String getLandmark() {
		return this.landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public Date getReConfirmDate() {
		return this.reConfirmDate;
	}

	public void setReConfirmDate(Date reConfirmDate) {
		this.reConfirmDate = reConfirmDate;
	}

	public String getRouteId() {
		return this.routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public int getSiteId() {
		return this.siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getWaitingList() {
		return this.waitingList;
	}

	public void setWaitingList(int waitingList) {
		this.waitingList = waitingList;
	}

}