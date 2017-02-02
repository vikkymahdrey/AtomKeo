package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the siteshiftadhoc database table.
 * 
 */
@Entity
@NamedQuery(name="Siteshiftadhoc.findAll", query="SELECT s FROM Siteshiftadhoc s")
public class Siteshiftadhoc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String status;

	//bi-directional many-to-one association to Logtime
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="shiftId")
	private Logtime logtime;

	//bi-directional many-to-one association to Site
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="siteId")
	private Site site;

	public Siteshiftadhoc() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Logtime getLogtime() {
		return this.logtime;
	}

	public void setLogtime(Logtime logtime) {
		this.logtime = logtime;
	}

	public Site getSite() {
		return this.site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

}