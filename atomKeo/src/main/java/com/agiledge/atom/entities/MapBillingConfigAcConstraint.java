package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the map_billing_config_ac_constraint database table.
 * 
 */
@Entity
@Table(name="map_billing_config_ac_constraint")
@NamedQuery(name="MapBillingConfigAcConstraint.findAll", query="SELECT m FROM MapBillingConfigAcConstraint m")
public class MapBillingConfigAcConstraint implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private int bc_refId;

	private String fromTime;

	private String rate;

	private String status;

	private String toTime;

	public MapBillingConfigAcConstraint() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBc_refId() {
		return this.bc_refId;
	}

	public void setBc_refId(int bc_refId) {
		this.bc_refId = bc_refId;
	}

	public String getFromTime() {
		return this.fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public String getRate() {
		return this.rate;
	}

	public void setRate(String rate) {
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