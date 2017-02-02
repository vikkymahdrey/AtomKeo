package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the trip_based_config database table.
 * 
 */
@Entity
@Table(name="trip_based_config")
@NamedQuery(name="TripBasedConfig.findAll", query="SELECT t FROM TripBasedConfig t")
public class TripBasedConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	@Column(name="ac_constraint")
	private String acConstraint;

	@Column(name="distance_constraint")
	private String distanceConstraint;

	@Column(name="escort_billing_rate")
	private float escortBillingRate;

	@Column(name="escort_billing_type")
	private String escortBillingType;

	private String status;

	@Column(name="trip_rate")
	private float tripRate;

	//bi-directional many-to-one association to TdBillingArg
	@OneToMany(mappedBy="tripBasedConfig")
	private List<TdBillingArg> tdBillingArgs;

	//bi-directional many-to-one association to BillingTypeMapping
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="billing_type_refId")
	private BillingTypeMapping billingTypeMapping;

	//bi-directional many-to-one association to VehicleType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vehicle_type")
	private VehicleType vehicleTypeBean;

	public TripBasedConfig() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAcConstraint() {
		return this.acConstraint;
	}

	public void setAcConstraint(String acConstraint) {
		this.acConstraint = acConstraint;
	}

	public String getDistanceConstraint() {
		return this.distanceConstraint;
	}

	public void setDistanceConstraint(String distanceConstraint) {
		this.distanceConstraint = distanceConstraint;
	}

	public float getEscortBillingRate() {
		return this.escortBillingRate;
	}

	public void setEscortBillingRate(float escortBillingRate) {
		this.escortBillingRate = escortBillingRate;
	}

	public String getEscortBillingType() {
		return this.escortBillingType;
	}

	public void setEscortBillingType(String escortBillingType) {
		this.escortBillingType = escortBillingType;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getTripRate() {
		return this.tripRate;
	}

	public void setTripRate(float tripRate) {
		this.tripRate = tripRate;
	}

	public List<TdBillingArg> getTdBillingArgs() {
		return this.tdBillingArgs;
	}

	public void setTdBillingArgs(List<TdBillingArg> tdBillingArgs) {
		this.tdBillingArgs = tdBillingArgs;
	}

	public TdBillingArg addTdBillingArg(TdBillingArg tdBillingArg) {
		getTdBillingArgs().add(tdBillingArg);
		tdBillingArg.setTripBasedConfig(this);

		return tdBillingArg;
	}

	public TdBillingArg removeTdBillingArg(TdBillingArg tdBillingArg) {
		getTdBillingArgs().remove(tdBillingArg);
		tdBillingArg.setTripBasedConfig(null);

		return tdBillingArg;
	}

	public BillingTypeMapping getBillingTypeMapping() {
		return this.billingTypeMapping;
	}

	public void setBillingTypeMapping(BillingTypeMapping billingTypeMapping) {
		this.billingTypeMapping = billingTypeMapping;
	}

	public VehicleType getVehicleTypeBean() {
		return this.vehicleTypeBean;
	}

	public void setVehicleTypeBean(VehicleType vehicleTypeBean) {
		this.vehicleTypeBean = vehicleTypeBean;
	}

}