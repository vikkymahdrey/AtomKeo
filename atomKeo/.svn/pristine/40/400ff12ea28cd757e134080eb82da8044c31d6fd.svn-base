package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="roles")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String description;

	private String name;

	private String type;

	private String usertype;

	//bi-directional many-to-one association to AccessRightRole
	@OneToMany(mappedBy="roleBean")
	private List<AccessRightRole> accessRightRoles;

	//bi-directional many-to-one association to Adhocapprovar
	@OneToMany(mappedBy="role")
	private List<Adhocapprovar> adhocapprovars;

	//bi-directional many-to-one association to Adhocrequester
	@OneToMany(mappedBy="role")
	private List<Adhocrequester> adhocrequesters;

	//bi-directional many-to-one association to Employee
	@OneToMany(mappedBy="role")
	private List<Employee> employees;

	
	//bi-directional many-to-one association to Page
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="homepage_url")
	private Page page;

	//bi-directional many-to-many association to View
	@ManyToMany(mappedBy="roles")
	private List<View> views;

	public Role() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsertype() {
		return this.usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public List<AccessRightRole> getAccessRightRoles() {
		return this.accessRightRoles;
	}

	public void setAccessRightRoles(List<AccessRightRole> accessRightRoles) {
		this.accessRightRoles = accessRightRoles;
	}

	public AccessRightRole addAccessRightRole(AccessRightRole accessRightRole) {
		getAccessRightRoles().add(accessRightRole);
		accessRightRole.setRoleBean(this);

		return accessRightRole;
	}

	public AccessRightRole removeAccessRightRole(AccessRightRole accessRightRole) {
		getAccessRightRoles().remove(accessRightRole);
		accessRightRole.setRoleBean(null);

		return accessRightRole;
	}

	public List<Adhocapprovar> getAdhocapprovars() {
		return this.adhocapprovars;
	}

	public void setAdhocapprovars(List<Adhocapprovar> adhocapprovars) {
		this.adhocapprovars = adhocapprovars;
	}

	public Adhocapprovar addAdhocapprovar(Adhocapprovar adhocapprovar) {
		getAdhocapprovars().add(adhocapprovar);
		adhocapprovar.setRole(this);

		return adhocapprovar;
	}

	public Adhocapprovar removeAdhocapprovar(Adhocapprovar adhocapprovar) {
		getAdhocapprovars().remove(adhocapprovar);
		adhocapprovar.setRole(null);

		return adhocapprovar;
	}

	public List<Adhocrequester> getAdhocrequesters() {
		return this.adhocrequesters;
	}

	public void setAdhocrequesters(List<Adhocrequester> adhocrequesters) {
		this.adhocrequesters = adhocrequesters;
	}

	public Adhocrequester addAdhocrequester(Adhocrequester adhocrequester) {
		getAdhocrequesters().add(adhocrequester);
		adhocrequester.setRole(this);

		return adhocrequester;
	}

	public Adhocrequester removeAdhocrequester(Adhocrequester adhocrequester) {
		getAdhocrequesters().remove(adhocrequester);
		adhocrequester.setRole(null);

		return adhocrequester;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee addEmployee(Employee employee) {
		getEmployees().add(employee);
		employee.setRole(this);

		return employee;
	}

	public Employee removeEmployee(Employee employee) {
		getEmployees().remove(employee);
		employee.setRole(null);

		return employee;
	}


	public Page getPage() {
		return this.page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<View> getViews() {
		return this.views;
	}

	public void setViews(List<View> views) {
		this.views = views;
	}

}