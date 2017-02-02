package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the role_page database table.
 * 
 */
@Entity
@Table(name="role_page")
@NamedQuery(name="RolePage.findAll", query="SELECT r FROM RolePage r")
public class RolePage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int ids;

	private String role;

	//bi-directional many-to-one association to Page
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="page")
	private Page pageBean;

	public RolePage() {
	}

	public int getIds() {
		return this.ids;
	}

	public void setIds(int ids) {
		this.ids = ids;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Page getPageBean() {
		return this.pageBean;
	}

	public void setPageBean(Page pageBean) {
		this.pageBean = pageBean;
	}

}