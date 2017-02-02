package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the views database table.
 * 
 */
@Entity
@Table(name="views")
@NamedQuery(name="View.findAll", query="SELECT v FROM View v")
public class View implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="parent_id")
	private BigInteger parentId;

	@Column(name="show_order")
	private String showOrder;

	private String type;

	@Column(name="view_name")
	private String viewName;

	private String viewKey;

	
	//bi-directional many-to-one association to Page
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="pageId")
	private Page page;

	//bi-directional many-to-many association to Role
	@ManyToMany
	@JoinTable(
		name="roles_views_association"
		, joinColumns={
			@JoinColumn(name="view_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="role_id")
			}
		)
	private List<Role> roles;

	public View() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigInteger getParentId() {
		return this.parentId;
	}

	public void setParentId(BigInteger parentId) {
		this.parentId = parentId;
	}

	public String getShowOrder() {
		return this.showOrder;
	}

	public void setShowOrder(String showOrder) {
		this.showOrder = showOrder;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getViewName() {
		return this.viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public String getViewKey() {
		return this.viewKey;
	}

	public void setViewKey(String viewKey) {
		this.viewKey = viewKey;
	}

	
	public Page getPage() {
		return this.page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}