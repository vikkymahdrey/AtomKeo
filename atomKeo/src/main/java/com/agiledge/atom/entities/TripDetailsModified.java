package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the trip_details_modified database table.
 * 
 */
@Entity
@Table(name="trip_details_modified")
@NamedQuery(name="TripDetailsModified.findAll", query="SELECT t FROM TripDetailsModified t")
public class TripDetailsModified implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private float dist;

	private int routedOrder;

	private String status;

	private String time;

	private String transportType;

	//bi-directional many-to-one association to Employee
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="empid")
	private Employee employee;

	//bi-directional many-to-one association to EmployeeSchedule
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="scheduleId")
	private EmployeeSchedule employeeSchedule;

	//bi-directional many-to-one association to Landmark
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="landmarkId")
	private Landmark landmark;

	//bi-directional many-to-one association to TripDetail
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tripid")
	private TripDetail tripDetail;

	public TripDetailsModified() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getDist() {
		return this.dist;
	}

	public void setDist(float dist) {
		this.dist = dist;
	}

	public int getRoutedOrder() {
		return this.routedOrder;
	}

	public void setRoutedOrder(int routedOrder) {
		this.routedOrder = routedOrder;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTransportType() {
		return this.transportType;
	}

	public void setTransportType(String transportType) {
		this.transportType = transportType;
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

	public TripDetail getTripDetail() {
		return this.tripDetail;
	}

	public void setTripDetail(TripDetail tripDetail) {
		this.tripDetail = tripDetail;
	}

}