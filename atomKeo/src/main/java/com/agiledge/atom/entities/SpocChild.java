package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the spoc_child database table.
 * 
 */
@Entity
@Table(name="spoc_child")
@NamedQuery(name="SpocChild.findAll", query="SELECT s FROM SpocChild s")
public class SpocChild implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String status;

	//bi-directional many-to-one association to Employee
	@ManyToOne(fetch=FetchType.LAZY)
	private Employee employee;

	//bi-directional many-to-one association to SpocParent
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="spoc_id")
	private SpocParent spocParent;

	public SpocChild() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public SpocParent getSpocParent() {
		return this.spocParent;
	}

	public void setSpocParent(SpocParent spocParent) {
		this.spocParent = spocParent;
	}

}