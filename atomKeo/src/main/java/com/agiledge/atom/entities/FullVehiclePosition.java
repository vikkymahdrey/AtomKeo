package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the full_vehicle_position database table.
 * 
 */
@Entity
@Table(name="full_vehicle_position")
@NamedQuery(name="FullVehiclePosition.findAll", query="SELECT f FROM FullVehiclePosition f")
public class FullVehiclePosition implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_time")
	private Date dateTime;

	private double lattitude;

	private double longitude;

	//bi-directional many-to-one association to Driver
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="dvId")
	private Driver driver;

	public FullVehiclePosition() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public double getLattitude() {
		return this.lattitude;
	}

	public void setLattitude(double lattitude) {
		this.lattitude = lattitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Driver getDriver() {
		return this.driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

}