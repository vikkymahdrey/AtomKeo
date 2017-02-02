package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the driver_vehicle database table.
 * 
 */
@Entity
@Table(name="driver_vehicle")
@NamedQuery(name="DriverVehicle.findAll", query="SELECT d FROM DriverVehicle d")
public class DriverVehicle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String status;

	//bi-directional many-to-one association to Driver
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="driverId")
	private Driver driver;

	//bi-directional many-to-one association to Vehicle
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vehicleId")
	private Vehicle vehicle;

	public DriverVehicle() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Vehicle getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

}