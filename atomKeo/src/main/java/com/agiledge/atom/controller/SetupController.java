package com.agiledge.atom.controller;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.agiledge.atom.config.files.OtherFunctions;
import com.agiledge.atom.constants.AuditLogConstants;
import com.agiledge.atom.entities.Area;
import com.agiledge.atom.entities.Atsconnect;
import com.agiledge.atom.entities.Branch;
import com.agiledge.atom.entities.Company;
import com.agiledge.atom.entities.Driver;
import com.agiledge.atom.entities.DriverVehicle;
import com.agiledge.atom.entities.Employee;
import com.agiledge.atom.entities.Escort;
import com.agiledge.atom.entities.Landmark;
import com.agiledge.atom.entities.Logtime;
import com.agiledge.atom.entities.Place;
import com.agiledge.atom.entities.Site;
import com.agiledge.atom.entities.SiteShift;
import com.agiledge.atom.entities.Vehicle;
import com.agiledge.atom.entities.VehicleType;
import com.agiledge.atom.entities.Vendormaster;
import com.agiledge.atom.service.intfc.AplService;
import com.agiledge.atom.service.intfc.AuditLogService;
import com.agiledge.atom.service.intfc.CompanyBranchService;
import com.agiledge.atom.service.intfc.DriverService;
import com.agiledge.atom.service.intfc.EmployeeService;
import com.agiledge.atom.service.intfc.EscortService;
import com.agiledge.atom.service.intfc.LandmarkService;
import com.agiledge.atom.service.intfc.LogTimeService;
import com.agiledge.atom.service.intfc.RoleService;
import com.agiledge.atom.service.intfc.SiteService;
import com.agiledge.atom.service.intfc.VehicleTypeService;
import com.agiledge.atom.service.intfc.VendorMasterService;

@SessionAttributes("status")
@Controller
public class SetupController {
	
	private static final Logger logger = Logger.getLogger(SetupController.class);
	
	@Autowired
    private CompanyBranchService companyBranchService;
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private LandmarkService landmarkService;
	
	@Autowired
	private AplService aplService;
	
	@Autowired 
	LogTimeService logTimeService;
	
	@Autowired
	AuditLogService auditLogService;
	
	@Autowired
	VehicleTypeService vehicleTypeService;
	
	@Autowired
	DriverService driverService;	
	
	@Autowired
	VendorMasterService vendorMasterService;
	
	@Autowired
	EscortService escortService;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	RoleService roleService;
	
	@RequestMapping(value= {"/showCompany"}, method=RequestMethod.GET)
	public String fetchingCompanyInfo(HttpServletRequest request) throws Exception  {
		Company comp=null;
		try{	
			comp = companyBranchService.getCompany();
			}catch(Exception ex){
			logger.error("Error in ",ex);
		}
		request.getServletContext().setAttribute("company",comp);
		return "addCompany";
	}
	
