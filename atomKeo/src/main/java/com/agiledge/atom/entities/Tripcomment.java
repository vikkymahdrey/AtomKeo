package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tripcomments database table.
 * 
 */
@Entity
@Table(name="tripcomments")
@NamedQuery(name="Tripcomment.findAll", query="SELECT t FROM Tripcomment t")
public class Tripcomment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String comment;

	private int commentedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private String tripCommentscol;

	//bi-directional many-to-one association to TripDetail
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tripId")
	private TripDetail tripDetail;

	public Tripcomment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getCommentedBy() {
		return this.commentedBy;
	}

	public void setCommentedBy(int commentedBy) {
		this.commentedBy = commentedBy;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTripCommentscol() {
		return this.tripCommentscol;
	}

	public void setTripCommentscol(String tripCommentscol) {
		this.tripCommentscol = tripCommentscol;
	}

	public TripDetail getTripDetail() {
		return this.tripDetail;
	}

	public void setTripDetail(TripDetail tripDetail) {
		this.tripDetail = tripDetail;
	}

}