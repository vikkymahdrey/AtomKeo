package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tripshuttle database table.
 * 
 */
@Entity
@NamedQuery(name="Tripshuttle.findAll", query="SELECT t FROM Tripshuttle t")
public class Tripshuttle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String imei;

	private String status;

	private String totaldistance;

	private Timestamp tripendtime;

	private Timestamp tripstarttime;

	//bi-directional many-to-one association to Escort
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="securityid")
	private Escort escort;

	//bi-directional many-to-one association to Shuttleposition
	@OneToMany(mappedBy="tripshuttle")
	private List<Shuttleposition> shuttlepositions;

	public Tripshuttle() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImei() {
		return this.imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTotaldistance() {
		return this.totaldistance;
	}

	public void setTotaldistance(String totaldistance) {
		this.totaldistance = totaldistance;
	}

	public Timestamp getTripendtime() {
		return this.tripendtime;
	}

	public void setTripendtime(Timestamp tripendtime) {
		this.tripendtime = tripendtime;
	}

	public Timestamp getTripstarttime() {
		return this.tripstarttime;
	}

	public void setTripstarttime(Timestamp tripstarttime) {
		this.tripstarttime = tripstarttime;
	}

	public Escort getEscort() {
		return this.escort;
	}

	public void setEscort(Escort escort) {
		this.escort = escort;
	}

	public List<Shuttleposition> getShuttlepositions() {
		return this.shuttlepositions;
	}

	public void setShuttlepositions(List<Shuttleposition> shuttlepositions) {
		this.shuttlepositions = shuttlepositions;
	}

	public Shuttleposition addShuttleposition(Shuttleposition shuttleposition) {
		getShuttlepositions().add(shuttleposition);
		shuttleposition.setTripshuttle(this);

		return shuttleposition;
	}

	public Shuttleposition removeShuttleposition(Shuttleposition shuttleposition) {
		getShuttlepositions().remove(shuttleposition);
		shuttleposition.setTripshuttle(null);

		return shuttleposition;
	}

}