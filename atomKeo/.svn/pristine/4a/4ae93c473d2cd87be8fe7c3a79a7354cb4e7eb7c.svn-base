package com.agiledge.atom.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agiledge.atom.constants.CoreAtomConstants;
import com.agiledge.atom.entities.Page;
import com.agiledge.atom.entities.Role;
import com.agiledge.atom.entities.View;
import com.agiledge.atom.service.intfc.ViewManagementService;

@Controller
@RequestMapping("/")
public class ViewManagementController {
	
	private static Logger logger = Logger.getLogger(ViewManagementController.class);
	
	@Autowired
	private ViewManagementService viewManagementService;
	
	@RequestMapping(value= {"/newUrl"}, method=RequestMethod.GET)
	public String showNewUrlPage(Model model){
		
		try {
			List<Page> pagesOnly = viewManagementService.getPagesByType(CoreAtomConstants.PAGE_TYPE_PAGE);
			List<Page> servletsOnly = viewManagementService.getPagesByType(CoreAtomConstants.PAGE_TYPE_SERVLET);
			model.addAttribute("pagesOnly", pagesOnly);
			model.addAttribute("servletsOnly",servletsOnly);
		} catch (Exception e) {
			logger.error("Error in loading pages or servlets ",e);
		}
		
		return "urlSetup";
	}
	
	
	@RequestMapping(value= {"/newRole"}, method=RequestMethod.GET)
	public String showNewRolePage(Model model){
		
		try {
			List<Page> pages = viewManagementService.getAllPages();
			List<Role> userTypes = viewManagementService.getSystemUserRoles();
			model.addAttribute("userTypes", userTypes);
			model.addAttribute("pages", pages);
		} catch (Exception e) {
			logger.error("Error in loading roles ",e);
		}
		
		return "userRoleSetUp";
	}
	
	
	
	@RequestMapping(value= {"/addNewRole"}, method=RequestMethod.POST)
	public String addNewRole(Model model, HttpServletRequest request){
		
		try {
		    Role role = new Role();
			role.setName(request.getParameter("name"));
			role.setDescription(request.getParameter("description"));
			role.setUsertype(request.getParameter("userType"));
			role.setType(CoreAtomConstants.ROLE_TYPE_SECONDARY);
			role =  viewManagementService.addNewRole(role);
			if(role != null) {
				request.getSession().setAttribute("status",
						"<div class=\"success\" width=\"100%\" >Success: New role added.</div>");
			} else {
				request.getSession().setAttribute("status",
						"<div class=\"failure\" width=\"100%\" >Failure: New role is not added.</div>");
			}
		}catch(Exception e) {
			logger.error("Error in adding a new role ",e);
			request.getSession().setAttribute("status",
					"<div class=\"failure\" width=\"100%\" > Error :"+e.getMessage() + "</div>");
		}
		return "redirect:/newRole";
	}
	
	
	@RequestMapping(value= {"/updateRole"}, method=RequestMethod.POST)
	public String updateRole(Model model, HttpServletRequest request){
		
		try {
		    Role role = viewManagementService.getRoleById(request.getParameter("id"));
			role.setName(request.getParameter("name"));
			role.setDescription(request.getParameter("description"));
			role.setUsertype(request.getParameter("userType"));
			role =  viewManagementService.updateRole(role);
			if(role != null) {
				request.getSession().setAttribute("status",
						"<div class=\"success\" width=\"100%\" >Success: Role is updated.</div>");
			} else {
				request.getSession().setAttribute("status",
						"<div class=\"failure\" width=\"100%\" >Failure: Role is not updated.</div>");
			}
		}catch(Exception e) {
			logger.error("Error in updating role ",e);
			request.getSession().setAttribute("status",
					"<div class=\"failure\" width=\"100%\" > Error :"+e.getMessage() + "</div>");
		}
		return "redirect:/newRole";
	}

	
	@RequestMapping(value= {"/roleView"}, method=RequestMethod.GET)
	public String roleView(Model model, HttpServletRequest request){
		
		try {
		    Role role = viewManagementService.getRoleById(request.getParameter("roleId"));
		    model.addAttribute("role",role);
		    
		}catch(Exception e) {
			logger.error("Error in loading view by role ",e);
			request.getSession().setAttribute("status",
					"<div class=\"failure\" width=\"100%\" > Error :"+e.getMessage() + "</div>");
		}
		return "roleView";
	}
	
