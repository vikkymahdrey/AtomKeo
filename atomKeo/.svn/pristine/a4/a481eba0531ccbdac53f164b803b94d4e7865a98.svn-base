package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the trip_details_removed database table.
 * 
 */
@Entity
@Table(name="trip_details_removed")
@NamedQuery(name="TripDetailsRemoved.findAll", query="SELECT t FROM TripDetailsRemoved t")
public class TripDetailsRemoved implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	@Column(name="in_out")
	private String inOut;

	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="trip_date")
	private Date tripDate;

	@Column(name="trip_time")
	private String tripTime;

	//bi-directional many-to-one association to Employee
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employeeid")
	private Employee employee;

	//bi-directional many-to-one association to EmployeeSchedule
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="scheduleId")
	private EmployeeSchedule employeeSchedule;

	//bi-directional many-to-one association to Landmark
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="landmarkId")
	private Landmark landmark;

	//bi-directional many-to-one association to Site
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="siteid")
	private Site site;

	public TripDetailsRemoved() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInOut() {
		return this.inOut;
	}

	public void setInOut(String inOut) {
		this.inOut = inOut;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getTripDate() {
		return this.tripDate;
	}

	public void setTripDate(Date tripDate) {
		this.tripDate = tripDate;
	}

	public String getTripTime() {
		return this.tripTime;
	}

	public void setTripTime(String tripTime) {
		this.tripTime = tripTime;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public EmployeeSchedule getEmployeeSchedule() {
		return this.employeeSchedule;
	}

	public void setEmployeeSchedule(EmployeeSchedule employeeSchedule) {
		this.employeeSchedule = employeeSchedule;
	}

	public Landmark getLandmark() {
		return this.landmark;
	}

	public void setLandmark(Landmark landmark) {
		this.landmark = landmark;
	}

	public Site getSite() {
		return this.site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

}