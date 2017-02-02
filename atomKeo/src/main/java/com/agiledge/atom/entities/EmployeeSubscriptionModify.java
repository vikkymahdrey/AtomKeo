package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;


/**
 * The persistent class for the employee_subscription_modify database table.
 * 
 */
@Entity
@Table(name="employee_subscription_modify")
@NamedQuery(name="EmployeeSubscriptionModify.findAll", query="SELECT e FROM EmployeeSubscriptionModify e")
public class EmployeeSubscriptionModify implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private int aplChangeCounter;

	private String changedBy;

	private String contactno;

	@Temporal(TemporalType.DATE)
	private Date effectiveDate;

	@Temporal(TemporalType.DATE)
	private Date fromDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdatedDate;

	private int site;

	@Temporal(TemporalType.TIMESTAMP)
	private Date subscriptionDate;

	private BigInteger subscriptionid;

	private String subscriptionStatus;

	//bi-directional many-to-one association to Employee
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="empID")
	private Employee employee1;

	//bi-directional many-to-one association to Employee
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="spoc")
	private Employee employee2;

	//bi-directional many-to-one association to Employee
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="supervisor")
	private Employee employee3;

	//bi-directional many-to-one association to Landmark
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="landMark")
	private Landmark landmark;

	public EmployeeSubscriptionModify() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAplChangeCounter() {
		return this.aplChangeCounter;
	}

	public void setAplChangeCounter(int aplChangeCounter) {
		this.aplChangeCounter = aplChangeCounter;
	}

	public String getChangedBy() {
		return this.changedBy;
	}

	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}

	public String getContactno() {
		return this.contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getLastUpdatedDate() {
		return this.lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public int getSite() {
		return this.site;
	}

	public void setSite(int site) {
		this.site = site;
	}

	public Date getSubscriptionDate() {
		return this.subscriptionDate;
	}

	public void setSubscriptionDate(Date subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}

	public BigInteger getSubscriptionid() {
		return this.subscriptionid;
	}

	public void setSubscriptionid(BigInteger subscriptionid) {
		this.subscriptionid = subscriptionid;
	}

	public String getSubscriptionStatus() {
		return this.subscriptionStatus;
	}

	public void setSubscriptionStatus(String subscriptionStatus) {
		this.subscriptionStatus = subscriptionStatus;
	}

	public Employee getEmployee1() {
		return this.employee1;
	}

	public void setEmployee1(Employee employee1) {
		this.employee1 = employee1;
	}

	public Employee getEmployee2() {
		return this.employee2;
	}

	public void setEmployee2(Employee employee2) {
		this.employee2 = employee2;
	}

	public Employee getEmployee3() {
		return this.employee3;
	}

	public void setEmployee3(Employee employee3) {
		this.employee3 = employee3;
	}

	public Landmark getLandmark() {
		return this.landmark;
	}

	public void setLandmark(Landmark landmark) {
		this.landmark = landmark;
	}

}