package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;


/**
 * The persistent class for the trip_details_parent_modified database table.
 * 
 */
@Entity
@Table(name="trip_details_parent_modified")
@NamedQuery(name="TripDetailsParentModified.findAll", query="SELECT t FROM TripDetailsParentModified t")
public class TripDetailsParentModified implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String tripid;

	private int destinationLandmark;

	private double distance;

	private double distanceFromPreTrip;

	private String driverPswd;

	private String escortPswd;

	private BigInteger preTripId;

	private int routeId;

	private String routingType;

	@Column(name="security_status")
	private String securityStatus;

	private String status;

	private String travelTime;

	@Column(name="trip_code")
	private String tripCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="trip_date")
	private Date tripDate;

	@Column(name="trip_log")
	private String tripLog;

	@Column(name="trip_time")
	private String tripTime;

	//bi-directional many-to-one association to Driver
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="driverId")
	private Driver driver;

	//bi-directional many-to-one association to Escort
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="escortId")
	private Escort escort;

	//bi-directional many-to-one association to Site
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="siteId")
	private Site site;

	//bi-directional many-to-one association to VehicleType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vehicle_type")
	private VehicleType vehicleTypeBean;

	//bi-directional many-to-one association to Vehicle
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vehicle")
	private Vehicle vehicleBean;

	public TripDetailsParentModified() {
	}

	public String getTripid() {
		return this.tripid;
	}

	public void setTripid(String tripid) {
		this.tripid = tripid;
	}

	public int getDestinationLandmark() {
		return this.destinationLandmark;
	}

	public void setDestinationLandmark(int destinationLandmark) {
		this.destinationLandmark = destinationLandmark;
	}

	public double getDistance() {
		return this.distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getDistanceFromPreTrip() {
		return this.distanceFromPreTrip;
	}

	public void setDistanceFromPreTrip(double distanceFromPreTrip) {
		this.distanceFromPreTrip = distanceFromPreTrip;
	}

	public String getDriverPswd() {
		return this.driverPswd;
	}

	public void setDriverPswd(String driverPswd) {
		this.driverPswd = driverPswd;
	}

	public String getEscortPswd() {
		return this.escortPswd;
	}

	public void setEscortPswd(String escortPswd) {
		this.escortPswd = escortPswd;
	}

	public BigInteger getPreTripId() {
		return this.preTripId;
	}

	public void setPreTripId(BigInteger preTripId) {
		this.preTripId = preTripId;
	}

	public int getRouteId() {
		return this.routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public String getRoutingType() {
		return this.routingType;
	}

	public void setRoutingType(String routingType) {
		this.routingType = routingType;
	}

	public String getSecurityStatus() {
		return this.securityStatus;
	}

	public void setSecurityStatus(String securityStatus) {
		this.securityStatus = securityStatus;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Date getTripDate() {
		return this.tripDate;
	}

	public void setTripDate(Date tripDate) {
		this.tripDate = tripDate;
	}

	public String getTripLog() {
		return this.tripLog;
	}

	public void setTripLog(String tripLog) {
		this.tripLog = tripLog;
	}

	public String getTripTime() {
		return this.tripTime;
	}

	public void setTripTime(String tripTime) {
		this.tripTime = tripTime;
	}

	public Driver getDriver() {
		return this.driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Escort getEscort() {
		return this.escort;
	}

	public void setEscort(Escort escort) {
		this.escort = escort;
	}

	public Site getSite() {
		return this.site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public VehicleType getVehicleTypeBean() {
		return this.vehicleTypeBean;
	}

	public void setVehicleTypeBean(VehicleType vehicleTypeBean) {
		this.vehicleTypeBean = vehicleTypeBean;
	}

	public Vehicle getVehicleBean() {
		return this.vehicleBean;
	}

	public void setVehicleBean(Vehicle vehicleBean) {
		this.vehicleBean = vehicleBean;
	}

}