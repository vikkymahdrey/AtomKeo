package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the employee_schedule database table.
 * 
 */
@Entity
@Table(name="employee_schedule")
@NamedQuery(name="EmployeeSchedule.findAll", query="SELECT e FROM EmployeeSchedule e")
public class EmployeeSchedule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="cancel_date")
	private Date cancelDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="from_date")
	private Date fromDate;

	@Column(name="log_status")
	private String logStatus;

	@Column(name="login_time")
	private String loginTime;

	@Column(name="logout_time")
	private String logoutTime;

	private String status;

	private Timestamp statusDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="to_date")
	private Date toDate;

	private String type;

	//bi-directional many-to-one association to Adhocbooking
	@OneToMany(mappedBy="employeeSchedule")
	private List<Adhocbooking> adhocbookings;

	//bi-directional many-to-one association to Atsconnect
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="project")
	private Atsconnect atsconnect;

	//bi-directional many-to-one association to Employee
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employee_id")
	private Employee employee1;

	//bi-directional many-to-one association to Employee
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="scheduledBy")
	private Employee employee2;

	//bi-directional many-to-one association to EmployeeSubscription
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="subscription_id")
	private EmployeeSubscription employeeSubscription;

	//bi-directional many-to-one association to EmployeeScheduleAlter
	@OneToMany(mappedBy="employeeSchedule")
	private List<EmployeeScheduleAlter> employeeScheduleAlters;

	//bi-directional many-to-one association to TripDetailsChild
	@OneToMany(mappedBy="employeeSchedule")
	private List<TripDetailsChild> tripDetailsChilds;

	//bi-directional many-to-one association to TripDetailsModified
	@OneToMany(mappedBy="employeeSchedule")
	private List<TripDetailsModified> tripDetailsModifieds;

	//bi-directional many-to-one association to TripDetailsRemoved
	@OneToMany(mappedBy="employeeSchedule")
	private List<TripDetailsRemoved> tripDetailsRemoveds;

	public EmployeeSchedule() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCancelDate() {
		return this.cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}

	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public String getLogStatus() {
		return this.logStatus;
	}

	public void setLogStatus(String logStatus) {
		this.logStatus = logStatus;
	}

	public String getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLogoutTime() {
		return this.logoutTime;
	}

	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getStatusDate() {
		return this.statusDate;
	}

	public void setStatusDate(Timestamp statusDate) {
		this.statusDate = statusDate;
	}

	public Date getToDate() {
		return this.toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Adhocbooking> getAdhocbookings() {
		return this.adhocbookings;
	}

	public void setAdhocbookings(List<Adhocbooking> adhocbookings) {
		this.adhocbookings = adhocbookings;
	}

	public Adhocbooking addAdhocbooking(Adhocbooking adhocbooking) {
		getAdhocbookings().add(adhocbooking);
		adhocbooking.setEmployeeSchedule(this);

		return adhocbooking;
	}

	public Adhocbooking removeAdhocbooking(Adhocbooking adhocbooking) {
		getAdhocbookings().remove(adhocbooking);
		adhocbooking.setEmployeeSchedule(null);

		return adhocbooking;
	}

	public Atsconnect getAtsconnect() {
		return this.atsconnect;
	}

	public void setAtsconnect(Atsconnect atsconnect) {
		this.atsconnect = atsconnect;
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

	public List<EmployeeScheduleAlter> getEmployeeScheduleAlters() {
		return this.employeeScheduleAlters;
	}

	public void setEmployeeScheduleAlters(List<EmployeeScheduleAlter> employeeScheduleAlters) {
		this.employeeScheduleAlters = employeeScheduleAlters;
	}

	public EmployeeScheduleAlter addEmployeeScheduleAlter(EmployeeScheduleAlter employeeScheduleAlter) {
		getEmployeeScheduleAlters().add(employeeScheduleAlter);
		employeeScheduleAlter.setEmployeeSchedule(this);

		return employeeScheduleAlter;
	}

	public EmployeeScheduleAlter removeEmployeeScheduleAlter(EmployeeScheduleAlter employeeScheduleAlter) {
		getEmployeeScheduleAlters().remove(employeeScheduleAlter);
		employeeScheduleAlter.setEmployeeSchedule(null);

		return employeeScheduleAlter;
	}

	public List<TripDetailsChild> getTripDetailsChilds() {
		return this.tripDetailsChilds;
	}

	public void setTripDetailsChilds(List<TripDetailsChild> tripDetailsChilds) {
		this.tripDetailsChilds = tripDetailsChilds;
	}

	public TripDetailsChild addTripDetailsChild(TripDetailsChild tripDetailsChild) {
		getTripDetailsChilds().add(tripDetailsChild);
		tripDetailsChild.setEmployeeSchedule(this);

		return tripDetailsChild;
	}

	public TripDetailsChild removeTripDetailsChild(TripDetailsChild tripDetailsChild) {
		getTripDetailsChilds().remove(tripDetailsChild);
		tripDetailsChild.setEmployeeSchedule(null);

		return tripDetailsChild;
	}

	public List<TripDetailsModified> getTripDetailsModifieds() {
		return this.tripDetailsModifieds;
	}

	public void setTripDetailsModifieds(List<TripDetailsModified> tripDetailsModifieds) {
		this.tripDetailsModifieds = tripDetailsModifieds;
	}

	public TripDetailsModified addTripDetailsModified(TripDetailsModified tripDetailsModified) {
		getTripDetailsModifieds().add(tripDetailsModified);
		tripDetailsModified.setEmployeeSchedule(this);

		return tripDetailsModified;
	}

	public TripDetailsModified removeTripDetailsModified(TripDetailsModified tripDetailsModified) {
		getTripDetailsModifieds().remove(tripDetailsModified);
		tripDetailsModified.setEmployeeSchedule(null);

		return tripDetailsModified;
	}

	public List<TripDetailsRemoved> getTripDetailsRemoveds() {
		return this.tripDetailsRemoveds;
	}

	public void setTripDetailsRemoveds(List<TripDetailsRemoved> tripDetailsRemoveds) {
		this.tripDetailsRemoveds = tripDetailsRemoveds;
	}

	public TripDetailsRemoved addTripDetailsRemoved(TripDetailsRemoved tripDetailsRemoved) {
		getTripDetailsRemoveds().add(tripDetailsRemoved);
		tripDetailsRemoved.setEmployeeSchedule(this);

		return tripDetailsRemoved;
	}

	public TripDetailsRemoved removeTripDetailsRemoved(TripDetailsRemoved tripDetailsRemoved) {
		getTripDetailsRemoveds().remove(tripDetailsRemoved);
		tripDetailsRemoved.setEmployeeSchedule(null);

		return tripDetailsRemoved;
	}

}