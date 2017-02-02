package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the driver_logins database table.
 * 
 */
@Entity
@Table(name="driver_logins")
@NamedQuery(name="DriverLogin.findAll", query="SELECT d FROM DriverLogin d")
public class DriverLogin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private Timestamp datetime;

	@Column(name="imei_number")
	private String imeiNumber;

	private String status;

	//bi-directional many-to-one association to Driver
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="driverId")
	private Driver driver;

	//bi-directional many-to-one association to TripDetail
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tripid")
	private TripDetail tripDetail;

	//bi-directional many-to-one association to Vehicle
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vehicleId")
	private Vehicle vehicle;

	public DriverLogin() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Timestamp getDatetime() {
		return this.datetime;
	}

	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}

	public String getImeiNumber() {
		return this.imeiNumber;
	}

	public void setImeiNumber(String imeiNumber) {
		this.imeiNumber = imeiNumber;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Driver getDriver() {
		return this.driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public TripDetail getTripDetail() {
		return this.tripDetail;
	}

	public void setTripDetail(TripDetail tripDetail) {
		this.tripDetail = tripDetail;
	}

	public Vehicle getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

}