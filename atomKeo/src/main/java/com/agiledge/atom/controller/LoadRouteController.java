package com.agiledge.atom.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.agiledge.atom.dao.intfc.BranchDao;
import com.agiledge.atom.entities.Area;
import com.agiledge.atom.entities.Employee;
import com.agiledge.atom.entities.Landmark;
import com.agiledge.atom.entities.Route;
import com.agiledge.atom.entities.Routechild;
import com.agiledge.atom.entities.Routetype;
import com.agiledge.atom.entities.Site;
import com.agiledge.atom.service.intfc.AplService;
import com.agiledge.atom.service.intfc.AuditLogService;
import com.agiledge.atom.service.intfc.LogTimeService;
import com.agiledge.atom.service.intfc.RouteService;
import com.agiledge.atom.service.intfc.SiteService;

@SessionAttributes("status")
@Controller
public class LoadRouteController {
    private static final Logger logger = Logger.getLogger(APLConfigurationController.class);
	
    		
	@Autowired
	AplService aplService;
		
	@Autowired
	AuditLogService auditLogService;
	
	@Autowired
	BranchDao branchDao;
	
	@Autowired 
	LogTimeService logTimeService;
	
	@Autowired
	SiteService siteService;
	
	@Autowired
	RouteService routeService;
	
	@RequestMapping(value= {"/loadRouteSetup"}, method=RequestMethod.GET)
	public String loadRouteSetupHandler(Map<String,Object> map, HttpServletRequest request) throws Exception{
	
		map.put("branches", aplService.getBranches());
		String branchId=request.getParameter("branchId");
		
		 if(branchId!=null && !(branchId.isEmpty())){
			 List<Area> areaList=aplService.getAreasBybranchId(Integer.parseInt(branchId));
			 List<Site> sites=siteService.getSites(Integer.parseInt(branchId));
			 List<Routetype> routeTypes=routeService.getRouteTypes();
			 map.put("areaList",areaList);
			 map.put("siteList",sites);
			 map.put("routeTypeList",routeTypes); }
			return "add_route";
	}
	
	@RequestMapping(value= {"/addRoute"}, method=RequestMethod.POST)
	public String addRoute(RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession(true);
		Employee emp = (Employee)session.getAttribute("userLoggedIn");
		String doneBy=emp.getId();
		String[] routeAPL = request.getParameterValues("selectedapl");
		String routeName = request.getParameter("routeName");
		String siteId = request.getParameter("routeSite");
		String inOut = request.getParameter("inOut");
		String routeType = request.getParameter("routeType");
		
		Site site=siteService.getSiteById(Integer.parseInt(siteId));
		Routetype rt=routeService.getRouteTypeByType(routeType);
		
		Route route = new Route();
		
	    route.setSite(site);
		route.setRoutetype(rt);
		route.setRouteName(routeName);
		
		if(routeType != null){
			routeService.insertRoute(route);
		}
		else{
			// shuttle route
		}
			
				
		int position = 0;
		
		
		Landmark lm = null;
		try{
			for (String landmark : routeAPL) {
				Routechild	routechild=new Routechild();
				lm = aplService.getLandmarkById(landmark);	
				routechild.setLandmark(lm);
				routechild.setRoute(route);
				routechild.setPosition(position);
				routeService.insertRouteChild(routechild);
				position ++;
			}	
			
			if (routeType != null){
			
				 redirectAttributes.addFlashAttribute("status",
					"<div class='success'>Route Created Successfully</div");
				}
			
			
		}catch(Exception ex){
			logger.debug("Error in Load setup Configuration", ex);
		}
		
		return "redirect:/showShiftRoute";
	}	
	
	
	
			
	@RequestMapping(value= {"/showShiftRoute"}, method=RequestMethod.GET)
	public String viewShiftRouteHandler(Map<String,Object> map, HttpServletRequest request) throws Exception{
			map.put("siteList", siteService.getSites());
				return "route_list";
   }
	
	@RequestMapping(value= {"/loadShiftRoute"}, method=RequestMethod.GET)
	public String loadShiftRouteHandler(RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception{
		String site = request.getParameter("siteId");
        String orderTheRoute = request.getParameter("orderTheRoute");
        logger.debug("orderTheRouteeee"+ orderTheRoute);
        List<Route> routeList=null;
        if(site!=null){
        	 routeList=routeService.getAllRoutesBySite(site);
                
        	if(orderTheRoute!=null && !orderTheRoute.equals("") && !orderTheRoute.equals("null"))
        	{
        		logger.debug("orderTheRoute IN  "+orderTheRoute);
        		routeService.orderTheRoute(site);
        	}
        	
        }
        redirectAttributes.addFlashAttribute("routeList",routeList);
        redirectAttributes.addAttribute("siteId",site);
		return "redirect:/showShiftRoute";
   }
	
	@RequestMapping(value= {"/routeSetup"}, method=RequestMethod.GET)
	public String routeSetupHandler(Map<String,Object> map) throws Exception{
			map.put("siteList", siteService.getSites());
				return "routeSetup";
   }
	
	
	/*@RequestMapping(value= {"/setRouteRule"}, method=RequestMethod.POST)
	public String setRouteRuleHandler(RedirectAttributes redirectAttributes,HttpServletRequest request) throws Exception{
		String[] shifts=request.getParameterValues("selectedshift");
		String weekoffstatus=request.getParameter("weekoffstatus");
		String siteId=request.getParameter("siteId");
		try {
			int value=logTimeService.updateLogtimeCombainRoute(shifts,weekoffstatus,siteId);
			if (value > 0) {		
				request.getSession()
						.setAttribute("status",
								"<div class=\"success\" >  Combained Shift Updation Successful </div>");
			} else {
				request.getSession()
						.setAttribute("status",
								"<div class=\"failure\" > Combained Shift Updation Failed </div>");
			}

		} catch (Exception e) {
			request.getSession()
			.setAttribute("status",	"<div class=\"failure\" > Combained Shift Updation Failed </div>");
			e.printStackTrace();
		}
		response.sendRedirect("routeSetup.jsp");
		}
   }*/
}
