package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the logtime database table.
 * 
 */
@Entity
@NamedQuery(name="Logtime.findAll", query="SELECT l FROM Logtime l")
public class Logtime implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String combroute;

	private int datedif;

	private String logtime;

	private String logtype;

	private String status;

	//bi-directional many-to-many association to Atsconnect
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		name="project_shifttime"
		, joinColumns={
			@JoinColumn(name="shifttime")
			}
		, inverseJoinColumns={
			@JoinColumn(name="project")
			}
		)
	private List<Atsconnect> atsconnects;

	
	//bi-directional many-to-one association to SiteShift
	@OneToMany(mappedBy="logtime")
	private List<SiteShift> siteShifts;

	//bi-directional many-to-one association to Siteshiftadhoc
	@OneToMany(mappedBy="logtime")
	private List<Siteshiftadhoc> siteshiftadhocs;

	public Logtime() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCombroute() {
		return this.combroute;
	}

	public void setCombroute(String combroute) {
		this.combroute = combroute;
	}

	public int getDatedif() {
		return this.datedif;
	}

	public void setDatedif(int datedif) {
		this.datedif = datedif;
	}

	public String getLogtime() {
		return this.logtime;
	}

	public void setLogtime(String logtime) {
		this.logtime = logtime;
	}

	public String getLogtype() {
		return this.logtype;
	}

	public void setLogtype(String logtype) {
		this.logtype = logtype;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Atsconnect> getAtsconnects() {
		return this.atsconnects;
	}

	public void setAtsconnects(List<Atsconnect> atsconnects) {
		this.atsconnects = atsconnects;
	}

	
	
	public List<SiteShift> getSiteShifts() {
		return this.siteShifts;
	}

	public void setSiteShifts(List<SiteShift> siteShifts) {
		this.siteShifts = siteShifts;
	}

	public SiteShift addSiteShift(SiteShift siteShift) {
		getSiteShifts().add(siteShift);
		siteShift.setLogtime(this);

		return siteShift;
	}

	public SiteShift removeSiteShift(SiteShift siteShift) {
		getSiteShifts().remove(siteShift);
		siteShift.setLogtime(null);

		return siteShift;
	}

	public List<Siteshiftadhoc> getSiteshiftadhocs() {
		return this.siteshiftadhocs;
	}

	public void setSiteshiftadhocs(List<Siteshiftadhoc> siteshiftadhocs) {
		this.siteshiftadhocs = siteshiftadhocs;
	}

	public Siteshiftadhoc addSiteshiftadhoc(Siteshiftadhoc siteshiftadhoc) {
		getSiteshiftadhocs().add(siteshiftadhoc);
		siteshiftadhoc.setLogtime(this);

		return siteshiftadhoc;
	}

	public Siteshiftadhoc removeSiteshiftadhoc(Siteshiftadhoc siteshiftadhoc) {
		getSiteshiftadhocs().remove(siteshiftadhoc);
		siteshiftadhoc.setLogtime(null);

		return siteshiftadhoc;
	}

}