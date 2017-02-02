package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;


/**
 * The persistent class for the general_shift_config database table.
 * 
 */
@Entity
@Table(name="general_shift_config")
@NamedQuery(name="GeneralShiftConfig.findAll", query="SELECT g FROM GeneralShiftConfig g")
public class GeneralShiftConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	@Column(name="approval_req")
	private String approvalReq;

	@Column(name="approved_by")
	private String approvedBy;

	@Column(name="cutoff_cancel")
	private String cutoffCancel;

	@Column(name="cutoff_waitlist")
	private BigInteger cutoffWaitlist;

	private int cutoffdays;

	private String deduction;

	@Column(name="deduction_amt")
	private BigInteger deductionAmt;

	@Column(name="deduction_type")
	private BigInteger deductionType;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="from_date")
	private Date fromDate;

	@Column(name="site_id")
	private BigInteger siteId;

	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="to_date")
	private Date toDate;

	@Column(name="waitlist_reconf")
	private String waitlistReconf;

	public GeneralShiftConfig() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApprovalReq() {
		return this.approvalReq;
	}

	public void setApprovalReq(String approvalReq) {
		this.approvalReq = approvalReq;
	}

	public String getApprovedBy() {
		return this.approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getCutoffCancel() {
		return this.cutoffCancel;
	}

	public void setCutoffCancel(String cutoffCancel) {
		this.cutoffCancel = cutoffCancel;
	}

	public BigInteger getCutoffWaitlist() {
		return this.cutoffWaitlist;
	}

	public void setCutoffWaitlist(BigInteger cutoffWaitlist) {
		this.cutoffWaitlist = cutoffWaitlist;
	}

	public int getCutoffdays() {
		return this.cutoffdays;
	}

	public void setCutoffdays(int cutoffdays) {
		this.cutoffdays = cutoffdays;
	}

	public String getDeduction() {
		return this.deduction;
	}

	public void setDeduction(String deduction) {
		this.deduction = deduction;
	}

	public BigInteger getDeductionAmt() {
		return this.deductionAmt;
	}

	public void setDeductionAmt(BigInteger deductionAmt) {
		this.deductionAmt = deductionAmt;
	}

	public BigInteger getDeductionType() {
		return this.deductionType;
	}

	public void setDeductionType(BigInteger deductionType) {
		this.deductionType = deductionType;
	}

	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public BigInteger getSiteId() {
		return this.siteId;
	}

	public void setSiteId(BigInteger siteId) {
		this.siteId = siteId;
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

	public String getWaitlistReconf() {
		return this.waitlistReconf;
	}

	public void setWaitlistReconf(String waitlistReconf) {
		this.waitlistReconf = waitlistReconf;
	}

}