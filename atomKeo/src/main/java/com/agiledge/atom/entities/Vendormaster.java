package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the vendormaster database table.
 * 
 */
@Entity
@NamedQuery(name="Vendormaster.findAll", query="SELECT v FROM Vendormaster v")
public class Vendormaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String address;

	private String company;

	private String contact;

	private String contact1;

	private String email;

	private String status;

	//bi-directional many-to-one association to Driver
	@OneToMany(mappedBy="vendormaster")
	private List<Driver> drivers;

	//bi-directional many-to-one association to Tripvendorassign
	@OneToMany(mappedBy="vendormaster")
	private List<Tripvendorassign> tripvendorassigns;

	//bi-directional many-to-one association to Vehicle
	@OneToMany(mappedBy="vendormaster")
	private List<Vehicle> vehicles;

	//bi-directional many-to-one association to Vendor
	@OneToMany(mappedBy="vendormaster")
	private List<Vendor> vendors;

	public Vendormaster() {
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

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContact1() {
		return this.contact1;
	}

	public void setContact1(String contact1) {
		this.contact1 = contact1;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Driver> getDrivers() {
		return this.drivers;
	}

	public void setDrivers(List<Driver> drivers) {
		this.drivers = drivers;
	}

	public Driver addDriver(Driver driver) {
		getDrivers().add(driver);
		driver.setVendormaster(this);

		return driver;
	}

	public Driver removeDriver(Driver driver) {
		getDrivers().remove(driver);
		driver.setVendormaster(null);

		return driver;
	}

	public List<Tripvendorassign> getTripvendorassigns() {
		return this.tripvendorassigns;
	}

	public void setTripvendorassigns(List<Tripvendorassign> tripvendorassigns) {
		this.tripvendorassigns = tripvendorassigns;
	}

	public Tripvendorassign addTripvendorassign(Tripvendorassign tripvendorassign) {
		getTripvendorassigns().add(tripvendorassign);
		tripvendorassign.setVendormaster(this);

		return tripvendorassign;
	}

	public Tripvendorassign removeTripvendorassign(Tripvendorassign tripvendorassign) {
		getTripvendorassigns().remove(tripvendorassign);
		tripvendorassign.setVendormaster(null);

		return tripvendorassign;
	}

	public List<Vehicle> getVehicles() {
		return this.vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public Vehicle addVehicle(Vehicle vehicle) {
		getVehicles().add(vehicle);
		vehicle.setVendormaster(this);

		return vehicle;
	}

	public Vehicle removeVehicle(Vehicle vehicle) {
		getVehicles().remove(vehicle);
		vehicle.setVendormaster(null);

		return vehicle;
	}

	public List<Vendor> getVendors() {
		return this.vendors;
	}

	public void setVendors(List<Vendor> vendors) {
		this.vendors = vendors;
	}

	public Vendor addVendor(Vendor vendor) {
		getVendors().add(vendor);
		vendor.setVendormaster(this);

		return vendor;
	}

	public Vendor removeVendor(Vendor vendor) {
		getVendors().remove(vendor);
		vendor.setVendormaster(null);

		return vendor;
	}

}