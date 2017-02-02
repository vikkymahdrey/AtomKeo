package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;


/**
 * The persistent class for the adhoctripvendorassign database table.
 * 
 */
@Entity
@NamedQuery(name="Adhoctripvendorassign.findAll", query="SELECT a FROM Adhoctripvendorassign a")
public class Adhoctripvendorassign implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String status;

	private BigInteger tripid;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;

	private int vendorId;

	public Adhoctripvendorassign() {
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

	public BigInteger getTripid() {
		return this.tripid;
	}

	public void setTripid(BigInteger tripid) {
		this.tripid = tripid;
	}

	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public int getVendorId() {
		return this.vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

}