package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the billing_config_ac_constraint database table.
 * 
 */
@Entity
@Table(name="billing_config_ac_constraint")
@NamedQuery(name="BillingConfigAcConstraint.findAll", query="SELECT b FROM BillingConfigAcConstraint b")
public class BillingConfigAcConstraint implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String fromTime;

	private float rate;

	private String status;

	private String toTime;

	//bi-directional many-to-one association to BillingTypeMapping
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="bc_refId")
	private BillingTypeMapping billingTypeMapping;

	public BillingConfigAcConstraint() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFromTime() {
		return this.fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
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

	public String getToTime() {
		return this.toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	public BillingTypeMapping getBillingTypeMapping() {
		return this.billingTypeMapping;
	}

	public void setBillingTypeMapping(BillingTypeMapping billingTypeMapping) {
		this.billingTypeMapping = billingTypeMapping;
	}

}