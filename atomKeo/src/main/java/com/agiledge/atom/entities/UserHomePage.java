package com.agiledge.atom.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;


/**
 * The persistent class for the user_home_page database table.
 * 
 */
@Entity
@Table(name="user_home_page")
@NamedQuery(name="UserHomePage.findAll", query="SELECT u FROM UserHomePage u")
public class UserHomePage implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String role;

	//bi-directional many-to-one association to Page
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="url")
	private Page page;

	public UserHomePage() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Page getPage() {
		return this.page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

}