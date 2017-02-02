package com.agiledge.atom.dao.intfc;

import java.util.List;

import com.agiledge.atom.entities.Page;
import com.agiledge.atom.entities.View;

public interface ViewManagementDao {
	
	public List<Page> getPagesByType(String pageType) throws Exception;
	public List<Page> getAllPAges() throws Exception;
	public Page addNewPage(Page newPage) throws Exception;
	public Page getPageById(String pageId) throws Exception;
	public Page updatePage(Page page) throws Exception;
	public List<View> getSubviewsbyView(String viewId) throws Exception;
	public List<View> roleViewExisting(String roleId) throws Exception;
	public List<View> getViewsbyRole(String roleId) throws Exception;

}
