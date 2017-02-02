package com.agiledge.atom.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.ServletContextAware;

import com.agiledge.atom.constants.AuditLogConstants;
import com.agiledge.atom.entities.Employee;
import com.agiledge.atom.entities.Vendor;
import com.agiledge.atom.entities.Vendormaster;
import com.agiledge.atom.service.intfc.AuditLogService;
import com.agiledge.atom.service.intfc.VendorMasterService;
import com.agiledge.atom.service.intfc.VendorService;

@SessionAttributes("status")
@Controller
public class VendorMasterController implements ServletContextAware{

	private ServletContext servletContext;

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	private final static Logger logger = Logger.getLogger(VendorMasterController.class);
			
	
	@Autowired
	private VendorMasterService vendorMasterService;
	
	@Autowired
	private VendorService vendorService;
	
	@Autowired
	AuditLogService auditLogService;
	
	//@Cacheable(value="vendorMasterResult")
	@RequestMapping(value = {"/showVendorMaster"}, method = RequestMethod.GET)
	public String vendormasterList(Map<String,Object> map) throws Exception{
		List<Vendormaster> vendorMasterSetupList = vendorMasterService.getMasterVendorlist();
		map.put("vendorMasterSetupList", vendorMasterSetupList);
			
		return "vendorMasterSetUp";
	}
	

	@RequestMapping(value = "/addVendorMaster", method = RequestMethod.POST)
	public String addVendorMaster(HttpServletRequest request, Map<String,Object> map) throws Exception {
		String errorMessage = "Vendor adding failure";
		//HttpSession session = request.getSession(true);
		//String doneBy = session.getAttribute("user").toString();
		String doneBy = "1385";
		Vendormaster vendormaster=null;
		try{
			vendormaster = new Vendormaster();
			vendormaster.setCompany(request.getParameter("name"));
			vendormaster.setAddress(request.getParameter("address"));
			vendormaster.setEmail(request.getParameter("email"));
			vendormaster.setContact(request.getParameter("contactNumber"));
			vendormaster.setStatus("active");
			Vendormaster vm = vendorMasterService.addVendorMaster(vendormaster);
						
			map.put("status","<div class=\"success\" width=\"100%\" > Vendor Master addition successful</div>");
			 try{
				 auditLogService.auditLogEntryforaddModule(vm.getId(),AuditLogConstants.VENDOR_MODULE, doneBy, AuditLogConstants.WORKFLOW_STATE_EMPTY,
							AuditLogConstants.WORKFLOW_STATE_VENDOR_MASTER_ADDED, AuditLogConstants.AUDIT_LOG_CREATE_ACTION);
			 } catch(Exception ex){
				logger.debug(" Error in AuditLog insertation for vendormaster ",ex);
			 }

			} catch(Exception ex) {
				map.put("status","<div class=\"failure\" width=\"100%\" > Vendor Master addition failure</div>");

			}
		
		return "redirect:/showVendorMaster";

	}

	@RequestMapping(value = { "/deleteVendorMaster" }, method = RequestMethod.POST)
	public String deleteVendorMaster(HttpServletRequest request,Map<String,Object> map) throws Exception {
		//HttpSession session = request.getSession(true);
		//String doneBy = session.getAttribute("user").toString();
		String doneBy = "1385";
		String id = request.getParameter("deleteid");
		int vendormasterId=Integer.parseInt(id);
		try {
			Vendormaster vm=vendorMasterService.getVendorMasterById(vendormasterId);  
			vm.setStatus("inactive");							
			vendorMasterService.deleteUpdateVendorMaster(vm);
			   map.put("status",
						"<div class=\"success\" width=\"100%\" >Deletion successful  </div>");
			   try{
				   	 auditLogService.auditLogEntryfordeleteModule(vendormasterId,AuditLogConstants.VENDOR_MODULE, doneBy, "",
				   			AuditLogConstants.WORKFLOW_STATE_VENDOR_DELETED, AuditLogConstants.AUDIT_LOG_DELETE_ACTION);
				   	}catch(Exception ex){
				   		logger.debug(" Error in AuditLog deletion for vendormaster ",ex);
				   	}
		}catch(Exception ex){
				 
				map.put("status",
								"<div class=\"failure\" width=\"100%\" >Deletion failure  </div>");
			}
		
		return "redirect:/showVendorMaster";

	}
	
