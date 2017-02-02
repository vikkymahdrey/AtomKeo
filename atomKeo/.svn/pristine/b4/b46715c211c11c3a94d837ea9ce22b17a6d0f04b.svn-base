package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the deduction database table.
 * 
 */
@Entity
@NamedQuery(name="Deduction.findAll", query="SELECT d FROM Deduction d")
public class Deduction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String status;

	private String type;

	public Deduction() {
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

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}