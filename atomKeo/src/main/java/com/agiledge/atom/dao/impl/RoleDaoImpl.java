package com.agiledge.atom.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.agiledge.atom.dao.intfc.RoleDao;
import com.agiledge.atom.entities.Role;
import com.agiledge.atom.entities.View;



@Repository("roleDao")
public class RoleDaoImpl extends AbstractDao implements RoleDao {

	private static final Logger logger = Logger.getLogger(RoleDaoImpl.class);
	public String gerUserMenuByRole(Role role) throws Exception {
		StringBuffer links = new StringBuffer();
		StringBuffer userMenu = new StringBuffer();
		StringBuffer eachMenu = new StringBuffer();
		
		List<View> mainViews = role.getViews();
		List <View> subViews = new ArrayList<View> ();
		try{
		for(View eachMainView : mainViews){
						
			links.append("<a href='").append(eachMainView.getPage().getUrl())
					.append("'><span>");
			links.append(eachMainView.getViewName()).append("</span></a>");
			
			// Fetch the sub views in each view
			Query query = getEntityManager().createQuery("from View v where v.parentId =:parentId");
			query.setParameter("parentId", new BigInteger(""+eachMainView.getId()));
			subViews = (List<View>) query.getResultList();
			
			if (subViews.isEmpty()) {
				eachMenu.append("<li>").append(links.toString())
						.append("</li> ");
				userMenu.append(eachMenu.toString());
				links = new StringBuffer();
				eachMenu = new StringBuffer();
			
			} else {
				eachMenu.append("<li class='has-sub'>")
						.append(links.toString()).append("<ul>");
				for (View eachSubVIew : subViews) {
					eachMenu.append("<li > <a href='")
							.append(eachSubVIew.getPage().getUrl()).append("'>")
							.append(eachSubVIew.getViewName()).append("</a></li>");
				}
			
				userMenu.append(eachMenu.toString()).append("</ul>")
						.append("</li>");
				links = new StringBuffer();
				eachMenu = new StringBuffer();
			}
		}}catch(Exception e){
		logger.error("ERrro in ",e);
		}
	 return userMenu.toString();
 }

	public Role getRoleByUserType(String userType) throws Exception {
		Query query = getEntityManager().createQuery("from Role r where r.usertype =:userType");
		query.setParameter("userType", userType);
		return (Role) query.getResultList().get(0);
	}

	public List<Role> getAllRoles() throws Exception{
		Query query = getEntityManager().createQuery("from Role r");
		return query.getResultList();
	}

	public Role addNewRole(Role role) throws Exception {
		Query query = getEntityManager().createQuery("from Role r where r.name =:name and r.usertype =:userType");
		query.setParameter("name", role.getName());
		query.setParameter("userType", role.getUsertype());
		if(query.getResultList() != null && query.getResultList().size() > 0)
			return null;
		else{
			persist(role);
			return role;
		}
	}

	public Role getRoleById(String roleId) throws Exception {
		Query query = getEntityManager().createQuery("from Role r where r.id =:roleId");
		query.setParameter("roleId", Integer.valueOf(roleId));
		return (Role) query.getResultList().get(0);
	}

	public Role updateRole(Role role) throws Exception {
		update(role);
		return role;
	}

	public List<Role> getRoleList() throws Exception {
		Query query = getEntityManager().createQuery("from Role");
				return query.getResultList();
	}

	

}
