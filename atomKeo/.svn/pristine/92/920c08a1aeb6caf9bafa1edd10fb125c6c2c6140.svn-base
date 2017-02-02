package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the adhocrequester database table.
 * 
 */
@Entity
@NamedQuery(name="Adhocrequester.findAll", query="SELECT a FROM Adhocrequester a")
public class Adhocrequester implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String requesterUsertype;

	private String status;

	//bi-directional many-to-one association to Adhoctype
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="adhocTypeId")
	private Adhoctype adhoctype;

	//bi-directional many-to-one association to Role
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="requesterRoleId")
	private Role role;

	public Adhocrequester() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRequesterUsertype() {
		return this.requesterUsertype;
	}

	public void setRequesterUsertype(String requesterUsertype) {
		this.requesterUsertype = requesterUsertype;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Adhoctype getAdhoctype() {
		return this.adhoctype;
	}

	public void setAdhoctype(Adhoctype adhoctype) {
		this.adhoctype = adhoctype;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}