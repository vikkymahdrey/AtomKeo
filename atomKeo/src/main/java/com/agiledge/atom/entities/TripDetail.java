package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the trip_details database table.
 * 
 */
@Entity
@Table(name="trip_details")
@NamedQuery(name="TripDetail.findAll", query="SELECT t FROM TripDetail t")
public class TripDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private int destinationLandmark;

	private double distance;

	private double distanceFromPreTrip;

	private String driverPswd;

	private String escortPswd;

	private BigInteger preTripId;

	private int routeId;

	private String routingType;

	@Column(name="security_status")
	private String securityStatus;

	private String status;

	private String travelTime;

	@Column(name="trip_code")
	private String tripCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="trip_date")
	private Date tripDate;

	@Column(name="trip_log")
	private String tripLog;

	@Column(name="trip_time")
	private String tripTime;

	//bi-directional many-to-one association to DriverLogin
	@OneToMany(mappedBy="tripDetail")
	private List<DriverLogin> driverLogins;

	//bi-directional many-to-one association to Panicaction
	@OneToMany(mappedBy="tripDetail")
	private List<Panicaction> panicactions;

	//bi-directional many-to-one association to TdBillingArg
	@OneToMany(mappedBy="tripDetail")
	private List<TdBillingArg> tdBillingArgs;

	//bi-directional many-to-one association to Driver
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="driverId")
	private Driver driver;

	//bi-directional many-to-one association to Escort
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="escortId")
	private Escort escort;

	//bi-directional many-to-one association to Site
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="siteId")
	private Site site;

	//bi-directional many-to-one association to VehicleType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vehicle_type")
	private VehicleType vehicleTypeBean;

	//bi-directional many-to-one association to Vehicle
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vehicle")
	private Vehicle vehicleBean;

	//bi-directional many-to-one association to TripDetailsChild
	@OneToMany(mappedBy="tripDetail")
	private List<TripDetailsChild> tripDetailsChilds;

	//bi-directional many-to-one association to TripDetailsModified
	@OneToMany(mappedBy="tripDetail")
	private List<TripDetailsModified> tripDetailsModifieds;

	//bi-directional many-to-one association to Tripcomment
	@OneToMany(mappedBy="tripDetail")
	private List<Tripcomment> tripcomments;

	//bi-directional one-to-one association to Tripvendorassign
	@OneToOne(mappedBy="tripDetail", fetch=FetchType.LAZY)
	private Tripvendorassign tripvendorassign;

	//bi-directional many-to-one association to VehiclePosition
	@OneToMany(mappedBy="tripDetail")
	private List<VehiclePosition> vehiclePositions;

	//bi-directional many-to-one association to VehiclePositionTemp
	@OneToMany(mappedBy="tripDetail")
	private List<VehiclePositionTemp> vehiclePositionTemps;

	//bi-directional many-to-one association to VendorTripSheet
	@OneToMany(mappedBy="tripDetail")
	private List<VendorTripSheet> vendorTripSheets;

	//bi-directional many-to-one association to VendorTripSheetEscort
	@OneToMany(mappedBy="tripDetail")
	private List<VendorTripSheetEscort> vendorTripSheetEscorts;

	//bi-directional many-to-one association to VendorTripSheetParent
	@OneToMany(mappedBy="tripDetail")
	private List<VendorTripSheetParent> vendorTripSheetParents;

	public TripDetail() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getDestinationLandmark() {
		return this.destinationLandmark;
	}

	public void setDestinationLandmark(int destinationLandmark) {
		this.destinationLandmark = destinationLandmark;
	}

	public double getDistance() {
		return this.distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getDistanceFromPreTrip() {
		return this.distanceFromPreTrip;
	}

	public void setDistanceFromPreTrip(double distanceFromPreTrip) {
		this.distanceFromPreTrip = distanceFromPreTrip;
	}

	public String getDriverPswd() {
		return this.driverPswd;
	}

	public void setDriverPswd(String driverPswd) {
		this.driverPswd = driverPswd;
	}

	public String getEscortPswd() {
		return this.escortPswd;
	}

	public void setEscortPswd(String escortPswd) {
		this.escortPswd = escortPswd;
	}

	public BigInteger getPreTripId() {
		return this.preTripId;
	}

	public void setPreTripId(BigInteger preTripId) {
		this.preTripId = preTripId;
	}

	public int getRouteId() {
		return this.routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public String getRoutingType() {
		return this.routingType;
	}

	public void setRoutingType(String routingType) {
		this.routingType = routingType;
	}

	public String getSecurityStatus() {
		return this.securityStatus;
	}

	public void setSecurityStatus(String securityStatus) {
		this.securityStatus = securityStatus;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTravelTime() {
		return this.travelTime;
	}

	public void setTravelTime(String travelTime) {
		this.travelTime = travelTime;
	}

	public String getTripCode() {
		return this.tripCode;
	}

	public void setTripCode(String tripCode) {
		this.tripCode = tripCode;
	}

	public Date getTripDate() {
		return this.tripDate;
	}

	public void setTripDate(Date tripDate) {
		this.tripDate = tripDate;
	}

	public String getTripLog() {
		return this.tripLog;
	}

	public void setTripLog(String tripLog) {
		this.tripLog = tripLog;
	}

	public String getTripTime() {
		return this.tripTime;
	}

	public void setTripTime(String tripTime) {
		this.tripTime = tripTime;
	}

	public List<DriverLogin> getDriverLogins() {
		return this.driverLogins;
	}

	public void setDriverLogins(List<DriverLogin> driverLogins) {
		this.driverLogins = driverLogins;
	}

	public DriverLogin addDriverLogin(DriverLogin driverLogin) {
		getDriverLogins().add(driverLogin);
		driverLogin.setTripDetail(this);

		return driverLogin;
	}

	public DriverLogin removeDriverLogin(DriverLogin driverLogin) {
		getDriverLogins().remove(driverLogin);
		driverLogin.setTripDetail(null);

		return driverLogin;
	}

	public List<Panicaction> getPanicactions() {
		return this.panicactions;
	}

	public void setPanicactions(List<Panicaction> panicactions) {
		this.panicactions = panicactions;
	}

	public Panicaction addPanicaction(Panicaction panicaction) {
		getPanicactions().add(panicaction);
		panicaction.setTripDetail(this);

		return panicaction;
	}

	public Panicaction removePanicaction(Panicaction panicaction) {
		getPanicactions().remove(panicaction);
		panicaction.setTripDetail(null);

		return panicaction;
	}

	public List<TdBillingArg> getTdBillingArgs() {
		return this.tdBillingArgs;
	}

	public void setTdBillingArgs(List<TdBillingArg> tdBillingArgs) {
		this.tdBillingArgs = tdBillingArgs;
	}

	public TdBillingArg addTdBillingArg(TdBillingArg tdBillingArg) {
		getTdBillingArgs().add(tdBillingArg);
		tdBillingArg.setTripDetail(this);

		return tdBillingArg;
	}

	public TdBillingArg removeTdBillingArg(TdBillingArg tdBillingArg) {
		getTdBillingArgs().remove(tdBillingArg);
		tdBillingArg.setTripDetail(null);

		return tdBillingArg;
	}

	public Driver getDriver() {
		return this.driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Escort getEscort() {
		return this.escort;
	}

	public void setEscort(Escort escort) {
		this.escort = escort;
	}

	public Site getSite() {
		return this.site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public VehicleType getVehicleTypeBean() {
		return this.vehicleTypeBean;
	}

	public void setVehicleTypeBean(VehicleType vehicleTypeBean) {
		this.vehicleTypeBean = vehicleTypeBean;
	}

	public Vehicle getVehicleBean() {
		return this.vehicleBean;
	}

	public void setVehicleBean(Vehicle vehicleBean) {
		this.vehicleBean = vehicleBean;
	}

	public List<TripDetailsChild> getTripDetailsChilds() {
		return this.tripDetailsChilds;
	}

	public void setTripDetailsChilds(List<TripDetailsChild> tripDetailsChilds) {
		this.tripDetailsChilds = tripDetailsChilds;
	}

	public TripDetailsChild addTripDetailsChild(TripDetailsChild tripDetailsChild) {
		getTripDetailsChilds().add(tripDetailsChild);
		tripDetailsChild.setTripDetail(this);

		return tripDetailsChild;
	}

	public TripDetailsChild removeTripDetailsChild(TripDetailsChild tripDetailsChild) {
		getTripDetailsChilds().remove(tripDetailsChild);
		tripDetailsChild.setTripDetail(null);

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
		tripDetailsModified.setTripDetail(this);

		return tripDetailsModified;
	}

	public TripDetailsModified removeTripDetailsModified(TripDetailsModified tripDetailsModified) {
		getTripDetailsModifieds().remove(tripDetailsModified);
		tripDetailsModified.setTripDetail(null);

		return tripDetailsModified;
	}

	public List<Tripcomment> getTripcomments() {
		return this.tripcomments;
	}

	public void setTripcomments(List<Tripcomment> tripcomments) {
		this.tripcomments = tripcomments;
	}

	public Tripcomment addTripcomment(Tripcomment tripcomment) {
		getTripcomments().add(tripcomment);
		tripcomment.setTripDetail(this);

		return tripcomment;
	}

	public Tripcomment removeTripcomment(Tripcomment tripcomment) {
		getTripcomments().remove(tripcomment);
		tripcomment.setTripDetail(null);

		return tripcomment;
	}

	public Tripvendorassign getTripvendorassign() {
		return this.tripvendorassign;
	}

	public void setTripvendorassign(Tripvendorassign tripvendorassign) {
		this.tripvendorassign = tripvendorassign;
	}

	public List<VehiclePosition> getVehiclePositions() {
		return this.vehiclePositions;
	}

	public void setVehiclePositions(List<VehiclePosition> vehiclePositions) {
		this.vehiclePositions = vehiclePositions;
	}

	public VehiclePosition addVehiclePosition(VehiclePosition vehiclePosition) {
		getVehiclePositions().add(vehiclePosition);
		vehiclePosition.setTripDetail(this);

		return vehiclePosition;
	}

	public VehiclePosition removeVehiclePosition(VehiclePosition vehiclePosition) {
		getVehiclePositions().remove(vehiclePosition);
		vehiclePosition.setTripDetail(null);

		return vehiclePosition;
	}

	public List<VehiclePositionTemp> getVehiclePositionTemps() {
		return this.vehiclePositionTemps;
	}

	public void setVehiclePositionTemps(List<VehiclePositionTemp> vehiclePositionTemps) {
		this.vehiclePositionTemps = vehiclePositionTemps;
	}

	public VehiclePositionTemp addVehiclePositionTemp(VehiclePositionTemp vehiclePositionTemp) {
		getVehiclePositionTemps().add(vehiclePositionTemp);
		vehiclePositionTemp.setTripDetail(this);

		return vehiclePositionTemp;
	}

	public VehiclePositionTemp removeVehiclePositionTemp(VehiclePositionTemp vehiclePositionTemp) {
		getVehiclePositionTemps().remove(vehiclePositionTemp);
		vehiclePositionTemp.setTripDetail(null);

		return vehiclePositionTemp;
	}

	public List<VendorTripSheet> getVendorTripSheets() {
		return this.vendorTripSheets;
	}

	public void setVendorTripSheets(List<VendorTripSheet> vendorTripSheets) {
		this.vendorTripSheets = vendorTripSheets;
	}

	public VendorTripSheet addVendorTripSheet(VendorTripSheet vendorTripSheet) {
		getVendorTripSheets().add(vendorTripSheet);
		vendorTripSheet.setTripDetail(this);

		return vendorTripSheet;
	}

	public VendorTripSheet removeVendorTripSheet(VendorTripSheet vendorTripSheet) {
		getVendorTripSheets().remove(vendorTripSheet);
		vendorTripSheet.setTripDetail(null);

		return vendorTripSheet;
	}

	public List<VendorTripSheetEscort> getVendorTripSheetEscorts() {
		return this.vendorTripSheetEscorts;
	}

	public void setVendorTripSheetEscorts(List<VendorTripSheetEscort> vendorTripSheetEscorts) {
		this.vendorTripSheetEscorts = vendorTripSheetEscorts;
	}

	public VendorTripSheetEscort addVendorTripSheetEscort(VendorTripSheetEscort vendorTripSheetEscort) {
		getVendorTripSheetEscorts().add(vendorTripSheetEscort);
		vendorTripSheetEscort.setTripDetail(this);

		return vendorTripSheetEscort;
	}

	public VendorTripSheetEscort removeVendorTripSheetEscort(VendorTripSheetEscort vendorTripSheetEscort) {
		getVendorTripSheetEscorts().remove(vendorTripSheetEscort);
		vendorTripSheetEscort.setTripDetail(null);

		return vendorTripSheetEscort;
	}

	public List<VendorTripSheetParent> getVendorTripSheetParents() {
		return this.vendorTripSheetParents;
	}

	public void setVendorTripSheetParents(List<VendorTripSheetParent> vendorTripSheetParents) {
		this.vendorTripSheetParents = vendorTripSheetParents;
	}

	public VendorTripSheetParent addVendorTripSheetParent(VendorTripSheetParent vendorTripSheetParent) {
		getVendorTripSheetParents().add(vendorTripSheetParent);
		vendorTripSheetParent.setTripDetail(this);

		return vendorTripSheetParent;
	}

	public VendorTripSheetParent removeVendorTripSheetParent(VendorTripSheetParent vendorTripSheetParent) {
		getVendorTripSheetParents().remove(vendorTripSheetParent);
		vendorTripSheetParent.setTripDetail(null);

		return vendorTripSheetParent;
	}

}