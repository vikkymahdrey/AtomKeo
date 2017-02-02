package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the area database table.
 * 
 */
@Entity
@NamedQuery(name="Area.findAll", query="SELECT a FROM Area a")
public class Area implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String area;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="location")
	private Branch branch;

	//bi-directional many-to-one association to Newplace
	@OneToMany(mappedBy="area")
	private List<Newplace> newplaces;

	//bi-directional many-to-one association to Place
	@OneToMany(mappedBy="areaBean")
	private List<Place> places;

	//bi-directional many-to-one association to TransportationInEmergency
	@OneToMany(mappedBy="areaBean")
	private List<TransportationInEmergency> transportationInEmergencies;

	public Area() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public List<Newplace> getNewplaces() {
		return this.newplaces;
	}

	public void setNewplaces(List<Newplace> newplaces) {
		this.newplaces = newplaces;
	}

	public Newplace addNewplace(Newplace newplace) {
		getNewplaces().add(newplace);
		newplace.setArea(this);

		return newplace;
	}

	public Newplace removeNewplace(Newplace newplace) {
		getNewplaces().remove(newplace);
		newplace.setArea(null);

		return newplace;
	}

	public List<Place> getPlaces() {
		return this.places;
	}

	public void setPlaces(List<Place> places) {
		this.places = places;
	}

	public Place addPlace(Place place) {
		getPlaces().add(place);
		place.setAreaBean(this);

		return place;
	}

	public Place removePlace(Place place) {
		getPlaces().remove(place);
		place.setAreaBean(null);

		return place;
	}

	public List<TransportationInEmergency> getTransportationInEmergencies() {
		return this.transportationInEmergencies;
	}

	public void setTransportationInEmergencies(List<TransportationInEmergency> transportationInEmergencies) {
		this.transportationInEmergencies = transportationInEmergencies;
	}

	public TransportationInEmergency addTransportationInEmergency(TransportationInEmergency transportationInEmergency) {
		getTransportationInEmergencies().add(transportationInEmergency);
		transportationInEmergency.setAreaBean(this);

		return transportationInEmergency;
	}

	public TransportationInEmergency removeTransportationInEmergency(TransportationInEmergency transportationInEmergency) {
		getTransportationInEmergencies().remove(transportationInEmergency);
		transportationInEmergency.setAreaBean(null);

		return transportationInEmergency;
	}

}