package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the km_based_template_child database table.
 * 
 */
@Entity
@Table(name="km_based_template_child")
@NamedQuery(name="KmBasedTemplateChild.findAll", query="SELECT k FROM KmBasedTemplateChild k")
public class KmBasedTemplateChild implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private float distance;

	private BigInteger landmark;

	private String status;

	@Column(name="template_id")
	private BigInteger templateId;

	public KmBasedTemplateChild() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getDistance() {
		return this.distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public BigInteger getLandmark() {
		return this.landmark;
	}

	public void setLandmark(BigInteger landmark) {
		this.landmark = landmark;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigInteger getTemplateId() {
		return this.templateId;
	}

	public void setTemplateId(BigInteger templateId) {
		this.templateId = templateId;
	}

}