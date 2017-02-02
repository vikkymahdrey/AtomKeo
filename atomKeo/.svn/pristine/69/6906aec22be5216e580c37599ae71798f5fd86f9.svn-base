package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the landmark database table.
 * 
 */
@Entity
@NamedQuery(name="Landmark.findAll", query="SELECT l FROM Landmark l")
public class Landmark implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String landmark;

	private double latitude;

	private double longitude;

	//bi-directional many-to-one association to Distchart
	@OneToMany(mappedBy="landmark1")
	private List<Distchart> distcharts1;

	//bi-directional many-to-one association to Distchart
	@OneToMany(mappedBy="landmark2")
	private List<Distchart> distcharts2;

	//bi-directional many-to-one association to EmployeeSubscription
	@OneToMany(mappedBy="landmark")
	private List<EmployeeSubscription> employeeSubscriptions;

	//bi-directional many-to-one association to EmployeeSubscriptionModify
	@OneToMany(mappedBy="landmark")
	private List<EmployeeSubscriptionModify> employeeSubscriptionModifies;

	//bi-directional many-to-one association to Place
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="place")
	private Place placeBean;

	//bi-directional many-to-one association to Routechild
	@OneToMany(mappedBy="landmark")
	private List<Routechild> routechilds;

	//bi-directional many-to-one association to Routechildpriority
	@OneToMany(mappedBy="landmark")
	private List<Routechildpriority> routechildpriorities;

	//bi-directional many-to-one association to Site
	@OneToMany(mappedBy="landmarkBean")
	private List<Site> sites;

	//bi-directional many-to-one association to TransportationInEmergency
	@OneToMany(mappedBy="landmark1")
	private List<TransportationInEmergency> transportationInEmergencies1;

	//bi-directional many-to-one association to TransportationInEmergency
	@OneToMany(mappedBy="landmark2")
	private List<TransportationInEmergency> transportationInEmergencies2;

	//bi-directional many-to-one association to TripDetailsChild
	@OneToMany(mappedBy="landmark")
	private List<TripDetailsChild> tripDetailsChilds;

	//bi-directional many-to-one association to TripDetailsModified
	@OneToMany(mappedBy="landmark")
	private List<TripDetailsModified> tripDetailsModifieds;

	//bi-directional many-to-one association to TripDetailsRemoved
	@OneToMany(mappedBy="landmark")
	private List<TripDetailsRemoved> tripDetailsRemoveds;

	public Landmark() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLandmark() {
		return this.landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
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

	public List<Distchart> getDistcharts1() {
		return this.distcharts1;
	}

	public void setDistcharts1(List<Distchart> distcharts1) {
		this.distcharts1 = distcharts1;
	}

	public Distchart addDistcharts1(Distchart distcharts1) {
		getDistcharts1().add(distcharts1);
		distcharts1.setLandmark1(this);

		return distcharts1;
	}

	public Distchart removeDistcharts1(Distchart distcharts1) {
		getDistcharts1().remove(distcharts1);
		distcharts1.setLandmark1(null);

		return distcharts1;
	}

	public List<Distchart> getDistcharts2() {
		return this.distcharts2;
	}

	public void setDistcharts2(List<Distchart> distcharts2) {
		this.distcharts2 = distcharts2;
	}

	public Distchart addDistcharts2(Distchart distcharts2) {
		getDistcharts2().add(distcharts2);
		distcharts2.setLandmark2(this);

		return distcharts2;
	}

	public Distchart removeDistcharts2(Distchart distcharts2) {
		getDistcharts2().remove(distcharts2);
		distcharts2.setLandmark2(null);

		return distcharts2;
	}

	public List<EmployeeSubscription> getEmployeeSubscriptions() {
		return this.employeeSubscriptions;
	}

	public void setEmployeeSubscriptions(List<EmployeeSubscription> employeeSubscriptions) {
		this.employeeSubscriptions = employeeSubscriptions;
	}

	public EmployeeSubscription addEmployeeSubscription(EmployeeSubscription employeeSubscription) {
		getEmployeeSubscriptions().add(employeeSubscription);
		employeeSubscription.setLandmark(this);

		return employeeSubscription;
	}

	public EmployeeSubscription removeEmployeeSubscription(EmployeeSubscription employeeSubscription) {
		getEmployeeSubscriptions().remove(employeeSubscription);
		employeeSubscription.setLandmark(null);

		return employeeSubscription;
	}

	public List<EmployeeSubscriptionModify> getEmployeeSubscriptionModifies() {
		return this.employeeSubscriptionModifies;
	}

	public void setEmployeeSubscriptionModifies(List<EmployeeSubscriptionModify> employeeSubscriptionModifies) {
		this.employeeSubscriptionModifies = employeeSubscriptionModifies;
	}

	public EmployeeSubscriptionModify addEmployeeSubscriptionModify(EmployeeSubscriptionModify employeeSubscriptionModify) {
		getEmployeeSubscriptionModifies().add(employeeSubscriptionModify);
		employeeSubscriptionModify.setLandmark(this);

		return employeeSubscriptionModify;
	}

	public EmployeeSubscriptionModify removeEmployeeSubscriptionModify(EmployeeSubscriptionModify employeeSubscriptionModify) {
		getEmployeeSubscriptionModifies().remove(employeeSubscriptionModify);
		employeeSubscriptionModify.setLandmark(null);

		return employeeSubscriptionModify;
	}

	public Place getPlaceBean() {
		return this.placeBean;
	}

	public void setPlaceBean(Place placeBean) {
		this.placeBean = placeBean;
	}

	public List<Routechild> getRoutechilds() {
		return this.routechilds;
	}

	public void setRoutechilds(List<Routechild> routechilds) {
		this.routechilds = routechilds;
	}

	public Routechild addRoutechild(Routechild routechild) {
		getRoutechilds().add(routechild);
		routechild.setLandmark(this);

		return routechild;
	}

	public Routechild removeRoutechild(Routechild routechild) {
		getRoutechilds().remove(routechild);
		routechild.setLandmark(null);

		return routechild;
	}

	public List<Routechildpriority> getRoutechildpriorities() {
		return this.routechildpriorities;
	}

	public void setRoutechildpriorities(List<Routechildpriority> routechildpriorities) {
		this.routechildpriorities = routechildpriorities;
	}

	public Routechildpriority addRoutechildpriority(Routechildpriority routechildpriority) {
		getRoutechildpriorities().add(routechildpriority);
		routechildpriority.setLandmark(this);

		return routechildpriority;
	}

	public Routechildpriority removeRoutechildpriority(Routechildpriority routechildpriority) {
		getRoutechildpriorities().remove(routechildpriority);
		routechildpriority.setLandmark(null);

		return routechildpriority;
	}

	public List<Site> getSites() {
		return this.sites;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}

	public Site addSite(Site site) {
		getSites().add(site);
		site.setLandmarkBean(this);

		return site;
	}

	public Site removeSite(Site site) {
		getSites().remove(site);
		site.setLandmarkBean(null);

		return site;
	}

	public List<TransportationInEmergency> getTransportationInEmergencies1() {
		return this.transportationInEmergencies1;
	}

	public void setTransportationInEmergencies1(List<TransportationInEmergency> transportationInEmergencies1) {
		this.transportationInEmergencies1 = transportationInEmergencies1;
	}

	public TransportationInEmergency addTransportationInEmergencies1(TransportationInEmergency transportationInEmergencies1) {
		getTransportationInEmergencies1().add(transportationInEmergencies1);
		transportationInEmergencies1.setLandmark1(this);

		return transportationInEmergencies1;
	}

	public TransportationInEmergency removeTransportationInEmergencies1(TransportationInEmergency transportationInEmergencies1) {
		getTransportationInEmergencies1().remove(transportationInEmergencies1);
		transportationInEmergencies1.setLandmark1(null);

		return transportationInEmergencies1;
	}

	public List<TransportationInEmergency> getTransportationInEmergencies2() {
		return this.transportationInEmergencies2;
	}

	public void setTransportationInEmergencies2(List<TransportationInEmergency> transportationInEmergencies2) {
		this.transportationInEmergencies2 = transportationInEmergencies2;
	}

	public TransportationInEmergency addTransportationInEmergencies2(TransportationInEmergency transportationInEmergencies2) {
		getTransportationInEmergencies2().add(transportationInEmergencies2);
		transportationInEmergencies2.setLandmark2(this);

		return transportationInEmergencies2;
	}

	public TransportationInEmergency removeTransportationInEmergencies2(TransportationInEmergency transportationInEmergencies2) {
		getTransportationInEmergencies2().remove(transportationInEmergencies2);
		transportationInEmergencies2.setLandmark2(null);

		return transportationInEmergencies2;
	}

	public List<TripDetailsChild> getTripDetailsChilds() {
		return this.tripDetailsChilds;
	}

	public void setTripDetailsChilds(List<TripDetailsChild> tripDetailsChilds) {
		this.tripDetailsChilds = tripDetailsChilds;
	}

	public TripDetailsChild addTripDetailsChild(TripDetailsChild tripDetailsChild) {
		getTripDetailsChilds().add(tripDetailsChild);
		tripDetailsChild.setLandmark(this);

		return tripDetailsChild;
	}

	public TripDetailsChild removeTripDetailsChild(TripDetailsChild tripDetailsChild) {
		getTripDetailsChilds().remove(tripDetailsChild);
		tripDetailsChild.setLandmark(null);

		return tripDetailsChild;
	}

	public List<TripDetailsModified> getTripDetailsModifieds() {
		return this.tripDetailsModifieds;
	}

	public void setTripDetailsModifieds(List<TripDetailsModified> tripDetailsModifieds) {
		this.tripDetailsModifieds = tripDetailsModifieds;
	}

	public TripDetailsModified addTripDetailsModified(TripDetailsModified tripDetailsModified) {
		getTripDetailsModifieds().add(tripDetailsModified);
		tripDetailsModified.setLandmark(this);

		return tripDetailsModified;
	}

	public TripDetailsModified removeTripDetailsModified(TripDetailsModified tripDetailsModified) {
		getTripDetailsModifieds().remove(tripDetailsModified);
		tripDetailsModified.setLandmark(null);

		return tripDetailsModified;
	}

	public List<TripDetailsRemoved> getTripDetailsRemoveds() {
		return this.tripDetailsRemoveds;
	}

	public void setTripDetailsRemoveds(List<TripDetailsRemoved> tripDetailsRemoveds) {
		this.tripDetailsRemoveds = tripDetailsRemoveds;
	}

	public TripDetailsRemoved addTripDetailsRemoved(TripDetailsRemoved tripDetailsRemoved) {
		getTripDetailsRemoveds().add(tripDetailsRemoved);
		tripDetailsRemoved.setLandmark(this);

		return tripDetailsRemoved;
	}

	public TripDetailsRemoved removeTripDetailsRemoved(TripDetailsRemoved tripDetailsRemoved) {
		getTripDetailsRemoveds().remove(tripDetailsRemoved);
		tripDetailsRemoved.setLandmark(null);

		return tripDetailsRemoved;
	}

}