package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the driver database table.
 * 
 */
@Entity
@NamedQuery(name="Driver.findAll", query="SELECT d FROM Driver d")
public class Driver implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String address;

	private String contact;

	private String dl;

	private String drivercol;

	private String name;

	private String password;

	private String status;

	private String username;

	//bi-directional many-to-one association to Vendormaster
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vendorId")
	private Vendormaster vendormaster;

	//bi-directional many-to-one association to DriverLogin
	@OneToMany(mappedBy="driver")
	private List<DriverLogin> driverLogins;

	//bi-directional many-to-one association to DriverVehicle
	@OneToMany(mappedBy="driver")
	private List<DriverVehicle> driverVehicles;

	//bi-directional many-to-one association to FullVehiclePosition
	@OneToMany(mappedBy="driver")
	private List<FullVehiclePosition> fullVehiclePositions;

	//bi-directional many-to-one association to TripDetail
	@OneToMany(mappedBy="driver")
	private List<TripDetail> tripDetails;

	//bi-directional many-to-one association to TripDetailsParentModified
	@OneToMany(mappedBy="driver")
	private List<TripDetailsParentModified> tripDetailsParentModifieds;

	public Driver() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getDl() {
		return this.dl;
	}

	public void setDl(String dl) {
		this.dl = dl;
	}

	public String getDrivercol() {
		return this.drivercol;
	}

	public void setDrivercol(String drivercol) {
		this.drivercol = drivercol;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Vendormaster getVendormaster() {
		return this.vendormaster;
	}

	public void setVendormaster(Vendormaster vendormaster) {
		this.vendormaster = vendormaster;
	}

	public List<DriverLogin> getDriverLogins() {
		return this.driverLogins;
	}

	public void setDriverLogins(List<DriverLogin> driverLogins) {
		this.driverLogins = driverLogins;
	}

	public DriverLogin addDriverLogin(DriverLogin driverLogin) {
		getDriverLogins().add(driverLogin);
		driverLogin.setDriver(this);

		return driverLogin;
	}

	public DriverLogin removeDriverLogin(DriverLogin driverLogin) {
		getDriverLogins().remove(driverLogin);
		driverLogin.setDriver(null);

		return driverLogin;
	}

	public List<DriverVehicle> getDriverVehicles() {
		return this.driverVehicles;
	}

	public void setDriverVehicles(List<DriverVehicle> driverVehicles) {
		this.driverVehicles = driverVehicles;
	}

	public DriverVehicle addDriverVehicle(DriverVehicle driverVehicle) {
		getDriverVehicles().add(driverVehicle);
		driverVehicle.setDriver(this);

		return driverVehicle;
	}

	public DriverVehicle removeDriverVehicle(DriverVehicle driverVehicle) {
		getDriverVehicles().remove(driverVehicle);
		driverVehicle.setDriver(null);

		return driverVehicle;
	}

	public List<FullVehiclePosition> getFullVehiclePositions() {
		return this.fullVehiclePositions;
	}

	public void setFullVehiclePositions(List<FullVehiclePosition> fullVehiclePositions) {
		this.fullVehiclePositions = fullVehiclePositions;
	}

	public FullVehiclePosition addFullVehiclePosition(FullVehiclePosition fullVehiclePosition) {
		getFullVehiclePositions().add(fullVehiclePosition);
		fullVehiclePosition.setDriver(this);

		return fullVehiclePosition;
	}

	public FullVehiclePosition removeFullVehiclePosition(FullVehiclePosition fullVehiclePosition) {
		getFullVehiclePositions().remove(fullVehiclePosition);
		fullVehiclePosition.setDriver(null);

		return fullVehiclePosition;
	}

	public List<TripDetail> getTripDetails() {
		return this.tripDetails;
	}

	public void setTripDetails(List<TripDetail> tripDetails) {
		this.tripDetails = tripDetails;
	}

	public TripDetail addTripDetail(TripDetail tripDetail) {
		getTripDetails().add(tripDetail);
		tripDetail.setDriver(this);

		return tripDetail;
	}

	public TripDetail removeTripDetail(TripDetail tripDetail) {
		getTripDetails().remove(tripDetail);
		tripDetail.setDriver(null);

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
		tripDetailsParentModified.setDriver(this);

		return tripDetailsParentModified;
	}

	public TripDetailsParentModified removeTripDetailsParentModified(TripDetailsParentModified tripDetailsParentModified) {
		getTripDetailsParentModifieds().remove(tripDetailsParentModified);
		tripDetailsParentModified.setDriver(null);

		return tripDetailsParentModified;
	}

}