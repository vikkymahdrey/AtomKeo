package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the settingsstring database table.
 * 
 */
@Entity
@NamedQuery(name="Settingsstring.findAll", query="SELECT s FROM Settingsstring s")
public class Settingsstring implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String keyVal;

	private String module;

	private String status;

	private String submodule;

	private String val;

	public Settingsstring() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKeyVal() {
		return this.keyVal;
	}

	public void setKeyVal(String keyVal) {
		this.keyVal = keyVal;
	}

	public String getModule() {
		return this.module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubmodule() {
		return this.submodule;
	}

	public void setSubmodule(String submodule) {
		this.submodule = submodule;
	}

	public String getVal() {
		return this.val;
	}

	public void setVal(String val) {
		this.val = val;
	}

}