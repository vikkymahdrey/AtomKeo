package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the transportation_in_emergency database table.
 * 
 */
@Entity
@Table(name="transportation_in_emergency")
@NamedQuery(name="TransportationInEmergency.findAll", query="SELECT t FROM TransportationInEmergency t")
public class TransportationInEmergency implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="booked_date_status")
	private Date bookedDateStatus;

	@Column(name="booked_time_status")
	private Time bookedTimeStatus;

	private String reason;

	private String startTime;

	@Temporal(TemporalType.DATE)
	private Date travelFromDate;

	@Temporal(TemporalType.DATE)
	private Date travelToDate;

	//bi-directional many-to-one association to Area
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="area")
	private Area areaBean;

	//bi-directional many-to-one association to Employee
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="bookingBy")
	private Employee employee1;

	//bi-directional many-to-one association to Employee
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="bookingFor")
	private Employee employee2;

	//bi-directional many-to-one association to Landmark
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="landmark")
	private Landmark landmark1;

	//bi-directional many-to-one association to Landmark
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="landmarkId")
	private Landmark landmark2;

	//bi-directional many-to-one association to Place
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="place")
	private Place placeBean;

	//bi-directional many-to-one association to Site
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="siteId")
	private Site site;

	//bi-directional many-to-one association to VehicleType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="type")
	private VehicleType vehicleType;

	//bi-directional many-to-one association to Vehicle
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vehicle")
	private Vehicle vehicleBean;

	public TransportationInEmergency() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getBookedDateStatus() {
		return this.bookedDateStatus;
	}

	public void setBookedDateStatus(Date bookedDateStatus) {
		this.bookedDateStatus = bookedDateStatus;
	}

	public Time getBookedTimeStatus() {
		return this.bookedTimeStatus;
	}

	public void setBookedTimeStatus(Time bookedTimeStatus) {
		this.bookedTimeStatus = bookedTimeStatus;
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

	public Date getTravelFromDate() {
		return this.travelFromDate;
	}

	public void setTravelFromDate(Date travelFromDate) {
		this.travelFromDate = travelFromDate;
	}

	public Date getTravelToDate() {
		return this.travelToDate;
	}

	public void setTravelToDate(Date travelToDate) {
		this.travelToDate = travelToDate;
	}

	public Area getAreaBean() {
		return this.areaBean;
	}

	public void setAreaBean(Area areaBean) {
		this.areaBean = areaBean;
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

	public Landmark getLandmark1() {
		return this.landmark1;
	}

	public void setLandmark1(Landmark landmark1) {
		this.landmark1 = landmark1;
	}

	public Landmark getLandmark2() {
		return this.landmark2;
	}

	public void setLandmark2(Landmark landmark2) {
		this.landmark2 = landmark2;
	}

	public Place getPlaceBean() {
		return this.placeBean;
	}

	public void setPlaceBean(Place placeBean) {
		this.placeBean = placeBean;
	}

	public Site getSite() {
		return this.site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public VehicleType getVehicleType() {
		return this.vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Vehicle getVehicleBean() {
		return this.vehicleBean;
	}

	public void setVehicleBean(Vehicle vehicleBean) {
		this.vehicleBean = vehicleBean;
	}

}