package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;


/**
 * The persistent class for the payroll database table.
 * 
 */
@Entity
@NamedQuery(name="Payroll.findAll", query="SELECT p FROM Payroll p")
public class Payroll implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private BigInteger amount;

	@Temporal(TemporalType.TIMESTAMP)
	private Date month;

	@Temporal(TemporalType.TIMESTAMP)
	private Date payrollDate;

	private String reason;

	private int site;

	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	//bi-directional many-to-one association to Employee
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="empid")
	private Employee employee1;

	//bi-directional many-to-one association to Employee
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="updatedBy")
	private Employee employee2;

	//bi-directional many-to-one association to EmployeeSubscription
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="subscriptionID")
	private EmployeeSubscription employeeSubscription;

	public Payroll() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigInteger getAmount() {
		return this.amount;
	}

	public void setAmount(BigInteger amount) {
		this.amount = amount;
	}

	public Date getMonth() {
		return this.month;
	}

	public void setMonth(Date month) {
		this.month = month;
	}

	public Date getPayrollDate() {
		return this.payrollDate;
	}

	public void setPayrollDate(Date payrollDate) {
		this.payrollDate = payrollDate;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getSite() {
		return this.site;
	}

	public void setSite(int site) {
		this.site = site;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
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

	public EmployeeSubscription getEmployeeSubscription() {
		return this.employeeSubscription;
	}

	public void setEmployeeSubscription(EmployeeSubscription employeeSubscription) {
		this.employeeSubscription = employeeSubscription;
	}

}