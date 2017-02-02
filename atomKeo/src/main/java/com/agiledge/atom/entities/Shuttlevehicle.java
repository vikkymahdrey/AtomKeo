package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the shuttlevehicle database table.
 * 
 */
@Entity
@NamedQuery(name="Shuttlevehicle.findAll", query="SELECT s FROM Shuttlevehicle s")
public class Shuttlevehicle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private int availableSeat;

	private int count;

	private String inOutTime;

	private int routeId;

	private int vehicleType;

	public Shuttlevehicle() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAvailableSeat() {
		return this.availableSeat;
	}

	public void setAvailableSeat(int availableSeat) {
		this.availableSeat = availableSeat;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getInOutTime() {
		return this.inOutTime;
	}

	public void setInOutTime(String inOutTime) {
		this.inOutTime = inOutTime;
	}

	public int getRouteId() {
		return this.routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public int getVehicleType() {
		return this.vehicleType;
	}

	public void setVehicleType(int vehicleType) {
		this.vehicleType = vehicleType;
	}

}