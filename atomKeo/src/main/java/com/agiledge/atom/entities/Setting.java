package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the settings database table.
 * 
 */
@Entity
@Table(name="settings")
@NamedQuery(name="Setting.findAll", query="SELECT s FROM Setting s")
public class Setting implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private int accountingDate;

	private String authentication;

	private double citylattitude;

	private double citylongitude;

	private String domainName;

	private int empAplChangeCounter;

	private float pricePerSubscription;

	private BigDecimal transportationCost;

	@Temporal(TemporalType.TIMESTAMP)
	private Date utilityLastUpdatedDate;

	private String utilityRunningTime;

	private int weekdaycombroute;

	public Setting() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAccountingDate() {
		return this.accountingDate;
	}

	public void setAccountingDate(int accountingDate) {
		this.accountingDate = accountingDate;
	}

	public String getAuthentication() {
		return this.authentication;
	}

	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}

	public double getCitylattitude() {
		return this.citylattitude;
	}

	public void setCitylattitude(double citylattitude) {
		this.citylattitude = citylattitude;
	}

	public double getCitylongitude() {
		return this.citylongitude;
	}

	public void setCitylongitude(double citylongitude) {
		this.citylongitude = citylongitude;
	}

	public String getDomainName() {
		return this.domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public int getEmpAplChangeCounter() {
		return this.empAplChangeCounter;
	}

	public void setEmpAplChangeCounter(int empAplChangeCounter) {
		this.empAplChangeCounter = empAplChangeCounter;
	}

	public float getPricePerSubscription() {
		return this.pricePerSubscription;
	}

	public void setPricePerSubscription(float pricePerSubscription) {
		this.pricePerSubscription = pricePerSubscription;
	}

	public BigDecimal getTransportationCost() {
		return this.transportationCost;
	}

	public void setTransportationCost(BigDecimal transportationCost) {
		this.transportationCost = transportationCost;
	}

	public Date getUtilityLastUpdatedDate() {
		return this.utilityLastUpdatedDate;
	}

	public void setUtilityLastUpdatedDate(Date utilityLastUpdatedDate) {
		this.utilityLastUpdatedDate = utilityLastUpdatedDate;
	}

	public String getUtilityRunningTime() {
		return this.utilityRunningTime;
	}

	public void setUtilityRunningTime(String utilityRunningTime) {
		this.utilityRunningTime = utilityRunningTime;
	}

	public int getWeekdaycombroute() {
		return this.weekdaycombroute;
	}

	public void setWeekdaycombroute(int weekdaycombroute) {
		this.weekdaycombroute = weekdaycombroute;
	}

}