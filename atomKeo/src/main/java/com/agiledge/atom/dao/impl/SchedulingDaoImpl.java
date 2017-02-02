package com.agiledge.atom.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.agiledge.atom.controller.VendorMasterController;
import com.agiledge.atom.dao.intfc.SchedulingDao;
import com.agiledge.atom.dto.TestDto;
import com.agiledge.atom.entities.Atsconnect;
import com.agiledge.atom.entities.EmployeeSchedule;
import com.agiledge.atom.entities.EmployeeScheduleAlter;
import com.agiledge.atom.entities.EmployeeSubscription;
import com.agiledge.atom.utils.DateUtil;

@Repository
public class SchedulingDaoImpl extends AbstractDao implements SchedulingDao {
	private final static Logger logger = Logger.getLogger(VendorMasterController.class);
	
	public List<EmployeeSubscription> getAllSubscribedEmployeeDetails() throws Exception{
				
		String query="from EmployeeSubscription esub where (esub.subscriptionStatus='subscribed' or esub.subscriptionStatus='pending')";
			Query q=getEntityManager().createQuery(query);
			List<EmployeeSubscription> empSubscriptionList=q.getResultList();
		return empSubscriptionList;
	}
	
	
	public List<TestDto> getAllSubscribedEmployeeDetails1() throws Exception{
		
		
		String query="select subscriptionID,e.id from employee_subscription esub join employee e on esub.empID=e.id where  (esub.subscriptionStatus='subscribed' or esub.subscriptionStatus='pending') limit 1";
		TestDto dto=new TestDto();
		List<TestDto> list=new ArrayList<TestDto>();
		Query q=getEntityManager().createNativeQuery(query);
			Object[] esOBj=(Object[]) q.getSingleResult();
			 String subID=String.valueOf(esOBj[0]);
			 String empID=String.valueOf(esOBj[1]);
			 dto.setId(subID);
			 dto.setEmpId(empID);
			 list.add(dto);
			 
			
		return list;
	}

	public EmployeeSubscription getSubscriptionDetailsById(String subsId)	throws Exception {
		/*String sId="";
		if(subsId!=null && subsId.trim().equals("")){
			sId=" esub.id= "+subsId+" and ";
		}*/
		String query="from EmployeeSubscription esub where esub.id=:subsId  and (esub.subscriptionStatus='subscribed' or esub.subscriptionStatus='pending')";
		Query q=getEntityManager().createQuery(query);
		q.setParameter("subsId", subsId);
		List<EmployeeSubscription> empSubscriptionList=q.getResultList();
	return empSubscriptionList.get(0);
		
	}

	public Atsconnect getProjectById(String projectId) throws Exception {
		String query="from Atsconnect where id=:projectId";
		Query q=getEntityManager().createQuery(query);
		q.setParameter("projectId", projectId);
		List<Atsconnect> projectList=q.getResultList();
	return projectList.get(0);
	}

	public List<EmployeeScheduleAlter> getAlterSchDetails() throws Exception {
		String query="from EmployeeScheduleAlter where status='a'";
		Query q=getEntityManager().createQuery(query);
		List<EmployeeScheduleAlter> empAlterScheduleList=q.getResultList();
		return empAlterScheduleList;
	}

	

	public void insertIntoEmployeeSchedule(EmployeeSchedule empSchedule)throws Exception {
		 Query query = getEntityManager().createQuery("from EmployeeSchedule where ((:fromDate between fromDate and ifnull(cancelDate,toDate)) or	(:toDate between fromDate and ifnull(cancelDate,toDate)) or 	(:fromDate <=fromDate and :toDate>=ifnull(cancelDate,toDate)) ) and employee1.id =:empId and status =:status");
		 
		 query.setParameter("fromDate", empSchedule.getFromDate());
		  query.setParameter("toDate", empSchedule.getToDate());
		    query.setParameter("empId", empSchedule.getEmployee1().getId());
		       query.setParameter("status", "a");
		 
		 List<EmployeeSchedule> activeSchedules = query.getResultList();
		 if(activeSchedules == null || activeSchedules.size()== 0)
			 persist(empSchedule); 
		      flush();
		}

	public void insertEmpWeekOff(EmployeeScheduleAlter empSchAlter) throws Exception {
		 persist(empSchAlter); 
	      flush();
	}



	public EmployeeSchedule getscheduleDetailsById(String scheduleId) throws Exception {
	Query query = getEntityManager().createQuery("from EmployeeSchedule es where es.id =:scheduleId");
		 
		 query.setParameter("scheduleId", scheduleId);
		 
		 return (EmployeeSchedule) query.getResultList().get(0);
		
	}


	

