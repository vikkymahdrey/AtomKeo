package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the slab_based_billing_config database table.
 * 
 */
@Entity
@Table(name="slab_based_billing_config")
@NamedQuery(name="SlabBasedBillingConfig.findAll", query="SELECT s FROM SlabBasedBillingConfig s")
public class SlabBasedBillingConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private BigInteger billing_type_refId;

	@Column(name="escort_billing_rate")
	private float escortBillingRate;

	@Column(name="escort_billing_type")
	private String escortBillingType;

	private String status;

	@Column(name="trip_rate")
	private float tripRate;

	@Column(name="vehicle_type")
	private BigInteger vehicleType;

	public SlabBasedBillingConfig() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigInteger getBilling_type_refId() {
		return this.billing_type_refId;
	}

	public void setBilling_type_refId(BigInteger billing_type_refId) {
		this.billing_type_refId = billing_type_refId;
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

	public BigInteger getVehicleType() {
		return this.vehicleType;
	}

	public void setVehicleType(BigInteger vehicleType) {
		this.vehicleType = vehicleType;
	}

}