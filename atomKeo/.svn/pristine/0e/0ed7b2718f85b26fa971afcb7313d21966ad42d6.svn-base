package com.agiledge.atom.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the escalationmatrix database table.
 * 
 */
@Entity
@NamedQuery(name="Escalationmatrix.findAll", query="SELECT e FROM Escalationmatrix e")
public class Escalationmatrix implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String escalationGroup;

	private String level;

	private String timeSlot;

	//bi-directional many-to-one association to Employee
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="personId")
	private Employee employee;

	public Escalationmatrix() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEscalationGroup() {
		return this.escalationGroup;
	}

	public void setEscalationGroup(String escalationGroup) {
		this.escalationGroup = escalationGroup;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getTimeSlot() {
		return this.timeSlot;
	}

	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}