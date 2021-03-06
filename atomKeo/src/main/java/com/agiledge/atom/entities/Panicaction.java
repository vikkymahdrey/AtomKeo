package com.agiledge.atom.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the panicaction database table.
 * 
 */
@Entity
@NamedQuery(name="Panicaction.findAll", query="SELECT p FROM Panicaction p")
public class Panicaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String acknowledgeBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date acknowledgeOn;

	private Timestamp actionTime;

	private String alarmCause;

	private String approvedBy;
	private String activatedBy;
	

	private String curStatus;

	private String primaryAction;

	private String primaryActiontakenBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date stopTime;

	//bi-directional many-to-one association to TripDetail
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tripId")
	private TripDetail tripDetail;

	
	public Panicaction() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAcknowledgeBy() {
		return this.acknowledgeBy;
	}

	public void setAcknowledgeBy(String acknowledgeBy) {
		this.acknowledgeBy = acknowledgeBy;
	}

	public Date getAcknowledgeOn() {
		return this.acknowledgeOn;
	}

	public void setAcknowledgeOn(Date acknowledgeOn) {
		this.acknowledgeOn = acknowledgeOn;
	}

	public Timestamp getActionTime() {
		return this.actionTime;
	}

	public void setActionTime(Timestamp actionTime) {
		this.actionTime = actionTime;
	}

	public String getAlarmCause() {
		return this.alarmCause;
	}

	public void setAlarmCause(String alarmCause) {
		this.alarmCause = alarmCause;
	}

	public String getApprovedBy() {
		return this.approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	public String getActivatedBy() {
		return this.activatedBy;
	}

	public void setActivatedBy(String activatedBy) {
		this.activatedBy = activatedBy;
	}
	public String getCurStatus() {
		return this.curStatus;
	}

	public void setCurStatus(String curStatus) {
		this.curStatus = curStatus;
	}

	public String getPrimaryAction() {
		return this.primaryAction;
	}

	public void setPrimaryAction(String primaryAction) {
		this.primaryAction = primaryAction;
	}

	public String getPrimaryActiontakenBy() {
		return this.primaryActiontakenBy;
	}

	public void setPrimaryActiontakenBy(String primaryActiontakenBy) {
		this.primaryActiontakenBy = primaryActiontakenBy;
	}

	public Date getStopTime() {
		return this.stopTime;
	}

	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}

	public TripDetail getTripDetail() {
		return this.tripDetail;
	}

	public void setTripDetail(TripDetail tripDetail) {
		this.tripDetail = tripDetail;
	}

}