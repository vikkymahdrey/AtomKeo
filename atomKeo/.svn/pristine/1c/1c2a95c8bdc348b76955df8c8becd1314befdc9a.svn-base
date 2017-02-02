package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the newlandmark database table.
 * 
 */
@Entity
@NamedQuery(name="Newlandmark.findAll", query="SELECT n FROM Newlandmark n")
public class Newlandmark implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String landmark;

	//bi-directional many-to-one association to Place
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="place")
	private Place placeBean;

	public Newlandmark() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLandmark() {
		return this.landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public Place getPlaceBean() {
		return this.placeBean;
	}

	public void setPlaceBean(Place placeBean) {
		this.placeBean = placeBean;
	}

}