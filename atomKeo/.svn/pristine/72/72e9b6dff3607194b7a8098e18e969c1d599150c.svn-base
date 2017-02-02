package com.agiledge.atom.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;

import com.agiledge.atom.dto.EmployeeDto;
import com.agiledge.atom.entities.Atsconnect;
import com.agiledge.atom.entities.Employee;
import com.agiledge.atom.entities.EmployeeSchedule;
import com.agiledge.atom.entities.EmployeeSubscription;
import com.agiledge.atom.entities.Logtime;
import com.agiledge.atom.entities.Site;
import com.agiledge.atom.entities.SiteShift;
import com.agiledge.atom.service.intfc.EmployeeService;
import com.agiledge.atom.service.intfc.LogTimeService;
import com.agiledge.atom.service.intfc.SchedulingService;
import com.agiledge.atom.service.intfc.SiteService;
import com.agiledge.atom.utils.DateUtil;

@Controller
public class SchedulingController implements ServletContextAware {
	private ServletContext servletContext;
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	private final static Logger logger = Logger.getLogger(SchedulingController.class);
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private SchedulingService schedulingService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private LogTimeService logTimeService;
	
	
	@RequestMapping(value = {"/schedulingByTransadmin"}, method = RequestMethod.GET)
	public String schedulingByTransadmin(HttpServletRequest request,Map<String,Object> map) throws Exception{
		try{
			map.put("siteList", siteService.getSites());
			}catch(Exception ex){
				logger.error("Error in",ex);
				 throw new Exception("Error in "+ex.getClass() +" "+ ex.getMessage() +" "+ ex.getCause());
			}
			return "transadmin_schedule_employee";
	}
	
