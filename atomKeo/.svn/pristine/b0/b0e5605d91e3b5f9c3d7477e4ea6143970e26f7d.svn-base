package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the billing_type_mapping database table.
 * 
 */
@Entity
@Table(name="billing_type_mapping")
@NamedQuery(name="BillingTypeMapping.findAll", query="SELECT b FROM BillingTypeMapping b")
public class BillingTypeMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fromDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date toDate;

	@Column(name="tran_type")
	private int tranType;

	//bi-directional many-to-one association to BillingConfigAcConstraint
	@OneToMany(mappedBy="billingTypeMapping")
	private List<BillingConfigAcConstraint> billingConfigAcConstraints;

	//bi-directional many-to-one association to BillingConfigDistanceConstraint
	@OneToMany(mappedBy="billingTypeMapping")
	private List<BillingConfigDistanceConstraint> billingConfigDistanceConstraints;

	//bi-directional many-to-one association to BillingType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="billing_type")
	private BillingType billingTypeBean;

	//bi-directional many-to-one association to Site
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="site")
	private Site siteBean;

	//bi-directional many-to-one association to TransportType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vendor")
	private TransportType transportType;

	//bi-directional many-to-one association to TripBasedConfig
	@OneToMany(mappedBy="billingTypeMapping")
	private List<TripBasedConfig> tripBasedConfigs;

	public BillingTypeMapping() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return this.toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public int getTranType() {
		return this.tranType;
	}

	public void setTranType(int tranType) {
		this.tranType = tranType;
	}

	public List<BillingConfigAcConstraint> getBillingConfigAcConstraints() {
		return this.billingConfigAcConstraints;
	}

	public void setBillingConfigAcConstraints(List<BillingConfigAcConstraint> billingConfigAcConstraints) {
		this.billingConfigAcConstraints = billingConfigAcConstraints;
	}

	public BillingConfigAcConstraint addBillingConfigAcConstraint(BillingConfigAcConstraint billingConfigAcConstraint) {
		getBillingConfigAcConstraints().add(billingConfigAcConstraint);
		billingConfigAcConstraint.setBillingTypeMapping(this);

		return billingConfigAcConstraint;
	}

	public BillingConfigAcConstraint removeBillingConfigAcConstraint(BillingConfigAcConstraint billingConfigAcConstraint) {
		getBillingConfigAcConstraints().remove(billingConfigAcConstraint);
		billingConfigAcConstraint.setBillingTypeMapping(null);

		return billingConfigAcConstraint;
	}

	public List<BillingConfigDistanceConstraint> getBillingConfigDistanceConstraints() {
		return this.billingConfigDistanceConstraints;
	}

	public void setBillingConfigDistanceConstraints(List<BillingConfigDistanceConstraint> billingConfigDistanceConstraints) {
		this.billingConfigDistanceConstraints = billingConfigDistanceConstraints;
	}

	public BillingConfigDistanceConstraint addBillingConfigDistanceConstraint(BillingConfigDistanceConstraint billingConfigDistanceConstraint) {
		getBillingConfigDistanceConstraints().add(billingConfigDistanceConstraint);
		billingConfigDistanceConstraint.setBillingTypeMapping(this);

		return billingConfigDistanceConstraint;
	}

	public BillingConfigDistanceConstraint removeBillingConfigDistanceConstraint(BillingConfigDistanceConstraint billingConfigDistanceConstraint) {
		getBillingConfigDistanceConstraints().remove(billingConfigDistanceConstraint);
		billingConfigDistanceConstraint.setBillingTypeMapping(null);

		return billingConfigDistanceConstraint;
	}

	public BillingType getBillingTypeBean() {
		return this.billingTypeBean;
	}

	public void setBillingTypeBean(BillingType billingTypeBean) {
		this.billingTypeBean = billingTypeBean;
	}

	public Site getSiteBean() {
		return this.siteBean;
	}

	public void setSiteBean(Site siteBean) {
		this.siteBean = siteBean;
	}

	public TransportType getTransportType() {
		return this.transportType;
	}

	public void setTransportType(TransportType transportType) {
		this.transportType = transportType;
	}

	public List<TripBasedConfig> getTripBasedConfigs() {
		return this.tripBasedConfigs;
	}

	public void setTripBasedConfigs(List<TripBasedConfig> tripBasedConfigs) {
		this.tripBasedConfigs = tripBasedConfigs;
	}

	public TripBasedConfig addTripBasedConfig(TripBasedConfig tripBasedConfig) {
		getTripBasedConfigs().add(tripBasedConfig);
		tripBasedConfig.setBillingTypeMapping(this);

		return tripBasedConfig;
	}

	public TripBasedConfig removeTripBasedConfig(TripBasedConfig tripBasedConfig) {
		getTripBasedConfigs().remove(tripBasedConfig);
		tripBasedConfig.setBillingTypeMapping(null);

		return tripBasedConfig;
	}

}