package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the unautherised_requests database table.
 * 
 */
@Entity
@Table(name="unautherised_requests")
@NamedQuery(name="UnautherisedRequest.findAll", query="SELECT u FROM UnautherisedRequest u")
public class UnautherisedRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private BigInteger employeeId;

	private String role;

	private int url;

	public UnautherisedRequest() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigInteger getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(BigInteger employeeId) {
		this.employeeId = employeeId;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getUrl() {
		return this.url;
	}

	public void setUrl(int url) {
		this.url = url;
	}

}