package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the place database table.
 * 
 */
@Entity
@NamedQuery(name="Place.findAll", query="SELECT p FROM Place p")
public class Place implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String place;

	//bi-directional many-to-one association to Landmark
	@OneToMany(mappedBy="placeBean")
	private List<Landmark> landmarks;

	//bi-directional many-to-one association to Newlandmark
	@OneToMany(mappedBy="placeBean")
	private List<Newlandmark> newlandmarks;

	//bi-directional many-to-one association to Area
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="area")
	private Area areaBean;

	//bi-directional many-to-one association to TransportationInEmergency
	@OneToMany(mappedBy="placeBean")
	private List<TransportationInEmergency> transportationInEmergencies;

	public Place() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public List<Landmark> getLandmarks() {
		return this.landmarks;
	}

	public void setLandmarks(List<Landmark> landmarks) {
		this.landmarks = landmarks;
	}

	public Landmark addLandmark(Landmark landmark) {
		getLandmarks().add(landmark);
		landmark.setPlaceBean(this);

		return landmark;
	}

	public Landmark removeLandmark(Landmark landmark) {
		getLandmarks().remove(landmark);
		landmark.setPlaceBean(null);

		return landmark;
	}

	public List<Newlandmark> getNewlandmarks() {
		return this.newlandmarks;
	}

	public void setNewlandmarks(List<Newlandmark> newlandmarks) {
		this.newlandmarks = newlandmarks;
	}

	public Newlandmark addNewlandmark(Newlandmark newlandmark) {
		getNewlandmarks().add(newlandmark);
		newlandmark.setPlaceBean(this);

		return newlandmark;
	}

	public Newlandmark removeNewlandmark(Newlandmark newlandmark) {
		getNewlandmarks().remove(newlandmark);
		newlandmark.setPlaceBean(null);

		return newlandmark;
	}

	public Area getAreaBean() {
		return this.areaBean;
	}

	public void setAreaBean(Area areaBean) {
		this.areaBean = areaBean;
	}

	public List<TransportationInEmergency> getTransportationInEmergencies() {
		return this.transportationInEmergencies;
	}

	public void setTransportationInEmergencies(List<TransportationInEmergency> transportationInEmergencies) {
		this.transportationInEmergencies = transportationInEmergencies;
	}

	public TransportationInEmergency addTransportationInEmergency(TransportationInEmergency transportationInEmergency) {
		getTransportationInEmergencies().add(transportationInEmergency);
		transportationInEmergency.setPlaceBean(this);

		return transportationInEmergency;
	}

	public TransportationInEmergency removeTransportationInEmergency(TransportationInEmergency transportationInEmergency) {
		getTransportationInEmergencies().remove(transportationInEmergency);
		transportationInEmergency.setPlaceBean(null);

		return transportationInEmergency;
	}

}