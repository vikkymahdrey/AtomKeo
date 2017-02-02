package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the km_based_trip_config database table.
 * 
 */
@Entity
@Table(name="km_based_trip_config")
@NamedQuery(name="KmBasedTripConfig.findAll", query="SELECT k FROM KmBasedTripConfig k")
public class KmBasedTripConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	@Column(name="billing_type_id")
	private BigInteger billingTypeId;

	@Column(name="km_billing_type")
	private String kmBillingType;

	private String status;

	public KmBasedTripConfig() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigInteger getBillingTypeId() {
		return this.billingTypeId;
	}

	public void setBillingTypeId(BigInteger billingTypeId) {
		this.billingTypeId = billingTypeId;
	}

	public String getKmBillingType() {
		return this.kmBillingType;
	}

	public void setKmBillingType(String kmBillingType) {
		this.kmBillingType = kmBillingType;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}