	@RequestMapping(value= {"/addingCompany"}, method=RequestMethod.GET)
	public String addingCompanyInfo(HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception{
		HttpSession session = request.getSession(true);
		String doneBy=session.getAttribute("user").toString();
	
		Company company = new Company();
		company.setName(request.getParameter("name"));
		company.setAddress(request.getParameter("address"));
		company.setWebsite(request.getParameter("website"));
		company.setContactpersonName(request.getParameter("contactPersonName"));
		company.setContactPersonNumber(request.getParameter("contactPersonNumber"));
		Company comp = null;
			try{
			    comp=companyBranchService.addNewCompany(company);
			    redirectAttributes.addFlashAttribute("status","<div class=\"success\" > Company Registration success </div>");
			
			 try{
				 auditLogService.auditLogEntryforaddModule(comp.getId(),AuditLogConstants.COMPANY_SETUP_MODULE, doneBy, AuditLogConstants.WORKFLOW_STATE_EMPTY,
							AuditLogConstants.WORKFLOW_STATE_COMPANY_ADDED, AuditLogConstants.AUDIT_LOG_CREATE_ACTION);
				 	 } catch(Exception ex){
				logger.debug(" Error in AuditLog insertation for Company ",ex);
			 }
			
			}catch(Exception ex){
				redirectAttributes.addFlashAttribute("status",
						"<div class=\"failure\" > Company Registration failed !</div>");
			   throw new Exception("Error in ",ex);
			}
	return "redirect:/showCompany";
	}

@RequestMapping(value= {"/modifyCompany"}, method=RequestMethod.POST)
public String modifyCompanyInfo(HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception{
	HttpSession session = request.getSession(true);
	String doneBy=session.getAttribute("user").toString();
	String companyId = request.getParameter("id");
	Company company = companyBranchService.getCompDetails(Integer.parseInt(companyId));
	company.setName(request.getParameter("name"));
	company.setAddress(request.getParameter("address"));
	company.setWebsite(request.getParameter("website"));
	company.setContactpersonName(request.getParameter("contactPersonName"));
	company.setContactPersonNumber(request.getParameter("contactPersonNumber"));
	
	Company comp = null;	
	try{
		comp=companyBranchService.modifyCompany(company);
		redirectAttributes.addFlashAttribute("status",
						"<div class=\"success\" > Company Modification success </div>");
		try{
		   	 auditLogService.auditLogEntryforupdateModule(Integer.parseInt(companyId),AuditLogConstants.COMPANY_SETUP_MODULE, doneBy, 
		   			AuditLogConstants.WORKFLOW_STATE_COMPANY_ADDED,AuditLogConstants.WORKFLOW_STATE_COMPANY_MODIFIED, AuditLogConstants.AUDIT_LOG_EDIT_ACTION);
		   			   	
		   	}catch(Exception ex){
		   		logger.debug(" Error in AuditLog during Editing in Company ",ex);
		   	}
		}catch(Exception ex){
			redirectAttributes.addFlashAttribute("status",
					"<div class=\"failure\" > Company Modification failed !</div>");
			throw new Exception("Error in "+ex.getClass() +" "+ ex.getMessage() +" "+ ex.getCause());
		}	
  return "redirect:/showCompany";
}
	

	
	@RequestMapping(value= {"/showBranches"}, method=RequestMethod.GET)
	public String getBranchHandler(HttpServletRequest request, Map<String,Object> map) throws Exception{
		 Company comp = companyBranchService.getCompany();
		 request.getServletContext().setAttribute("branchesByCompanyId", comp.getBranches());
		return "branch";
	}
	
	@RequestMapping(value= {"/addBranch"}, method=RequestMethod.POST)
	public String addBranch(HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception{
		HttpSession session = request.getSession(true);
		String doneBy=session.getAttribute("user").toString();
		String companyId = request.getParameter("companyId");
		String branchLocation = request.getParameter("branchLocation");
		Company comp = null;
		   try{         /* adding branches to company */
	         comp = companyBranchService.addBranchToCompany(companyId,branchLocation);
	         redirectAttributes.addFlashAttribute("status",
				"<div class=\"success\" > Branch Registration success </div>");
			 /*try{
				 auditLogService.auditLogEntryforaddModule(comp.getId(),AuditLogConstants.COMPANY_BRANCH_MODULE, doneBy, AuditLogConstants.WORKFLOW_STATE_EMPTY,
							AuditLogConstants.WORKFLOW_STATE_BRANCH_ADDED, AuditLogConstants.AUDIT_LOG_CREATE_ACTION);
				 	 } catch(Exception ex){
				logger.debug(" Error in AuditLog insertation for Company ",ex);
			 }*/
		}catch(Exception ex){
			redirectAttributes.addFlashAttribute("status",
					"<div class=\"failure\" > Branch Registration failed !</div>");
			  }
		   		   
		return "redirect:/showBranches";
		}
	
	@RequestMapping(value= {"/modifyBranch"}, method=RequestMethod.POST)
	public String modifyBranch(HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception{
		HttpSession session = request.getSession(true);
		String doneBy=session.getAttribute("user").toString();
		
		String branchId = request.getParameter("id");
		String branchLocation = request.getParameter("branchLocation");
		
		Branch branch = companyBranchService.getBranchById(Integer.parseInt(branchId));
		branch.setLocation(branchLocation);
		
		
		   try{         /* adding branches to company */
	         companyBranchService.modifyBranch(branch);
	         redirectAttributes.addFlashAttribute("status",
				"<div class=\"success\" > Branch Registration success </div>");
			 try{
			   	 auditLogService.auditLogEntryforupdateModule(Integer.parseInt(branchId),AuditLogConstants.COMPANY_BRANCH_MODULE, doneBy, 
			   			AuditLogConstants.WORKFLOW_STATE_BRANCH_ADDED,AuditLogConstants.WORKFLOW_STATE_BRANCH_MODIFIED, AuditLogConstants.AUDIT_LOG_EDIT_ACTION);
			 
			   	}catch(Exception ex){
			   		logger.debug(" Error in AuditLog during Editing in Branch ",ex);
			   	}
		}catch(Exception ex){
			redirectAttributes.addFlashAttribute("status",
					"<div class=\"failure\" > Branch Registration failed !</div>");
			  }
		   		   	   
		 return "redirect:/showBranches";
		}

	@RequestMapping(value= {"/showSite"}, method=RequestMethod.GET)
	public String getSiteHandler(HttpServletRequest request, 
			/*@RequestParam("branchId") int branchId,*/	Map<String,Object> map) throws Exception{
		String bId=request.getParameter("branchId");
		String cId=request.getParameter("companyId");
		if (bId != null && !bId.equals("")) {
			try{
			List<Site> sites = siteService.getSites(Integer.parseInt(bId));
			map.put("siteList", sites);	
			//map.put("branchLocation",companyBranchService.getBranchById(Integer.parseInt(bId)));
	}	catch(Exception ex){
		throw new Exception("Error in "+ex.getClass() +" "+ ex.getMessage() +" "+ ex.getCause());
	}
		}
		
		Company company = companyBranchService.getCompDetails(Integer.parseInt(cId));
		request.getServletContext().setAttribute("companyById", companyBranchService.getCompDetails(Integer.parseInt(cId)));
		request.getServletContext().setAttribute("branchById",companyBranchService.getBranchById(Integer.parseInt(bId)));
		return "site";
	}
	
	@RequestMapping(value= {"/addSite"}, method=RequestMethod.POST)
	public String addSite(HttpSession session,HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception{
		String doneBy = session.getAttribute("user").toString();
		String companyId=request.getParameter("companyId");
		String branchId=request.getParameter("branchId");
		Branch branch=null;
		Site site=null;
				try{
					
		//List<Site> sites=siteService.getSites(Integer.parseInt(branchId));
		// site=sites.get(0);				
		site=new Site();
		site.setSiteName(request.getParameter("siteName"));
		Landmark lm=landmarkService.getLandmarkById("2122");
		site.setLandmarkBean(lm);
		site.setNightShiftStart(request.getParameter("night_shift_start"));
		site.setNightShiftEnd(request.getParameter("night_shift_end"));
		site.setLadySecurity(Integer.parseInt(request.getParameter("lady_security")));
		//dto.setDoneBy(doneBy);
		branch=siteService.addSiteToBranch(site,Integer.parseInt(branchId));
		try{
			 auditLogService.auditLogEntryforaddModule(branch.getId(),AuditLogConstants.SITE_MODULE, doneBy, AuditLogConstants.WORKFLOW_STATE_EMPTY,
					 AuditLogConstants.WORKFLOW_STATE_SITE_ADDED, AuditLogConstants.AUDIT_LOG_CREATE_ACTION);
			 	 } catch(Exception ex){
			 		 	logger.debug(" Error in AuditLog insertation for Company ",ex);
			 	 }
		
				
		redirectAttributes.addFlashAttribute("status","<div class='success' >Site Added Successfully </div>");
		
				
		}catch(Exception ex){ 
			redirectAttributes.addFlashAttribute("status",
					"<div class='failure' >Site Insertion Failed ! </div>");
			logger.error("Error",ex);
		
			}
    
		return "redirect:/showSite?companyId=" +companyId+ "&branchId=" + branchId;
		}
	@RequestMapping(value= {"/updateSite"}, method=RequestMethod.POST)
	public String siteUpdateHandler(HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception{
		HttpSession session = request.getSession();
		String companyId=request.getParameter("companyId");
			String branchId=request.getParameter("branchId");
		HttpSession session1 = request.getSession(true);
		String doneBy = session1.getAttribute("user").toString();
		
		logger.debug("Comapny ID : " + companyId + " Branch Id : " + branchId+"ID :"+request.getParameter("id"));
		
		Site site=siteService.getSiteById(Integer.valueOf(request.getParameter("id")));
		Landmark landmark=landmarkService.getLandmarkById(request.getParameter("landMarkID"));
		
		site.setSiteName(request.getParameter("siteName"));
		site.setNightShiftStart(request.getParameter("night_shift_start"));
		site.setNightShiftEnd(request.getParameter("night_shift_end"));
		site.setLadySecurity(Integer.valueOf(request.getParameter("lady_security")));
		site.setLandmarkBean(landmark);
		Site s=siteService.updateSite(site);
	
		if(s.getId()>0){
			redirectAttributes.addFlashAttribute("status","<div class='success' >Site Modified Successfully </div>");
			try{
				 auditLogService.auditLogEntryforupdateModule(site.getId(),AuditLogConstants.SITE_MODULE, doneBy, AuditLogConstants.WORKFLOW_STATE_SITE_ADDED,
						 AuditLogConstants.WORKFLOW_STATE_SITE_MODIFIED, AuditLogConstants.AUDIT_LOG_EDIT_ACTION);
				 	 } catch(Exception ex){
				logger.debug(" Error in AuditLog insertation for Company ",ex);
			 }
			
		}

		else{
			redirectAttributes.addFlashAttribute("status",
					"<div class='failure' >Site Modification Failed ! </div>");
		}
		

		return "redirect:/showSite?companyId=" +companyId+ "&branchId=" + branchId;
	
	}
		
	@RequestMapping(value= {"/showShiftTime"}, method=RequestMethod.GET)
	public String getShiftTimeHandler(Map<String,Object> map) throws Exception{
	List<Logtime> generalLogtimes=logTimeService.getAllGeneralLogtime();
	map.put("generalLogtimes",generalLogtimes);
	
	return "logtime_list";
	}
	
	@RequestMapping(value= {"/assignShiftToProcess"}, method=RequestMethod.GET)
	public String assignShiftToProcessHandler(HttpServletRequest request,Map<String,Object> map) throws Exception{
			Logtime lt=logTimeService.getLogtimeById(request.getParameter("id"));
			map.put("logtime", lt);
	return "log_time_modify";
	}
	
	@RequestMapping(value= {"/addProjectToTime"}, method=RequestMethod.POST)
	public String addProjectToTimeHandler(HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception{
		HttpSession session = request.getSession(true);
    	String DoneBy = session.getAttribute("user").toString();
    	String path = "showShiftTime";
		
    		try{    			
    			String project=request.getParameter("project");
    			String projectdesc=request.getParameter("projectdesc");
    			String timeId=request.getParameter("id");
    			   			
    			Logtime ltVal=logTimeService.mapTimeAndProject(project,timeId);
    			    			
    			/*Logtime lt=logTimeService.getLogtimeById(request.getParameter("id"));
    			Atsconnect ats=logTimeService.getProjectsById(project);
    			
    			path="assignShiftToProcess?id=" + timeId;
    		   			
    			Logtime ltVal=logTimeService.mapTimeAndProject(ats,lt);*/
    			
    			if(ltVal.getAtsconnects()==null){
    				redirectAttributes.addFlashAttribute("shiftType", "Shared");
    			}else{
    				redirectAttributes.addFlashAttribute("shiftType", "Project");
    			}
    			
    			
    			if (ltVal!=null){
    				redirectAttributes.addFlashAttribute("status",
							"<div class=\"success\" width=\"100%\" > Shift time assigned for the project " + projectdesc + " </div>");
    			}else{
    				redirectAttributes.addFlashAttribute("status",
							"<div class=\"failure\" > Shift time assignment failure !</div>");
				}
    			
    		}catch(Exception e){
    			logger.error("Error in ",e);
    		}
    		
    		
	return "redirect:/"+path;
	}
	
	@RequestMapping(value= {"/logTimeModify"}, method=RequestMethod.POST)
	public String logTimeModifyHandler(HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception{
		HttpSession session = request.getSession(true);
		String doneBy = session.getAttribute("user").toString();
		Logtime ltUpdated=null;
		String logTime= request.getParameter("logTime");
		Logtime lt=logTimeService.getLogtimeByIdAndStatus(request.getParameter("id"));
		if(!lt.getLogtime().equalsIgnoreCase(logTime)){
				lt.setLogtime(logTime);
				ltUpdated=logTimeService.updateLogtime(lt);
				if(ltUpdated!=null){
					redirectAttributes.addFlashAttribute("status","<div class='success' >LogTime Updated Successfully </div>");
				}
				else{
					redirectAttributes.addFlashAttribute("status","<div class='success' >LogTime Updation Failed </div>");
				}
		}	else{
			redirectAttributes.addFlashAttribute("status","<div class='success' >Logtime already exist.. </div>");
		}
		
		return "redirect:/showShiftTime";
			
	}
	@RequestMapping(value= {"/getProjectCall"}, method=RequestMethod.GET)
	public String getProjectCallHandler() throws Exception{
		logger.debug("In GetProjectCall");
			return "getproject";
	}
	
	@RequestMapping(value= {"/getProject"}, method=RequestMethod.GET)
	public @ResponseBody String ajaxOnGetProject(HttpServletRequest request,Map<String,Object> map) throws Exception{
	
		String type=request.getParameter("type");
		String projectCode =  request.getParameter("projectCode");
		String projectName =  request.getParameter("projectName");	
		String retValue = "";
		List<Atsconnect> projectList = new ArrayList<Atsconnect>();
		
		if (projectCode==null || projectCode.equals("")) {
			projectList = logTimeService.getProjectsByName(projectName);
		} else {
			projectList = logTimeService.getProjectsByCode(projectCode);
		}
		
		for (Atsconnect ats : projectList) {
			if(type.equalsIgnoreCase("project"))
			{
			retValue += "<tr><td>"+ats.getProject() +"</td>" +
					"<td id='"+ats.getId()+"projecttd'>"+ats.getDescription() +"</td>" +
					"<td><a href='#' onclick='closeWindow(\""+ats.getId()+"\",\""+ats.getProject()+"\")'>Select</a></td></tr>";
			}
			else
			{
				retValue += "<tr><td>"+ats.getProject() +"</td>" +
						"<td id='"+ats.getId()+"projecttd'>"+ats.getDescription() +"</td>" +
						"<td><a href='#' onclick='closeWindow(\""+ats.getId()+"\",\""+ats.getProject()+"\")'>Select</a></td></tr>";	
			}
		}
		return retValue;
	

	}
	
	@RequestMapping(value= {"/deleteLogModify"}, method=RequestMethod.GET)
	public String enabledDisabledShiftTimeHandler(HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception{
		HttpSession session = request.getSession(true);
		String doneBy = session.getAttribute("user").toString();
		String previousstate = null, currentworkstate = null;
		String id;
		String status;
		String flag="";

		try {
			id=request.getParameter("id");
			status=request.getParameter("status");	
			Logtime lt=logTimeService.getLogtimeById(id);
			Logtime ltUpdated=null;
					
			if (status.equals("disable")) {
				lt.setStatus("inactive");
				ltUpdated=logTimeService.deleteLogTime(lt);
				flag="disable";
			} else {
				lt.setStatus("active");
				ltUpdated=logTimeService.deleteLogTime(lt);
				flag="enable";
			}
			
			if (ltUpdated.getId() > 0) {
				redirectAttributes.addFlashAttribute("status",
						"<div class=\"success\" > LogTime enabled/disabled successfully </div>");
			} else {
				redirectAttributes.addFlashAttribute("status",
						"<div class=\"failure\" > LogTime enabled/disabled failed !</div>");
			}
			
			if(ltUpdated.getLogtype().equalsIgnoreCase("IN")){
				if(flag.equals("disable"))
				{
				previousstate = AuditLogConstants.WORK_FLOW_STATE_ENABLE_LOGIN;
				currentworkstate = AuditLogConstants.WORK_FLOW_STATE_DISABLE_LOGIN;
				}
				else
				{
					currentworkstate = AuditLogConstants.WORK_FLOW_STATE_ENABLE_LOGIN;
					previousstate = AuditLogConstants.WORK_FLOW_STATE_DISABLE_LOGIN;
				}
			}
				
				
			if(ltUpdated.getLogtype().equalsIgnoreCase("OUT")){
				if(flag.equals("disable"))
				{
				previousstate = AuditLogConstants.WORK_FLOW_STATE_ENABLE_LOGOUT;
				currentworkstate = AuditLogConstants.WORK_FLOW_STATE_DISABLE_LOGOUT;
				}else
				{
					currentworkstate = AuditLogConstants.WORK_FLOW_STATE_ENABLE_LOGOUT;
					previousstate = AuditLogConstants.WORK_FLOW_STATE_DISABLE_LOGOUT;
				}
			}
			
			try{
				auditLogService.auditLogEntryforupdateModule(ltUpdated.getId(),AuditLogConstants.SHIFTTIME_MODULE, doneBy,
						previousstate, currentworkstate,
						AuditLogConstants.AUDIT_LOG_EDIT_ACTION);
			}catch(Exception ex){
				logger.error("Error In ",ex);
			}

			
		} catch (Exception ex) {
			redirectAttributes.addFlashAttribute("status",
					"<div class=\"failure\" > LogTime enabled/disabled failed !</div>");

		}
	return "redirect:/showShiftTime";
	}
	
	@RequestMapping(value= {"/addLogTime"}, method=RequestMethod.POST)
	public String addLogTimeHandler(HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception{
		HttpSession session = request.getSession(true);
		String doneBy = session.getAttribute("user").toString();
		String logTime = request.getParameter("logTime");
		String logtype = request.getParameter("logtype");
		Logtime logtime=null;
		String errorMessage="";
		String curworkstate = AuditLogConstants.WORK_FLOW_STATE_CREATE_LOGOUT;
		try {
			logtime=new Logtime();
			logtime.setLogtime(logTime);
			logtime.setLogtype(logtype);
			logtime.setStatus("active");
			
			if(OtherFunctions.isEmpty(logTime)) {
				errorMessage = "Log Time is empty";
			} else if(OtherFunctions.isEmpty(logtype)) {
				errorMessage = "Log Type is empty";
			} else{
				   try{
				Logtime lt=logTimeService.insertLogtime(logtime);
				redirectAttributes.addFlashAttribute("status",
						"<div class=\"success\" width=\"100%\" > Time insertion successful</div>");
				try{
					if (lt.getLogtype().equals("IN")) {
						curworkstate = AuditLogConstants.WORK_FLOW_STATE_CREATE_LOGIN;
					}
					 auditLogService.auditLogEntryforaddModule(lt.getId(),AuditLogConstants.SHIFTTIME_MODULE, doneBy, AuditLogConstants.WORKFLOW_STATE_EMPTY,
							 curworkstate, AuditLogConstants.AUDIT_LOG_CREATE_ACTION);
				 } catch(Exception ex){
					logger.error(" Error in AuditLog insertation for vendormaster ",ex);
				 }
				}catch(Exception ex){
					redirectAttributes.addFlashAttribute("failure",
							"<div class=\"success\" width=\"100%\" > Time insertion failed !</div>");
					
					logger.error("Error IN ",ex);
				}
				   
			}
			
			if(!errorMessage.equals("")) {
				redirectAttributes.addFlashAttribute("failure",
						"<div class=\"success\" width=\"100%\" > Validation Error : " + errorMessage + "</div>");
			}
									
		} catch (Exception ex) {
			logger.error("Error In ",ex);
				}
		
		return "redirect:/showShiftTime";
	}
	
	
	@RequestMapping(value= {"/showSiteShift"}, method=RequestMethod.GET)
	public String getSiteShift(Map<String,Object> map) throws Exception{
		
	map.put("siteList",siteService.getSites());
	  return "site_shift";
	}
	
	@RequestMapping(value= {"/setSiteShift"}, method=RequestMethod.POST)
	public String setSiteShiftSubmission(HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception{
		String[] shifts=request.getParameterValues("selectedshift");
		String siteId=request.getParameter("siteId");
		SiteShift ss=null;
		SiteShift updatedSS=null;
		try {
			Site site=siteService.getSiteById(Integer.valueOf(siteId));
			for (String logTimeId : shifts) {
				ss=new SiteShift();
				ss.setSite(site);
				ss.setLogtime(logTimeService.getLogtimeById(logTimeId));
				ss.setCombainroute("no");
				ss.setStatus("active");
				updatedSS=siteService.updateSiteShift(ss);
				
			}

			
			if (updatedSS!=null) {		
				redirectAttributes.addFlashAttribute("status",
								"<div class=\"success\" >  Site Shift Updation Successful </div>");
			} else {
				redirectAttributes.addFlashAttribute("status",
								"<div class=\"failure\" > Site Shift Updation Failed </div>");
			}

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("status",	"<div class=\"failure\" > Site Shift Updation Failed </div>");
			logger.error("Error IN ",e);
		}
		
	  return "redirect:/showSiteShift";
	}
	
	
	@RequestMapping(value= {"/getSiteShift"}, method=RequestMethod.GET)
	public @ResponseBody String getShiftBySite(HttpServletRequest request,HttpServletResponse response,Map<String,Object> map) throws Exception{
		
		int siteId = Integer.valueOf(request.getParameter("siteId")).intValue();
		String source = request.getParameter("src");
		String retString = "|";
		String remainPart="";
		List<Logtime> primaryLogTimes =new ArrayList<Logtime>();
		List<Logtime> combinedLogTimes =new ArrayList<Logtime>();
		List<Logtime> allLogTimes = logTimeService.getAllGeneralLogtime();
		Site site=siteService.getSiteById(siteId);
		if(source==null)
		{
				
		for(Logtime eachLogtime : allLogTimes){
			if(eachLogtime.getStatus().equals("active")){
				List<SiteShift> siteShifts = eachLogtime.getSiteShifts();
				if(siteShifts != null && siteShifts.size() > 0){
					for(SiteShift eachSiteShift : siteShifts){
						if(eachSiteShift.getSite().getId()== siteId  && eachSiteShift.getStatus().equalsIgnoreCase("active")){
							if(eachSiteShift.getCombainroute().equalsIgnoreCase("no")){
								primaryLogTimes.add(eachLogtime);
							}else{
								combinedLogTimes.add(eachLogtime);
							}
						}
					 }		
						
				}
			}
		}	
		
		/*List<SiteShift> siteLogtimes=site.getSiteShifts();
		for(SiteShift ss: siteLogtimes){
			if(ss.getStatus().equals("active") || ss.getStatus()!=null){
				if(ss.getCombainroute())
				Logtime lt=ss.getLogtime();
					if(lt.getStatus().equals("active"))
					combinedLogTimes.add(lt);
			}
		}*/
		     remainPart=""+site.getCombainedRouteOnweekoff()+"|";
		}
		else
		{
			
			for(Logtime eachLogtime : allLogTimes){
				if(eachLogtime.getStatus().equals("active")){
					List<SiteShift> siteShifts = eachLogtime.getSiteShifts();
					if(siteShifts != null && siteShifts.size() > 0){
						for(SiteShift eachSiteShift : siteShifts){
							if(eachSiteShift.getSite().getId() != siteId){
								primaryLogTimes.add(eachLogtime);
							}
							/*else{
								combinedLogTimes.add(eachLogtime);
							}*/
						 }		
							
					}else
						primaryLogTimes.add(eachLogtime);
				}
					
			}
			
			List<SiteShift> siteLogtimes=site.getSiteShifts();
			for(SiteShift ss: siteLogtimes){
				if(ss.getStatus().equals("active") || ss.getStatus()!=null){
					Logtime lt=ss.getLogtime();
						if(lt.getStatus().equals("active"))
						combinedLogTimes.add(lt);
				}
			}
		}		
		retString += "<select name='shift' id='shift' multiple='multiple'>";
		for (Logtime lt : primaryLogTimes) {
				retString += "<option value='" + lt.getId() + "'>"
						+ lt.getLogtime() + " : "
						+ lt.getLogtype() + "</option>";
			
		}
		retString +="</select>";
		retString += "|<select name='selectedshift' id='selectedshift' multiple='multiple'>";		
		for (Logtime lt : combinedLogTimes) {
				retString += "<option value='" + lt.getId() + "'>"
						+ lt.getLogtime() + " : "
						+ lt.getLogtype() + "</option>";
			}				
		
		retString +="</select>";
		
		retString += " | "+remainPart;		
		
	
	  return retString;
	}
	
	@RequestMapping(value= {"/showVehicleType"}, method=RequestMethod.GET)
	public String getVehicleType(Map<String,Object> map) throws Exception{
		
	map.put("vehicleTypeList",vehicleTypeService.getAllVehicleType());
	  return "vehicle_type_list";
	}
	
	@RequestMapping(value= {"/modifyVehicleType"}, method=RequestMethod.GET)
	public String modifyVehicleType(Map<String,Object> map,HttpServletRequest request) throws Exception{
		
		String vehicleTypeId=request.getParameter("id");
		VehicleType vehicleTypeList=vehicleTypeService.getVehicleTypeById(Integer.parseInt(vehicleTypeId));
		map.put("vehicleTypeList", vehicleTypeList);
	  return "vehicle_type_modify";
	}
	
	@RequestMapping(value= {"/newVehicleType"}, method=RequestMethod.GET)
	public String newVehicleTypeHandler() throws Exception{
		 return "vehicle_type";
	}
	
	@RequestMapping(value= {"/addingVehicleType"}, method=RequestMethod.GET)
	public String submitVehicleDetails(HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception{
		HttpSession session = request.getSession(true);
			String doneBy = session.getAttribute("user").toString();
				String vehicleType = request.getParameter("vehicleType");
		int seat = Integer.parseInt(request.getParameter("seat"));
		int sittingCapacity = Integer.parseInt(request.getParameter("sittingCapacity"));
		//VehicleType vehTyp=null;
		VehicleType vt=new VehicleType();
		vt.setType(vehicleType);
		vt.setSeat(seat);
		vt.setSitCap(sittingCapacity);
		vt.setRatePerKm(BigInteger.valueOf(0));
		vt.setRatePerTrip(BigInteger.valueOf(0));
		
		try {
			VehicleType	 vehTyp= vehicleTypeService.insertVehicleType(vt);
			if(vehTyp!=null) {
				redirectAttributes.addFlashAttribute("status",
						"<div class=\"success\" >VehicleType Addition Successfully</div>");				
				try{					
				   	 auditLogService.auditLogEntryforaddModule(vehTyp.getId(),AuditLogConstants.VEHICLE_MODULE, doneBy, 
				   			AuditLogConstants.WORKFLOW_STATE_EMPTY,AuditLogConstants.WORK_FLOW_STATE_VEHICLE_ADDED, AuditLogConstants.AUDIT_LOG_CREATE_ACTION);
				   			   	
				   	}catch(Exception ex){
				   		logger.debug("Error in AuditLog during Editing in Company ",ex);
				   	}
								
			}else {
				redirectAttributes.addFlashAttribute("status",
						"<div class=\"failure\" >VehicleType Addition Failed..</div>");
			}
		}	
		catch(Exception e){
			logger.debug("Error In ",e);
		}
			
		 return "redirect:/showVehicleType";
	}
	
	
	
	@RequestMapping(value= {"/addModifyVehicleType"}, method=RequestMethod.GET)
	public String addModifyVehicleType(HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception{
		HttpSession session = request.getSession(true);
		Employee emp = (Employee)session.getAttribute("userLoggedIn");
		String doneBy=emp.getId();
		String vehicleTypeId = request.getParameter("id");
		String vehicleTypeName = request.getParameter("vehicleType");
		int seat = Integer.parseInt(request.getParameter("seat"));
		int sittingCapacity = Integer.parseInt(request.getParameter("sittingCapacity"));
		
		try{
				
		VehicleType vt=vehicleTypeService.getVehicleTypeById(Integer.parseInt(vehicleTypeId));	
		vt.setType(vehicleTypeName);
		vt.setSeat(seat);
		vt.setSitCap(sittingCapacity);
		VehicleType vehTyp=vehicleTypeService.insertVehicleType(vt);
		redirectAttributes.addFlashAttribute("status",
						"<div class='success' >VehicleType Modified Successfully </div>");
			try{
			   	 auditLogService.auditLogEntryforupdateModule(vehTyp.getId(),AuditLogConstants.VEHICLE_MODULE, doneBy, 
			   			AuditLogConstants.WORK_FLOW_STATE_VEHICLE_ADDED,AuditLogConstants.WORK_FLOW_STATE_VEHICLE_MODIFIED, AuditLogConstants.AUDIT_LOG_EDIT_ACTION);
			   			   	
			   	}catch(Exception ex){
			   		logger.debug(" Error in AuditLog during Editing in Company ",ex);
			   	}
			
			
		}catch(Exception ex){
			logger.error("Error in VehicleType modified",ex);
			redirectAttributes.addFlashAttribute("status",
					"<div class='failure' >VehicleType Modification Failed ! </div>");
		}
			
	  return "redirect:/showVehicleType";
	}
	
	
	@RequestMapping(value= {"/showVehicle"})
	public String showVehicleHandler(Map<String,Object> map,HttpServletRequest request) throws Exception{
		List<Vehicle> vehicles=vehicleTypeService.getAllVehicle();
		map.put("vehicles", vehicles);
		return "view_vehicle";
	}
	
	@RequestMapping(value= {"/updateVehicleStatus"}, method=RequestMethod.POST)
	public String updateVehicleStatusHandler(HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception{
		HttpSession session=request.getSession();
		String id=request.getParameter("vehid");
		String status=request.getParameter("active");
		Vehicle v=vehicleTypeService.getVehicleByIdNotStatus(id);
		if(status.equalsIgnoreCase("a"))
		{
			status="c";
		}
		else
		{
			
			status="a";
		}
		v.setStatus(status);
		Vehicle veh=vehicleTypeService.updateVehicleStatus(v);
		if(veh!=null)
		{
			redirectAttributes.addFlashAttribute("status",
					"<div class='success'>Status Updated Successfully</div");
		}
		else
		{
			redirectAttributes.addFlashAttribute("status",
					"<div class='failure'>Status Updation Failed</div");
		}
				
		return "redirect:/showVehicle";
	}
	
	@RequestMapping(value= {"/addVehicle"})
	public String addVehicleHandler(Map<String,Object> map,HttpServletRequest request) throws Exception{
		List<VehicleType> vehicleTypes=vehicleTypeService.getAllVehicleType();
		List<Vendormaster> vendorMasList = vendorMasterService.getMasterVendorlist();
		map.put("vehicleTypes", vehicleTypes);
		map.put("vendorMasList", vendorMasList);
		return "addvehicle";
	}
	
	@RequestMapping(value= {"/addVehicleSubmission"}, method=RequestMethod.POST)
	public String addVehicleSubmissionHandler(HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception{
		HttpSession session = request.getSession(true);
		String doneBy = session.getAttribute("user").toString();
		String vehicleTypeId = request.getParameter("vehicleType");
		String vehicleNo = request.getParameter("vehicleNo");
		String vendorId=request.getParameter("vendor");
		Vehicle v=new Vehicle();
		VehicleType vt=vehicleTypeService.getVehicleTypeById(Integer.parseInt(vehicleTypeId));
		Vendormaster vm=vendorMasterService.getVendorMasterById(Integer.parseInt(vendorId));
		v.setRegNo(vehicleNo);
		v.setVehicleType(vt);
		v.setVendormaster(vm);
		v.setStatus("a");
		
		try {			
			Vehicle vehicle=vehicleTypeService.addVehicle(v);
			redirectAttributes.addFlashAttribute("status",
					"<div class='success' >Vehicle Added Successfully </div>");
				
		}catch(Exception ex){
		logger.error("Error in VehicleType modified",ex);
		redirectAttributes.addFlashAttribute("status",
				"<div class='failure' >Vehicle Addition Failed ! </div>");
	}	
		return "redirect:/showVehicle";
		}
	
	
	@RequestMapping(value= {"/showSiteVehicle"})
	public String showSiteVehicleHandler(Map<String,Object> map,HttpServletRequest request) throws Exception{
		List<VehicleType> vehicleTypes=vehicleTypeService.getAllVehicleType();
		List<Site> sites = siteService.getSites();
		map.put("vehicleTypes", vehicleTypes);
		map.put("sites",sites);
		return "site_vehicle_type";
	}
	
	@RequestMapping(value= {"/getVehicleNotInSite"}, method=RequestMethod.POST)
	public @ResponseBody String ajaxForGetVehicleNotInSite(HttpServletRequest request) throws Exception{
		String siteId =request.getParameter("siteId");
		
		String options = "";
		
		List<VehicleType> vehicleTypes = vehicleTypeService.getAllVehicleType();
				
		for (VehicleType vt : vehicleTypes) {
				List<Site> sites=vt.getSites();
				
			if(sites==null || sites.size()==0){
					
				options += "<option value='" + vt.getId() + "'>" + vt.getType()
									+ "</option>";
				}
		}			
							
		options += "";
		options += "$ ";
		
		for (VehicleType vt : vehicleTypes) {
				
			List<Site> sites=vt.getSites();
				
			if(sites!=null && sites.size()>0){
					
				for(Site s : sites){
						
					if(s.getId()==Integer.parseInt(siteId)){
							
						options += "<option value='" + vt.getId() + "'>" + vt.getType()
									+ "</option>";
					}
				}	
			}		
		}
		options += "";
		
		return options;
	}
	
	@RequestMapping(value= {"/siteVehicle"}, method=RequestMethod.GET)
	public String siteVehicleSubmission(HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception{
		HttpSession session = request.getSession(true);
		String doneBy = session.getAttribute("user").toString();
		String vehicleTypeIds[] = request.getParameterValues("chosenVehicleType");
		String siteId = request.getParameter("siteId");
		Site site=siteService.getSiteById(Integer.parseInt(siteId));
		VehicleType vehTyp=null;
		
		for (String vehicleType : vehicleTypeIds) {
			/*VehicleType vt=vehicleTypeService.getVehicleTypeById(Integer.parseInt(vehicleType));
			List<Site> sites=vt.getSites();
			sites.add(site);
			vt.setSites(sites);
			vehTyp=vehicleTypeService.setSiteVehicle(vt);*/
			vehTyp=vehicleTypeService.setSiteVehicle(vehicleType,siteId);
			
		}
		
		if (vehTyp!=null) {
			redirectAttributes.addFlashAttribute("status",
							"<div class='success'>Site Vehicle Assign Successfully</div");
		} else {
			redirectAttributes.addFlashAttribute("status",
					"<div class='failure'>Site Vehicle Assign Failed</div");
		}

		return "redirect:/showSiteVehicle";
	}
	
	@RequestMapping(value= {"/showDriver"})
	public String showDriverDetailsHandler(Map<String,Object> map) throws Exception{
		map.put("driverList", driverService.getDriversDetails());
		map.put("vendorMasterList",vendorMasterService.getMasterVendorlist());
		return "add_driver";
	}
	
	@RequestMapping(value= {"/addDriver"},method=RequestMethod.POST)
	public String addDriverHandler(HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception{
		HttpSession session = request.getSession(true);
		String doneBy = session.getAttribute("user").toString();
		String driverName = request.getParameter("name");
		String address1 = request.getParameter("address1");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String vendor=request.getParameter("vendor");
		String contactNo=request.getParameter("contactNo");
		logger.debug("UUUUUUUUUUUUUU"+doneBy);	
		
		try {
			Vendormaster vm=vendorMasterService.getVendorMasterById(Integer.parseInt(vendor));
			Driver driver=new Driver();
			driver.setName(driverName);
			driver.setAddress(address1);
			driver.setUsername(username);
			driver.setPassword(password);
			driver.setContact(contactNo);
			driver.setVendormaster(vm);
			driver.setStatus("a");
			Driver d = driverService.addDriver(driver);
			 
			if(d!=null) {
				redirectAttributes.addFlashAttribute("status",
						"<div class=\"success\" width=\"100%\" > Driver added Successfully..</div>");
				
			} else {
				redirectAttributes.addFlashAttribute("status",
						"<div class=\"failure\" width=\"100%\" >  Driver addition Failed..  </div>");
			 }
			 
			
		} catch (Exception ex) {
			redirectAttributes.addFlashAttribute("status",
					"<div class=\"failure\" width=\"100%\" >  Driver addition throwing Exception..  </div>");
			logger.error("Error in ",ex);
			return "redirect:/showDriver";
		}
		return "redirect:/showDriver";
	}	
	
		@RequestMapping(value= {"/updateDriver"})
		public String updateDriverHandler(HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception{
			HttpSession session = request.getSession(true);
			String doneBy = session.getAttribute("user").toString();
			String driverName = request.getParameter("name");
			String driverId = request.getParameter("driverId");
			String address1 = request.getParameter("address1");
			String address2=request.getParameter("address2");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String vendor=request.getParameter("vendor");
			String contactNo=request.getParameter("contactNo");
		
			try {
				Driver driver=driverService.getDriverById(driverId);
				Vendormaster vm=vendorMasterService.getVendorMasterById(Integer.parseInt(vendor));
				driver.setName(driverName);
				driver.setAddress(address1);
				driver.setUsername(username);
				driver.setPassword(password);
				driver.setContact(contactNo);
				driver.setVendormaster(vm);
				driver.setStatus("a");
				Driver d = driverService.updateDriver(driver);
				if(d!=null) {
					redirectAttributes.addFlashAttribute("status",
							"<div class=\"success\" width=\"100%\" > Driver Updated successful</div>");
				}else {
					redirectAttributes.addFlashAttribute("status",
							"<div class=\"failure\" width=\"100%\" > Driver Update Failed</div>");
				}
				
			} catch (Exception ex) {
				
				redirectAttributes.addFlashAttribute("status","<div class=\"failure\" width=\"100%\" > Error :"+ex+"</div>");
				return "redirect:/showDriver";
			}
		return "redirect:/showDriver";
	}
		
		@RequestMapping(value= {"/showDriverVehicle"})
		public String showDriverVehicleHandler(Map<String,Object> map) throws Exception{
			List<Vendormaster> vmList= vendorMasterService.getMasterVendorlist();
			map.put("vmList",vmList);
			return "driver_vehicle";
		}
		
		@RequestMapping(value= {"/vendorBaseVehicle"},method=RequestMethod.POST)
		public @ResponseBody String vendorBaseVehicleHandler(HttpServletRequest request,Map<String,Object> map) throws Exception{
		String vendorId = request.getParameter("vendor");
		List<Vehicle> vehicles=vendorMasterService.getVendorVehiclesById(vendorId);
		String vehicle="";
		for (Vehicle v : vehicles) {
			vehicle=vehicle+"<option value="+v.getId()+">"+v.getRegNo()+"</option>";
		}
		return vehicle;
	}
		
		@RequestMapping(value= {"/getSitesByBranch"},method=RequestMethod.POST)
		public @ResponseBody String getSitesByBranchHandler(HttpServletRequest request,Map<String,Object> map) throws Exception{
		String branchId = request.getParameter("branchId");
		Branch branch=companyBranchService.getBranchById(Integer.valueOf(branchId));
		List<Site> sites=branch.getSites();
		String retVal="";
		if(sites!=null && !(sites.isEmpty())){
		for (Site s : sites) {
			retVal=retVal+"<option value="+s.getId()+">"+s.getSiteName()+"</option>";
		}
		}
		return retVal;
	}
		
		
		@RequestMapping(value= {"/driverVehicle"},method=RequestMethod.POST)
		public @ResponseBody String driverVehicleHandler(HttpServletRequest request,Map<String,Object> map) throws Exception{
			String vehicle = request.getParameter("vehicle");
			String submitAction = request.getParameter("source");
			HttpSession session=request.getSession();
			String returnString="";
	if(submitAction==null)
	{		
			Vehicle veh=vehicleTypeService.getVehicleById(vehicle);
			List<DriverVehicle> driversVehicle=veh.getDriverVehicles();
			List<Driver> drivers=driverService.getDriversDetails();
			returnString="<table border='1'><th>DriverName</th><th>Address</th><th>Contact</th><th>Veh#</th><th>Vehicle type</th><th>Vendor</th></tr>";
			String returnStringSecondPart="";
			
			
			if(driversVehicle!=null && driversVehicle.size()>0)
			{
				for(DriverVehicle dv:driversVehicle)
				{
					logger.debug("IN DriverVehicle List");		
				returnString+="<tr><td>"+dv.getDriver().getName()+"</td>";
				returnString+="<td>"+dv.getDriver().getAddress()+"</td>";
				returnString+="<td>"+dv.getDriver().getContact()+"</td>";
				returnString+="<td>"+dv.getVehicle().getRegNo()+"</td>";
				returnString+="<td>"+dv.getVehicle().getVehicleType().getType()+"</td>";
				returnString+="<td>"+dv.getDriver().getVendormaster().getCompany()+"</td></tr>";
				returnStringSecondPart+="<option value='"+dv.getDriver().getId()+"'>"+dv.getDriver().getName()+" : "+dv.getDriver().getVendormaster().getCompany()+"</option>";
				}
			
		    }
			else
			{
				returnString+="<tr><td>No driver Associated with the vehicle</td></tr>";	
			}
			
			
			
			
			returnStringSecondPart+=" | ";
			for(Driver d : drivers)
			{	logger.debug("IN Driver List");			
				if(d.getDriverVehicles()==null || d.getDriverVehicles().size()==0){
					returnStringSecondPart+=" <option value='"+d.getId()+"'>"+d.getName()+" : "+d.getVendormaster().getCompany()+"</option>";	
				}
			}
			
			returnString+="</table>"+" | "+returnStringSecondPart;
			
						
	}
	return "Current Details |"+returnString;
  }
		
		@RequestMapping(value= {"/driverVehicleSubmit"},method=RequestMethod.POST)
		public String driverVehicleSubmitHandler(HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception{
			String vehicle = request.getParameter("vehicle");
			String vehicleDrivers[]=request.getParameterValues("vehicleDrivers");
			DriverVehicle drVeh=vehicleTypeService.addDriverVehicle(vehicle,vehicleDrivers);
			if(drVeh!=null)
			{
				redirectAttributes.addFlashAttribute("status", "Driver Vehicle Association Successful");	
			}
			else
			{
				redirectAttributes.addFlashAttribute("status", "Driver Vehicle Association Failure");	
			}
			return "redirect:/showDriverVehicle";	
		}

		
		@RequestMapping(value= {"/showTimesSloat"})
		public String showTimeSlotHandler(HttpServletRequest request,Map<String,Object> map) throws Exception{
			logger.debug("IN showtimeSloat handler");
			map.put("timeslots",logTimeService.getTimeSloats());
			return "showTimeSloat";
	}
		
		
		@RequestMapping(value= {"/showEscort"})
		public String showEscorthandler(HttpServletRequest request,Map<String,Object> map) throws Exception{
		List<Escort> escorts=escortService.getAllEscorts();
		List<Site> sites=siteService.getSites();
			map.put("escorts", escorts);
				map.put("sites", sites);
					return "escortSetUp";
	}
		
		@RequestMapping(value= {"/termsAndConditions"})
		public String termsAndConditionsHandler(HttpServletRequest request,Map<String,Object> map) throws Exception{
					return "termsAndConditions";
	}
		
		@RequestMapping(value= {"/addEscort"},method=RequestMethod.POST)
		public String addEscortHandler(HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception{
			HttpSession session = request.getSession(true);
			String doneBy = session.getAttribute("user").toString();
			
			String escortName = request.getParameter("name");
			//String address = request.getParameter("address");
			String escortClock = request.getParameter("escortClock");
			//String password = request.getParameter("password");
			//String email=request.getParameter("email");
			String phone=request.getParameter("phone");
			String site=request.getParameter("site");
			
			Escort es=null;
			
			try {
				Site s=siteService.getSiteById(Integer.valueOf(site));	
				es=new Escort();
				es.setName(escortName);
				//es.setAddress(address);
				es.setEscortClock(escortClock);
				//es.setPassword(password);
				//es.setEmail(email);
				es.setPhone(phone);
				es.setSiteBean(s);
				 
			boolean flag=escortService.validateAddEscort(es);
			if(flag)
			{
					  
					Escort escort=escortService.addEscort(es);
					if(escort!=null) {
						redirectAttributes.addFlashAttribute("status",
								"<div class=\"success\" width=\"100%\" >Security added successfully.... </div>");
						
					} else {
						redirectAttributes.addFlashAttribute("status",
								"<div class=\"failure\" width=\"100%\" > Security addtion Failed....</div>");
						
				 }
			}else{
						redirectAttributes.addFlashAttribute("status",
								"<div class=\"failure\" width=\"100%\" > Validation Error :"+escortService.getErrorMessage()+"</div>");
						
				 }
				 
				
			} catch (Exception ex) {
				logger.debug("Error in Dao", ex);
				
			}
			return "redirect:/showEscort";
	}
		
		@RequestMapping(value= {"/updateEscort"},method={RequestMethod.POST,RequestMethod.GET})
		public String updateEscortHandler(HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception{
			logger.debug(" Inside UpdateEscort");
			HttpSession session = request.getSession(true);
			String doneBy = session.getAttribute("user").toString();
			String id = request.getParameter("id");
			String escortName = request.getParameter("name");
			//String address = request.getParameter("address");
			String escortClock = request.getParameter("escortClock");
			//String password = request.getParameter("password");
			//String email=request.getParameter("email");
			String phone=request.getParameter("phone");
			String site=request.getParameter("site");
			
			Escort es=null;
			
			try {
				es=escortService.getEscortById(id);
				Site s=siteService.getSiteById(Integer.valueOf(site));	
				es.setName(escortName);
				//es.setAddress(address);
				es.setEscortClock(escortClock);
				//es.setPassword(password);
				//es.setEmail(email);
				es.setPhone(phone);
				es.setSiteBean(s);
						
				boolean flag=escortService.validateAddEscort(es);
				if(flag)
				{
						  
						Escort escort=escortService.updateEscort(es);
						if(escort!=null) {
							redirectAttributes.addFlashAttribute("status",
									"<div class=\"success\" width=\"100%\" >Security updation successfully.... </div>");
							
						} else {
							redirectAttributes.addFlashAttribute("status",
									"<div class=\"failure\" width=\"100%\" > Security updation Failed....</div>");
							
					 }
				}else{
							redirectAttributes.addFlashAttribute("status",
									"<div class=\"failure\" width=\"100%\" > Validation Error :"+escortService.getErrorMessage()+"</div>");
							
					 }
					 
					
				} catch (Exception ex) {
					logger.debug("Error in Dao", ex);
					
				}
				return "redirect:/showEscort";
				 
	}
		
		@RequestMapping(value= {"/employeeRegister"})
		public String showEmployeeRegistration(Map<String,Object> map) throws Exception{
			map.put("branchList",companyBranchService.getBranchs());
			map.put("sites",siteService.getSites());
		return "employee_register";
	}
		
		@RequestMapping(value= {"/addEmployee"},method=RequestMethod.POST)
		public String submitEmployeeRegistration(HttpServletRequest request,HttpSession session,RedirectAttributes redirectAttributes) throws Exception{
							
			String firstname=request.getParameter("fname");
			String lastname=request.getParameter("lname");
			String gender=request.getParameter("gender");
			String phonenumber=request.getParameter("mob");
			String emailAddress=request.getParameter("email");
			String address=request.getParameter("addr");
			String branchId=request.getParameter("branchId");
			String siteId=request.getParameter("siteId");
			Site site=siteService.getSiteById(Integer.valueOf(siteId));
			Employee emp=(Employee)session.getAttribute("userLoggedIn");
			emp.setEmployeeFirstName(firstname);
			emp.setEmployeeLastName(lastname);
			emp.setGender(gender);
			emp.setLoginId(emailAddress);
			emp.setAddress(address);
			emp.setContactNumber1(phonenumber);
			emp.setEmailAddress(emailAddress);
			emp.setDisplayname(firstname+" "+lastname);
			emp.setRegisterStatus("a");
			emp.setSiteBean(site);
			Employee employee=employeeService.insertEmployee(emp);
			if (employee!=null) {
	        
				redirectAttributes.addFlashAttribute("status",
						"<div class='success'>Employee Registration successful...</div");
			} else {
				redirectAttributes.addFlashAttribute("status",
						"<div class='failure'>Employee Registration Failed....</div");
				return "redirect:/employeeRegister";
			}
			
			if(emp.getEmpLat()==null && emp.getEmpLong()==null || (emp.getEmpLat().equals("") && emp.getEmpLong().equals("")) || (emp.getEmpLat()=="" && emp.getEmpLong()=="")){
				return "redirect:/geoTagging";
			}else{
				return "redirect:/transportRequest";
			}

	}
		@RequestMapping(value= {"/searchLandmark"}, method=RequestMethod.GET)
		public String showAPLSearch(){
			return "APLSearch";
		}
		
		
		// An ajax call comes from APLSearch.jsp
		@RequestMapping(value = {"/searchAPL"},  method=RequestMethod.POST)
		public  @ResponseBody String searchAPL(@RequestParam(value = "landMarkText") String searchText,
				@RequestParam(required = false, value = "location") String branchId, @RequestParam(required = false, value = "site") String siteId)
		{
			String response = "";
			Landmark landmarkMatched = null;
			Place placeMatched = null;
			Area areaMatched = null;
			List<Landmark> landmarksMatched = null;
			List<Place> placesMatched = null;
			List<Area> areasMatched = null;
			
			int branch = 0;
			if(siteId != null && !(siteId.isEmpty())){
				try {
					branch = siteService.getSiteById(Integer.parseInt(siteId)).getBranchBean().getId();
				}catch (Exception e) {
					logger.error("Error in fetching site for siteId "+siteId,e);
					return "";
				}
			}
			else 
				branch = Integer.parseInt(branchId);
			
			logger.debug("branch Id ::: "+branch);
			
			
			try {
			    landmarksMatched = aplService.getMatchingLandmarks(searchText);
				
				// See if the entered text matches any landmark
				if(landmarksMatched != null && landmarksMatched.size() > 0){
					for(Landmark eachLandmarkMatched : landmarksMatched){
						landmarkMatched = eachLandmarkMatched;
						placeMatched = eachLandmarkMatched.getPlaceBean();
						areaMatched = placeMatched.getAreaBean();
						if(areaMatched.getBranch().getId() == branch)
							response += landmarkMatched.getId() + ":" +areaMatched.getArea()+" ->"+placeMatched.getPlace()
							+" ->"+landmarkMatched.getLandmark() + "|";
					}
				}
				
				// See if the entered text matches any place
				placesMatched = aplService.getmatchingPlaces(searchText);
				if(placesMatched != null && placesMatched.size() > 0){
					for(Place eachPlaceMatched : placesMatched){
						placeMatched = eachPlaceMatched;
						areaMatched = placeMatched.getAreaBean();
						if(areaMatched.getBranch().getId() == branch){
							landmarksMatched = eachPlaceMatched.getLandmarks();
							if(landmarksMatched != null && landmarksMatched.size() > 0){
								for(Landmark eachLandMarkMatched : landmarksMatched){
									landmarkMatched = eachLandMarkMatched;
									response += landmarkMatched.getId() + ":" +areaMatched.getArea()+" ->"+placeMatched.getPlace()
											+" ->"+landmarkMatched.getLandmark() + "|";
								}
							}
								
						}
					}
				}
				
				// See if the entered text matches any area
				areasMatched = aplService.getMatchingAreas(searchText);
				if(areasMatched != null && areasMatched.size() > 0){
					for(Area eachAreaMatched : areasMatched){
						areaMatched = eachAreaMatched;
						if(areaMatched.getBranch().getId() == branch){
							placesMatched = areaMatched.getPlaces();
							if(placesMatched != null && placesMatched.size() >0){
								for(Place eachPlaceMatched : placesMatched){
									placeMatched = eachPlaceMatched;
									landmarksMatched = placeMatched.getLandmarks();
									if(landmarksMatched != null && landmarksMatched.size() >0){
										for(Landmark eachLandmarkMatched : landmarksMatched){
											landmarkMatched = eachLandmarkMatched;
											response += landmarkMatched.getId() + ":" +areaMatched.getArea()+" ->"+placeMatched.getPlace()
													+" ->"+landmarkMatched.getLandmark() + "|";
										}
									}
								}
							}
						}
					}
				}
				
				
			} catch (Exception e) {
				logger.error("Error in fetching the APLs ",e);
				response = "";
			}
			
			return response;
		}
		
}
