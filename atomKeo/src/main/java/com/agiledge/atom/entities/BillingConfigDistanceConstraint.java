package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the billing_config_distance_constraint database table.
 * 
 */
@Entity
@Table(name="billing_config_distance_constraint")
@NamedQuery(name="BillingConfigDistanceConstraint.findAll", query="SELECT b FROM BillingConfigDistanceConstraint b")
public class BillingConfigDistanceConstraint implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private double dcAcRate;

	private int fromKm;

	private float rate;

	private String status;

	private int toKm;

	//bi-directional many-to-one association to BillingTypeMapping
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="bc_refId")
	private BillingTypeMapping billingTypeMapping;

	public BillingConfigDistanceConstraint() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getDcAcRate() {
		return this.dcAcRate;
	}

	public void setDcAcRate(double dcAcRate) {
		this.dcAcRate = dcAcRate;
	}

	public int getFromKm() {
		return this.fromKm;
	}

	public void setFromKm(int fromKm) {
		this.fromKm = fromKm;
	}

	public float getRate() {
		return this.rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getToKm() {
		return this.toKm;
	}

	public void setToKm(int toKm) {
		this.toKm = toKm;
	}

	public BillingTypeMapping getBillingTypeMapping() {
		return this.billingTypeMapping;
	}

	public void setBillingTypeMapping(BillingTypeMapping billingTypeMapping) {
		this.billingTypeMapping = billingTypeMapping;
	}

}