package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;


/**
 * The persistent class for the registertransport database table.
 * 
 */
@Entity
@NamedQuery(name="Registertransport.findAll", query="SELECT r FROM Registertransport r")
public class Registertransport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private BigInteger employeeId;

	private BigInteger landmark;

	@Temporal(TemporalType.TIMESTAMP)
	private Date reguestedDate;

	private String status;

	private String transportType;

	public Registertransport() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigInteger getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(BigInteger employeeId) {
		this.employeeId = employeeId;
	}

	public BigInteger getLandmark() {
		return this.landmark;
	}

	public void setLandmark(BigInteger landmark) {
		this.landmark = landmark;
	}

	public Date getReguestedDate() {
		return this.reguestedDate;
	}

	public void setReguestedDate(Date reguestedDate) {
		this.reguestedDate = reguestedDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTransportType() {
		return this.transportType;
	}

	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}

}