package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the triproute database table.
 * 
 */
@Entity
@NamedQuery(name="Triproute.findAll", query="SELECT t FROM Triproute t")
public class Triproute implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private double latitude;

	private double longitude;

	private int position;

	private BigInteger tripId;

	public Triproute() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getPosition() {
		return this.position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public BigInteger getTripId() {
		return this.tripId;
	}

	public void setTripId(BigInteger tripId) {
		this.tripId = tripId;
	}

}