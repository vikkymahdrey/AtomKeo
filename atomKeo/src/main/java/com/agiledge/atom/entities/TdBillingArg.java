package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the td_billing_args database table.
 * 
 */
@Entity
@Table(name="td_billing_args")
@NamedQuery(name="TdBillingArg.findAll", query="SELECT t FROM TdBillingArg t")
public class TdBillingArg implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private float classicEscortRate;

	private BigInteger classicTripBillingId;

	private float classicTripDistance;

	private float classicTripRate;

	private float mapEscortRate;

	private BigInteger mapTripBillingId;

	private float mapTripDistance;

	private float mapTripRate;

	private float mobileEscortRate;

	private BigInteger mobileTripBillingId;

	private float mobileTripDistance;

	private float mobileTripRate;

	private float slabEscortRate;

	private BigInteger slabTripBillingId;

	private float slabTripRate;

	private float templateEscortRate;

	private float templateSwingDistance;

	private BigInteger templateTripBillingId;

	private float templateTripDistance;

	private float templateTripRate;

	private float tripEscortRate;

	private float tripTripRate;

	//bi-directional many-to-one association to TripBasedConfig
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tripTripBillingId")
	private TripBasedConfig tripBasedConfig;

	//bi-directional many-to-one association to TripDetail
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tripId")
	private TripDetail tripDetail;

	public TdBillingArg() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getClassicEscortRate() {
		return this.classicEscortRate;
	}

	public void setClassicEscortRate(float classicEscortRate) {
		this.classicEscortRate = classicEscortRate;
	}

	public BigInteger getClassicTripBillingId() {
		return this.classicTripBillingId;
	}

	public void setClassicTripBillingId(BigInteger classicTripBillingId) {
		this.classicTripBillingId = classicTripBillingId;
	}

	public float getClassicTripDistance() {
		return this.classicTripDistance;
	}

	public void setClassicTripDistance(float classicTripDistance) {
		this.classicTripDistance = classicTripDistance;
	}

	public float getClassicTripRate() {
		return this.classicTripRate;
	}

	public void setClassicTripRate(float classicTripRate) {
		this.classicTripRate = classicTripRate;
	}

	public float getMapEscortRate() {
		return this.mapEscortRate;
	}

	public void setMapEscortRate(float mapEscortRate) {
		this.mapEscortRate = mapEscortRate;
	}

	public BigInteger getMapTripBillingId() {
		return this.mapTripBillingId;
	}

	public void setMapTripBillingId(BigInteger mapTripBillingId) {
		this.mapTripBillingId = mapTripBillingId;
	}

	public float getMapTripDistance() {
		return this.mapTripDistance;
	}

	public void setMapTripDistance(float mapTripDistance) {
		this.mapTripDistance = mapTripDistance;
	}

	public float getMapTripRate() {
		return this.mapTripRate;
	}

	public void setMapTripRate(float mapTripRate) {
		this.mapTripRate = mapTripRate;
	}

	public float getMobileEscortRate() {
		return this.mobileEscortRate;
	}

	public void setMobileEscortRate(float mobileEscortRate) {
		this.mobileEscortRate = mobileEscortRate;
	}

	public BigInteger getMobileTripBillingId() {
		return this.mobileTripBillingId;
	}

	public void setMobileTripBillingId(BigInteger mobileTripBillingId) {
		this.mobileTripBillingId = mobileTripBillingId;
	}

	public float getMobileTripDistance() {
		return this.mobileTripDistance;
	}

	public void setMobileTripDistance(float mobileTripDistance) {
		this.mobileTripDistance = mobileTripDistance;
	}

	public float getMobileTripRate() {
		return this.mobileTripRate;
	}

	public void setMobileTripRate(float mobileTripRate) {
		this.mobileTripRate = mobileTripRate;
	}

	public float getSlabEscortRate() {
		return this.slabEscortRate;
	}

	public void setSlabEscortRate(float slabEscortRate) {
		this.slabEscortRate = slabEscortRate;
	}

	public BigInteger getSlabTripBillingId() {
		return this.slabTripBillingId;
	}

	public void setSlabTripBillingId(BigInteger slabTripBillingId) {
		this.slabTripBillingId = slabTripBillingId;
	}

	public float getSlabTripRate() {
		return this.slabTripRate;
	}

	public void setSlabTripRate(float slabTripRate) {
		this.slabTripRate = slabTripRate;
	}

	public float getTemplateEscortRate() {
		return this.templateEscortRate;
	}

	public void setTemplateEscortRate(float templateEscortRate) {
		this.templateEscortRate = templateEscortRate;
	}

	public float getTemplateSwingDistance() {
		return this.templateSwingDistance;
	}

	public void setTemplateSwingDistance(float templateSwingDistance) {
		this.templateSwingDistance = templateSwingDistance;
	}

	public BigInteger getTemplateTripBillingId() {
		return this.templateTripBillingId;
	}

	public void setTemplateTripBillingId(BigInteger templateTripBillingId) {
		this.templateTripBillingId = templateTripBillingId;
	}

	public float getTemplateTripDistance() {
		return this.templateTripDistance;
	}

	public void setTemplateTripDistance(float templateTripDistance) {
		this.templateTripDistance = templateTripDistance;
	}

	public float getTemplateTripRate() {
		return this.templateTripRate;
	}

	public void setTemplateTripRate(float templateTripRate) {
		this.templateTripRate = templateTripRate;
	}

	public float getTripEscortRate() {
		return this.tripEscortRate;
	}

	public void setTripEscortRate(float tripEscortRate) {
		this.tripEscortRate = tripEscortRate;
	}

	public float getTripTripRate() {
		return this.tripTripRate;
	}

	public void setTripTripRate(float tripTripRate) {
		this.tripTripRate = tripTripRate;
	}

	public TripBasedConfig getTripBasedConfig() {
		return this.tripBasedConfig;
	}

	public void setTripBasedConfig(TripBasedConfig tripBasedConfig) {
		this.tripBasedConfig = tripBasedConfig;
	}

	public TripDetail getTripDetail() {
		return this.tripDetail;
	}

	public void setTripDetail(TripDetail tripDetail) {
		this.tripDetail = tripDetail;
	}

}