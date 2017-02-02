package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the transport_type_mapping database table.
 * 
 */
@Entity
@Table(name="transport_type_mapping")
@NamedQuery(name="TransportTypeMapping.findAll", query="SELECT t FROM TransportTypeMapping t")
public class TransportTypeMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fromDate;

	private int site;

	@Temporal(TemporalType.TIMESTAMP)
	private Date toDate;

	@Column(name="tran_type")
	private int tranType;

	private int vendor;

	public TransportTypeMapping() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public int getSite() {
		return this.site;
	}

	public void setSite(int site) {
		this.site = site;
	}

	public Date getToDate() {
		return this.toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public int getTranType() {
		return this.tranType;
	}

	public void setTranType(int tranType) {
		this.tranType = tranType;
	}

	public int getVendor() {
		return this.vendor;
	}

	public void setVendor(int vendor) {
		this.vendor = vendor;
	}

}