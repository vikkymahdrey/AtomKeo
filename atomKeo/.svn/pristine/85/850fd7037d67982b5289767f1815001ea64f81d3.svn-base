package com.agiledge.atom.service.intfc;

import java.util.List;

import com.agiledge.atom.entities.Page;
import com.agiledge.atom.entities.Role;
import com.agiledge.atom.entities.View;

public interface ViewManagementService {
	
	public List<Page> getPagesByType(String pageType) throws Exception;
	public List<Page> getAllPages() throws Exception;
	public Page addNewPage(Page newPage) throws Exception;
	public Page getPageById(String pageId) throws Exception;
	public Page updatePage(Page page) throws Exception;
	public List<Role> getSystemUserRoles() throws Exception;
	public Role addNewRole(Role role) throws Exception;
	public Role getRoleById(String string) throws Exception;
	public Role updateRole(Role role) throws Exception;
	public List<View> getSubviewsbyView(String viewId) throws Exception;
	public List<View> roleViewExisting(String roleId) throws Exception;
	public List<View> getViewsbyRole(String roleId) throws Exception;
}
