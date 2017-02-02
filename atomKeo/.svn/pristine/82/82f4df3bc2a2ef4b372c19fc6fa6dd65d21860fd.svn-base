package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the fencecordinates database table.
 * 
 */
@Entity
@Table(name="fencecordinates")
@NamedQuery(name="Fencecordinate.findAll", query="SELECT f FROM Fencecordinate f")
public class Fencecordinate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String endLat;

	private String endLon;

	private int fenceId;

	private int pathOrder;

	private String startLat;

	private String startLon;

	public Fencecordinate() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEndLat() {
		return this.endLat;
	}

	public void setEndLat(String endLat) {
		this.endLat = endLat;
	}

	public String getEndLon() {
		return this.endLon;
	}

	public void setEndLon(String endLon) {
		this.endLon = endLon;
	}

	public int getFenceId() {
		return this.fenceId;
	}

	public void setFenceId(int fenceId) {
		this.fenceId = fenceId;
	}

	public int getPathOrder() {
		return this.pathOrder;
	}

	public void setPathOrder(int pathOrder) {
		this.pathOrder = pathOrder;
	}

	public String getStartLat() {
		return this.startLat;
	}

	public void setStartLat(String startLat) {
		this.startLat = startLat;
	}

	public String getStartLon() {
		return this.startLon;
	}

	public void setStartLon(String startLon) {
		this.startLon = startLon;
	}

}