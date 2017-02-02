package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the employee_unsubscription database table.
 * 
 */
@Entity
@Table(name="employee_unsubscription")
@NamedQuery(name="EmployeeUnsubscription.findAll", query="SELECT e FROM EmployeeUnsubscription e")
public class EmployeeUnsubscription implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String subscriptionID;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fromDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date unsubscriptionDate;

	public EmployeeUnsubscription() {
	}

	public String getSubscriptionID() {
		return this.subscriptionID;
	}

	public void setSubscriptionID(String subscriptionID) {
		this.subscriptionID = subscriptionID;
	}

	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getUnsubscriptionDate() {
		return this.unsubscriptionDate;
	}

	public void setUnsubscriptionDate(Date unsubscriptionDate) {
		this.unsubscriptionDate = unsubscriptionDate;
	}

}