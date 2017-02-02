package com.agiledge.atom.dao.impl;

import java.util.List;

import javax.persistence.Query;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.agiledge.atom.config.files.OtherFunctions;
import com.agiledge.atom.config.files.SettingStatus;
import com.agiledge.atom.dao.intfc.FilterSupportDao;
import com.agiledge.atom.dto.PageDto;
import com.agiledge.atom.entities.MenuUrl;
import com.agiledge.atom.entities.Page;
import com.agiledge.atom.entities.Role;
import com.agiledge.atom.entities.RolePage;
import com.agiledge.atom.entities.Setting;
import com.agiledge.atom.entities.View;
import com.agiledge.atom.interceptor.RequestProcessingInterceptor;
import com.agiledge.atom.service.intfc.PageManagementStatus;
@Repository
public class FilterSupportDaoImpl extends AbstractDao implements FilterSupportDao {
	private static final Logger logger = Logger.getLogger(FilterSupportDaoImpl.class);
	
	public boolean hasAuthentication() throws Exception{
		boolean flag = false;
		Setting setting= (Setting) getEntityManager().createQuery("from Setting where authentication='true'").getResultList().get(0);
			if (setting!=null) {
				flag = true;
			}
		return flag;
	}
	
	public boolean authenticatePageRole(HttpServletRequest request,  String role, String page, String employeeId)  throws Exception{
		
		boolean flag = false;
		String wellPageStatus = page + "_" + role+"_MainPage";
		ServletContext context = request.getSession().getServletContext();
					
			String lastWellPage="";
			try{
			    	lastWellPage=request.getSession().getAttribute("lastAutherisedPage").toString();
			    	logger.debug("lastWellPageeee"+lastWellPage);
				}catch(Exception e){
				logger.error("Error in "+e);
			}
			String query="from Page p where p.url='"+ page + "' ";					
			//String query = "select * from page p join menu_url m on p.id = m.url  join role_page r on m.menuKey=r.page where  p.url='" + page + "' and r.role='" + role + "'";
					
			if(isWellPage(page, role)) {
				
				context.setAttribute(wellPageStatus,"YES" );
				flag = true;
			}
			List<Page> pList=getEntityManager().createQuery(query).getResultList();
			for(Page p : pList){
				for(MenuUrl mu: p.getMenuUrls2()){
					for(RolePage rp: mu.getPage1().getRolePages()){
						if(flag==false && rp.getRole().equals(role)){
							flag=true;
						}
						else if(flag==false){
							if(giveAutherisationOfPageItselfForRole(page,role)>0) {								 
								context.setAttribute(wellPageStatus,"YES" );
								flag = true;
							 }else if(lastWellPage.trim().equals("")==false && RequestProcessingInterceptor.autherisation_insertion_mode.equalsIgnoreCase(SettingStatus.AUTHERISATION_INSERTION_MODE_VALUE_ON)) {
								 		
								 		if(giveAutherisationOfPageForRole(lastWellPage,page,role)>0) {
								 				flag=true;
								  		}
								  
							 		 } else 
							 		 {
							 			 		int insVal=0;
							 			 			if(OtherFunctions.isEmpty( lastWellPage)) {
							 			 				if(RequestProcessingInterceptor.autherisation_insertion_mode.equalsIgnoreCase(SettingStatus.AUTHERISATION_INSERTION_MODE_VALUE_ON)) {
							 			 						insVal=insertRolePage(role,page);
							 			 							flag = true;
							 			 				 }
							 			 			  }  
							 			 			
							 			 			if(insVal<=0 ) {  
							 			 				insertUnautherisedRequest(role, p.getId(), employeeId,lastWellPage);
							 			 			}
								 
									 
								 
							 		 }
							   }
						logger.debug("Flag Value"+flag);
						if(flag==true) {
							request.getSession().setAttribute("lastAutherisedPage", page);
						} else{
							flag = true;
						}
					}
				 }
			 }	
					
					
		return flag;
	
}
	public void insertUnautherisedRequest(String role, int page,String employeeId, String lastWellPage) throws Exception {
				/*Data is not inserting here........*/
		
	}

	public boolean isWellPage(String url, String role) throws Exception {
	  
	boolean flag = false;
	Query q= getEntityManager().createQuery("from Page p where p.url='"+url+"'");
	List<Page> pageList=q.getResultList();
	if(pageList!=null && pageList.size()>0){
		for(Page p :pageList){
	for(RolePage rp: p.getRolePages()){
		if(rp.getRole().equals(role)){
			 flag = true;
		}
	  }	 
		}
		}	
		  		return flag;
}
	
	
	public int insertRolePage(String usertype, String page ) throws Exception{
		
			int returnData = 0;
			  String query="from RolePage";
			  List<RolePage> rolepageList=getEntityManager().createQuery("query").getResultList();
			RolePage rpNew=null;
			for(RolePage rp : rolepageList){
				if(String.valueOf(rp.getPageBean().getUrl()).equals(page)){
					if(rp.getRole().equalsIgnoreCase(usertype)){
						rpNew=new RolePage();
						rpNew.setRole(usertype);
						rpNew.setPageBean(rp.getPageBean());
						persist(rp);
						returnData+=1;
					}
				}
			}
			
		return returnData;
	}

