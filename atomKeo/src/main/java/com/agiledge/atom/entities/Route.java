package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the route database table.
 * 
 */
@Entity
@NamedQuery(name="Route.findAll", query="SELECT r FROM Route r")
public class Route implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String routeName;

	//bi-directional many-to-one association to Routetype
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="type")
	private Routetype routetype;

	//bi-directional many-to-one association to Site
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="siteId")
	private Site site;

	//bi-directional many-to-one association to Routechild
	@OneToMany(mappedBy="route")
	private List<Routechild> routechilds;

	//bi-directional many-to-one association to Routechildpriority
	@OneToMany(mappedBy="route")
	private List<Routechildpriority> routechildpriorities;

	public Route() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRouteName() {
		return this.routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public Routetype getRoutetype() {
		return this.routetype;
	}

	public void setRoutetype(Routetype routetype) {
		this.routetype = routetype;
	}

	public Site getSite() {
		return this.site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public List<Routechild> getRoutechilds() {
		return this.routechilds;
	}

	public void setRoutechilds(List<Routechild> routechilds) {
		this.routechilds = routechilds;
	}

	public Routechild addRoutechild(Routechild routechild) {
		getRoutechilds().add(routechild);
		routechild.setRoute(this);

		return routechild;
	}

	public Routechild removeRoutechild(Routechild routechild) {
		getRoutechilds().remove(routechild);
		routechild.setRoute(null);

		return routechild;
	}

	public List<Routechildpriority> getRoutechildpriorities() {
		return this.routechildpriorities;
	}

	public void setRoutechildpriorities(List<Routechildpriority> routechildpriorities) {
		this.routechildpriorities = routechildpriorities;
	}

	public Routechildpriority addRoutechildpriority(Routechildpriority routechildpriority) {
		getRoutechildpriorities().add(routechildpriority);
		routechildpriority.setRoute(this);

		return routechildpriority;
	}

	public Routechildpriority removeRoutechildpriority(Routechildpriority routechildpriority) {
		getRoutechildpriorities().remove(routechildpriority);
		routechildpriority.setRoute(null);

		return routechildpriority;
	}

}