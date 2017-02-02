package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the vehicle_km_rate database table.
 * 
 */
@Entity
@Table(name="vehicle_km_rate")
@NamedQuery(name="VehicleKmRate.findAll", query="SELECT v FROM VehicleKmRate v")
public class VehicleKmRate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private float ratePerKm;

	private BigInteger refId;

	@Column(name="vehicle_type")
	private int vehicleType;

	public VehicleKmRate() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getRatePerKm() {
		return this.ratePerKm;
	}

	public void setRatePerKm(float ratePerKm) {
		this.ratePerKm = ratePerKm;
	}

	public BigInteger getRefId() {
		return this.refId;
	}

	public void setRefId(BigInteger refId) {
		this.refId = refId;
	}

	public int getVehicleType() {
		return this.vehicleType;
	}

	public void setVehicleType(int vehicleType) {
		this.vehicleType = vehicleType;
	}

}