package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the vendor_site database table.
 * 
 */
@Entity
@Table(name="vendor_site")
@NamedQuery(name="VendorSite.findAll", query="SELECT v FROM VendorSite v")
public class VendorSite implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private BigInteger site;

	private BigInteger vendor;

	public VendorSite() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigInteger getSite() {
		return this.site;
	}

	public void setSite(BigInteger site) {
		this.site = site;
	}

	public BigInteger getVendor() {
		return this.vendor;
	}

	public void setVendor(BigInteger vendor) {
		this.vendor = vendor;
	}

}