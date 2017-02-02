package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the site_project_settings database table.
 * 
 */
@Entity
@Table(name="site_project_settings")
@NamedQuery(name="SiteProjectSetting.findAll", query="SELECT s FROM SiteProjectSetting s")
public class SiteProjectSetting implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private BigInteger project;

	private BigInteger settingsId;

	private int site;

	public SiteProjectSetting() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigInteger getProject() {
		return this.project;
	}

	public void setProject(BigInteger project) {
		this.project = project;
	}

	public BigInteger getSettingsId() {
		return this.settingsId;
	}

	public void setSettingsId(BigInteger settingsId) {
		this.settingsId = settingsId;
	}

	public int getSite() {
		return this.site;
	}

	public void setSite(int site) {
		this.site = site;
	}

}