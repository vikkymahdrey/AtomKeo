package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the site_vehicle_rate_calander database table.
 * 
 */
@Entity
@Table(name="site_vehicle_rate_calander")
@NamedQuery(name="SiteVehicleRateCalander.findAll", query="SELECT s FROM SiteVehicleRateCalander s")
public class SiteVehicleRateCalander implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fromDate;

	private float ratePerTrip;

	private int site;

	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date toDate;

	@Column(name="vehicle_type")
	private int vehicleType;

	public SiteVehicleRateCalander() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public float getRatePerTrip() {
		return this.ratePerTrip;
	}

	public void setRatePerTrip(float ratePerTrip) {
		this.ratePerTrip = ratePerTrip;
	}

	public int getSite() {
		return this.site;
	}

	public void setSite(int site) {
		this.site = site;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getToDate() {
		return this.toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public int getVehicleType() {
		return this.vehicleType;
	}

	public void setVehicleType(int vehicleType) {
		this.vehicleType = vehicleType;
	}

}