	public EmployeeSchedule insertionOnEmployeeSchedule(EmployeeSchedule empSch)throws Exception {
		persist(empSch);
		flush();
		return empSch;
	}


	public List<EmployeeSchedule> getSchedulingForTripSheet(String tripDate,String tripMode, String tripTime) throws Exception {
		
		String tTime="";
		if(tripMode.equalsIgnoreCase("IN")){
			tTime=" and loginTime='"+tripTime+"'";
		}else if(tripMode.equalsIgnoreCase("OUT")){
			tTime=" and logoutTime='"+tripTime+"'";
		}		
		
		List<EmployeeSchedule> esLst=new ArrayList<EmployeeSchedule>(); 
		String q="from EmployeeSchedule where fromDate<=:tripDate and toDate>=:tripDate and status='a' ";
		Query query = getEntityManager().createQuery(q);
		  query.setParameter("tripDate",  DateUtil.convertStringToDate(tripDate,  "dd/MM/yyyy","yyyy-MM-dd hh:mm::ss"));
		  List<EmployeeSchedule> eslist=query.getResultList();
		  for(EmployeeSchedule es : eslist ){
			  if(es.getEmployeeScheduleAlters()!=null && !(es.getEmployeeScheduleAlters().isEmpty())){
				  for(EmployeeScheduleAlter esa : es.getEmployeeScheduleAlters()){
					 if(esa.getStatus().equalsIgnoreCase("a") && esa.getLogoutTime().equalsIgnoreCase(tripTime) && (esa.getDate().compareTo(DateUtil.convertStringToDate(tripDate,  "dd/MM/yyyy","yyyy-MM-dd hh:mm::ss"))==0)){
					  esLst.add(esa.getEmployeeSchedule());
					 }
					 
				  }
				  
				  if(es.getLogoutTime().equals(tripTime)){
					  esLst.add(es); 
				 }
			  }else{
				  if(es.getLogoutTime().equals(tripTime))
					  esLst.add(es); 
				  }
		  }
		  Set<EmployeeSchedule> setEmpSch = new HashSet<EmployeeSchedule>(esLst);
		  esLst.clear();
		  esLst.addAll(setEmpSch);
		 return esLst;
	}


	public EmployeeSchedule getExistingSchDetails(String fromDate,String toDate, String time,String empid) throws Exception {
		String q="from EmployeeSchedule where employee1.id=:empid and ((fromDate between :fromDate and :toDate) or (toDate between :fromDate and :toDate) or (fromDate<=:fromDate and toDate>=:toDate)) ";
		logger.debug("logtime"+time);
		Query query = getEntityManager().createQuery(q);
		 query.setParameter("empid", empid);
		 query.setParameter("fromDate",  DateUtil.convertStringToDate(fromDate,  "dd/MM/yyyy","yyyy-MM-dd hh:mm::ss"));
		 query.setParameter("toDate",  DateUtil.convertStringToDate(toDate,  "dd/MM/yyyy","yyyy-MM-dd hh:mm::ss"));
		 
		List<EmployeeSchedule> schList=query.getResultList();
		List<EmployeeSchedule> newSchList=new ArrayList<EmployeeSchedule>();
		/*if(schList!=null && schList.size()>0){
			return schList.get(0);
		}else{
		 return null;
		}*/
				
		if(schList!=null && !(schList.isEmpty())){
			for(EmployeeSchedule es: schList){
				if(es.getEmployeeScheduleAlters()!=null && !(es.getEmployeeScheduleAlters().isEmpty())){
					for(EmployeeScheduleAlter esa : es.getEmployeeScheduleAlters()){
						if(esa.getStatus().equalsIgnoreCase("a") && esa.getLogoutTime().equalsIgnoreCase("Cancel")){
							logger.debug("in if condition");
							newSchList.clear();
							//newSchList.add(esa.getEmployeeSchedule());
						}else{
							logger.debug("in else condition");
							newSchList.add(esa.getEmployeeSchedule());
						}
					}
				}
				else{
					newSchList.add(es);
				}
			}
		}
		logger.debug("vvvvvvvvvvvvvv"+newSchList.size());
		if(newSchList!=null && !(newSchList.isEmpty())){
			return newSchList.get(0);
		}else{
		 return null;
		}
	
	}


	public void delete(EmployeeSchedule esDetails) throws Exception {
				
	}


	

	
	
}
