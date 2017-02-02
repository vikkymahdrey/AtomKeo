package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the employee_subscription database table.
 * 
 */
@Entity
@Table(name="employee_subscription")
@NamedQuery(name="EmployeeSubscription.findAll", query="SELECT e FROM EmployeeSubscription e")
public class EmployeeSubscription implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String subscriptionID;

	private int aplChangeCounter;

	@Temporal(TemporalType.DATE)
	private Date fromDate;

	private String geocodestatus;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdatedDate;

	private double latitude;

	private double longitude;

	@Temporal(TemporalType.TIMESTAMP)
	private Date subscriptionDate;

	private String subscriptionStatus;

	//bi-directional many-to-one association to EmployeeSchedule
	@OneToMany(mappedBy="employeeSubscription")
	private List<EmployeeSchedule> employeeSchedules;

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

	//bi-directional many-to-one association to Payroll
	@OneToMany(mappedBy="employeeSubscription")
	private List<Payroll> payrolls;

	public EmployeeSubscription() {
	}

	public String getSubscriptionID() {
		return this.subscriptionID;
	}

	public void setSubscriptionID(String subscriptionID) {
		this.subscriptionID = subscriptionID;
	}

	public int getAplChangeCounter() {
		return this.aplChangeCounter;
	}

	public void setAplChangeCounter(int aplChangeCounter) {
		this.aplChangeCounter = aplChangeCounter;
	}

	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public String getGeocodestatus() {
		return this.geocodestatus;
	}

	public void setGeocodestatus(String geocodestatus) {
		this.geocodestatus = geocodestatus;
	}

	public Date getLastUpdatedDate() {
		return this.lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Date getSubscriptionDate() {
		return this.subscriptionDate;
	}

	public void setSubscriptionDate(Date subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}

	public String getSubscriptionStatus() {
		return this.subscriptionStatus;
	}

	public void setSubscriptionStatus(String subscriptionStatus) {
		this.subscriptionStatus = subscriptionStatus;
	}

	public List<EmployeeSchedule> getEmployeeSchedules() {
		return this.employeeSchedules;
	}

	public void setEmployeeSchedules(List<EmployeeSchedule> employeeSchedules) {
		this.employeeSchedules = employeeSchedules;
	}

	public EmployeeSchedule addEmployeeSchedule(EmployeeSchedule employeeSchedule) {
		getEmployeeSchedules().add(employeeSchedule);
		employeeSchedule.setEmployeeSubscription(this);

		return employeeSchedule;
	}

	public EmployeeSchedule removeEmployeeSchedule(EmployeeSchedule employeeSchedule) {
		getEmployeeSchedules().remove(employeeSchedule);
		employeeSchedule.setEmployeeSubscription(null);

		return employeeSchedule;
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

	public List<Payroll> getPayrolls() {
		return this.payrolls;
	}

	public void setPayrolls(List<Payroll> payrolls) {
		this.payrolls = payrolls;
	}

	public Payroll addPayroll(Payroll payroll) {
		getPayrolls().add(payroll);
		payroll.setEmployeeSubscription(this);

		return payroll;
	}

	public Payroll removePayroll(Payroll payroll) {
		getPayrolls().remove(payroll);
		payroll.setEmployeeSubscription(null);

		return payroll;
	}

}