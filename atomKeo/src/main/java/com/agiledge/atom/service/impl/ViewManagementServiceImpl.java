package com.agiledge.atom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agiledge.atom.dao.intfc.RoleDao;
import com.agiledge.atom.dao.intfc.ViewManagementDao;
import com.agiledge.atom.entities.Page;
import com.agiledge.atom.entities.Role;
import com.agiledge.atom.entities.View;
import com.agiledge.atom.service.intfc.ViewManagementService;

@Service("viewManagementService")
public class ViewManagementServiceImpl implements ViewManagementService{
	
	@Autowired
	private ViewManagementDao viewManagementDao;
	
	@Autowired
	private RoleDao roleDao;

	public List<Page> getPagesByType(String pageType) throws Exception {
		return viewManagementDao.getPagesByType(pageType);
	}

	public List<Page> getAllPages() throws Exception {
		return viewManagementDao.getAllPAges();
	}

	public Page addNewPage(Page newPage) throws Exception {
		return viewManagementDao.addNewPage(newPage);
	}

	public Page getPageById(String pageId) throws Exception {
		return viewManagementDao.getPageById(pageId);
	}

	public Page updatePage(Page page) throws Exception {
		return viewManagementDao.updatePage(page);
	}

	public List<Role> getSystemUserRoles() throws Exception {
		return roleDao.getAllRoles();
	}

	public Role addNewRole(Role role) throws Exception {
		return roleDao.addNewRole(role);
	}

	public Role getRoleById(String roleId) throws Exception {
		return roleDao.getRoleById(roleId);
	}

	public Role updateRole(Role role) throws Exception {
		return roleDao.updateRole(role);
	}

	public List<View> getSubviewsbyView(String viewId) throws Exception {
		return viewManagementDao.getSubviewsbyView(viewId);
	}

	public List<View> roleViewExisting(String roleId) throws Exception {
		return viewManagementDao.roleViewExisting(roleId);
	}

	public List<View> getViewsbyRole(String roleId) throws Exception {
		return viewManagementDao.getViewsbyRole(roleId);
	}

}
