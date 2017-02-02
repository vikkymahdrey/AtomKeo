package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the adhocapprovar database table.
 * 
 */
@Entity
@NamedQuery(name="Adhocapprovar.findAll", query="SELECT a FROM Adhocapprovar a")
public class Adhocapprovar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String approvarusertype;

	private String status;

	//bi-directional many-to-one association to Adhoctype
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="adhoctypeId")
	private Adhoctype adhoctype;

	//bi-directional many-to-one association to Role
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="approvarRoleId")
	private Role role;

	public Adhocapprovar() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApprovarusertype() {
		return this.approvarusertype;
	}

	public void setApprovarusertype(String approvarusertype) {
		this.approvarusertype = approvarusertype;
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