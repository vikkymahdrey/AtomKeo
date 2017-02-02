package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the general_shift database table.
 * 
 */
@Entity
@Table(name="general_shift")
@NamedQuery(name="GeneralShift.findAll", query="SELECT g FROM GeneralShift g")
public class GeneralShift implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	@Column(name="config_id")
	private BigInteger configId;

	private String logtime;

	private String logtype;

	private String status;

	public GeneralShift() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigInteger getConfigId() {
		return this.configId;
	}

	public void setConfigId(BigInteger configId) {
		this.configId = configId;
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

}