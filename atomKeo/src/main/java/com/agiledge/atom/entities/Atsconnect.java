package com.agiledge.atom.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the atsconnect database table.
 * 
 */
@Entity
@NamedQuery(name="Atsconnect.findAll", query="SELECT a FROM Atsconnect a")
public class Atsconnect implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String description;

	@Column(name="location_id")
	private String locationId;

	private String project;

	private String projecttype;

	private String projectUnit;

	private String status;

	//bi-directional many-to-one association to Employee
	@OneToMany(mappedBy="atsconnect")
	private List<Employee> employees;

	//bi-directional many-to-one association to EmployeeSchedule
	@OneToMany(mappedBy="atsconnect")
	private List<EmployeeSchedule> employeeSchedules;

	//bi-directional many-to-many association to Logtime
	@ManyToMany(mappedBy="atsconnects",cascade = CascadeType.ALL)
	private List<Logtime> logtimes;

	

	public Atsconnect() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocationId() {
		return this.locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getProject() {
		return this.project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getProjecttype() {
		return this.projecttype;
	}

	public void setProjecttype(String projecttype) {
		this.projecttype = projecttype;
	}

	public String getProjectUnit() {
		return this.projectUnit;
	}

	public void setProjectUnit(String projectUnit) {
		this.projectUnit = projectUnit;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee addEmployee(Employee employee) {
		getEmployees().add(employee);
		employee.setAtsconnect(this);

		return employee;
	}

	public Employee removeEmployee(Employee employee) {
		getEmployees().remove(employee);
		employee.setAtsconnect(null);

		return employee;
	}

	public List<EmployeeSchedule> getEmployeeSchedules() {
		return this.employeeSchedules;
	}

	public void setEmployeeSchedules(List<EmployeeSchedule> employeeSchedules) {
		this.employeeSchedules = employeeSchedules;
	}

	public EmployeeSchedule addEmployeeSchedule(EmployeeSchedule employeeSchedule) {
		getEmployeeSchedules().add(employeeSchedule);
		employeeSchedule.setAtsconnect(this);

		return employeeSchedule;
	}

	public EmployeeSchedule removeEmployeeSchedule(EmployeeSchedule employeeSchedule) {
		getEmployeeSchedules().remove(employeeSchedule);
		employeeSchedule.setAtsconnect(null);

		return employeeSchedule;
	}

	public List<Logtime> getLogtimes() {
		return this.logtimes;
	}

	public void setLogtimes(List<Logtime> logtimes) {
		this.logtimes = logtimes;
	}

	
}