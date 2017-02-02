package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the shuttletripemployee database table.
 * 
 */
@Entity
@NamedQuery(name="Shuttletripemployee.findAll", query="SELECT s FROM Shuttletripemployee s")
public class Shuttletripemployee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String employeeid;

	private String landmarkid;

	private String shuttletripid;

	private String status;

	public Shuttletripemployee() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmployeeid() {
		return this.employeeid;
	}

	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}

	public String getLandmarkid() {
		return this.landmarkid;
	}

	public void setLandmarkid(String landmarkid) {
		this.landmarkid = landmarkid;
	}

	public String getShuttletripid() {
		return this.shuttletripid;
	}

	public void setShuttletripid(String shuttletripid) {
		this.shuttletripid = shuttletripid;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}