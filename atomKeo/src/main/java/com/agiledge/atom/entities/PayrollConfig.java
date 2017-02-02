package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the payroll_config database table.
 * 
 */
@Entity
@Table(name="payroll_config")
@NamedQuery(name="PayrollConfig.findAll", query="SELECT p FROM PayrollConfig p")
public class PayrollConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private double flatRate;

	private Timestamp fromDate;

	private String oneWayDivider;

	private double oneWayDivRate;

	private String payrollType;

	@Temporal(TemporalType.TIMESTAMP)
	private Date toDate;

	private int transportType;

	//bi-directional many-to-one association to Site
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="site")
	private Site siteBean;

	public PayrollConfig() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getFlatRate() {
		return this.flatRate;
	}

	public void setFlatRate(double flatRate) {
		this.flatRate = flatRate;
	}

	public Timestamp getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Timestamp fromDate) {
		this.fromDate = fromDate;
	}

	public String getOneWayDivider() {
		return this.oneWayDivider;
	}

	public void setOneWayDivider(String oneWayDivider) {
		this.oneWayDivider = oneWayDivider;
	}

	public double getOneWayDivRate() {
		return this.oneWayDivRate;
	}

	public void setOneWayDivRate(double oneWayDivRate) {
		this.oneWayDivRate = oneWayDivRate;
	}

	public String getPayrollType() {
		return this.payrollType;
	}

	public void setPayrollType(String payrollType) {
		this.payrollType = payrollType;
	}

	public Date getToDate() {
		return this.toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public int getTransportType() {
		return this.transportType;
	}

	public void setTransportType(int transportType) {
		this.transportType = transportType;
	}

	public Site getSiteBean() {
		return this.siteBean;
	}

	public void setSiteBean(Site siteBean) {
		this.siteBean = siteBean;
	}

}