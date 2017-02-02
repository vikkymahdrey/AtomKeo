package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the employee_schedule_alter database table.
 * 
 */
@Entity
@Table(name="employee_schedule_alter")
@NamedQuery(name="EmployeeScheduleAlter.findAll", query="SELECT e FROM EmployeeScheduleAlter e")
public class EmployeeScheduleAlter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Column(name="login_time")
	private String loginTime;

	@Column(name="logout_time")
	private String logoutTime;

	private String routingtype;

	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date statusDate;

	//bi-directional many-to-one association to Employee
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="updatedBy")
	private Employee employee;

	//bi-directional many-to-one association to EmployeeSchedule
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="scheduleId")
	private EmployeeSchedule employeeSchedule;

	public EmployeeScheduleAlter() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public String getRoutingtype() {
		return this.routingtype;
	}

	public void setRoutingtype(String routingtype) {
		this.routingtype = routingtype;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStatusDate() {
		return this.statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public EmployeeSchedule getEmployeeSchedule() {
		return this.employeeSchedule;
	}

	public void setEmployeeSchedule(EmployeeSchedule employeeSchedule) {
		this.employeeSchedule = employeeSchedule;
	}

}