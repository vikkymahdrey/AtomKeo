package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the spoc_parent database table.
 * 
 */
@Entity
@Table(name="spoc_parent")
@NamedQuery(name="SpocParent.findAll", query="SELECT s FROM SpocParent s")
public class SpocParent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="from_date")
	private Date fromDate;

	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="to_date")
	private Date toDate;

	//bi-directional many-to-one association to SpocChild
	@OneToMany(mappedBy="spocParent")
	private List<SpocChild> spocChilds;

	//bi-directional many-to-one association to Employee
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employee_id")
	private Employee employee1;

	//bi-directional many-to-one association to Employee
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="manager_id")
	private Employee employee2;

	public SpocParent() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getToDate() {
		return this.toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public List<SpocChild> getSpocChilds() {
		return this.spocChilds;
	}

	public void setSpocChilds(List<SpocChild> spocChilds) {
		this.spocChilds = spocChilds;
	}

	public SpocChild addSpocChild(SpocChild spocChild) {
		getSpocChilds().add(spocChild);
		spocChild.setSpocParent(this);

		return spocChild;
	}

	public SpocChild removeSpocChild(SpocChild spocChild) {
		getSpocChilds().remove(spocChild);
		spocChild.setSpocParent(null);

		return spocChild;
	}

	public Employee getEmployee1() {
		return this.employee1;
	}

	public void setEmployee1(Employee employee1) {
		this.employee1 = employee1;
	}

	public Employee getEmployee2() {
		return this.employee2;
	}

	public void setEmployee2(Employee employee2) {
		this.employee2 = employee2;
	}

}