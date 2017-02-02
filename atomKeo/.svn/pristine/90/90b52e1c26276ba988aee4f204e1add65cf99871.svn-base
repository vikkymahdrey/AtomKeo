package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the classic_billing_config_ac_constraint database table.
 * 
 */
@Entity
@Table(name="classic_billing_config_ac_constraint")
@NamedQuery(name="ClassicBillingConfigAcConstraint.findAll", query="SELECT c FROM ClassicBillingConfigAcConstraint c")
public class ClassicBillingConfigAcConstraint implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private BigInteger bc_refId;

	private String fromTime;

	private float rate;

	private String status;

	private String toTime;

	public ClassicBillingConfigAcConstraint() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigInteger getBc_refId() {
		return this.bc_refId;
	}

	public void setBc_refId(BigInteger bc_refId) {
		this.bc_refId = bc_refId;
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

}