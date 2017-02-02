package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the site database table.
 * 
 */
@Entity
@NamedQuery(name="Site.findAll", query="SELECT s FROM Site s")
public class Site implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private int combainedRouteOnspecifiedTime;

	private int combainedRouteOnweekoff;

	@Column(name="lady_security")
	private int ladySecurity;

	@Column(name="night_shift_end")
	private String nightShiftEnd;

	@Column(name="night_shift_start")
	private String nightShiftStart;

	@Column(name="site_name")
	private String siteName;

	//bi-directional many-to-one association to Adhocbooking
	@OneToMany(mappedBy="site")
	private List<Adhocbooking> adhocbookings;

	//bi-directional many-to-one association to Adhoctype
	@OneToMany(mappedBy="siteBean")
	private List<Adhoctype> adhoctypes;

	//bi-directional many-to-one association to BillingTypeMapping
	@OneToMany(mappedBy="siteBean")
	private List<BillingTypeMapping> billingTypeMappings;

	//bi-directional many-to-one association to Employee
	@OneToMany(mappedBy="siteBean")
	private List<Employee> employees;

	//bi-directional many-to-one association to Escort
	@OneToMany(mappedBy="siteBean")
	private List<Escort> escorts;

	//bi-directional many-to-one association to PayrollConfig
	@OneToMany(mappedBy="siteBean")
	private List<PayrollConfig> payrollConfigs;

	//bi-directional many-to-one association to Priorityroute
	@OneToMany(mappedBy="site")
	private List<Priorityroute> priorityroutes;

	//bi-directional many-to-one association to Route
	@OneToMany(mappedBy="site")
	private List<Route> routes;

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branch")
	private Branch branchBean;

	//bi-directional many-to-one association to Landmark
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="landmark")
	private Landmark landmarkBean;

	//bi-directional many-to-many association to SettingsTable
	@ManyToMany
	@JoinTable(
		name="site_settings"
		, joinColumns={
			@JoinColumn(name="site")
			}
		, inverseJoinColumns={
			@JoinColumn(name="settingsId")
			}
		)
	private List<SettingsTable> settingsTables;

	//bi-directional many-to-one association to SiteShift
	@OneToMany(mappedBy="site")
	private List<SiteShift> siteShifts;

	//bi-directional many-to-one association to Siteshiftadhoc
	@OneToMany(mappedBy="site")
	private List<Siteshiftadhoc> siteshiftadhocs;

	//bi-directional many-to-one association to TransportationInEmergency
	@OneToMany(mappedBy="site")
	private List<TransportationInEmergency> transportationInEmergencies;

	//bi-directional many-to-one association to TripDetail
	@OneToMany(mappedBy="site")
	private List<TripDetail> tripDetails;

	//bi-directional many-to-one association to TripDetailsParentModified
	@OneToMany(mappedBy="site")
	private List<TripDetailsParentModified> tripDetailsParentModifieds;

	//bi-directional many-to-one association to TripDetailsRemoved
	@OneToMany(mappedBy="site")
	private List<TripDetailsRemoved> tripDetailsRemoveds;

	//bi-directional many-to-many association to VehicleType
	@ManyToMany(mappedBy="sites")
	private List<VehicleType> vehicleTypes;

	public Site() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCombainedRouteOnspecifiedTime() {
		return this.combainedRouteOnspecifiedTime;
	}

	public void setCombainedRouteOnspecifiedTime(int combainedRouteOnspecifiedTime) {
		this.combainedRouteOnspecifiedTime = combainedRouteOnspecifiedTime;
	}

	public int getCombainedRouteOnweekoff() {
		return this.combainedRouteOnweekoff;
	}

	public void setCombainedRouteOnweekoff(int combainedRouteOnweekoff) {
		this.combainedRouteOnweekoff = combainedRouteOnweekoff;
	}

	public int getLadySecurity() {
		return this.ladySecurity;
	}

	public void setLadySecurity(int ladySecurity) {
		this.ladySecurity = ladySecurity;
	}

	public String getNightShiftEnd() {
		return this.nightShiftEnd;
	}

	public void setNightShiftEnd(String nightShiftEnd) {
		this.nightShiftEnd = nightShiftEnd;
	}

	public String getNightShiftStart() {
		return this.nightShiftStart;
	}

	public void setNightShiftStart(String nightShiftStart) {
		this.nightShiftStart = nightShiftStart;
	}

	public String getSiteName() {
		return this.siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public List<Adhocbooking> getAdhocbookings() {
		return this.adhocbookings;
	}

	public void setAdhocbookings(List<Adhocbooking> adhocbookings) {
		this.adhocbookings = adhocbookings;
	}

	public Adhocbooking addAdhocbooking(Adhocbooking adhocbooking) {
		getAdhocbookings().add(adhocbooking);
		adhocbooking.setSite(this);

		return adhocbooking;
	}

	public Adhocbooking removeAdhocbooking(Adhocbooking adhocbooking) {
		getAdhocbookings().remove(adhocbooking);
		adhocbooking.setSite(null);

		return adhocbooking;
	}

	public List<Adhoctype> getAdhoctypes() {
		return this.adhoctypes;
	}

	public void setAdhoctypes(List<Adhoctype> adhoctypes) {
		this.adhoctypes = adhoctypes;
	}

	public Adhoctype addAdhoctype(Adhoctype adhoctype) {
		getAdhoctypes().add(adhoctype);
		adhoctype.setSiteBean(this);

		return adhoctype;
	}

	public Adhoctype removeAdhoctype(Adhoctype adhoctype) {
		getAdhoctypes().remove(adhoctype);
		adhoctype.setSiteBean(null);

		return adhoctype;
	}

	public List<BillingTypeMapping> getBillingTypeMappings() {
		return this.billingTypeMappings;
	}

	public void setBillingTypeMappings(List<BillingTypeMapping> billingTypeMappings) {
		this.billingTypeMappings = billingTypeMappings;
	}

	public BillingTypeMapping addBillingTypeMapping(BillingTypeMapping billingTypeMapping) {
		getBillingTypeMappings().add(billingTypeMapping);
		billingTypeMapping.setSiteBean(this);

		return billingTypeMapping;
	}

	public BillingTypeMapping removeBillingTypeMapping(BillingTypeMapping billingTypeMapping) {
		getBillingTypeMappings().remove(billingTypeMapping);
		billingTypeMapping.setSiteBean(null);

		return billingTypeMapping;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee addEmployee(Employee employee) {
		getEmployees().add(employee);
		employee.setSiteBean(this);

		return employee;
	}

	public Employee removeEmployee(Employee employee) {
		getEmployees().remove(employee);
		employee.setSiteBean(null);

		return employee;
	}

	public List<Escort> getEscorts() {
		return this.escorts;
	}

	public void setEscorts(List<Escort> escorts) {
		this.escorts = escorts;
	}

	public Escort addEscort(Escort escort) {
		getEscorts().add(escort);
		escort.setSiteBean(this);

		return escort;
	}

	public Escort removeEscort(Escort escort) {
		getEscorts().remove(escort);
		escort.setSiteBean(null);

		return escort;
	}

	public List<PayrollConfig> getPayrollConfigs() {
		return this.payrollConfigs;
	}

	public void setPayrollConfigs(List<PayrollConfig> payrollConfigs) {
		this.payrollConfigs = payrollConfigs;
	}

	public PayrollConfig addPayrollConfig(PayrollConfig payrollConfig) {
		getPayrollConfigs().add(payrollConfig);
		payrollConfig.setSiteBean(this);

		return payrollConfig;
	}

	public PayrollConfig removePayrollConfig(PayrollConfig payrollConfig) {
		getPayrollConfigs().remove(payrollConfig);
		payrollConfig.setSiteBean(null);

		return payrollConfig;
	}

	public List<Priorityroute> getPriorityroutes() {
		return this.priorityroutes;
	}

	public void setPriorityroutes(List<Priorityroute> priorityroutes) {
		this.priorityroutes = priorityroutes;
	}

	public Priorityroute addPriorityroute(Priorityroute priorityroute) {
		getPriorityroutes().add(priorityroute);
		priorityroute.setSite(this);

		return priorityroute;
	}

	public Priorityroute removePriorityroute(Priorityroute priorityroute) {
		getPriorityroutes().remove(priorityroute);
		priorityroute.setSite(null);

		return priorityroute;
	}

	public List<Route> getRoutes() {
		return this.routes;
	}

	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}

	public Route addRoute(Route route) {
		getRoutes().add(route);
		route.setSite(this);

		return route;
	}

	public Route removeRoute(Route route) {
		getRoutes().remove(route);
		route.setSite(null);

		return route;
	}

	public Branch getBranchBean() {
		return this.branchBean;
	}

	public void setBranchBean(Branch branchBean) {
		this.branchBean = branchBean;
	}

	public Landmark getLandmarkBean() {
		return this.landmarkBean;
	}

	public void setLandmarkBean(Landmark landmarkBean) {
		this.landmarkBean = landmarkBean;
	}

	public List<SettingsTable> getSettingsTables() {
		return this.settingsTables;
	}

	public void setSettingsTables(List<SettingsTable> settingsTables) {
		this.settingsTables = settingsTables;
	}

	public List<SiteShift> getSiteShifts() {
		return this.siteShifts;
	}

	public void setSiteShifts(List<SiteShift> siteShifts) {
		this.siteShifts = siteShifts;
	}

	public SiteShift addSiteShift(SiteShift siteShift) {
		getSiteShifts().add(siteShift);
		siteShift.setSite(this);

		return siteShift;
	}

	public SiteShift removeSiteShift(SiteShift siteShift) {
		getSiteShifts().remove(siteShift);
		siteShift.setSite(null);

		return siteShift;
	}

	public List<Siteshiftadhoc> getSiteshiftadhocs() {
		return this.siteshiftadhocs;
	}

	public void setSiteshiftadhocs(List<Siteshiftadhoc> siteshiftadhocs) {
		this.siteshiftadhocs = siteshiftadhocs;
	}

	public Siteshiftadhoc addSiteshiftadhoc(Siteshiftadhoc siteshiftadhoc) {
		getSiteshiftadhocs().add(siteshiftadhoc);
		siteshiftadhoc.setSite(this);

		return siteshiftadhoc;
	}

	public Siteshiftadhoc removeSiteshiftadhoc(Siteshiftadhoc siteshiftadhoc) {
		getSiteshiftadhocs().remove(siteshiftadhoc);
		siteshiftadhoc.setSite(null);

		return siteshiftadhoc;
	}

	public List<TransportationInEmergency> getTransportationInEmergencies() {
		return this.transportationInEmergencies;
	}

	public void setTransportationInEmergencies(List<TransportationInEmergency> transportationInEmergencies) {
		this.transportationInEmergencies = transportationInEmergencies;
	}

	public TransportationInEmergency addTransportationInEmergency(TransportationInEmergency transportationInEmergency) {
		getTransportationInEmergencies().add(transportationInEmergency);
		transportationInEmergency.setSite(this);

		return transportationInEmergency;
	}

	public TransportationInEmergency removeTransportationInEmergency(TransportationInEmergency transportationInEmergency) {
		getTransportationInEmergencies().remove(transportationInEmergency);
		transportationInEmergency.setSite(null);

		return transportationInEmergency;
	}

	public List<TripDetail> getTripDetails() {
		return this.tripDetails;
	}

	public void setTripDetails(List<TripDetail> tripDetails) {
		this.tripDetails = tripDetails;
	}

	public TripDetail addTripDetail(TripDetail tripDetail) {
		getTripDetails().add(tripDetail);
		tripDetail.setSite(this);

		return tripDetail;
	}

	public TripDetail removeTripDetail(TripDetail tripDetail) {
		getTripDetails().remove(tripDetail);
		tripDetail.setSite(null);

		return tripDetail;
	}

	public List<TripDetailsParentModified> getTripDetailsParentModifieds() {
		return this.tripDetailsParentModifieds;
	}

	public void setTripDetailsParentModifieds(List<TripDetailsParentModified> tripDetailsParentModifieds) {
		this.tripDetailsParentModifieds = tripDetailsParentModifieds;
	}

	public TripDetailsParentModified addTripDetailsParentModified(TripDetailsParentModified tripDetailsParentModified) {
		getTripDetailsParentModifieds().add(tripDetailsParentModified);
		tripDetailsParentModified.setSite(this);

		return tripDetailsParentModified;
	}

	public TripDetailsParentModified removeTripDetailsParentModified(TripDetailsParentModified tripDetailsParentModified) {
		getTripDetailsParentModifieds().remove(tripDetailsParentModified);
		tripDetailsParentModified.setSite(null);

		return tripDetailsParentModified;
	}

	public List<TripDetailsRemoved> getTripDetailsRemoveds() {
		return this.tripDetailsRemoveds;
	}

	public void setTripDetailsRemoveds(List<TripDetailsRemoved> tripDetailsRemoveds) {
		this.tripDetailsRemoveds = tripDetailsRemoveds;
	}

	public TripDetailsRemoved addTripDetailsRemoved(TripDetailsRemoved tripDetailsRemoved) {
		getTripDetailsRemoveds().add(tripDetailsRemoved);
		tripDetailsRemoved.setSite(this);

		return tripDetailsRemoved;
	}

	public TripDetailsRemoved removeTripDetailsRemoved(TripDetailsRemoved tripDetailsRemoved) {
		getTripDetailsRemoveds().remove(tripDetailsRemoved);
		tripDetailsRemoved.setSite(null);

		return tripDetailsRemoved;
	}

	public List<VehicleType> getVehicleTypes() {
		return this.vehicleTypes;
	}

	public void setVehicleTypes(List<VehicleType> vehicleTypes) {
		this.vehicleTypes = vehicleTypes;
	}

}