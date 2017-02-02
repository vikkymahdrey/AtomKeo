package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the routechildsample database table.
 * 
 */
@Entity
@NamedQuery(name="Routechildsample.findAll", query="SELECT r FROM Routechildsample r")
public class Routechildsample implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private int landmarkId;

	private int position;

	private int routeId;

	public Routechildsample() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLandmarkId() {
		return this.landmarkId;
	}

	public void setLandmarkId(int landmarkId) {
		this.landmarkId = landmarkId;
	}

	public int getPosition() {
		return this.position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getRouteId() {
		return this.routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

}