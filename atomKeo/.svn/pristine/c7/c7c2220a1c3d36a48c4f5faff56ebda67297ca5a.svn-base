package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the shuttleposition database table.
 * 
 */
@Entity
@NamedQuery(name="Shuttleposition.findAll", query="SELECT s FROM Shuttleposition s")
public class Shuttleposition implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private Timestamp datetime;

	private String distance;

	private String lat;

	private String lng;

	private String status;

	//bi-directional many-to-one association to Tripshuttle
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="shuttleid")
	private Tripshuttle tripshuttle;

	public Shuttleposition() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getDatetime() {
		return this.datetime;
	}

	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}

	public String getDistance() {
		return this.distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getLat() {
		return this.lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return this.lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Tripshuttle getTripshuttle() {
		return this.tripshuttle;
	}

	public void setTripshuttle(Tripshuttle tripshuttle) {
		this.tripshuttle = tripshuttle;
	}

}