package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the vendor_trip_sheet_escort database table.
 * 
 */
@Entity
@Table(name="vendor_trip_sheet_escort")
@NamedQuery(name="VendorTripSheetEscort.findAll", query="SELECT v FROM VendorTripSheetEscort v")
public class VendorTripSheetEscort implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String curStatus;

	private double inLatitude;

	private double inLongitude;

	private String inTime;

	private double outLatitude;

	private double outLongitude;

	private String outTime;

	private String showStatus;

	//bi-directional many-to-one association to Escort
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="escortId")
	private Escort escort;

	//bi-directional many-to-one association to TripDetail
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tripId")
	private TripDetail tripDetail;

	public VendorTripSheetEscort() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCurStatus() {
		return this.curStatus;
	}

	public void setCurStatus(String curStatus) {
		this.curStatus = curStatus;
	}

	public double getInLatitude() {
		return this.inLatitude;
	}

	public void setInLatitude(double inLatitude) {
		this.inLatitude = inLatitude;
	}

	public double getInLongitude() {
		return this.inLongitude;
	}

	public void setInLongitude(double inLongitude) {
		this.inLongitude = inLongitude;
	}

	public String getInTime() {
		return this.inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public double getOutLatitude() {
		return this.outLatitude;
	}

	public void setOutLatitude(double outLatitude) {
		this.outLatitude = outLatitude;
	}

	public double getOutLongitude() {
		return this.outLongitude;
	}

	public void setOutLongitude(double outLongitude) {
		this.outLongitude = outLongitude;
	}

	public String getOutTime() {
		return this.outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	public String getShowStatus() {
		return this.showStatus;
	}

	public void setShowStatus(String showStatus) {
		this.showStatus = showStatus;
	}

	public Escort getEscort() {
		return this.escort;
	}

	public void setEscort(Escort escort) {
		this.escort = escort;
	}

	public TripDetail getTripDetail() {
		return this.tripDetail;
	}

	public void setTripDetail(TripDetail tripDetail) {
		this.tripDetail = tripDetail;
	}

}