package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the empheirarchy database table.
 * 
 */
@Entity
@NamedQuery(name="Empheirarchy.findAll", query="SELECT e FROM Empheirarchy e")
public class Empheirarchy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String role;

	private String uniqkey;

	public Empheirarchy() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUniqkey() {
		return this.uniqkey;
	}

	public void setUniqkey(String uniqkey) {
		this.uniqkey = uniqkey;
	}

}