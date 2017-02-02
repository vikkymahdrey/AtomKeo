package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the newplace database table.
 * 
 */
@Entity
@NamedQuery(name="Newplace.findAll", query="SELECT n FROM Newplace n")
public class Newplace implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String place;

	//bi-directional many-to-one association to Area
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="areal")
	private Area area;

	public Newplace() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Area getArea() {
		return this.area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

}