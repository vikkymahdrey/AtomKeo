package com.agiledge.atom.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.agiledge.atom.config.files.OtherFunctions;
import com.agiledge.atom.dao.intfc.ScheduleReportDao;
import com.agiledge.atom.entities.EmployeeSchedule;




@Repository("ScheduleReportDao")
public class ScheduleReportDaoImpl extends AbstractDao implements ScheduleReportDao{
	private final static Logger logger = Logger.getLogger(ScheduleReportDaoImpl.class);

	public List<EmployeeSchedule> getActiveScheduleDetails(Date fromDate,
			Date toDate, String siteID, String spoc, String project,
			String shiftInTime, String shiftOutTime, String empId) {
		
		
		
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<EmployeeSchedule> criteria = builder.createQuery(EmployeeSchedule.class);
		Root<EmployeeSchedule> hRoot = criteria.from(EmployeeSchedule.class);
				
		List<Predicate> predicates = new ArrayList<Predicate> ();

		if(fromDate != null)
			predicates.add(builder.equal(hRoot.get("fromDate"),fromDate));
		if(toDate!= null)
			predicates.add(builder.equal(hRoot.get("toDate"), toDate));
		if(project!= null && !(project.trim().isEmpty()))
			predicates.add(builder.equal(hRoot.get("project"), project));
		if(shiftInTime!= null && !(shiftInTime.trim().isEmpty()))
			predicates.add(builder.equal(hRoot.get("loginTime"), shiftInTime));
		if(shiftOutTime!= null && !(shiftOutTime.trim().isEmpty()))
			predicates.add(builder.equal(hRoot.get("logoutTime"), shiftOutTime));
		
		
	
		criteria.select(hRoot).where(predicates.toArray((new Predicate[]{})));
		
		List<EmployeeSchedule> empschedules = getEntityManager().createQuery(criteria).getResultList();
		List<EmployeeSchedule> addtoempsch = new ArrayList<EmployeeSchedule>();
		if(empschedules!=null && empschedules.size()>0){
			for(EmployeeSchedule es:empschedules)
			{
				if(siteID != null && es.getEmployee1().getSiteBean().getId()== Integer.parseInt(siteID)){
					
					empschedules.add(es);
					
				}
				
			}
		}
		
		return addtoempsch;
				

	
	}
}
