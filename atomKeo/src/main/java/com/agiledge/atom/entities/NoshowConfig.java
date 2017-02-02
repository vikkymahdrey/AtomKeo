package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the noshow_config database table.
 * 
 */
@Entity
@Table(name="noshow_config")
@NamedQuery(name="NoshowConfig.findAll", query="SELECT n FROM NoshowConfig n")
public class NoshowConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private BigInteger count;

	private BigInteger days;

	private String type;

	public NoshowConfig() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigInteger getCount() {
		return this.count;
	}

	public void setCount(BigInteger count) {
		this.count = count;
	}

	public BigInteger getDays() {
		return this.days;
	}

	public void setDays(BigInteger days) {
		this.days = days;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}