	@RequestMapping(value = {"/schedulingOpenByTransadmin"}, method = RequestMethod.GET)
	public String schedulingOpenByTransadmin(HttpServletRequest request,Map<String,Object> map) throws Exception{
		
		String site = "";
		site = request.getParameter("site");
		//List<EmployeeSchedule> empSchedulingList=null;
		List<Employee> empBySiteAndSubscription=null;
		List<Logtime> masterLoginTimeList = null;
		List<Logtime> masterLogoutTimeList = null;
	
		Logtime logTime = null;
		
		/*starting special section for pagination middle */
		/*int RECORD_SIZE = 50;
		String startPos = request.getParameter("startPos");
		String endPos = request.getParameter("endPos");
		if (startPos == null && endPos == null) {
			startPos = "0";
			endPos = "" + RECORD_SIZE;
		}*/
		try{	
					
		Site sites = siteService.getSiteById(Integer.parseInt(site));
		List<SiteShift> siteShifts = sites.getSiteShifts();
		masterLoginTimeList = new ArrayList<Logtime> ();
		masterLogoutTimeList = new ArrayList<Logtime> ();
						
		//fetching log IN/OUT based on sites....
		for(SiteShift eachSiteShift : siteShifts){
			if(eachSiteShift.getStatus().equals("active")){
				logTime = eachSiteShift.getLogtime();
				if(logTime!=null && logTime.getStatus().equals("active")){
				if(logTime.getLogtype().equals("IN")){
					masterLoginTimeList.add(logTime);
			}else{
				masterLogoutTimeList.add(logTime);
			}
			
			}
		}
		    }
		map.put("masterLoginTimeList",masterLoginTimeList);
		map.put("masterLogoutTimeList",masterLogoutTimeList);
		map.put("siteList", siteService.getSites());
			
			//fetching subscribed/pending emp based on sites....
			//empSchedulingList=new ArrayList<EmployeeSchedule>();
			empBySiteAndSubscription=new ArrayList<Employee>();
			List<EmployeeSubscription> empSubscriptionList=schedulingService.getAllSubscribedEmployeeDetails();
			for(EmployeeSubscription es: empSubscriptionList){
				Employee emp=es.getEmployee1();
				if(emp.getSiteBean().getId()==Integer.parseInt(site)){
					empBySiteAndSubscription.add(emp);
					
					//fetching scheduling info based on employee....
					
				
			}
		}
			map.put("empBySiteAndSubscription",empBySiteAndSubscription);
				    
		
}catch(Exception ex){
	logger.error("Error in ",ex);
	throw new Exception("Error in "+ex.getClass() +" "+ ex.getMessage() +" "+ ex.getCause());
}
		
		return "transadmin_schedule_employee";
	}
	
	
	@RequestMapping(value = {"/scheduleEmployee"}, method = RequestMethod.POST)
	public String empSchedulingInfoInsertion(HttpServletRequest request,Map<String,Object> map) throws Exception{
		HttpSession session = request.getSession(true);
		String scheduledBy = session.getAttribute("user").toString();
	
		String source = request.getParameter("source");
		String subscriptionIds[] = request.getParameterValues("subscriptionId");
		EmployeeSchedule empSchedule=null;
					
		try{
		
		for (int i = 0; i < subscriptionIds.length; i++) {
			try{
			empSchedule = new EmployeeSchedule();
			EmployeeSubscription empSubs=schedulingService.getSubscriptionDetailsById(subscriptionIds[i]);
			empSchedule.setEmployeeSubscription(empSubs);
			
			Employee subsEmp=empSubs.getEmployee1();
			empSchedule.setEmployee1(subsEmp);
			
			String project =request.getParameter("project" + subscriptionIds[i]);
			Atsconnect ats=schedulingService.getProjectById(project);
			empSchedule.setAtsconnect(ats);
			
			empSchedule.setFromDate(DateUtil.convertStringToDate(request.getParameter("from_date" + subscriptionIds[i]),"dd/MM/yyyy","yyyy-MM-dd hh:mm:ss"));
			empSchedule.setToDate(DateUtil.convertStringToDate(request.getParameter("to_date" + subscriptionIds[i]),"dd/MM/yyyy","yyyy-MM-dd hh:mm:ss"));
			
			/*List<EmployeeScheduleAlter> empSchAlterList=schedulingService.getAlterSchDetails();
			empSchedule.setEmployeeScheduleAlters(empSchAlterList);*/
			
			String weekOffStatus=request.getParameter("weeklyoff" + subscriptionIds[i]);
			
			empSchedule.setLoginTime(request.getParameter("logintime"+ subscriptionIds[i]));
			empSchedule.setLogoutTime(request.getParameter("logouttime" + subscriptionIds[i]));
			
			Employee scheduleByEmp=employeeService.getEmployeeById(scheduledBy);
			empSchedule.setEmployee2(scheduleByEmp);
			
			empSchedule.setLogStatus("both");
			empSchedule.setStatus("a");
			
			schedulingService.insertIntoEmployeeSchedule(empSchedule,weekOffStatus);
			
			
			map.put("status",
					"<div class=\"success\" >  Scheduling successful </div>");
			
		    
		  }catch(Exception ex){
				  map.put("status",
							"<div class=\"failure\" > Scheduling failed </div>");
					logger.error("Error in",ex);
					throw new Exception("Error in "+ex.getClass() +" "+ ex.getMessage() +" "+ ex.getCause());
				  
			  }
			
			//for inserting weekOff into employee Schedule alter....
						
		}	
			if (source == null)
				return "redirect:/scheduled_employee";
			else
				return "redirect:/schedulingByTransadmin";
						
		}catch(Exception ex){
		
			map.put("status",
					"<div class=\"failure\" > Scheduling failed </div>");
			logger.error("Error in",ex);
			throw new Exception("Error in "+ex.getClass() +" "+ ex.getMessage() +" "+ ex.getCause());
		}
		
	
}
		
	
	@RequestMapping(value = {"/schedulingAlterByTransadmin"}, method = RequestMethod.GET)
	public String schedulingAlterByTransadmin(HttpServletRequest request,Map<String,Object> map) throws Exception{
		try{
			map.put("siteList", siteService.getSites());
			}catch(Exception ex){
				logger.error("Error in",ex);
				 throw new Exception("Error in "+ex.getClass() +" "+ ex.getMessage() +" "+ ex.getCause());
			}
			return "transadmin_schedule_employee";
	}
	
	
	/*Employee Management........*/
	/*@RequestMapping(value = {"/schedulingEmployee"}, method = RequestMethod.GET)
	public String schedulingEmployeeHandler(HttpServletRequest request,Map<String,Object> map) throws Exception{
		HttpSession session = request.getSession(true);
		String empId = session.getAttribute("user").toString();
		
		ScheduledEmpDao ScheduledDaoObj = new ScheduledEmpDao();
		ArrayList<ScheduledEmpDto> scheduledEmpList;
		ArrayList<ScheduledEmpDto> scheduledEmpListfirst;   
		ArrayList<ProjectDto> projectList = null;
		ProjectDao projectDaoObj = new ProjectDao();
		LogTimeDao logTimeDaoObj = new LogTimeDao();
			
			List<EmployeeDto> subordinateList = new ArrayList<EmployeeDto>();
			EmployeeDto empAllDto = new EmployeeDto();
				empAllDto.setEmployeeID("ALL");
				empAllDto.setDisplayName("--ALL--");
				empAllDto.setPersonnelNo("");
			
			EmployeeDto empSelfDto = new EmployeeDto();
				empSelfDto.setEmployeeID("SELF");
				empSelfDto.setDisplayName("--SELF--");
				empSelfDto.setPersonnelNo("");
				
			subordinateList.add(empAllDto);
			subordinateList.add(empSelfDto);
			

			subordinateList.addAll(employeeService.getAllDirectSubordinate(empId));
			logger.debug("Size : " + subordinateList.size());
			map.put("siteList", siteService.getSites());
				return "scheduled_employee";
	}*/
}
