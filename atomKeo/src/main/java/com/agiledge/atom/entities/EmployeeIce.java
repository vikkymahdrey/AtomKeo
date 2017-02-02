package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the employee_ice database table.
 * 
 */
@Entity
@Table(name="employee_ice")
@NamedQuery(name="EmployeeIce.findAll", query="SELECT e FROM EmployeeIce e")
public class EmployeeIce implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	@Column(name="contact_email1")
	private String contactEmail1;

	@Column(name="contact_email2")
	private String contactEmail2;

	@Column(name="contact_name1")
	private String contactName1;

	@Column(name="contact_name2")
	private String contactName2;

	@Column(name="contact_number1")
	private String contactNumber1;

	@Column(name="contact_number2")
	private String contactNumber2;

	private BigInteger empid;

	private String status;

	public EmployeeIce() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContactEmail1() {
		return this.contactEmail1;
	}

	public void setContactEmail1(String contactEmail1) {
		this.contactEmail1 = contactEmail1;
	}

	public String getContactEmail2() {
		return this.contactEmail2;
	}

	public void setContactEmail2(String contactEmail2) {
		this.contactEmail2 = contactEmail2;
	}

	public String getContactName1() {
		return this.contactName1;
	}

	public void setContactName1(String contactName1) {
		this.contactName1 = contactName1;
	}

	public String getContactName2() {
		return this.contactName2;
	}

	public void setContactName2(String contactName2) {
		this.contactName2 = contactName2;
	}

	public String getContactNumber1() {
		return this.contactNumber1;
	}

	public void setContactNumber1(String contactNumber1) {
		this.contactNumber1 = contactNumber1;
	}

	public String getContactNumber2() {
		return this.contactNumber2;
	}

	public void setContactNumber2(String contactNumber2) {
		this.contactNumber2 = contactNumber2;
	}

	public BigInteger getEmpid() {
		return this.empid;
	}

	public void setEmpid(BigInteger empid) {
		this.empid = empid;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}