	@RequestMapping(value = { "/updateVendorMaster" }, method = RequestMethod.POST)
	public String updateVendorMaster(HttpServletRequest request,Map<String,Object> map) throws Exception {
		//HttpSession session = request.getSession(true);
		//String doneBy = session.getAttribute("user").toString();
		String id=request.getParameter("id");
		String changedBy ="1385";
		int vendormasterId=Integer.parseInt(id);
		try {
			Vendormaster vm=vendorMasterService.getVendorMasterById(vendormasterId); 
			vm.setCompany(request.getParameter("name"));
			vm.setAddress(request.getParameter("address"));
			vm.setEmail(request.getParameter("email"));
			vm.setContact(request.getParameter("contactNumber"));
			vendorMasterService.deleteUpdateVendorMaster(vm);
			   map.put("status",
						"<div class=\"success\" width=\"100%\" >Vendor Company updated successfully  </div>");
			   try{
				   	 auditLogService.auditLogEntryforupdateModule(vendormasterId,AuditLogConstants.VENDOR_MODULE, changedBy, 
				   			AuditLogConstants.WORKFLOW_STATE_VENDOR_ADDED,AuditLogConstants.WORKFLOW_STATE_VENDOR_MODIFIED, AuditLogConstants.AUDIT_LOG_EDIT_ACTION);
				   					   	
				   	}catch(Exception ex){
				   		logger.debug(" Error in AuditLog during Editingg in vendormaster ",ex);
				   	}
		}catch(Exception ex){
				 
				map.put("status",
								"<div class=\"failure\" width=\"100%\" > Vendor Company update failure  </div>");
			}
			
			
			
		return "redirect:/showVendorMaster";

	}
	
	

	@RequestMapping(value = {"/showVendor"}, method = RequestMethod.GET)
	public String vendorList() throws Exception{
		List<Vendor> vendorSetupList = vendorService.getVendorList();
		servletContext.setAttribute("vendorSetupList", vendorSetupList);
		if(servletContext.getAttribute("vendorMasterSetupList") == null)
		servletContext.setAttribute("vendorMasterSetupList",vendorMasterService.getMasterVendorlist());
		//servletContext.setAttribute("vendorMasterSetupList", object);	
		return "transadmin_vendorSetUp";
	}
	
	@RequestMapping(value = "/addVendor", method = RequestMethod.POST)
	public String addVendor(HttpServletRequest request,Map<String,Object> map) throws Exception {
		// String errorMessage="Vendor adding failure";
		//String doneBy = session.getAttribute("user").toString();
		 String doneBy ="1385";
		 String vmId =request.getParameter("company");
		 int vendormasterId=Integer.parseInt(vmId);
		try{	
			
			Vendormaster vm=vendorMasterService.getVendorMasterById(vendormasterId); 
			Vendor vendor = new Vendor();
			vendor.setName(request.getParameter("name"));
			vendor.setAddress(request.getParameter("address"));
			vendor.setEmail(request.getParameter("email"));
			vendor.setStatus("active");
			vendor.setContactNumber(request.getParameter("contactNumber"));
			vendor.setPassword(request.getParameter("password"));
			vendor.setVendormaster(vm);
			vendor.setLoginId(request.getParameter("loginId"));
		    Employee emp=vendorService.getEmpByLoginId(request.getParameter("loginId"));
			vendor.setEmployee(emp);		
			Vendor v=vendorService.addVendor(vendor);			
			map.put("status",
						"<div class=\"success\" width=\"100%\" > Vendor addition successful</div>");
			try{
				 auditLogService.auditLogEntryforaddModule(Integer.parseInt(v.getId()),AuditLogConstants.VENDOR_MODULE, doneBy, AuditLogConstants.WORKFLOW_STATE_EMPTY,
						 AuditLogConstants.WORKFLOW_STATE_VENDOR_ADDED, AuditLogConstants.AUDIT_LOG_CREATE_ACTION);
			 } catch(Exception ex){
				logger.debug(" Error in AuditLog insertation for vendor ",ex);
			 }
		} catch(Exception ex) {
			map.put("status","<div class=\"failure\" width=\"100%\" > Vendor addition failure</div>");

		}
	
	return "redirect:/showVendor";
						
	}
	
		
	
