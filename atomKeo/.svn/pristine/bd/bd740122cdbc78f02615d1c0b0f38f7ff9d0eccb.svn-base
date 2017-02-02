package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the audit_log database table.
 * 
 */
@Entity
@Table(name="audit_log")
@NamedQuery(name="AuditLog.findAll", query="SELECT a FROM AuditLog a")
public class AuditLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String action;

	@Column(name="current_state")
	private String currentState;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_changed")
	private Date dateChanged;

	@Column(name="module_name")
	private String moduleName;

	@Column(name="previous_state")
	private String previousState;

	@Column(name="reference_id")
	private int referenceId;

	//bi-directional many-to-one association to Employee
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="changed_by")
	private Employee employee;

	public AuditLog() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getCurrentState() {
		return this.currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	public Date getDateChanged() {
		return this.dateChanged;
	}

	public void setDateChanged(Date dateChanged) {
		this.dateChanged = dateChanged;
	}

	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getPreviousState() {
		return this.previousState;
	}

	public void setPreviousState(String previousState) {
		this.previousState = previousState;
	}

	public int getReferenceId() {
		return this.referenceId;
	}

	public void setReferenceId(int referenceId) {
		this.referenceId = referenceId;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}