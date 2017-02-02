package com.agiledge.atom.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.agiledge.atom.entities.Site;
import com.agiledge.atom.entities.Vendor;
import com.agiledge.atom.service.intfc.SiteService;
import com.agiledge.atom.service.intfc.VendorService;

@Controller
@RequestMapping("/")
@SessionAttributes("status")
public class VendorController {
	
	private static final Logger logger = Logger.getLogger(VendorController.class);
	
	@Autowired
	private VendorService vendorService;
	
	@Autowired
	private SiteService siteService;
	
    @RequestMapping(value= {"/vendorHome"}, method=RequestMethod.GET)
	public ModelAndView home(){
		return new ModelAndView("vendorHome");
	}
    
    
    @RequestMapping(value= {"/vendorSite"}, method=RequestMethod.GET)
	public String assignSiteToVendorHandler(HttpServletRequest request, Map<String,Object> map) throws Exception{
    	
		Site site = siteService.getSiteById((Integer.valueOf(request.getParameter("siteId"))).intValue());
			List<Vendor> vendorInSiteList = vendorService.getVendorInSite(request.getParameter("siteId"));
				List<Vendor> vendorInNotSiteList = vendorService.getVendorNotInSite(request.getParameter("siteId"));
		
		map.put("site", site);
		map.put("vendorInSiteList", vendorInSiteList);
		map.put("vendorInNotSiteList", vendorInNotSiteList);
	return "vendor_site";
	}
}
