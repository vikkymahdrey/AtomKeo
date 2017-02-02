package com.agiledge.atom.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import com.agiledge.atom.dao.intfc.AdhocDao;
import com.agiledge.atom.entities.Adhocapprovar;
import com.agiledge.atom.entities.Adhocbooking;
import com.agiledge.atom.entities.Adhocrequester;
import com.agiledge.atom.entities.Adhoctype;
import com.agiledge.atom.entities.Settingsstring;

@Repository("adhocDao")
public class AdhocDaoImpl extends AbstractDao implements AdhocDao{
	
	List<Adhocrequester> adhocReqList=new ArrayList<Adhocrequester>();
	List<Adhocapprovar> approverRoles=new ArrayList<Adhocapprovar>();
	List<Adhoctype> adhocDetailsList=new ArrayList<Adhoctype>();
	
	public List<Adhocapprovar> getApproverRoles()throws Exception {
		return approverRoles;
	}

	public void setApproverRoles(List<Adhocapprovar> approverRoles) throws Exception {
		this.approverRoles = approverRoles;
	}

		
	public List<Adhocrequester> getAdhocReqList() throws Exception{
		return adhocReqList;
	}

	public void setAdhocReqList(List<Adhocrequester> adhocReqList) throws Exception {
		this.adhocReqList = adhocReqList;
	}
	
	public List<Adhocbooking> getAllPendingAhdocBookings() throws Exception {
		Query query = getEntityManager().createQuery("from Adhocbooking ab where ab.status is null or ab.status=:status");
		query.setParameter("status", "Pending");
		
		return query.getResultList();
	}

	public int[] getAdhocNotificationCount(String employeeId, String roleId,String role) throws Exception {
		
		int returnCount[]=new int[2];
		
			String query="";
			if(role.equalsIgnoreCase("admin")||role.equalsIgnoreCase("tm")||role.equalsIgnoreCase("ta")||role.equalsIgnoreCase("tc")||role.equalsIgnoreCase("v"))
			{
				query="select count(e.id) from employee e,adhocbooking ab where (ab.status is null or ab.status='Pending' ) and e.id=ab.empId";
				//query="select count(*) from AdhocBooking ab join ab.employee3 e where (ab.status is null or ab.status='Pending')";
				Query q=getEntityManager().createNativeQuery(query);
				List<BigInteger> oList=q.getResultList();
				for(BigInteger bi: oList)
				{
					returnCount[0]=bi.intValue();
				}
			}
			
			query="select count(e.id) from employee e,adhocbooking ab,adhoctypes at,adhocapprovar aa where (ab.status is null or ab.status='Pending') and ( e.LineManager="+employeeId+" or e.LineManager in (select id from employee where linemanager="+employeeId+")) and e.id=ab.empId and e.site=ab.siteid and at.type=ab.adhocType and e.site=at.site  and aa.adhocTypeId=at.id  and aa.approvarRoleId="+roleId+" and aa.status='a'  ";		
			Query q1=getEntityManager().createNativeQuery(query);
			List<BigInteger> objList=q1.getResultList();
					returnCount[1]=0;
			for(BigInteger bint: objList){
			returnCount[1]=bint.intValue();
			}			

			return returnCount;
		
	}

	public List<Settingsstring> getSettingsStrings(String adhoc, String type,
			String siteId, String projectUnit) throws Exception {
		
		Query query = getEntityManager().createQuery("from Settingsstring ss where ss.module=:adhoc and ss.submodule=:type");
		query.setParameter("adhoc", "adhoc");
		query.setParameter("type", "type");
		List<Settingsstring> ssList=query.getResultList();
			return ssList;
	}

	public Adhoctype getAdhocTypeByType(String adhoctype) throws Exception {
		Query query = getEntityManager().createQuery("from Adhoctype at where at.type=:adhoctype");
		query.setParameter("adhoctype", adhoctype);
			List<Adhoctype> adhocTypeList=query.getResultList();
			if(adhocTypeList!=null && adhocTypeList.size()>0){
				return adhocTypeList.get(0);	
			}
				return null;
	}

	public List<String> getProjectUnits() throws Exception {
		Query query = getEntityManager().createQuery("select Distinct(ats.projectUnit) from Atsconnect ats"); 
		List<String> projectUnit=query.getResultList();
		return projectUnit;
	}
	
	
	public List<Adhoctype> getAdhoctypeDetails(String adhoctype,String siteId, String projectUnit) throws Exception {
		Query query = getEntityManager().createQuery("from Adhoctype at where at.type=:adhoctype and at.siteBean.id=:siteId and (projectUnit='all' or projectUnit=:projectUnit)");
		query.setParameter("adhoctype", adhoctype);
		query.setParameter("siteId", Integer.valueOf(siteId));
		query.setParameter("projectUnit", projectUnit);
		
		List<Adhoctype> adhoctypeList=query.getResultList();
			return adhoctypeList;
	}

	public void getRequesterDetails(Adhoctype at) throws Exception {
		String query="from Adhocrequester";
		//String query="from Adhocrequester ar where ar.adhoctype.id=:adhoctypeId and ar.status='a'";
		Query q = getEntityManager().createQuery(query);
		//q.setParameter("adhoctypeId", Integer.valueOf(at.getId()));
		List<Adhocrequester> adhocrequesterList=q.getResultList();
		//List<Adhocrequester> adhocReqList=new ArrayList<Adhocrequester>();
		if(adhocrequesterList!=null && adhocrequesterList.size()>0){
			for(Adhocrequester ar: adhocrequesterList){
				if(ar.getAdhoctype().getId()==at.getId()){
					if(ar.getStatus().equals("a")){
						if(ar.getRole()!=null){
							adhocReqList.add(ar);
						}
					}
					
				}else{
					if(ar.getAdhoctype()==null){
						if(!ar.getStatus().equals("a")) {
							if(ar.getRole()==null){
								adhocReqList.add(ar);
							}
						}
					}
				}
			}
		}
		setAdhocReqList(adhocReqList);
		
	}

	public void getApprovalDetails(Adhoctype at) throws Exception {
		String query="from Adhocapprovar";
		Query q = getEntityManager().createQuery(query);
		List<Adhocapprovar> adhocapprovarList=q.getResultList();
		//List<Adhocrequester> adhocReqList=new ArrayList<Adhocrequester>();
		if(adhocapprovarList!=null && adhocapprovarList.size()>0){
			for(Adhocapprovar aa: adhocapprovarList){
				if(aa.getAdhoctype().getId()==at.getId()){
					if(aa.getStatus().equals("a")){
						if(aa.getRole()!=null){
							approverRoles.add(aa);
						}
					}
					
				}else{
					if(aa.getAdhoctype()==null){
						if(!aa.getStatus().equals("a")) {
							if(aa.getRole()==null){
								approverRoles.add(aa);
							}
						}
					}
			
				}
		
			}
		}	
			setApproverRoles(approverRoles);
	}

	 

}
