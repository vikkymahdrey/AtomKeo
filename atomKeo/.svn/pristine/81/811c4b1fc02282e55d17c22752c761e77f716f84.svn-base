package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the km_based_template_trip_config database table.
 * 
 */
@Entity
@Table(name="km_based_template_trip_config")
@NamedQuery(name="KmBasedTemplateTripConfig.findAll", query="SELECT k FROM KmBasedTemplateTripConfig k")
public class KmBasedTemplateTripConfig implements Serializable {
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

	private float swingRate;

	private String swingRateType;

	private BigInteger templateId;

	@Column(name="trip_rate")
	private float tripRate;

	@Column(name="vehicle_type")
	private int vehicleType;

	public KmBasedTemplateTripConfig() {
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

	public float getSwingRate() {
		return this.swingRate;
	}

	public void setSwingRate(float swingRate) {
		this.swingRate = swingRate;
	}

	public String getSwingRateType() {
		return this.swingRateType;
	}

	public void setSwingRateType(String swingRateType) {
		this.swingRateType = swingRateType;
	}

	public BigInteger getTemplateId() {
		return this.templateId;
	}

	public void setTemplateId(BigInteger templateId) {
		this.templateId = templateId;
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