package com.agiledge.atom.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.agiledge.atom.entities.EmployeeSchedule;
import com.agiledge.atom.entities.GeneralShift;
import com.agiledge.atom.entities.Logtime;
import com.agiledge.atom.entities.Site;
import com.agiledge.atom.service.intfc.ScheduleReportService;
import com.agiledge.atom.service.intfc.ShiftService;
import com.agiledge.atom.service.intfc.SiteService;


@Controller
public class ScheduleReportController implements ServletContextAware {
	private final static Logger logger = Logger.getLogger(ScheduleReportController.class);
	private ServletContext servletContext;

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;}
	/*}
	@Autowired
	private ScheduleReportService scheduleReportService;
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private ShiftService shiftService;
	
	@RequestMapping(value = { "/display_emp_sch_report" }, method = RequestMethod.GET)
	public ModelAndView siteList() {

		 List<Site> siteList = siteService.getSites();
				

		logger.debug("siteList " + siteList.size());

		servletContext.setAttribute("siteList", siteList);
		return new ModelAndView("display_emp_sch_report","siteList",siteList );

	}
	
	
	@RequestMapping(value = { "ddisplayempschreport" }, method = RequestMethod.GET)
	public ModelAndView empList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, Exception {
		logger.debug("IN empList Controller");
		String fromDate = request.getParameter("fromDate");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-DD-MMM");
		Date frDate =  formatter.parse(fromDate);
		
		String toDate = request.getParameter("toDate");
		Date tDt =  formatter.parse(toDate);
		String siteID = request.getParameter("siteId");
//		String manager = request.getParameter("supervisorID1");
	    String empId = request.getParameter("employeeId");
		String spoc = request.getParameter("supervisorID2");
		String project = request.getParameter("project");
		String shiftInTime=request.getParameter(("shiftInTime"));
		String shiftOutTime=request.getParameter(("shiftOutTime"));
		
		List<EmployeeSchedule> emplist=null;
		if(fromDate!=null&&toDate!=null)
		{
		 emplist = scheduleReportService.getActiveScheduleDetails(frDate, tDt, siteID, spoc, project, shiftInTime, shiftOutTime, empId);
		}
		  

		logger.debug("list size "+emplist.size());
		servletContext.setAttribute("emplist", emplist);
				
		
		return new ModelAndView("display_emp_sch_report","emplist",emplist);

	}
	
	*/
	


}
