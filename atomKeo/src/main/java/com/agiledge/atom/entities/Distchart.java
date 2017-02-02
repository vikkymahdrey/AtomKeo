package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the distchart database table.
 * 
 */
@Entity
@NamedQuery(name="Distchart.findAll", query="SELECT d FROM Distchart d")
public class Distchart implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private double distance;

	//bi-directional many-to-one association to Landmark
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="destId")
	private Landmark landmark1;

	//bi-directional many-to-one association to Landmark
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="srcId")
	private Landmark landmark2;

	public Distchart() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getDistance() {
		return this.distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public Landmark getLandmark1() {
		return this.landmark1;
	}

	public void setLandmark1(Landmark landmark1) {
		this.landmark1 = landmark1;
	}

	public Landmark getLandmark2() {
		return this.landmark2;
	}

	public void setLandmark2(Landmark landmark2) {
		this.landmark2 = landmark2;
	}

}