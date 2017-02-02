package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the km_based_template database table.
 * 
 */
@Entity
@Table(name="km_based_template")
@NamedQuery(name="KmBasedTemplate.findAll", query="SELECT k FROM KmBasedTemplate k")
public class KmBasedTemplate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	@Column(name="km_billing_id")
	private BigInteger kmBillingId;

	private String name;

	private int site;

	private String status;

	public KmBasedTemplate() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigInteger getKmBillingId() {
		return this.kmBillingId;
	}

	public void setKmBillingId(BigInteger kmBillingId) {
		this.kmBillingId = kmBillingId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSite() {
		return this.site;
	}

	public void setSite(int site) {
		this.site = site;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}