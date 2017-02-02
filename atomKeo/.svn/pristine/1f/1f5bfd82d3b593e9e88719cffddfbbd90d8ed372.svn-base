package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the adhocbooking database table.
 * 
 */
@Entity
@NamedQuery(name="Adhocbooking.findAll", query="SELECT a FROM Adhocbooking a")
public class Adhocbooking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String adhocType;

	@Temporal(TemporalType.TIMESTAMP)
	private Date bookedDate;

	private String comment;

	private String destination;

	private String endTime;

	private String orgination;

	private String pickDrop;

	private String reason;

	private String startTime;

	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date travelDate;

	//bi-directional many-to-one association to Employee
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="approvedBy")
	private Employee employee1;

	//bi-directional many-to-one association to Employee
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="bookedBy")
	private Employee employee2;

	//bi-directional many-to-one association to Employee
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="empId")
	private Employee employee3;

	//bi-directional many-to-one association to EmployeeSchedule
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="scheduleId")
	private EmployeeSchedule employeeSchedule;

	//bi-directional many-to-one association to Site
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="siteId")
	private Site site;

	public Adhocbooking() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdhocType() {
		return this.adhocType;
	}

	public void setAdhocType(String adhocType) {
		this.adhocType = adhocType;
	}

	public Date getBookedDate() {
		return this.bookedDate;
	}

	public void setBookedDate(Date bookedDate) {
		this.bookedDate = bookedDate;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDestination() {
		return this.destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getOrgination() {
		return this.orgination;
	}

	public void setOrgination(String orgination) {
		this.orgination = orgination;
	}

	public String getPickDrop() {
		return this.pickDrop;
	}

	public void setPickDrop(String pickDrop) {
		this.pickDrop = pickDrop;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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

	public Employee getEmployee1() {
		return this.employee1;
	}

	public void setEmployee1(Employee employee1) {
		this.employee1 = employee1;
	}

	public Employee getEmployee2() {
		return this.employee2;
	}

	public void setEmployee2(Employee employee2) {
		this.employee2 = employee2;
	}

	public Employee getEmployee3() {
		return this.employee3;
	}

	public void setEmployee3(Employee employee3) {
		this.employee3 = employee3;
	}

	public EmployeeSchedule getEmployeeSchedule() {
		return this.employeeSchedule;
	}

	public void setEmployeeSchedule(EmployeeSchedule employeeSchedule) {
		this.employeeSchedule = employeeSchedule;
	}

	public Site getSite() {
		return this.site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

}