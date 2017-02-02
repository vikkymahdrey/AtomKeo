package com.agiledge.atom.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.agiledge.atom.dao.intfc.LoadSettingsDao;
import com.agiledge.atom.dao.intfc.ViewManagementStatus;
import com.agiledge.atom.dto.PageDto;
import com.agiledge.atom.dto.ViewManagementDto;
import com.agiledge.atom.entities.MenuUrl;
import com.agiledge.atom.entities.Page;
import com.agiledge.atom.entities.Role;
import com.agiledge.atom.entities.RolePage;
import com.agiledge.atom.entities.UserHomePage;
import com.agiledge.atom.entities.View;
@Repository
public class LoadSettingsDaoImpl extends AbstractDao implements LoadSettingsDao {
	private static final Logger logger = Logger.getLogger(LoadSettingsDaoImpl.class);

		
	public int truncateAllPageRoleTables() throws Exception {
		String [] queries= {"truncate table page","truncate table user_home_page", "truncate table views", "truncate table ROLES_VIEWS_ASSOCIATION", "truncate table role_page", "truncate table menu_url" };
		int retVal=0;
				
			for(int i = 0 ; i < queries.length; i ++ ) {
				Query q=getEntityManager().createNativeQuery(queries[i]);
				 List<Object[]> oList=q.getResultList();
				 if(oList.size()>0){
				 retVal ++;
				 }
				logger.debug(queries[i]+"-- DONE.");
				
			}

		return retVal;
	}


	public void loadPage_Default(ArrayList<PageDto> pageList) throws Exception {
				 
		Page p=null;
			if(pageList!=null&&pageList.size()>0) {
				for(PageDto dto : pageList) {
					p=new Page();
					p.setUrl(dto.getUrl());
					p.setType(dto.getUrlType());
					persist(p);
					
			}
		}
	}


	public void setHomePage_Default(ArrayList<PageDto> pageList)	throws Exception {
		UserHomePage uhp=null;
			if(pageList!=null&&pageList.size()>0) {
				for(PageDto dto : pageList) {
					uhp=new UserHomePage();
				Page p=(Page) getEntityManager().createQuery("from Page p where p.url='"+dto.getUrl()+"'").getResultList().get(0);
				uhp.setPage(p);
				uhp.setRole(dto.getUserType());
				persist(uhp);
				}
						
			}		
	   }


	public void loadViews_Default(ArrayList<ViewManagementDto> pageList)	throws Exception {
		
			int retVal=0;
			View v=null;
			if(pageList!=null&&pageList.size()>0) {
					for(ViewManagementDto dto : pageList) {
					Page p=(Page) getEntityManager().createQuery("from Page p where p.url='"+dto.getViewURL()+"'").getResultList().get(0);
					v.setViewName(dto.getViewName());
					v.setShowOrder(String.valueOf(dto.getViewShowOrder()));
					v.setPage(p);
					v.setType(ViewManagementStatus.ROOT);
					v.setViewKey(dto.getViewKey());
					
								 	
				 	if(dto.getRoles()!=null&& dto.getRoles().size()>0) {
				 		ArrayList<ViewManagementDto> dtoList  = new ArrayList<ViewManagementDto>();
				 		for(String role : dto.getRoles()) {
				 			ViewManagementDto viewDto = new ViewManagementDto ();
				 			viewDto.setRoleName(role);
				 			viewDto.setViewName(dto.getViewName());
				 			dtoList.add(viewDto);
				 		}
				 	List<Role> roleList=loadRolesViewsAssociation(dtoList);
				 	for(Role r:roleList){
				 		v.getRoles().add(r);
				 		persist(v);
				 		}
				 		 
				 	}
				
				}
			}	
		
	}
	
	public List<Role> loadRolesViewsAssociation(ArrayList<ViewManagementDto> viewList) throws Exception {
		List<Role> roleList=new ArrayList<Role>();
		
			if(viewList!=null&&viewList.size()>0) {
				for(ViewManagementDto dto : viewList) {
					//View v=(View) getEntityManager().createQuery("from View v where v.viewKey='"+dto.getViewName()+"'").getResultList().get(0);
					Role r=(Role) getEntityManager().createQuery("from Role r where r.usertype='"+dto.getRoleName()+"'").getResultList().get(0);
					roleList.add(r);
				}
			}
			return roleList;
	}


	public void loadSubViews_Default(ArrayList<ViewManagementDto> pageList)	throws Exception {
				
		View v=null;
		if(pageList!=null&&pageList.size()>0) {
					for(ViewManagementDto dto : pageList) {
						v=new View();
						Page p=(Page) getEntityManager().createQuery("from Page p where p.url='"+dto.getViewURL()+"'").getResultList().get(0);
						View view=p.getViews().get(0);
								v.setViewName(dto.getSubViewName());
								v.setShowOrder(String.valueOf(dto.getSubViewShowOrder()));
								if(view.getViewName().equalsIgnoreCase(dto.getViewName()))
									{
										v.setParentId(BigInteger.valueOf(view.getId()));
									}
								v.setPage(p);
								v.setType(ViewManagementStatus.CHILD);
								v.setViewKey(dto.getSubViewKey());
							persist(v);
								}
						}
				}


	public void loadRolesViewsAssociation_Default(ArrayList<ViewManagementDto> viewList) throws Exception {
		
			if(viewList!=null&&viewList.size()>0) {
				for(ViewManagementDto dto : viewList) {
					View v=(View) getEntityManager().createQuery("from View v where v.viewKey='"+dto.getViewName()+"'").getResultList().get(0);
					//Role r=(Role) getEntityManager().createQuery("from Role r where r.usertype='"+dto.getRoleName()+"'").getResultList().get(0);
					Role r=v.getRoles().get(0);
					if(r.getUsertype().equalsIgnoreCase(dto.getRoleName())){
						v.getRoles().add(r);
						persist(v);
					}
				}
			}
	}


	public void loadPageRole_Default() throws Exception {
		String query="from Role";
		Query q=getEntityManager().createQuery("from Role");
		List<Role> roleList=q.getResultList();
		RolePage rp=null;
		for(Role r : roleList){
			for(View v: r.getViews()){
				rp=new RolePage();
				if(v.getPage()!=null){
					rp.setRole(String.valueOf(r.getId()));
					rp.setPageBean(v.getPage());
					persist(rp);
					}
			}
		}	 
		
	}


	public void loadMenu_Url_Default() throws Exception {
		Query q=getEntityManager().createQuery("from View");
		List<View> vList=q.getResultList();
		MenuUrl mu=null;
		for(View v: vList){
			mu=new MenuUrl();
			mu.setPage1(v.getPage());
			mu.setPage2(v.getPage());
			mu.setType("MAIN");
			persist(mu);
		}
		
	}		
						
			
						
						
				
}			
