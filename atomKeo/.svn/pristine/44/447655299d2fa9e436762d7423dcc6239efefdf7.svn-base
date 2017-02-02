package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the km_based_classic_trip_config database table.
 * 
 */
@Entity
@Table(name="km_based_classic_trip_config")
@NamedQuery(name="KmBasedClassicTripConfig.findAll", query="SELECT k FROM KmBasedClassicTripConfig k")
public class KmBasedClassicTripConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	@Column(name="ac_constraint")
	private String acConstraint;

	@Column(name="escort_billing_rate")
	private float escortBillingRate;

	@Column(name="escort_billing_type")
	private String escortBillingType;

	@Column(name="km_billing_id")
	private BigInteger kmBillingId;

	private String status;

	@Column(name="trip_rate")
	private float tripRate;

	@Column(name="vehicle_type")
	private int vehicleType;

	public KmBasedClassicTripConfig() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAcConstraint() {
		return this.acConstraint;
	}

	public void setAcConstraint(String acConstraint) {
		this.acConstraint = acConstraint;
	}

	public float getEscortBillingRate() {
		return this.escortBillingRate;
	}

	public void setEscortBillingRate(float escortBillingRate) {
		this.escortBillingRate = escortBillingRate;
	}

	public String getEscortBillingType() {
		return this.escortBillingType;
	}

	public void setEscortBillingType(String escortBillingType) {
		this.escortBillingType = escortBillingType;
	}

	public BigInteger getKmBillingId() {
		return this.kmBillingId;
	}

	public void setKmBillingId(BigInteger kmBillingId) {
		this.kmBillingId = kmBillingId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getTripRate() {
		return this.tripRate;
	}

	public void setTripRate(float tripRate) {
		this.tripRate = tripRate;
	}

	public int getVehicleType() {
		return this.vehicleType;
	}

	public void setVehicleType(int vehicleType) {
		this.vehicleType = vehicleType;
	}

}