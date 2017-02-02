package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="adhoctypes")
@NamedQuery(name="Adhoctype.findAll", query="SELECT a FROM Adhoctype a")
public class Adhoctype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String approval;

	private String cancelCutoff;

	private String existingCancel;

	private int maxrequestPerDay;

	private int maxrequestWithOutApproval;

	private String pickdrop;

	private String projectUnit;

	private String requestCutoff;

	private String requester;

	private String scheduleCancelMode;

	private String type;

	//bi-directional many-to-one association to Adhocapprovar
	@OneToMany(mappedBy="adhoctype")
	private List<Adhocapprovar> adhocapprovars;

	//bi-directional many-to-one association to Adhocrequester
	@OneToMany(mappedBy="adhoctype")
	private List<Adhocrequester> adhocrequesters;

	//bi-directional many-to-one association to Site
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="site")
	private Site siteBean;

	public Adhoctype() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApproval() {
		return this.approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

	public String getCancelCutoff() {
		return this.cancelCutoff;
	}

	public void setCancelCutoff(String cancelCutoff) {
		this.cancelCutoff = cancelCutoff;
	}

	public String getExistingCancel() {
		return this.existingCancel;
	}

	public void setExistingCancel(String existingCancel) {
		this.existingCancel = existingCancel;
	}

	public int getMaxrequestPerDay() {
		return this.maxrequestPerDay;
	}

	public void setMaxrequestPerDay(int maxrequestPerDay) {
		this.maxrequestPerDay = maxrequestPerDay;
	}

	public int getMaxrequestWithOutApproval() {
		return this.maxrequestWithOutApproval;
	}

	public void setMaxrequestWithOutApproval(int maxrequestWithOutApproval) {
		this.maxrequestWithOutApproval = maxrequestWithOutApproval;
	}

	public String getPickdrop() {
		return this.pickdrop;
	}

	public void setPickdrop(String pickdrop) {
		this.pickdrop = pickdrop;
	}

	public String getProjectUnit() {
		return this.projectUnit;
	}

	public void setProjectUnit(String projectUnit) {
		this.projectUnit = projectUnit;
	}

	public String getRequestCutoff() {
		return this.requestCutoff;
	}

	public void setRequestCutoff(String requestCutoff) {
		this.requestCutoff = requestCutoff;
	}

	public String getRequester() {
		return this.requester;
	}

	public void setRequester(String requester) {
		this.requester = requester;
	}

	public String getScheduleCancelMode() {
		return this.scheduleCancelMode;
	}

	public void setScheduleCancelMode(String scheduleCancelMode) {
		this.scheduleCancelMode = scheduleCancelMode;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Adhocapprovar> getAdhocapprovars() {
		return this.adhocapprovars;
	}

	public void setAdhocapprovars(List<Adhocapprovar> adhocapprovars) {
		this.adhocapprovars = adhocapprovars;
	}

	public Adhocapprovar addAdhocapprovar(Adhocapprovar adhocapprovar) {
		getAdhocapprovars().add(adhocapprovar);
		adhocapprovar.setAdhoctype(this);

		return adhocapprovar;
	}

	public Adhocapprovar removeAdhocapprovar(Adhocapprovar adhocapprovar) {
		getAdhocapprovars().remove(adhocapprovar);
		adhocapprovar.setAdhoctype(null);

		return adhocapprovar;
	}

	public List<Adhocrequester> getAdhocrequesters() {
		return this.adhocrequesters;
	}

	public void setAdhocrequesters(List<Adhocrequester> adhocrequesters) {
		this.adhocrequesters = adhocrequesters;
	}

	public Adhocrequester addAdhocrequester(Adhocrequester adhocrequester) {
		getAdhocrequesters().add(adhocrequester);
		adhocrequester.setAdhoctype(this);

		return adhocrequester;
	}

	public Adhocrequester removeAdhocrequester(Adhocrequester adhocrequester) {
		getAdhocrequesters().remove(adhocrequester);
		adhocrequester.setAdhoctype(null);

		return adhocrequester;
	}

	public Site getSiteBean() {
		return this.siteBean;
	}

	public void setSiteBean(Site siteBean) {
		this.siteBean = siteBean;
	}

}