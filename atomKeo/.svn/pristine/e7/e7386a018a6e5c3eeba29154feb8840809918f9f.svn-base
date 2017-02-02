package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the device database table.
 * 
 */
@Entity
@NamedQuery(name="Device.findAll", query="SELECT d FROM Device d")
public class Device implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String company;

	private String description;

	private String imei;

	private String model;

	private String status;

	//bi-directional many-to-one association to DeviceVehicle
	@OneToMany(mappedBy="device")
	private List<DeviceVehicle> deviceVehicles;

	public Device() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImei() {
		return this.imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<DeviceVehicle> getDeviceVehicles() {
		return this.deviceVehicles;
	}

	public void setDeviceVehicles(List<DeviceVehicle> deviceVehicles) {
		this.deviceVehicles = deviceVehicles;
	}

	public DeviceVehicle addDeviceVehicle(DeviceVehicle deviceVehicle) {
		getDeviceVehicles().add(deviceVehicle);
		deviceVehicle.setDevice(this);

		return deviceVehicle;
	}

	public DeviceVehicle removeDeviceVehicle(DeviceVehicle deviceVehicle) {
		getDeviceVehicles().remove(deviceVehicle);
		deviceVehicle.setDevice(null);

		return deviceVehicle;
	}

}