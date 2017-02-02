package com.agiledge.atom.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.agiledge.atom.dao.intfc.ViewManagementDao;
import com.agiledge.atom.dao.intfc.ViewManagementStatus;
import com.agiledge.atom.entities.Page;
import com.agiledge.atom.entities.Role;
import com.agiledge.atom.entities.View;

@Repository("viewManagementDao")
public class ViewManagementDaoImpl extends AbstractDao implements ViewManagementDao{

	public List<Page> getPagesByType(String pageType) throws Exception {
		Query query = getEntityManager().createQuery("from Page p where p.type =:pageType");
		query.setParameter("pageType", pageType);
		return query.getResultList();
	}

	public List<Page> getAllPAges() throws Exception {
		Query query = getEntityManager().createQuery("from Page p");
		
		return query.getResultList();
	}

	public Page addNewPage(Page newPage) throws Exception {
		Query query = getEntityManager().createQuery("from Page p where p.url =:pageUrl");
		query.setParameter("pageUrl", newPage.getUrl());
		if(query.getResultList() == null || query.getResultList().size() == 0){
			persist(newPage);
			return newPage;
		}
		return null;
	}

	public Page getPageById(String pageId) throws Exception {
		Query query = getEntityManager().createQuery("from Page p where p.id =:pageId");
		query.setParameter("pageId", Integer.parseInt(pageId));
		return (Page) query.getResultList().get(0);
	}

	public Page updatePage(Page page) throws Exception {
		update(page);
		return page;
	}

	public List<View> getSubviewsbyView(String viewId) throws Exception {
			Query query = getEntityManager().createQuery("from View v where v.parentId =:viewId order by showOrder*1");
				query.setParameter("viewId", new BigInteger(viewId));
					return (List<View>)query.getResultList();
	
	}

	public List<View> roleViewExisting(String roleId) throws Exception {
		/*String query = "SELECT v.*, p.url FROM VIEWS v join page p on v.pageId=p.id where viewKey not in(SELECT viewKey FROM VIEWS WHERE id IN (SELECT view_id FROM roles_views_association WHERE role_id="
				+ roleId + ")) and v.type='" + ViewManagementStatus.ROOT + "'";*/
		List<View> vList=new ArrayList<View>(); 
		String query="from View v where v.type='" + ViewManagementStatus.ROOT + "'";
		Query q=getEntityManager().createQuery(query);
		List<View> viewList=q.getResultList();
		for(View v : viewList){
			for(Role r : v.getRoles()){
				if(r.getId()==Integer.parseInt(roleId)){
					if(!v.getViewKey().equalsIgnoreCase(r.getViews().get(0).getViewKey())){
						vList.add(v);
					}
				}
			
			}
		}	
			
		return vList;
	}

	public List<View> getViewsbyRole(String roleId) throws Exception {
		/*String query = "SELECT distinct rva.id rvaid, v.*, p.url FROM VIEWS v join page p on v.pageid=p.id join roles_views_association  rva on rva.view_id=v.id  WHERE v.type='" + ViewManagementStatus.ROOT + "'  and rva.role_id=" + roleId
				+ " ORDER BY rva.id ";*/
		List<View> vList=new ArrayList<View>(); 
		String query="from View v where v.type='" + ViewManagementStatus.ROOT + "'";
		Query q=getEntityManager().createQuery(query);
		List<View> viewList=q.getResultList();
		for(View v:viewList){
			for(Role r : v.getRoles()){
				if(r.getId()==Integer.parseInt(roleId)){
					vList.add(v);
				}
			}
		}
			return vList;
	}

}
