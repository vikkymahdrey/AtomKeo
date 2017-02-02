package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the vendor_contract database table.
 * 
 */
@Entity
@Table(name="vendor_contract")
@NamedQuery(name="VendorContract.findAll", query="SELECT v FROM VendorContract v")
public class VendorContract implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String vendor;

	private String description;

	private BigInteger rate;

	public VendorContract() {
	}

	public String getVendor() {
		return this.vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigInteger getRate() {
		return this.rate;
	}

	public void setRate(BigInteger rate) {
		this.rate = rate;
	}

}