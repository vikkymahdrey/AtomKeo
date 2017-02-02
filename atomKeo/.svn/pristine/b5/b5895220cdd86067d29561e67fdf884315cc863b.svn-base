package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the site_shift database table.
 * 
 */
@Entity
@Table(name="site_shift")
@NamedQuery(name="SiteShift.findAll", query="SELECT s FROM SiteShift s")
public class SiteShift implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String combainroute;

	private String status;

	//bi-directional many-to-one association to Logtime
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="shiftId")
	private Logtime logtime;

	//bi-directional many-to-one association to Site
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="siteId")
	private Site site;

	public SiteShift() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCombainroute() {
		return this.combainroute;
	}

	public void setCombainroute(String combainroute) {
		this.combainroute = combainroute;
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