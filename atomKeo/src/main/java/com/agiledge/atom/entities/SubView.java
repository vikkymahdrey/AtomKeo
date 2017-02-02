package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sub_views database table.
 * 
 */
@Entity
@Table(name="sub_views")
@NamedQuery(name="SubView.findAll", query="SELECT s FROM SubView s")
public class SubView implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="parent_id")
	private int parentId;

	@Column(name="show_order")
	private int showOrder;

	private String url;

	@Column(name="view_name")
	private String viewName;

	public SubView() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return this.parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getShowOrder() {
		return this.showOrder;
	}

	public void setShowOrder(int showOrder) {
		this.showOrder = showOrder;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getViewName() {
		return this.viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

}