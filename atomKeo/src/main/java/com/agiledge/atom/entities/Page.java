package com.agiledge.atom.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the page database table.
 * 
 */
@Entity
@NamedQuery(name="Page.findAll", query="SELECT p FROM Page p")
public class Page implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String type;

	private String url;

	//bi-directional many-to-one association to MenuUrl
	@OneToMany(mappedBy="page1")
	private List<MenuUrl> menuUrls1;

	//bi-directional many-to-one association to MenuUrl
	@OneToMany(mappedBy="page2")
	private List<MenuUrl> menuUrls2;

	//bi-directional many-to-one association to RolePage
	@OneToMany(mappedBy="pageBean")
	private List<RolePage> rolePages;

	
	//bi-directional many-to-one association to Role
	@OneToMany(mappedBy="page")
	private List<Role> roles;

	
	//bi-directional many-to-one association to View
	@OneToMany(mappedBy="page")
	private List<View> views;

	//bi-directional many-to-one association to UserHomePage
	@OneToMany(mappedBy="page")
	private List<UserHomePage> userHomePages;

	public Page() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<MenuUrl> getMenuUrls1() {
		return this.menuUrls1;
	}

	public void setMenuUrls1(List<MenuUrl> menuUrls1) {
		this.menuUrls1 = menuUrls1;
	}

	public MenuUrl addMenuUrls1(MenuUrl menuUrls1) {
		getMenuUrls1().add(menuUrls1);
		menuUrls1.setPage1(this);

		return menuUrls1;
	}

	public MenuUrl removeMenuUrls1(MenuUrl menuUrls1) {
		getMenuUrls1().remove(menuUrls1);
		menuUrls1.setPage1(null);

		return menuUrls1;
	}

	public List<MenuUrl> getMenuUrls2() {
		return this.menuUrls2;
	}

	public void setMenuUrls2(List<MenuUrl> menuUrls2) {
		this.menuUrls2 = menuUrls2;
	}

	public MenuUrl addMenuUrls2(MenuUrl menuUrls2) {
		getMenuUrls2().add(menuUrls2);
		menuUrls2.setPage2(this);

		return menuUrls2;
	}

	public MenuUrl removeMenuUrls2(MenuUrl menuUrls2) {
		getMenuUrls2().remove(menuUrls2);
		menuUrls2.setPage2(null);

		return menuUrls2;
	}

	public List<RolePage> getRolePages() {
		return this.rolePages;
	}

	public void setRolePages(List<RolePage> rolePages) {
		this.rolePages = rolePages;
	}

	public RolePage addRolePage(RolePage rolePage) {
		getRolePages().add(rolePage);
		rolePage.setPageBean(this);

		return rolePage;
	}

	public RolePage removeRolePage(RolePage rolePage) {
		getRolePages().remove(rolePage);
		rolePage.setPageBean(null);

		return rolePage;
	}

	
	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Role addRole(Role role) {
		getRoles().add(role);
		role.setPage(this);

		return role;
	}

	public Role removeRole(Role role) {
		getRoles().remove(role);
		role.setPage(null);

		return role;
	}

	
	public List<View> getViews() {
		return this.views;
	}

	public void setViews(List<View> views) {
		this.views = views;
	}

	public View addView(View view) {
		getViews().add(view);
		view.setPage(this);

		return view;
	}

	public View removeView(View view) {
		getViews().remove(view);
		view.setPage(null);

		return view;
	}

	public List<UserHomePage> getUserHomePages() {
		return this.userHomePages;
	}

	public void setUserHomePages(List<UserHomePage> userHomePages) {
		this.userHomePages = userHomePages;
	}

	public UserHomePage addUserHomePage(UserHomePage userHomePage) {
		getUserHomePages().add(userHomePage);
		userHomePage.setPage(this);

		return userHomePage;
	}

	public UserHomePage removeUserHomePage(UserHomePage userHomePage) {
		getUserHomePages().remove(userHomePage);
		userHomePage.setPage(null);

		return userHomePage;
	}

}