	@RequestMapping(value= {"/addroleView"}, method=RequestMethod.GET)
	public String addroleViewHandler(Model model, HttpServletRequest request) throws Exception{
		String roleId=request.getParameter("roleId");
			String roleName=request.getParameter("roleName");
		List<View> viewList= viewManagementService.roleViewExisting(roleId);
		List<View> activeList= viewManagementService.getViewsbyRole(roleId);
		model.addAttribute("viewList", viewList);
		model.addAttribute("activeList", activeList);
		return "AddroleView";
	}
	
	@RequestMapping(value= {"/subViews"}, method=RequestMethod.GET)
	public String showSubviews(Model model, HttpServletRequest request){
		String viewId=request.getParameter("viewId");
		String viewName=request.getParameter("viewName");
		try {
		    List<View> viewsList = viewManagementService.getSubviewsbyView(viewId);
		    model.addAttribute("viewsList",viewsList);
		    
		}catch(Exception e) {
			logger.error("Error in loading view by role ",e);
			request.getSession().setAttribute("status",
					"<div class=\"failure\" width=\"100%\" > Error :"+e.getMessage() + "</div>");
		}
		return "ViewSubview";
	}


	@RequestMapping(value= {"/addPage"}, method=RequestMethod.GET)
	public @ResponseBody String addNewPage(@RequestParam(value = "page") String pageUrl,
			@RequestParam(value = "pageType") String pageType){
		
		JSONObject jsonResp = new JSONObject();
		
		logger.debug("addPage , url = "+pageUrl);
		if(pageUrl == null || pageUrl.trim().equals("") ) {
			jsonResp.put("result", "false");
			jsonResp.put("message", "URL is blank" );
		} else {
			Page newPage = new Page();
			newPage.setUrl(pageUrl);
			newPage.setType(pageType);
			Page retPage;
			try {
				retPage = viewManagementService.addNewPage(newPage);
			if(retPage != null){
				jsonResp.put("result", "true");
				jsonResp.put("message", "URL inserted successfuly" );
				jsonResp.put("id", retPage.getId());
			}else{
				jsonResp.put("result", "false");
				jsonResp.put("message", "Page already exists" );
			}
			
			} catch (Exception e) {
				logger.error("Error in adding the page ",e);
				jsonResp.put("result", "false");
				jsonResp.put("message", "Error in adding the page" );
			}
		} 
		logger.debug("json response  = "+jsonResp.toString());
		return jsonResp.toString();
		
	}
	
	@RequestMapping(value= {"/editPage"}, method=RequestMethod.GET)
	public @ResponseBody String editPage(@RequestParam(value = "page") String pageUrl,
			@RequestParam(value = "pageType") String pageType, @RequestParam(value = "id") String pageId){
		
		JSONObject jsonResp = new JSONObject();
		
		logger.debug("Edit page, page id = "+ pageId);
		if(pageUrl == null || pageUrl.trim().equals("") ) {
			jsonResp.put("result", "false");
			jsonResp.put("message", "URL is blank" );
		} else {
			
			Page retPage;
			try {
				Page page = viewManagementService.getPageById(pageId);
				page.setUrl(pageUrl);
				page.setType(pageType);
				retPage = viewManagementService.updatePage(page);
				jsonResp.put("result", "true");
				jsonResp.put("message", "URL updated successfuly" );
				jsonResp.put("id", retPage.getId());
			
			} catch (Exception e) {
				logger.error("Error in updating the page ",e);
				jsonResp.put("result", "false");
				jsonResp.put("message", "Error in updating the page" );
			}
		} 
		logger.debug("json response  = "+jsonResp.toString());
		return jsonResp.toString();
		
	}

}