	public int giveAutherisationOfPageItselfForRole(String  subPage, String role) throws Exception{
		logger.debug("FilterSupportDao givAutherisation ......");
		int returnInt=0;
		if(RequestProcessingInterceptor.autherisation_insertion_mode.equalsIgnoreCase(SettingStatus.AUTHERISATION_INSERTION_MODE_VALUE_ON)) {
			  
		Page page = getPageFromUrlString(subPage);
		if( page!=null) {
			PageDto paramDto = new PageDto();
			paramDto.setParentPageId(String.valueOf(page.getId()));
			paramDto.setUrl(String.valueOf(page.getId()));
			paramDto.setUserType(role);
			paramDto.setUrlType(PageManagementStatus.MAIN);
			returnInt = groupAssignedUrlItself(paramDto);
		}
		
		}
		logger.debug("in giveAutherisationOfPageItselfForRole : "+ returnInt);
		return returnInt;
	}

	public int groupAssignedUrlItself(PageDto paramDto) throws Exception {
		
		int returnInt = 0;
		RolePage rp=null;
		MenuUrl mu=null;
		Role r=getRoleByUsertype(paramDto.getUserType());
		List<View> viewList=getEntityManager().createQuery("from View").getResultList();
		for(View v : viewList){
			mu=new MenuUrl();
			rp=new RolePage();
			mu.setType(PageManagementStatus.MAIN);
			rp.setRole(paramDto.getUserType());
			if(String.valueOf(v.getPage().getId()).equals(paramDto.getParentPageId())){
				rp.setPageBean(v.getPage());
				mu.setPage2(v.getPage());
				persist(rp);
				returnInt+=1;
			}
			returnInt=0;
			if(String.valueOf(v.getPage().getId()).equals(paramDto.getUserType())){
				mu.setPage1(v.getPage());
				persist(mu);
				returnInt+=1;
			}
		}
		 return returnInt;
				 	
	}
	
	public int giveAutherisationOfPageForRole(String mainPage, String subPage, String role) throws Exception{
		logger.debug("FilterSupportDao givAutherisation ..");
		int returnInt=0;
		Page subPage1 = getPageFromUrlString(subPage);
		Page mainPage1 = getPageFromUrlString(mainPage);
						
		if(mainPage1!=null&& subPage1!=null) {
			PageDto paramDto = new PageDto();
			paramDto.setParentPageId(String.valueOf(mainPage1.getId()));
			paramDto.setUrl(String.valueOf(subPage1.getId()));			   
			paramDto.setUserType(role);
			paramDto.setUrlType(PageManagementStatus.SUB);
			returnInt = groupAssignedUrl(paramDto);
		}
		return returnInt;
	}
	
	public int groupAssignedUrl(PageDto  dto)  throws Exception{
		int returnInt = 0;
		RolePage rp=null;
		MenuUrl mu=null;
		Role r=getRoleByUsertype(dto.getUserType());
		Page p=getPageByPageUrlId(dto.getUrl());
		List<View> viewList=getEntityManager().createQuery("from View").getResultList();
		for(View v : viewList){
			mu=new MenuUrl();
			rp=new RolePage();
			if(String.valueOf(v.getPage().getId()).equals(dto.getUrl())){
				rp.setRole(dto.getUserType());
				rp.setPageBean(v.getPage());
				mu.setPage1(v.getPage());
				mu.setPage2(v.getPage());
				mu.setType(PageManagementStatus.MAIN);
				persist(rp);
				persist(mu);
				returnInt+=1;
				
			}else if(String.valueOf(v.getPage().getId()).equals(dto.getParentPageId())) {
				rp.setRole(dto.getUserType());
				rp.setPageBean(v.getPage());
				mu.setPage1(v.getPage());
				mu.setPage2(p);
				mu.setType(dto.getUrlType());
				persist(rp);
				persist(mu);
				returnInt+=1;
			
			}
		}	 
		return returnInt;

	}

	

	public Page getPageByPageUrlId(String url)  throws Exception{
		return (Page) getEntityManager().createQuery("from Page p where p.id=:pageId").setParameter("pageId",url).getResultList().get(0); 
		
	}

	public Page getPageFromUrlString(String subPage) throws Exception{
		return (Page) getEntityManager().createQuery("from Page p where p.url='"+subPage+"'").getResultList().get(0); 
	}
	
	public Role getRoleByUsertype(String usertype) throws Exception{
		return (Role)getEntityManager().createQuery("from Role r where r.usertype='"+usertype+"'").getResultList().get(0);
	}
}
