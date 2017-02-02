package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tripvendorassign database table.
 * 
 */
@Entity
@NamedQuery(name="Tripvendorassign.findAll", query="SELECT t FROM Tripvendorassign t")
public class Tripvendorassign implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;

	//bi-directional one-to-one association to TripDetail
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tripId")
	private TripDetail tripDetail;

	//bi-directional many-to-one association to Vendormaster
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vendorId")
	private Vendormaster vendormaster;

	public Tripvendorassign() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public TripDetail getTripDetail() {
		return this.tripDetail;
	}

	public void setTripDetail(TripDetail tripDetail) {
		this.tripDetail = tripDetail;
	}

	public Vendormaster getVendormaster() {
		return this.vendormaster;
	}

	public void setVendormaster(Vendormaster vendormaster) {
		this.vendormaster = vendormaster;
	}

}