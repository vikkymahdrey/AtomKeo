package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the routechildpriority database table.
 * 
 */
@Entity
@NamedQuery(name="Routechildpriority.findAll", query="SELECT r FROM Routechildpriority r")
public class Routechildpriority implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private int position;

	//bi-directional many-to-one association to Landmark
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="landmarkId")
	private Landmark landmark;

	//bi-directional many-to-one association to Route
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="routeId")
	private Route route;

	public Routechildpriority() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPosition() {
		return this.position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Landmark getLandmark() {
		return this.landmark;
	}

	public void setLandmark(Landmark landmark) {
		this.landmark = landmark;
	}

	public Route getRoute() {
		return this.route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

}