	@RequestMapping(value = { "/deleteVendor" }, method = RequestMethod.POST)
	public String deleteVendor(HttpServletRequest request,Map<String,Object> map) throws Exception {
			//HttpSession session = request.getSession(true);
			//String doneBy = session.getAttribute("user").toString();
		String doneBy="1385";
		String vId=request.getParameter("deleteid");
		try{
				Vendor vm=vendorService.getVendorById(vId);  
				vm.setStatus("inactive");							
				vendorService.deleteUpdateVendor(vm);
				   map.put("status",
							"<div class=\"success\" width=\"100%\" >Vendor Deletion successful  </div>");
				   try{
					   	 auditLogService.auditLogEntryfordeleteModule(Integer.parseInt(vId),AuditLogConstants.VENDOR_MODULE, doneBy,"",
					   			AuditLogConstants.WORKFLOW_STATE_VENDOR_DELETED, AuditLogConstants.AUDIT_LOG_DELETE_ACTION);
					   	}catch(Exception ex){
					   		logger.debug(" Error in AuditLog deletion for vendormaster ",ex);
					   	}
			}catch(Exception ex){
					 
					map.put("status",
									"<div class=\"failure\" width=\"100%\" >Vendor Deletion failure  </div>");
				}
		
		return "redirect:/showVendor";

	}
	
	@RequestMapping(value = { "/updateVendor" }, method = RequestMethod.POST)
	public String updateVendor(HttpServletRequest request,Map<String,Object> map) throws Exception {
		//HttpSession session = request.getSession(true);
		//String doneBy = session.getAttribute("user").toString();
		String doneBy="1385";
		String vmId=request.getParameter("company");
		String vId=request.getParameter("id");
		
		try{	
			Vendor vendor=vendorService.getVendorById(vId);
			Vendormaster vm=vendorMasterService.getVendorMasterById(Integer.parseInt(vmId));
			vendor.setName(request.getParameter("name"));
			vendor.setAddress(request.getParameter("address"));
			vendor.setEmail(request.getParameter("email"));
			vendor.setContactNumber(request.getParameter("contactNumber"));
			vendor.setLoginId(request.getParameter("loginId"));
			Employee emp=vendorService.getEmpByLoginId(request.getParameter("loginId"));
			vendor.setEmployee(emp);
			vendor.setPassword(request.getParameter("password"));
			vendor.setVendormaster(vm);	
			String changePassword=request.getParameter("changePassword");
			
			if(changePassword!=null&&changePassword.equals("true"))
			{
				vendor.setPassword(changePassword);
								
			}
			vendorService.deleteUpdateVendor(vendor);
			map.put("status",
					"<div class=\"success\" width=\"100%\" >Vendor updated successfully  </div>");
		   try{
			   	 auditLogService.auditLogEntryforupdateModule(Integer.parseInt(vId),AuditLogConstants.VENDOR_MODULE, doneBy, 
			   			AuditLogConstants.WORKFLOW_STATE_VENDOR_ADDED,AuditLogConstants.WORKFLOW_STATE_VENDOR_MODIFIED, AuditLogConstants.AUDIT_LOG_EDIT_ACTION);
			   					   	
			   	}catch(Exception ex){
			   		logger.debug(" Error in AuditLog during Updating in vendor ",ex);
			   	}
	}catch(Exception ex){
			 
			map.put("status",
							"<div class=\"failure\" width=\"100%\" > Vendor update failure  </div>");
		}
				
	return "redirect:/showVendor";
	}

	

}
