package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the employee database table.
 * 
 */
@Entity
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private int active;

	private String address;

	private Timestamp addressChangeDate;

	private int addresschangestatus;

	private String authType;

	private String autoRoleUpdate;

	private String businessUnitCode;

	private String businessUnitDescription;

	private int careerLevel;

	private String careerPathwayDesc;

	private String city;

	private String clientServiceManager;

	private String contactNumber1;

	private String contactNumber2;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateofjoining;

	private String deptName;

	private String deptno;

	private String designationName;

	private String displayname;

	private String emailAddress;

	@Column(name="emp_lat")
	private String empLat;

	@Column(name="emp_long")
	private String empLong;

	private String employeeFirstName;

	private String employeeLastName;

	private String employeeMiddleName;

	private String externalUser;

	private String gender;

	private String homeCountry;

	private String isContractEmployee;

	private String lineManager;

	private String loginId;

	private String operationCode;

	private String operationDescription;

	private String password;

	private String pathways;

	private String personnelNo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date pwdChangedDate;

	private String registerStatus;

	private String routingType;

	private String staffManager;

	private String state;

	private String usertype;

	//bi-directional many-to-one association to Adhocbooking
	@OneToMany(mappedBy="employee1")
	private List<Adhocbooking> adhocbookings1;

	//bi-directional many-to-one association to Adhocbooking
	@OneToMany(mappedBy="employee2")
	private List<Adhocbooking> adhocbookings2;

	//bi-directional many-to-one association to Adhocbooking
	@OneToMany(mappedBy="employee3")
	private List<Adhocbooking> adhocbookings3;

	//bi-directional many-to-one association to AuditLog
	@OneToMany(mappedBy="employee")
	private List<AuditLog> auditLogs;

	//bi-directional many-to-one association to Atsconnect
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="projectId")
	private Atsconnect atsconnect;

	//bi-directional many-to-one association to Role
	@ManyToOne(fetch=FetchType.LAZY)
	private Role role;

	//bi-directional many-to-one association to Site
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="site")
	private Site siteBean;

	//bi-directional many-to-one association to EmployeeImei
	@OneToMany(mappedBy="employee")
	private List<EmployeeImei> employeeImeis;

	//bi-directional many-to-one association to EmployeeSchedule
	@OneToMany(mappedBy="employee1")
	private List<EmployeeSchedule> employeeSchedules1;

	//bi-directional many-to-one association to EmployeeSchedule
	@OneToMany(mappedBy="employee2")
	private List<EmployeeSchedule> employeeSchedules2;

	//bi-directional many-to-one association to EmployeeScheduleAlter
	@OneToMany(mappedBy="employee")
	private List<EmployeeScheduleAlter> employeeScheduleAlters;

	//bi-directional many-to-one association to EmployeeSubscription
	@OneToMany(mappedBy="employee1")
	private List<EmployeeSubscription> employeeSubscriptions1;

	//bi-directional many-to-one association to EmployeeSubscription
	@OneToMany(mappedBy="employee2")
	private List<EmployeeSubscription> employeeSubscriptions2;

	//bi-directional many-to-one association to EmployeeSubscription
	@OneToMany(mappedBy="employee3")
	private List<EmployeeSubscription> employeeSubscriptions3;

	//bi-directional many-to-one association to EmployeeSubscriptionModify
	@OneToMany(mappedBy="employee1")
	private List<EmployeeSubscriptionModify> employeeSubscriptionModifies1;

	//bi-directional many-to-one association to EmployeeSubscriptionModify
	@OneToMany(mappedBy="employee2")
	private List<EmployeeSubscriptionModify> employeeSubscriptionModifies2;

	//bi-directional many-to-one association to EmployeeSubscriptionModify
	@OneToMany(mappedBy="employee3")
	private List<EmployeeSubscriptionModify> employeeSubscriptionModifies3;

	//bi-directional many-to-one association to Escalationmatrix
	@OneToMany(mappedBy="employee")
	private List<Escalationmatrix> escalationmatrixs;

	//bi-directional many-to-one association to Payroll
	@OneToMany(mappedBy="employee1")
	private List<Payroll> payrolls1;

	//bi-directional many-to-one association to Payroll
	@OneToMany(mappedBy="employee2")
	private List<Payroll> payrolls2;

	//bi-directional many-to-one association to Roledelegation
	@OneToMany(mappedBy="employee1")
	private List<Roledelegation> roledelegations1;

	//bi-directional many-to-one association to Roledelegation
	@OneToMany(mappedBy="employee2")
	private List<Roledelegation> roledelegations2;

	//bi-directional many-to-one association to SpocChild
	@OneToMany(mappedBy="employee")
	private List<SpocChild> spocChilds;

	//bi-directional many-to-one association to SpocParent
	@OneToMany(mappedBy="employee1")
	private List<SpocParent> spocParents1;

	//bi-directional many-to-one association to SpocParent
	@OneToMany(mappedBy="employee2")
	private List<SpocParent> spocParents2;

	//bi-directional many-to-one association to TransportationInEmergency
	@OneToMany(mappedBy="employee1")
	private List<TransportationInEmergency> transportationInEmergencies1;

	//bi-directional many-to-one association to TransportationInEmergency
	@OneToMany(mappedBy="employee2")
	private List<TransportationInEmergency> transportationInEmergencies2;

	//bi-directional many-to-one association to TripDetailsChild
	@OneToMany(mappedBy="employee")
	private List<TripDetailsChild> tripDetailsChilds;

	//bi-directional many-to-one association to TripDetailsModified
	@OneToMany(mappedBy="employee")
	private List<TripDetailsModified> tripDetailsModifieds;

	//bi-directional many-to-one association to TripDetailsRemoved
	@OneToMany(mappedBy="employee")
	private List<TripDetailsRemoved> tripDetailsRemoveds;

	//bi-directional many-to-one association to Vendor
	@OneToMany(mappedBy="employee")
	private List<Vendor> vendors;

	//bi-directional many-to-one association to VendorTripSheet
	@OneToMany(mappedBy="employee")
	private List<VendorTripSheet> vendorTripSheets;

	//bi-directional many-to-one association to VendorTripSheetParent
	@OneToMany(mappedBy="employee")
	private List<VendorTripSheetParent> vendorTripSheetParents;

	public Employee() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getActive() {
		return this.active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getAddressChangeDate() {
		return this.addressChangeDate;
	}

	public void setAddressChangeDate(Timestamp addressChangeDate) {
		this.addressChangeDate = addressChangeDate;
	}

	public int getAddresschangestatus() {
		return this.addresschangestatus;
	}

	public void setAddresschangestatus(int addresschangestatus) {
		this.addresschangestatus = addresschangestatus;
	}

	public String getAuthType() {
		return this.authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	public String getAutoRoleUpdate() {
		return this.autoRoleUpdate;
	}

	public void setAutoRoleUpdate(String autoRoleUpdate) {
		this.autoRoleUpdate = autoRoleUpdate;
	}

	public String getBusinessUnitCode() {
		return this.businessUnitCode;
	}

	public void setBusinessUnitCode(String businessUnitCode) {
		this.businessUnitCode = businessUnitCode;
	}

	public String getBusinessUnitDescription() {
		return this.businessUnitDescription;
	}

	public void setBusinessUnitDescription(String businessUnitDescription) {
		this.businessUnitDescription = businessUnitDescription;
	}

	public int getCareerLevel() {
		return this.careerLevel;
	}

	public void setCareerLevel(int careerLevel) {
		this.careerLevel = careerLevel;
	}

	public String getCareerPathwayDesc() {
		return this.careerPathwayDesc;
	}

	public void setCareerPathwayDesc(String careerPathwayDesc) {
		this.careerPathwayDesc = careerPathwayDesc;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getClientServiceManager() {
		return this.clientServiceManager;
	}

	public void setClientServiceManager(String clientServiceManager) {
		this.clientServiceManager = clientServiceManager;
	}

	public String getContactNumber1() {
		return this.contactNumber1;
	}

	public void setContactNumber1(String contactNumber1) {
		this.contactNumber1 = contactNumber1;
	}

	public String getContactNumber2() {
		return this.contactNumber2;
	}

	public void setContactNumber2(String contactNumber2) {
		this.contactNumber2 = contactNumber2;
	}

	public Date getDateofjoining() {
		return this.dateofjoining;
	}

	public void setDateofjoining(Date dateofjoining) {
		this.dateofjoining = dateofjoining;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptno() {
		return this.deptno;
	}

	public void setDeptno(String deptno) {
		this.deptno = deptno;
	}

	public String getDesignationName() {
		return this.designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}

	public String getDisplayname() {
		return this.displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getEmpLat() {
		return this.empLat;
	}

	public void setEmpLat(String empLat) {
		this.empLat = empLat;
	}

	public String getEmpLong() {
		return this.empLong;
	}

	public void setEmpLong(String empLong) {
		this.empLong = empLong;
	}

	public String getEmployeeFirstName() {
		return this.employeeFirstName;
	}

	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}

	public String getEmployeeLastName() {
		return this.employeeLastName;
	}

	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}

	public String getEmployeeMiddleName() {
		return this.employeeMiddleName;
	}

	public void setEmployeeMiddleName(String employeeMiddleName) {
		this.employeeMiddleName = employeeMiddleName;
	}

	public String getExternalUser() {
		return this.externalUser;
	}

	public void setExternalUser(String externalUser) {
		this.externalUser = externalUser;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHomeCountry() {
		return this.homeCountry;
	}

	public void setHomeCountry(String homeCountry) {
		this.homeCountry = homeCountry;
	}

	public String getIsContractEmployee() {
		return this.isContractEmployee;
	}

	public void setIsContractEmployee(String isContractEmployee) {
		this.isContractEmployee = isContractEmployee;
	}

	public String getLineManager() {
		return this.lineManager;
	}

	public void setLineManager(String lineManager) {
		this.lineManager = lineManager;
	}

	public String getLoginId() {
		return this.loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getOperationCode() {
		return this.operationCode;
	}

	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}

	public String getOperationDescription() {
		return this.operationDescription;
	}

	public void setOperationDescription(String operationDescription) {
		this.operationDescription = operationDescription;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPathways() {
		return this.pathways;
	}

	public void setPathways(String pathways) {
		this.pathways = pathways;
	}

	public String getPersonnelNo() {
		return this.personnelNo;
	}

	public void setPersonnelNo(String personnelNo) {
		this.personnelNo = personnelNo;
	}

	public Date getPwdChangedDate() {
		return this.pwdChangedDate;
	}

	public void setPwdChangedDate(Date pwdChangedDate) {
		this.pwdChangedDate = pwdChangedDate;
	}

	public String getRegisterStatus() {
		return this.registerStatus;
	}

	public void setRegisterStatus(String registerStatus) {
		this.registerStatus = registerStatus;
	}

	public String getRoutingType() {
		return this.routingType;
	}

	public void setRoutingType(String routingType) {
		this.routingType = routingType;
	}

	public String getStaffManager() {
		return this.staffManager;
	}

	public void setStaffManager(String staffManager) {
		this.staffManager = staffManager;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUsertype() {
		return this.usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public List<Adhocbooking> getAdhocbookings1() {
		return this.adhocbookings1;
	}

	public void setAdhocbookings1(List<Adhocbooking> adhocbookings1) {
		this.adhocbookings1 = adhocbookings1;
	}

	public Adhocbooking addAdhocbookings1(Adhocbooking adhocbookings1) {
		getAdhocbookings1().add(adhocbookings1);
		adhocbookings1.setEmployee1(this);

		return adhocbookings1;
	}

	public Adhocbooking removeAdhocbookings1(Adhocbooking adhocbookings1) {
		getAdhocbookings1().remove(adhocbookings1);
		adhocbookings1.setEmployee1(null);

		return adhocbookings1;
	}

	public List<Adhocbooking> getAdhocbookings2() {
		return this.adhocbookings2;
	}

	public void setAdhocbookings2(List<Adhocbooking> adhocbookings2) {
		this.adhocbookings2 = adhocbookings2;
	}

	public Adhocbooking addAdhocbookings2(Adhocbooking adhocbookings2) {
		getAdhocbookings2().add(adhocbookings2);
		adhocbookings2.setEmployee2(this);

		return adhocbookings2;
	}

	public Adhocbooking removeAdhocbookings2(Adhocbooking adhocbookings2) {
		getAdhocbookings2().remove(adhocbookings2);
		adhocbookings2.setEmployee2(null);

		return adhocbookings2;
	}

	public List<Adhocbooking> getAdhocbookings3() {
		return this.adhocbookings3;
	}

	public void setAdhocbookings3(List<Adhocbooking> adhocbookings3) {
		this.adhocbookings3 = adhocbookings3;
	}

	public Adhocbooking addAdhocbookings3(Adhocbooking adhocbookings3) {
		getAdhocbookings3().add(adhocbookings3);
		adhocbookings3.setEmployee3(this);

		return adhocbookings3;
	}

	public Adhocbooking removeAdhocbookings3(Adhocbooking adhocbookings3) {
		getAdhocbookings3().remove(adhocbookings3);
		adhocbookings3.setEmployee3(null);

		return adhocbookings3;
	}

	public List<AuditLog> getAuditLogs() {
		return this.auditLogs;
	}

	public void setAuditLogs(List<AuditLog> auditLogs) {
		this.auditLogs = auditLogs;
	}

	public AuditLog addAuditLog(AuditLog auditLog) {
		getAuditLogs().add(auditLog);
		auditLog.setEmployee(this);

		return auditLog;
	}

	public AuditLog removeAuditLog(AuditLog auditLog) {
		getAuditLogs().remove(auditLog);
		auditLog.setEmployee(null);

		return auditLog;
	}

	public Atsconnect getAtsconnect() {
		return this.atsconnect;
	}

	public void setAtsconnect(Atsconnect atsconnect) {
		this.atsconnect = atsconnect;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Site getSiteBean() {
		return this.siteBean;
	}

	public void setSiteBean(Site siteBean) {
		this.siteBean = siteBean;
	}

	public List<EmployeeImei> getEmployeeImeis() {
		return this.employeeImeis;
	}

	public void setEmployeeImeis(List<EmployeeImei> employeeImeis) {
		this.employeeImeis = employeeImeis;
	}

	public EmployeeImei addEmployeeImei(EmployeeImei employeeImei) {
		getEmployeeImeis().add(employeeImei);
		employeeImei.setEmployee(this);

		return employeeImei;
	}

	public EmployeeImei removeEmployeeImei(EmployeeImei employeeImei) {
		getEmployeeImeis().remove(employeeImei);
		employeeImei.setEmployee(null);

		return employeeImei;
	}

	public List<EmployeeSchedule> getEmployeeSchedules1() {
		return this.employeeSchedules1;
	}

	public void setEmployeeSchedules1(List<EmployeeSchedule> employeeSchedules1) {
		this.employeeSchedules1 = employeeSchedules1;
	}

	public EmployeeSchedule addEmployeeSchedules1(EmployeeSchedule employeeSchedules1) {
		getEmployeeSchedules1().add(employeeSchedules1);
		employeeSchedules1.setEmployee1(this);

		return employeeSchedules1;
	}

	public EmployeeSchedule removeEmployeeSchedules1(EmployeeSchedule employeeSchedules1) {
		getEmployeeSchedules1().remove(employeeSchedules1);
		employeeSchedules1.setEmployee1(null);

		return employeeSchedules1;
	}

	public List<EmployeeSchedule> getEmployeeSchedules2() {
		return this.employeeSchedules2;
	}

	public void setEmployeeSchedules2(List<EmployeeSchedule> employeeSchedules2) {
		this.employeeSchedules2 = employeeSchedules2;
	}

	public EmployeeSchedule addEmployeeSchedules2(EmployeeSchedule employeeSchedules2) {
		getEmployeeSchedules2().add(employeeSchedules2);
		employeeSchedules2.setEmployee2(this);

		return employeeSchedules2;
	}

	public EmployeeSchedule removeEmployeeSchedules2(EmployeeSchedule employeeSchedules2) {
		getEmployeeSchedules2().remove(employeeSchedules2);
		employeeSchedules2.setEmployee2(null);

		return employeeSchedules2;
	}

	public List<EmployeeScheduleAlter> getEmployeeScheduleAlters() {
		return this.employeeScheduleAlters;
	}

	public void setEmployeeScheduleAlters(List<EmployeeScheduleAlter> employeeScheduleAlters) {
		this.employeeScheduleAlters = employeeScheduleAlters;
	}

	public EmployeeScheduleAlter addEmployeeScheduleAlter(EmployeeScheduleAlter employeeScheduleAlter) {
		getEmployeeScheduleAlters().add(employeeScheduleAlter);
		employeeScheduleAlter.setEmployee(this);

		return employeeScheduleAlter;
	}

	public EmployeeScheduleAlter removeEmployeeScheduleAlter(EmployeeScheduleAlter employeeScheduleAlter) {
		getEmployeeScheduleAlters().remove(employeeScheduleAlter);
		employeeScheduleAlter.setEmployee(null);

		return employeeScheduleAlter;
	}

	public List<EmployeeSubscription> getEmployeeSubscriptions1() {
		return this.employeeSubscriptions1;
	}

	public void setEmployeeSubscriptions1(List<EmployeeSubscription> employeeSubscriptions1) {
		this.employeeSubscriptions1 = employeeSubscriptions1;
	}

	public EmployeeSubscription addEmployeeSubscriptions1(EmployeeSubscription employeeSubscriptions1) {
		getEmployeeSubscriptions1().add(employeeSubscriptions1);
		employeeSubscriptions1.setEmployee1(this);

		return employeeSubscriptions1;
	}

	public EmployeeSubscription removeEmployeeSubscriptions1(EmployeeSubscription employeeSubscriptions1) {
		getEmployeeSubscriptions1().remove(employeeSubscriptions1);
		employeeSubscriptions1.setEmployee1(null);

		return employeeSubscriptions1;
	}

	public List<EmployeeSubscription> getEmployeeSubscriptions2() {
		return this.employeeSubscriptions2;
	}

	public void setEmployeeSubscriptions2(List<EmployeeSubscription> employeeSubscriptions2) {
		this.employeeSubscriptions2 = employeeSubscriptions2;
	}

	public EmployeeSubscription addEmployeeSubscriptions2(EmployeeSubscription employeeSubscriptions2) {
		getEmployeeSubscriptions2().add(employeeSubscriptions2);
		employeeSubscriptions2.setEmployee2(this);

		return employeeSubscriptions2;
	}

	public EmployeeSubscription removeEmployeeSubscriptions2(EmployeeSubscription employeeSubscriptions2) {
		getEmployeeSubscriptions2().remove(employeeSubscriptions2);
		employeeSubscriptions2.setEmployee2(null);

		return employeeSubscriptions2;
	}

	public List<EmployeeSubscription> getEmployeeSubscriptions3() {
		return this.employeeSubscriptions3;
	}

	public void setEmployeeSubscriptions3(List<EmployeeSubscription> employeeSubscriptions3) {
		this.employeeSubscriptions3 = employeeSubscriptions3;
	}

	public EmployeeSubscription addEmployeeSubscriptions3(EmployeeSubscription employeeSubscriptions3) {
		getEmployeeSubscriptions3().add(employeeSubscriptions3);
		employeeSubscriptions3.setEmployee3(this);

		return employeeSubscriptions3;
	}

	public EmployeeSubscription removeEmployeeSubscriptions3(EmployeeSubscription employeeSubscriptions3) {
		getEmployeeSubscriptions3().remove(employeeSubscriptions3);
		employeeSubscriptions3.setEmployee3(null);

		return employeeSubscriptions3;
	}

	public List<EmployeeSubscriptionModify> getEmployeeSubscriptionModifies1() {
		return this.employeeSubscriptionModifies1;
	}

	public void setEmployeeSubscriptionModifies1(List<EmployeeSubscriptionModify> employeeSubscriptionModifies1) {
		this.employeeSubscriptionModifies1 = employeeSubscriptionModifies1;
	}

	public EmployeeSubscriptionModify addEmployeeSubscriptionModifies1(EmployeeSubscriptionModify employeeSubscriptionModifies1) {
		getEmployeeSubscriptionModifies1().add(employeeSubscriptionModifies1);
		employeeSubscriptionModifies1.setEmployee1(this);

		return employeeSubscriptionModifies1;
	}

	public EmployeeSubscriptionModify removeEmployeeSubscriptionModifies1(EmployeeSubscriptionModify employeeSubscriptionModifies1) {
		getEmployeeSubscriptionModifies1().remove(employeeSubscriptionModifies1);
		employeeSubscriptionModifies1.setEmployee1(null);

		return employeeSubscriptionModifies1;
	}

	public List<EmployeeSubscriptionModify> getEmployeeSubscriptionModifies2() {
		return this.employeeSubscriptionModifies2;
	}

	public void setEmployeeSubscriptionModifies2(List<EmployeeSubscriptionModify> employeeSubscriptionModifies2) {
		this.employeeSubscriptionModifies2 = employeeSubscriptionModifies2;
	}

	public EmployeeSubscriptionModify addEmployeeSubscriptionModifies2(EmployeeSubscriptionModify employeeSubscriptionModifies2) {
		getEmployeeSubscriptionModifies2().add(employeeSubscriptionModifies2);
		employeeSubscriptionModifies2.setEmployee2(this);

		return employeeSubscriptionModifies2;
	}

	public EmployeeSubscriptionModify removeEmployeeSubscriptionModifies2(EmployeeSubscriptionModify employeeSubscriptionModifies2) {
		getEmployeeSubscriptionModifies2().remove(employeeSubscriptionModifies2);
		employeeSubscriptionModifies2.setEmployee2(null);

		return employeeSubscriptionModifies2;
	}

	public List<EmployeeSubscriptionModify> getEmployeeSubscriptionModifies3() {
		return this.employeeSubscriptionModifies3;
	}

	public void setEmployeeSubscriptionModifies3(List<EmployeeSubscriptionModify> employeeSubscriptionModifies3) {
		this.employeeSubscriptionModifies3 = employeeSubscriptionModifies3;
	}

	public EmployeeSubscriptionModify addEmployeeSubscriptionModifies3(EmployeeSubscriptionModify employeeSubscriptionModifies3) {
		getEmployeeSubscriptionModifies3().add(employeeSubscriptionModifies3);
		employeeSubscriptionModifies3.setEmployee3(this);

		return employeeSubscriptionModifies3;
	}

	public EmployeeSubscriptionModify removeEmployeeSubscriptionModifies3(EmployeeSubscriptionModify employeeSubscriptionModifies3) {
		getEmployeeSubscriptionModifies3().remove(employeeSubscriptionModifies3);
		employeeSubscriptionModifies3.setEmployee3(null);

		return employeeSubscriptionModifies3;
	}

	public List<Escalationmatrix> getEscalationmatrixs() {
		return this.escalationmatrixs;
	}

	public void setEscalationmatrixs(List<Escalationmatrix> escalationmatrixs) {
		this.escalationmatrixs = escalationmatrixs;
	}

	public Escalationmatrix addEscalationmatrix(Escalationmatrix escalationmatrix) {
		getEscalationmatrixs().add(escalationmatrix);
		escalationmatrix.setEmployee(this);

		return escalationmatrix;
	}

	public Escalationmatrix removeEscalationmatrix(Escalationmatrix escalationmatrix) {
		getEscalationmatrixs().remove(escalationmatrix);
		escalationmatrix.setEmployee(null);

		return escalationmatrix;
	}

	public List<Payroll> getPayrolls1() {
		return this.payrolls1;
	}

	public void setPayrolls1(List<Payroll> payrolls1) {
		this.payrolls1 = payrolls1;
	}

	public Payroll addPayrolls1(Payroll payrolls1) {
		getPayrolls1().add(payrolls1);
		payrolls1.setEmployee1(this);

		return payrolls1;
	}

	public Payroll removePayrolls1(Payroll payrolls1) {
		getPayrolls1().remove(payrolls1);
		payrolls1.setEmployee1(null);

		return payrolls1;
	}

	public List<Payroll> getPayrolls2() {
		return this.payrolls2;
	}

	public void setPayrolls2(List<Payroll> payrolls2) {
		this.payrolls2 = payrolls2;
	}

	public Payroll addPayrolls2(Payroll payrolls2) {
		getPayrolls2().add(payrolls2);
		payrolls2.setEmployee2(this);

		return payrolls2;
	}

	public Payroll removePayrolls2(Payroll payrolls2) {
		getPayrolls2().remove(payrolls2);
		payrolls2.setEmployee2(null);

		return payrolls2;
	}

	public List<Roledelegation> getRoledelegations1() {
		return this.roledelegations1;
	}

	public void setRoledelegations1(List<Roledelegation> roledelegations1) {
		this.roledelegations1 = roledelegations1;
	}

	public Roledelegation addRoledelegations1(Roledelegation roledelegations1) {
		getRoledelegations1().add(roledelegations1);
		roledelegations1.setEmployee1(this);

		return roledelegations1;
	}

	public Roledelegation removeRoledelegations1(Roledelegation roledelegations1) {
		getRoledelegations1().remove(roledelegations1);
		roledelegations1.setEmployee1(null);

		return roledelegations1;
	}

	public List<Roledelegation> getRoledelegations2() {
		return this.roledelegations2;
	}

	public void setRoledelegations2(List<Roledelegation> roledelegations2) {
		this.roledelegations2 = roledelegations2;
	}

	public Roledelegation addRoledelegations2(Roledelegation roledelegations2) {
		getRoledelegations2().add(roledelegations2);
		roledelegations2.setEmployee2(this);

		return roledelegations2;
	}

	public Roledelegation removeRoledelegations2(Roledelegation roledelegations2) {
		getRoledelegations2().remove(roledelegations2);
		roledelegations2.setEmployee2(null);

		return roledelegations2;
	}

	public List<SpocChild> getSpocChilds() {
		return this.spocChilds;
	}

	public void setSpocChilds(List<SpocChild> spocChilds) {
		this.spocChilds = spocChilds;
	}

	public SpocChild addSpocChild(SpocChild spocChild) {
		getSpocChilds().add(spocChild);
		spocChild.setEmployee(this);

		return spocChild;
	}

	public SpocChild removeSpocChild(SpocChild spocChild) {
		getSpocChilds().remove(spocChild);
		spocChild.setEmployee(null);

		return spocChild;
	}

	public List<SpocParent> getSpocParents1() {
		return this.spocParents1;
	}

	public void setSpocParents1(List<SpocParent> spocParents1) {
		this.spocParents1 = spocParents1;
	}

	public SpocParent addSpocParents1(SpocParent spocParents1) {
		getSpocParents1().add(spocParents1);
		spocParents1.setEmployee1(this);

		return spocParents1;
	}

	public SpocParent removeSpocParents1(SpocParent spocParents1) {
		getSpocParents1().remove(spocParents1);
		spocParents1.setEmployee1(null);

		return spocParents1;
	}

	public List<SpocParent> getSpocParents2() {
		return this.spocParents2;
	}

	public void setSpocParents2(List<SpocParent> spocParents2) {
		this.spocParents2 = spocParents2;
	}

	public SpocParent addSpocParents2(SpocParent spocParents2) {
		getSpocParents2().add(spocParents2);
		spocParents2.setEmployee2(this);

		return spocParents2;
	}

	public SpocParent removeSpocParents2(SpocParent spocParents2) {
		getSpocParents2().remove(spocParents2);
		spocParents2.setEmployee2(null);

		return spocParents2;
	}

	public List<TransportationInEmergency> getTransportationInEmergencies1() {
		return this.transportationInEmergencies1;
	}

	public void setTransportationInEmergencies1(List<TransportationInEmergency> transportationInEmergencies1) {
		this.transportationInEmergencies1 = transportationInEmergencies1;
	}

	public TransportationInEmergency addTransportationInEmergencies1(TransportationInEmergency transportationInEmergencies1) {
		getTransportationInEmergencies1().add(transportationInEmergencies1);
		transportationInEmergencies1.setEmployee1(this);

		return transportationInEmergencies1;
	}

	public TransportationInEmergency removeTransportationInEmergencies1(TransportationInEmergency transportationInEmergencies1) {
		getTransportationInEmergencies1().remove(transportationInEmergencies1);
		transportationInEmergencies1.setEmployee1(null);

		return transportationInEmergencies1;
	}

	public List<TransportationInEmergency> getTransportationInEmergencies2() {
		return this.transportationInEmergencies2;
	}

	public void setTransportationInEmergencies2(List<TransportationInEmergency> transportationInEmergencies2) {
		this.transportationInEmergencies2 = transportationInEmergencies2;
	}

	public TransportationInEmergency addTransportationInEmergencies2(TransportationInEmergency transportationInEmergencies2) {
		getTransportationInEmergencies2().add(transportationInEmergencies2);
		transportationInEmergencies2.setEmployee2(this);

		return transportationInEmergencies2;
	}

	public TransportationInEmergency removeTransportationInEmergencies2(TransportationInEmergency transportationInEmergencies2) {
		getTransportationInEmergencies2().remove(transportationInEmergencies2);
		transportationInEmergencies2.setEmployee2(null);

		return transportationInEmergencies2;
	}

	public List<TripDetailsChild> getTripDetailsChilds() {
		return this.tripDetailsChilds;
	}

	public void setTripDetailsChilds(List<TripDetailsChild> tripDetailsChilds) {
		this.tripDetailsChilds = tripDetailsChilds;
	}

	public TripDetailsChild addTripDetailsChild(TripDetailsChild tripDetailsChild) {
		getTripDetailsChilds().add(tripDetailsChild);
		tripDetailsChild.setEmployee(this);

		return tripDetailsChild;
	}

	public TripDetailsChild removeTripDetailsChild(TripDetailsChild tripDetailsChild) {
		getTripDetailsChilds().remove(tripDetailsChild);
		tripDetailsChild.setEmployee(null);

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
		tripDetailsModified.setEmployee(this);

		return tripDetailsModified;
	}

	public TripDetailsModified removeTripDetailsModified(TripDetailsModified tripDetailsModified) {
		getTripDetailsModifieds().remove(tripDetailsModified);
		tripDetailsModified.setEmployee(null);

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
		tripDetailsRemoved.setEmployee(this);

		return tripDetailsRemoved;
	}

	public TripDetailsRemoved removeTripDetailsRemoved(TripDetailsRemoved tripDetailsRemoved) {
		getTripDetailsRemoveds().remove(tripDetailsRemoved);
		tripDetailsRemoved.setEmployee(null);

		return tripDetailsRemoved;
	}

	public List<Vendor> getVendors() {
		return this.vendors;
	}

	public void setVendors(List<Vendor> vendors) {
		this.vendors = vendors;
	}

	public Vendor addVendor(Vendor vendor) {
		getVendors().add(vendor);
		vendor.setEmployee(this);

		return vendor;
	}

	public Vendor removeVendor(Vendor vendor) {
		getVendors().remove(vendor);
		vendor.setEmployee(null);

		return vendor;
	}

	public List<VendorTripSheet> getVendorTripSheets() {
		return this.vendorTripSheets;
	}

	public void setVendorTripSheets(List<VendorTripSheet> vendorTripSheets) {
		this.vendorTripSheets = vendorTripSheets;
	}

	public VendorTripSheet addVendorTripSheet(VendorTripSheet vendorTripSheet) {
		getVendorTripSheets().add(vendorTripSheet);
		vendorTripSheet.setEmployee(this);

		return vendorTripSheet;
	}

	public VendorTripSheet removeVendorTripSheet(VendorTripSheet vendorTripSheet) {
		getVendorTripSheets().remove(vendorTripSheet);
		vendorTripSheet.setEmployee(null);

		return vendorTripSheet;
	}

	public List<VendorTripSheetParent> getVendorTripSheetParents() {
		return this.vendorTripSheetParents;
	}

	public void setVendorTripSheetParents(List<VendorTripSheetParent> vendorTripSheetParents) {
		this.vendorTripSheetParents = vendorTripSheetParents;
	}

	public VendorTripSheetParent addVendorTripSheetParent(VendorTripSheetParent vendorTripSheetParent) {
		getVendorTripSheetParents().add(vendorTripSheetParent);
		vendorTripSheetParent.setEmployee(this);

		return vendorTripSheetParent;
	}

	public VendorTripSheetParent removeVendorTripSheetParent(VendorTripSheetParent vendorTripSheetParent) {
		getVendorTripSheetParents().remove(vendorTripSheetParent);
		vendorTripSheetParent.setEmployee(null);

		return vendorTripSheetParent;
	}

}