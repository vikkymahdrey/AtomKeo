package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the vehicle_type database table.
 * 
 */
@Entity
@Table(name="vehicle_type")
@NamedQuery(name="VehicleType.findAll", query="SELECT v FROM VehicleType v")
public class VehicleType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private BigInteger ratePerKm;

	private BigInteger ratePerTrip;

	private int seat;

	@Column(name="sit_cap")
	private int sitCap;

	private String type;

	//bi-directional many-to-one association to TransportationInEmergency
	@OneToMany(mappedBy="vehicleType")
	private List<TransportationInEmergency> transportationInEmergencies;

	//bi-directional many-to-one association to TripBasedConfig
	@OneToMany(mappedBy="vehicleTypeBean")
	private List<TripBasedConfig> tripBasedConfigs;

	//bi-directional many-to-one association to TripDetail
	@OneToMany(mappedBy="vehicleTypeBean")
	private List<TripDetail> tripDetails;

	//bi-directional many-to-one association to TripDetailsParentModified
	@OneToMany(mappedBy="vehicleTypeBean")
	private List<TripDetailsParentModified> tripDetailsParentModifieds;

	//bi-directional many-to-many association to Site
	@ManyToMany
	@JoinTable(
		name="site_vehicle"
		, joinColumns={
			@JoinColumn(name="vehicleTypeId")
			}
		, inverseJoinColumns={
			@JoinColumn(name="siteId")
			}
		)
	private List<Site> sites;

	//bi-directional many-to-one association to Vehicle
	@OneToMany(mappedBy="vehicleType")
	private List<Vehicle> vehicles;

	public VehicleType() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigInteger getRatePerKm() {
		return this.ratePerKm;
	}

	public void setRatePerKm(BigInteger ratePerKm) {
		this.ratePerKm = ratePerKm;
	}

	public BigInteger getRatePerTrip() {
		return this.ratePerTrip;
	}

	public void setRatePerTrip(BigInteger ratePerTrip) {
		this.ratePerTrip = ratePerTrip;
	}

	public int getSeat() {
		return this.seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public int getSitCap() {
		return this.sitCap;
	}

	public void setSitCap(int sitCap) {
		this.sitCap = sitCap;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<TransportationInEmergency> getTransportationInEmergencies() {
		return this.transportationInEmergencies;
	}

	public void setTransportationInEmergencies(List<TransportationInEmergency> transportationInEmergencies) {
		this.transportationInEmergencies = transportationInEmergencies;
	}

	public TransportationInEmergency addTransportationInEmergency(TransportationInEmergency transportationInEmergency) {
		getTransportationInEmergencies().add(transportationInEmergency);
		transportationInEmergency.setVehicleType(this);

		return transportationInEmergency;
	}

	public TransportationInEmergency removeTransportationInEmergency(TransportationInEmergency transportationInEmergency) {
		getTransportationInEmergencies().remove(transportationInEmergency);
		transportationInEmergency.setVehicleType(null);

		return transportationInEmergency;
	}

	public List<TripBasedConfig> getTripBasedConfigs() {
		return this.tripBasedConfigs;
	}

	public void setTripBasedConfigs(List<TripBasedConfig> tripBasedConfigs) {
		this.tripBasedConfigs = tripBasedConfigs;
	}

	public TripBasedConfig addTripBasedConfig(TripBasedConfig tripBasedConfig) {
		getTripBasedConfigs().add(tripBasedConfig);
		tripBasedConfig.setVehicleTypeBean(this);

		return tripBasedConfig;
	}

	public TripBasedConfig removeTripBasedConfig(TripBasedConfig tripBasedConfig) {
		getTripBasedConfigs().remove(tripBasedConfig);
		tripBasedConfig.setVehicleTypeBean(null);

		return tripBasedConfig;
	}

	public List<TripDetail> getTripDetails() {
		return this.tripDetails;
	}

	public void setTripDetails(List<TripDetail> tripDetails) {
		this.tripDetails = tripDetails;
	}

	public TripDetail addTripDetail(TripDetail tripDetail) {
		getTripDetails().add(tripDetail);
		tripDetail.setVehicleTypeBean(this);

		return tripDetail;
	}

	public TripDetail removeTripDetail(TripDetail tripDetail) {
		getTripDetails().remove(tripDetail);
		tripDetail.setVehicleTypeBean(null);

		return tripDetail;
	}

	public List<TripDetailsParentModified> getTripDetailsParentModifieds() {
		return this.tripDetailsParentModifieds;
	}

	public void setTripDetailsParentModifieds(List<TripDetailsParentModified> tripDetailsParentModifieds) {
		this.tripDetailsParentModifieds = tripDetailsParentModifieds;
	}

	public TripDetailsParentModified addTripDetailsParentModified(TripDetailsParentModified tripDetailsParentModified) {
		getTripDetailsParentModifieds().add(tripDetailsParentModified);
		tripDetailsParentModified.setVehicleTypeBean(this);

		return tripDetailsParentModified;
	}

	public TripDetailsParentModified removeTripDetailsParentModified(TripDetailsParentModified tripDetailsParentModified) {
		getTripDetailsParentModifieds().remove(tripDetailsParentModified);
		tripDetailsParentModified.setVehicleTypeBean(null);

		return tripDetailsParentModified;
	}

	public List<Site> getSites() {
		return this.sites;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}

	public List<Vehicle> getVehicles() {
		return this.vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public Vehicle addVehicle(Vehicle vehicle) {
		getVehicles().add(vehicle);
		vehicle.setVehicleType(this);

		return vehicle;
	}

	public Vehicle removeVehicle(Vehicle vehicle) {
		getVehicles().remove(vehicle);
		vehicle.setVehicleType(null);

		return vehicle;
	}

}