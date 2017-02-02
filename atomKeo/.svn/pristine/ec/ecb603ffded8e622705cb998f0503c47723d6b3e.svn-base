package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the timesloat database table.
 * 
 */
@Entity
@NamedQuery(name="Timesloat.findAll", query="SELECT t FROM Timesloat t")
public class Timesloat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String endTime;

	private double speedpkm;

	private String startTime;

	private String traffic;

	public Timesloat() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public double getSpeedpkm() {
		return this.speedpkm;
	}

	public void setSpeedpkm(double speedpkm) {
		this.speedpkm = speedpkm;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getTraffic() {
		return this.traffic;
	}

	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}

}