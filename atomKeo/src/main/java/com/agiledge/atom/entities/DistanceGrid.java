package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the distance_grid database table.
 * 
 */
@Entity
@Table(name="distance_grid")
@NamedQuery(name="DistanceGrid.findAll", query="SELECT d FROM DistanceGrid d")
public class DistanceGrid implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private double endKm;

	private double startKm;

	public DistanceGrid() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getEndKm() {
		return this.endKm;
	}

	public void setEndKm(double endKm) {
		this.endKm = endKm;
	}

	public double getStartKm() {
		return this.startKm;
	}

	public void setStartKm(double startKm) {
		this.startKm = startKm;
	}

}