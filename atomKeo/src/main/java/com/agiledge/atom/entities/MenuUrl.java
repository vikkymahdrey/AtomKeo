package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the menu_url database table.
 * 
 */
@Entity
@Table(name="menu_url")
@NamedQuery(name="MenuUrl.findAll", query="SELECT m FROM MenuUrl m")
public class MenuUrl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String type;

	//bi-directional many-to-one association to Page
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="menuKey")
	private Page page1;

	//bi-directional many-to-one association to Page
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="url")
	private Page page2;

	public MenuUrl() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Page getPage1() {
		return this.page1;
	}

	public void setPage1(Page page1) {
		this.page1 = page1;
	}

	public Page getPage2() {
		return this.page2;
	}

	public void setPage2(Page page2) {
		this.page2 = page2;
	}

}