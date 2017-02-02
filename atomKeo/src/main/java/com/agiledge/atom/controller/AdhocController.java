package com.agiledge.atom.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.agiledge.atom.constants.SettingsConstant;
import com.agiledge.atom.entities.Adhoctype;
import com.agiledge.atom.entities.Role;
import com.agiledge.atom.entities.Settingsstring;
import com.agiledge.atom.service.intfc.AdhocService;
import com.agiledge.atom.service.intfc.AplService;
import com.agiledge.atom.service.intfc.AuditLogService;
import com.agiledge.atom.service.intfc.CompanyBranchService;
import com.agiledge.atom.service.intfc.DriverService;
import com.agiledge.atom.service.intfc.EscortService;
import com.agiledge.atom.service.intfc.LandmarkService;
import com.agiledge.atom.service.intfc.LogTimeService;
import com.agiledge.atom.service.intfc.RoleService;
import com.agiledge.atom.service.intfc.SiteService;
import com.agiledge.atom.service.intfc.VehicleTypeService;
import com.agiledge.atom.service.intfc.VendorMasterService;

@Controller
public class AdhocController {
	private static final Logger logger = Logger.getLogger(AdhocController.class);
	
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
	RoleService roleService;
	
	@Autowired
	DriverService driverService;	
	
	@Autowired
	VendorMasterService vendorMasterService;
	
	@Autowired
	EscortService escortService;
	
	@Autowired
	AdhocService adhocService;
		
	@RequestMapping(value= {"/adhocSetupEdit"},method={RequestMethod.GET,RequestMethod.POST})
	public String adhocSetupEditHandler(HttpServletRequest request,Map<String,Object> map) throws Exception  {
		String adhoctype = request.getParameter("adhoctype");
		String siteId = request.getParameter("siteId");
		String projectUnit = request.getParameter("projectUnit");
		
		List<Adhoctype> adhocdetailslist=null;
		
		if (adhoctype != null && adhoctype!=""){
			adhocdetailslist = adhocService.getSetupDetails(adhoctype,siteId, projectUnit);
			if (adhocdetailslist.size()==0) {
				return "redirect:/adhocSetup?adhoctype="
						+ adhoctype + "&siteId=" + siteId + "&projectUnit="
						+ projectUnit;
			} 
		}
						
		
		List<Settingsstring> ssList=adhocService.getSettingsStrings(SettingsConstant.ADHOC, SettingsConstant.TYPE, siteId,projectUnit);
		List<Settingsstring> settingsStr=new ArrayList<Settingsstring>();
		Adhoctype adhocType=adhocService.getAdhocTypeByType(adhoctype);
		for(Settingsstring ss: ssList){
		 if(adhocType!=null){
			if(ss.getKeyVal().equalsIgnoreCase(adhocType.getType())){
				if(adhocType.getSiteBean().getId()==(Integer.valueOf(siteId).intValue())){
					if(adhocType.getProjectUnit().equalsIgnoreCase("all") || adhocType.getProjectUnit().equalsIgnoreCase(projectUnit)){
						settingsStr.add(ss);
					}	
				}
			}
		}
	}	 
		ssList.addAll(settingsStr);
		List<String> atsList=adhocService.getProjectUnits();
		
		map.put("settingsStr", ssList);
		//map.put("siteById", siteService.getSiteById(Integer.parseInt(siteId)));
		map.put("sites", siteService.getSites());
		map.put("atsList", atsList);
		map.put("adhocdetailslist",adhocdetailslist);
			return "AdhocSetupEdit";
	}
	
	@RequestMapping(value= {"/adhocSetup"},method={RequestMethod.GET,RequestMethod.POST})
	public String adhocSetupHandler(HttpServletRequest request, Map<String,Object> map) throws Exception  {
		String adhoctype = request.getParameter("adhoctype");
		String siteId = request.getParameter("siteId");
		String projectUnit = request.getParameter("projectUnit");
		List<Role> roleList = roleService.getRoleList();
		List<String> atsList=adhocService.getProjectUnits();
		
		
		List<Settingsstring> ssList=adhocService.getSettingsStrings(SettingsConstant.ADHOC, SettingsConstant.TYPE, siteId,projectUnit);
		List<Settingsstring> settingsStr=new ArrayList<Settingsstring>();
		Adhoctype adhocType=adhocService.getAdhocTypeByType(adhoctype);
		for(Settingsstring ss: ssList){
			if(ss.getKeyVal().equalsIgnoreCase(adhocType.getType())){
				if(adhocType.getSiteBean().getId()==Integer.parseInt(siteId)){
					if(adhocType.getProjectUnit().equalsIgnoreCase("all") || adhocType.getProjectUnit().equalsIgnoreCase(projectUnit)){
						settingsStr.add(ss);
					}	
				}
			}
		}
		
		ssList.addAll(settingsStr);
		map.put("roleList", roleList);
		map.put("settingsStr", ssList);
		map.put("sites", siteService.getSiteById(Integer.parseInt(siteId)));
		map.put("atsList", atsList);
		return projectUnit;

	}
	
}
