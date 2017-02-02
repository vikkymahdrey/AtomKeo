package com.agiledge.atom.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.agiledge.atom.config.files.OtherFunctions;
import com.agiledge.atom.config.files.SendMailFactory;
import com.agiledge.atom.dto.DriverDto;
import com.agiledge.atom.dto.DriverVehicleDto;
import com.agiledge.atom.dto.EscortDto;
import com.agiledge.atom.dto.ModifyTripDto;
import com.agiledge.atom.dto.RoutingDto;
import com.agiledge.atom.dto.TripDetailsChildDto;
import com.agiledge.atom.dto.TripDetailsDto;
import com.agiledge.atom.dto.VehicleDto;
import com.agiledge.atom.dto.VehicleTypeDto;
import com.agiledge.atom.entities.Branch;
import com.agiledge.atom.entities.Driver;
import com.agiledge.atom.entities.Employee;
import com.agiledge.atom.entities.EmployeeSchedule;
import com.agiledge.atom.entities.Escort;
import com.agiledge.atom.entities.Logtime;
import com.agiledge.atom.entities.Site;
import com.agiledge.atom.entities.TripDetail;
import com.agiledge.atom.entities.Tripvendorassign;
import com.agiledge.atom.entities.Vehicle;
import com.agiledge.atom.entities.VehicleType;
import com.agiledge.atom.entities.Vendormaster;
import com.agiledge.atom.mail.SendMail;
import com.agiledge.atom.service.intfc.CompanyBranchService;
import com.agiledge.atom.service.intfc.DriverService;
import com.agiledge.atom.service.intfc.EmployeeService;
import com.agiledge.atom.service.intfc.EscortService;
import com.agiledge.atom.service.intfc.Export2PDFService;
import com.agiledge.atom.service.intfc.LogTimeService;
import com.agiledge.atom.service.intfc.RoutingService;
import com.agiledge.atom.service.intfc.SchedulingService;
import com.agiledge.atom.service.intfc.SettingsService;
import com.agiledge.atom.service.intfc.SiteService;
import com.agiledge.atom.service.intfc.TripDetailsService;
import com.agiledge.atom.service.intfc.VehicleTypeService;
import com.agiledge.atom.service.intfc.VendorMasterService;
import com.agiledge.atom.service.intfc.VendorService;
import com.agiledge.atom.utils.DateUtil;

@Controller
@SessionAttributes("status")
public class RoutingController {

	private static final Logger logger = Logger.getLogger(RoutingController.class);
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private VehicleTypeService vehicleTypeService;
	
	@Autowired
	private LogTimeService logTimeService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private SettingsService settingsService;
	
	@Autowired
	private RoutingService routingService;
	
	@Autowired
	private TripDetailsService tripDetailsService;
	
	@Autowired
    private CompanyBranchService companyBranchService;
	
	@Autowired
	private Export2PDFService export2PDFService;
	
	@Autowired
	private VendorMasterService vendorMasterService;
	
	@Autowired
	private VendorService vendorService;
	
	@Autowired
	private EscortService escortService;
	
	@Autowired
	private DriverService driverService;
	
	@Autowired
	private SchedulingService schedulingService;
	
	
	
	
	
	/*Automatic Routing Implementation started...... */
	@RequestMapping(value= {"/automaticRouting"}, method=RequestMethod.GET)
	public String showAutomaticRouting(Map<String,Object> map) throws Exception  {
		logger.debug("IN automaticRouting page");
		map.put("siteList", siteService.getSites());
		map.put("vehicleTypeList", vehicleTypeService.getAllVehicleType());
		return "automatic_routing";
	}
	
	/* Ajax calling for automatic_routing.jsp */
	
	@RequestMapping(value= {"/getLogTime"}, method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String getLogTimeHandler(HttpServletRequest request,Map<String,Object> map) throws Exception  {
		logger.debug("/*Ajax getting logtime */");
		String logtype = request.getParameter("logtype");
		String type = request.getParameter("type");
		String site = request.getParameter("site");
		String isShuttle = request.getParameter("shuttle");
		String returnString = "";
		List<Logtime> logtimeList=null;
		try {
			if(isShuttle!=null)
			{
				/*ArrayList<GeneralShiftDTO> shutleList=new GeneralShiftDAO().getActiveLogData(logtype);
			for(GeneralShiftDTO dto : shutleList)
			{
				returnString += "<option value=" + dto.getLogtime() + ">"
						+ dto.getLogtime() + "</option>";	
			}*/
			}
		else{
			if (logtype != null) {
				if (type != null && type.equals("disabled")) {
					logtimeList =logTimeService.getAllInactiveLogtime(logtype, site);
				} else {
					logtimeList = logTimeService.getAllLogtime(logtype, site);
				}
				returnString += "";
				for (Logtime logt : logtimeList) {

					returnString += "<option value=" + logt.getLogtime() + ">"
							+ logt.getLogtime() + "</option>";
				}
			} else {
				List<Logtime> loginTimeList = logTimeService.getAllLogtime("IN", site);
				List<Logtime> logoutTimeList = logTimeService.getAllLogtime("OUT", site);
				returnString += "";
				for (Logtime logt : loginTimeList) {
					returnString += "<option value=" + logt.getLogtime() + ">"
							+ logt.getLogtime() + "</option>";
				}
				returnString +="|";
				for (Logtime logt : logoutTimeList) {
					returnString += "<option value=" + logt.getLogtime() + ">"
							+ logt.getLogtime() + "</option>";
				}

				
			}
		  } 
		}catch (Exception e) {
			logger.error("Error in Ajax calling getLogTime ",e);
		}
		
		return returnString;

	}
		
	
	
	@RequestMapping(value= {"/routing"}, method=RequestMethod.GET)
	public @ResponseBody String automaticRoutingHandler(HttpServletRequest request,HttpServletResponse response,Map<String,Object> map) throws Exception  {
			//PrintWriter out = response.getWriter();	
			HttpSession session1 = request.getSession(true);
			session1.setAttribute("routeStatus", "started");
			
			Employee emp = (Employee)session1.getAttribute("userLoggedIn");
			String doneBy=emp.getId();
			String check = request.getParameter("check");
		 	String siteIdstr = request.getParameter("siteId");
		 	int siteid = Integer.parseInt(siteIdstr);
		 	String tripdate =request.getParameter("for_date");
		 	session1.setAttribute("date", request.getParameter("for_date"));
		 	String logTime = request.getParameter("logTime");
		 	String log = request.getParameter("log");
		 	String overwrite = request.getParameter("isOverwrite");
		 	ArrayList<VehicleTypeDto> vehicleType = new ArrayList<VehicleTypeDto>();
			ArrayList<VehicleTypeDto> vehicleTypeGiven=null;
		 	
			RoutingDto routingDto = new RoutingDto();
			routingDto = new RoutingDto();
			routingDto.setDoneBy(doneBy);
			routingDto.setDate(tripdate);
			routingDto.setTime(logTime);
			routingDto.setTravelMode(log);
		String retDat = "Please wait for 10 min and go to view trip ";
		int statusForDisplyStyleofVehicleCount=0;
		if(OtherFunctions.isEmpty(doneBy)==false){							 
				if (check == null  ) {
					String[] vehicleTypeIds = request.getParameterValues("vehicleType");
					VehicleTypeDto dto = null;
											
				try {
					boolean flag=false;	
					String[] givenCounts = new String[vehicleTypeIds.length];
					VehicleType vt = null;
					for (int i = 0; i < vehicleTypeIds.length; i++) {
						try{
						givenCounts[i] = request.getParameter("count"+ vehicleTypeIds[i]);
							dto = new VehicleTypeDto();
								dto.setId(Integer.parseInt(vehicleTypeIds[i]));
									dto.setCount(Integer.parseInt(givenCounts[i]));
										vehicleType.add(dto);
											flag=true;
												statusForDisplyStyleofVehicleCount=1;
												}catch(NumberFormatException e){
													logger.debug("ERROR IN Routing during parsing");
												}
						
										}
					
					if(!flag)
					{
						vehicleType=vehicleTypeService.setVehicleTypeCount(siteid); 	
					}		
					  
						vehicleTypeGiven=new ArrayList<VehicleTypeDto>(vehicleType);
						String status = routingService.routeProcess(routingDto,siteid, overwrite, doneBy, vehicleType);
						
						if (status.equals("success")) {
							retDat = "Routing Successful|";					
							retDat+= tripDetailsService.routingSummaryHTMLTable(siteid,tripdate, log, logTime,vehicleTypeGiven,statusForDisplyStyleofVehicleCount);
							logger.debug("retDatttttttt"+retDat);
						} else if (status.equals("noEmps")) {
							retDat = "No employee is scheduled or Allocation already Done";
							// response.sendRedirect("view_routing.jsp");
						}
						
					} catch (Exception ex) {		
						retDat = " Routing failed !";
						logger.error("Error in routing...",ex);
					}
					
								
					session1.setAttribute("routingData", retDat);
					// response.sendRedirect("automatic_routing.jsp");
					session1.setAttribute("routeStatus", "finished");
					
					//return retDat;
					
				}	/* check if condition closed */	
				else {		
					int rsv =routingService.checkTripExist(routingDto,siteid);
					if (rsv > 0) {
						//out.print("true");
						retDat="true";
						
					} else {
						retDat="false";
					}
				}				 			
		} else {
			retDat="Session Expired. Please login...";
		}
				
		//return "redirect:/automaticRouting";
		return retDat;
}
	
	@RequestMapping(value= {"/RoutingCheck"}, method=RequestMethod.GET)
	public @ResponseBody String getRoutingCheck(HttpServletRequest request,Map<String,Object> map) throws Exception  {
		logger.debug("/*welcome in getRoutingCheck */");
		String retVal="";
		try {
			
			if( request.getSession().getAttribute("routeStatus")!=null && request.getSession().getAttribute("routeStatus").toString().equalsIgnoreCase("started") ) {
				//response.getWriter().print("started");
				retVal="started";
				logger.debug("_____________________________________________________________");
				
			} else if( request.getSession().getAttribute("routeStatus")!=null && request.getSession().getAttribute("routeStatus").toString().equalsIgnoreCase("finished") ) {  
			
				logger.debug("___________________________finished__________________________________");
				//response.getWriter().print(request.getSession().getAttribute("routingData"));
				retVal=request.getSession().getAttribute("routingData").toString();
			
			} else {
			//response.getWriter().print("No Routing Done");
				retVal="No Routing Done";
			}
			
		} catch(Exception e) {
				logger.error("Errror .......................... ",e);
			//response.getWriter().print("error " +e );
		
		}
		
		return retVal;
	}
	
	/*End AutomaticRouting Implementation...... */
	
	
	/*View Trip Implementation started .......*/
	@RequestMapping(value= {"/viewTrips"}, method=RequestMethod.GET)
	public String showViewTrips(Map<String,Object> map) throws Exception  {
		logger.debug("IN View Trips Page");
		map.put("siteList", siteService.getSites());
		return "view_routing";
	}
	
	@RequestMapping(value= {"/tripGeneration"}, method=RequestMethod.GET)
	public String tripGenerationHandler(Map<String,Object> map) throws Exception  {
		logger.debug("IN tripGeneration");
			return "trip_sheet";
	}
	
	@RequestMapping(value= {"/tripDetails"}, method={RequestMethod.POST,RequestMethod.GET})
	public String viewTripDetails(HttpServletRequest request,HttpSession session,Map<String,Object> map) throws Exception  {
		logger.debug("IN TripDetails handler");
		String tripDate = OtherFunctions.changeDateFromatToIso(request.getParameter("tripDate"));
			//String siteId = request.getParameter("siteId");
			String siteId = "1";
				String tripMode = request.getParameter("tripMode");
					String tripTime = request.getParameter("tripTime");
					String vendorCompany = (String)session.getAttribute("vendorCompany");
					logger.debug("VendorIddddd"+vendorCompany);
					
					ArrayList<TripDetailsDto> tripSheetSaved=null;
					ArrayList<TripDetailsDto> tripSheetList=null;
		if(vendorCompany!=null && !vendorCompany.equals("")){
			logger.debug("IN Vendor Allocation");
			 tripSheetSaved = tripDetailsService.getTripSheetSaved(vendorCompany,tripDate, tripMode, siteId, tripTime);
	         tripSheetList= tripDetailsService.getTripSheetModified(vendorCompany,tripDate, tripMode, siteId, tripTime);
	       	
		} else{
			logger.debug("IN Admin Allocation");
			 tripSheetList=tripDetailsService.getTripSheetModified(tripDate, tripMode, siteId, tripTime);
			 tripSheetSaved=tripDetailsService.getTripSheetSaved(tripDate, tripMode, siteId, tripTime);
		}
			
		map.put("tripSheetList", tripSheetList);
			map.put("tripSheetSaved",tripSheetSaved);
						
	return "trip_details";
		}
	
	
	

	@RequestMapping(value= {"/tripAllocation"}, method={RequestMethod.POST,RequestMethod.GET})
	public String tripAllocationHandler(HttpServletRequest request,HttpSession session,Map<String,Object> map) throws Exception  {
		logger.debug("IN tripAllocation handler");
		Employee employee = (Employee)session.getAttribute("userLoggedIn");
		String empId=employee.getId();
		String tripDate = request.getParameter("tripDate");
				String tripMode = request.getParameter("log");
					String tripTime = request.getParameter("tripTime");
					List<TripDetail> tripSheetSaved= tripDetailsService.getTripSheetSaved(tripDate, tripMode,tripTime);
					if(tripSheetSaved==null || tripSheetSaved.isEmpty()){
						List<TripDetail> tdDetails=tripDetailsService.getAllTripSheetDetails(tripDate, tripMode, tripTime);
							if(tdDetails==null){
									tripDetailsService.getTripSheetInsertion(tripDate, tripMode,tripTime,empId);
							}
					}		
					List<TripDetail> tripSheetList = tripDetailsService.getTripSheet(tripDate, tripMode,tripTime,empId);
					//List<TripDetail> tripSheetSaved= tripDetailsService.getTripSheetSaved(tripDate, tripMode,tripTime);
										      				 
			map.put("tripSheetSaved",tripSheetSaved);
			map.put("tripSheetList",tripSheetList);
								
	return "tripDetailsStatus";
		}
	
	
	@RequestMapping(value= {"/tripModifyAllocation"}, method={RequestMethod.POST,RequestMethod.GET})
	public String tripModifyAllocationHandler(HttpServletRequest request,HttpSession session,Map<String,Object> map) throws Exception  {
		Employee employee = (Employee)session.getAttribute("userLoggedIn");
		String empId=employee.getId();
		String tripDate = request.getParameter("tripDate");
				String tripMode = request.getParameter("log");
					String tripTime = request.getParameter("tripTime");
					List<TripDetail> tripSheetList = tripDetailsService.getTripSheet(tripDate, tripMode,tripTime,empId);
					List<TripDetail> tripSheetSaved= tripDetailsService.getTripSheetSaved(tripDate, tripMode,tripTime);
										      				 
			map.put("tripSheetSaved",tripSheetSaved);
			map.put("tripSheetList",tripSheetList);
								
	return "tripDetailsStatus";
		}
	
	@RequestMapping(value= {"/tripReAllocation"}, method={RequestMethod.POST,RequestMethod.GET})
	public String tripReAllocationHandler(HttpServletRequest request,HttpSession session,Map<String,Object> map) throws Exception  {
		logger.debug("IN tripReAllocation handler");
		Employee employee = (Employee)session.getAttribute("userLoggedIn");
		String empId=employee.getId();
		String tripDate = request.getParameter("tripDate");
				String tripMode = request.getParameter("tripMode");
					String tripTime = request.getParameter("tripTime");
							
							
					List<TripDetail> tripSheetSaved= tripDetailsService.getTripSheetSavedOrModify(tripDate, tripMode,tripTime);
					List<Vehicle> vehicles=vehicleTypeService.getAllVehicle();
					List<Driver> drivers=driverService.getDriversDetails();
					List<Escort> escorts=escortService.getAllEscorts();
					      				 
			map.put("tripSheetSaved",tripSheetSaved);
			map.put("vehicles",vehicles);
			map.put("drivers",drivers);
			map.put("escorts",escorts);
						
	return "trip_detailSheet";
		}

	
	@RequestMapping(value= {"/tripDetailsModify"}, method=RequestMethod.POST)
	public String tripDetailsModifyHandler(HttpServletRequest request,Map<String,Object> map) throws Exception  {
		String tripDate = request.getParameter("tripDate");
			String tripTime = request.getParameter("tripTime");
				String tripMode = request.getParameter("log");
					String siteId = "1";
		ArrayList<TripDetailsDto> tripSheetList=tripDetailsService.getTripSheetModified(tripDate, tripMode, siteId, tripTime);
		ArrayList<TripDetailsDto> tripSheetListActual = tripDetailsService.getTripSheetActual(tripDate, tripMode, siteId, tripTime);
		map.put("tripSheetList", tripSheetList);
		map.put("tripSheetListActual",tripSheetListActual);
		
					return "trip_details_modify";
		}
	
	@RequestMapping(value= {"/tripSheetModify"}, method={RequestMethod.GET,RequestMethod.POST})
	public String tripSheetModifyHandler(HttpServletRequest request,HttpSession session,Map<String,Object> map) throws Exception  {
		Employee employee = (Employee)session.getAttribute("userLoggedIn");
		String empId=employee.getId();
		String tripDate = request.getParameter("tripDate");
			String tripTime = request.getParameter("tripTime");
				String tripMode = request.getParameter("tripMode");
				logger.debug("@@@@@@@@"+tripDate+tripTime+tripMode);
					String siteId = "1";
		List<TripDetail> tripSheetList=tripDetailsService.getTripSheet(tripDate, tripMode, tripTime,empId);
		List<TripDetail> tripSheetSaved= tripDetailsService.getTripSheetSaved(tripDate, tripMode,tripTime);
		List<TripDetail> tripSheetActual= tripDetailsService.getActualTripSheet(tripDate, tripMode,tripTime);
		map.put("tripSheetList", tripSheetList);
		map.put("tripSheetActual",tripSheetActual);
		map.put("tripSheetSaved",tripSheetSaved);
		
					return "trip_sheet_modify";
		}
	
	@RequestMapping(value= {"/modifyTrip"}, method=RequestMethod.POST)
	public String modifyTripHandler(HttpServletRequest request,Map<String,Object> map) throws Exception  {
		
		String siteId = request.getParameter("siteId");
			String tripDate = request.getParameter("tripDate");
				String tripMode =request.getParameter("tripMode");
					String tripTime = request.getParameter("tripTime");
					logger.debug("@@@@@@@@"+tripDate+tripTime+tripMode);
						String tripIds[] = request.getParameterValues("tripidcheck");
		int isSecNeeded=tripDetailsService.checkSecurity(siteId,tripTime);
		ArrayList <VehicleTypeDto> vehicleTypeDtos=vehicleTypeDtos = vehicleTypeService.getAllVehicleTypeBySite(Integer.parseInt(siteId));
		ArrayList<TripDetailsChildDto> removedEmployee=tripDetailsService.getTripSheetRemoved(tripDate,siteId, tripMode,tripTime);
		map.put("vehicleTypeDtos", vehicleTypeDtos);
		map.put("removedEmployee", removedEmployee);
		map.put("isSecNeeded", Integer.valueOf(isSecNeeded));
					return "modify_trip";
	}
	
	@RequestMapping(value= {"/modifyTripSheet"}, method=RequestMethod.POST)
	public String modifyTripSheetHandler(HttpServletRequest request,Map<String,Object> map) throws Exception  {
		
		String siteId = "1";
			String tripDate = request.getParameter("tripDate");
				String tripMode =request.getParameter("tripMode");
					String tripTime = request.getParameter("tripTime");
						String tripIds[] = request.getParameterValues("tripidcheck");
		
						List<VehicleType> vehicleTypes=vehicleTypeService.getAllVehicleType();
		List<TripDetail> removedEmployee=null;
		map.put("vehicleTypes", vehicleTypes);
		map.put("removedEmployee", removedEmployee);
		map.put("tripDetailsService", tripDetailsService);
			return "modify_trip_sheet";
	}
	
	@RequestMapping(value= {"/updateTripSheet"}, method=RequestMethod.POST)
	public String updateTripSheetHandler(HttpServletRequest request,Map<String,Object> map) throws Exception  {
		
		String siteId = "1";
			String tripDate = request.getParameter("tripDate");
				String tripMode =request.getParameter("tripMode");
					String tripTime = request.getParameter("tripTime");
					logger.debug("@@@@@@@@"+tripDate+tripTime+tripMode);
						String tripIds[] = request.getParameterValues("tripidcheck");
		
						List<VehicleType> vehicleTypes=vehicleTypeService.getAllVehicleType();
		List<TripDetail> removedEmployee=null;
		map.put("vehicleTypes", vehicleTypes);
		map.put("removedEmployee", removedEmployee);
		map.put("tripDetailsService", tripDetailsService);
			return "modify_trip_sheet";
	}
	
	@RequestMapping(value= {"/updateTripSheetSubmit"}, method={RequestMethod.POST,RequestMethod.GET})
	public String updateTripSheetSubmitHandler(HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception  {
		logger.debug("IN Update tripSheet");
		HttpSession session = request.getSession(true);
		Employee emp = (Employee)session.getAttribute("userLoggedIn");
		
		String tripDate = request.getParameter("tripDate");
			String siteId = "1";
				String tripMode =request.getParameter("tripMode");
					String tripTime =request.getParameter("tripTime");
						String tripIds[] = request.getParameterValues("tripIds");
						
						logger.debug("Update tripSheet details"+tripMode+tripTime+"tripIDS[]"+ tripIds.length);
		try {
			ModifyTripDto modifyTripDto = null;
			ArrayList<ModifyTripDto> modifyTripDtos = null;
			LinkedHashMap<String, ArrayList<ModifyTripDto>> modifiedList = new LinkedHashMap<String, ArrayList<ModifyTripDto>>();
			ArrayList<String> deletelist=new ArrayList<String>();
			int status=0;
			String vehicleType;
			String escort ;
			for(int i=0;i<tripIds.length;i++)
			{							

			String tripEmps[] = request.getParameterValues("trip"+i);
		//	logger.debug("tripEmps valuesssss"+tripEmps.length);
			if(tripEmps!=null && tripEmps.length>0)
			{		
				vehicleType = request.getParameter("vehicleType"+i).split(":")[0];
				escort = request.getParameter("escort"+i);			
															
				modifyTripDtos = new ArrayList<ModifyTripDto>();
				for (String empId : tripEmps) {
					String empidlandmark[] = empId.split(":");
					modifyTripDto = new ModifyTripDto();
					modifyTripDto.setEmployeId(empidlandmark[0]);
					modifyTripDto.setLandmarkId(empidlandmark[1]);
					modifyTripDto.setScheduleId(empidlandmark[2]);
					modifyTripDto.setTripStatus("modify");
					modifyTripDto.setVehicleType(vehicleType);
					modifyTripDto.setEscort(escort);
					modifyTripDtos.add(modifyTripDto);
						if(i<tripIds.length)
						{
							modifiedList.put(tripIds[i], modifyTripDtos);
				
						}
						else
						{			
							modifiedList.put("ADD"+i, modifyTripDtos);
						}
			    }					
			}else{
				deletelist.add(tripIds[i]);
			//	tripDetailsService.deleteEmptyTripIds(tripIds[i]);
				
			}
		}
			for(String id:deletelist){
				
				tripDetailsService.deleteEmptyTripIds(id);
			}
			/*
			for(Iterator it=modifiedList.entrySet().iterator();it.hasNext();)
			{
				Entry entry=(Entry) it.next();
				System.out.println(entry.getKey());
				ArrayList<ModifyTripDto> emplist=(ArrayList<ModifyTripDto>) entry.getValue();
					for(ModifyTripDto dto:emplist)
					{
							System.out.println("EMPID "+dto.getEmployeId());
								System.out.println("LAndmArkId "+dto.getLandmarkId());
									System.out.println("ScheduleId "+dto.getScheduleId());
										System.out.println("Status "+dto.getTripStatus());
					}
			
			}
			
			for(String tripid: tripIds)
			{
				System.out.println(tripid);
			}*/
			
			status = tripDetailsService.modifyTrip(modifiedList, tripIds,emp.getId(),tripMode,siteId,tripTime,tripDate);
				
			
			/*String removedEmpids[] = request.getParameterValues("empids");
			System.out.println("removeEmpIds length"+removedEmpids);
			ArrayList<ModifyTripDto> addList = null;
			if (removedEmpids != null) {
				addList=new ArrayList<ModifyTripDto>();
				for (String removedEmpId : removedEmpids) {
					// System.out.println("Removed Employee" + removedEmpId);
					String empidlandmark[] = removedEmpId.split(":");
					modifyTripDto = new ModifyTripDto();
					modifyTripDto.setTripId(tripIds[0]);
					modifyTripDto.setEmployeId(empidlandmark[0]);
					modifyTripDto.setLandmarkId(empidlandmark[1]);
					modifyTripDto.setScheduleId(empidlandmark[2]);
					modifyTripDto.setTripStatus("modified");
					addList.add(modifyTripDto);
				}
				
					status=tripDetailsService.addNewtrip(addList,emp.getId(),tripMode,siteId,tripTime,tripDate);				
			}			*/
			if (status >= 0) {
				redirectAttributes.addFlashAttribute("status",
						"<div class='success'>Tripsheet Modififed</div");
			} else {
				redirectAttributes.addFlashAttribute("status",
						"<div class='failure'>Tripsheet Not Modififed</div");
			}

		} catch (Exception e) {
			logger.error("ERROR",e);
			redirectAttributes.addFlashAttribute("status",
					"<div class='failure'>Tripsheet Not Modififed</div");
		}
		return "redirect:/tripModifyAllocation?siteId=" + siteId	+ "&tripDate=" + tripDate + "&tripTime=" + tripTime+ "&log=" + tripMode;
	}
	
	
	
	@RequestMapping(value= {"/printTrips"})
	public String printTripsOnExcelHandler(HttpServletRequest request,Map<String,Object> map) throws Exception  {
		String tripDate = request.getParameter("tripDate");
        	String siteId ="1";
        		String tripMode = request.getParameter("tripMode");
        			String tripTime =  request.getParameter("tripTime");
        				String notSaved = request.getParameter("notSaved");
        ArrayList<TripDetailsDto> tripSheetSaved = null;
        if(OtherFunctions.isEmpty(notSaved)) {
        	tripSheetSaved = tripDetailsService.getTripSheetSaved(tripDate, tripMode, siteId, tripTime);
        } else {
        	tripSheetSaved = tripDetailsService.getTripSheetModified(tripDate, tripMode, siteId, tripTime);
        }
       
		map.put("tripSheetSaved",tripSheetSaved);
					
return "print_trips";
	}
	
	@RequestMapping(value= {"/export2PDF"})
	public void printTripsOnPDFHandler(HttpServletRequest request,HttpServletResponse response,Map<String,Object> map) throws Exception  {
		response.setContentType("application/pdf");
		String tripDate =request.getParameter("tripDate");
		String siteId = request.getParameter("siteId");
		String tripMode =request.getParameter("tripMode");
		String tripTime =request.getParameter("tripTime");
		ArrayList<TripDetailsDto> tripSheetSaved = tripDetailsService.getTripSheetSaved(tripDate, tripMode, siteId, tripTime);
		try {
			ServletOutputStream sos = response.getOutputStream();
			export2PDFService.export2PDF(sos, tripSheetSaved);

		} catch (Exception e) {
			logger.error("Exception in Exporting to PDF");
		}
	}
	
	
	
	@RequestMapping(value= {"/saveTrip"})
	public String saveTripsHandler(HttpServletRequest request,Map<String,Object> map) throws Exception  {
		
		HttpSession session = request.getSession(true);
		Employee emp = (Employee)session.getAttribute("userLoggedIn");
		String doneBy=emp.getId();
		String tripDate = request.getParameter("tripDate");
		String tripTime = request.getParameter("tripTime");
		String tripMode = request.getParameter("tripMode");
		String siteId = request.getParameter("siteId");
		
		String tripids = request.getParameter("tripids");		
		tripDetailsService.saveTrip(tripDate, tripMode, siteId, tripTime,doneBy,tripids);
		
		return "redirect:/tripDetails?siteId=" + siteId
				+ "&tripDate=" + tripDate + "&tripTime=" + tripTime
				+ "&tripMode=" + tripMode + "";
	}
	
	@RequestMapping(value= {"/saveTripSheet"})
	public String saveTripSheetHandler(HttpServletRequest request) throws Exception  {
		
		HttpSession session = request.getSession(true);
		Employee emp = (Employee)session.getAttribute("userLoggedIn");
		String doneBy=emp.getId();
		String tripDate = request.getParameter("tripDate");
		String tripTime = request.getParameter("tripTime");
		String tripMode = request.getParameter("tripMode");
		String tripids = request.getParameter("tripids");
		logger.debug("VVVVVVVVVVV"+tripDate+tripTime+tripMode+tripids);
		/*String siteId = request.getParameter("siteId");*/
		String siteId="1";
				
		tripDetailsService.saveTripSheet(tripDate, tripMode, siteId, tripTime, doneBy, tripids);
		
		
		return "redirect:/tripReAllocation?siteId=" + siteId
				+ "&tripDate=" + tripDate + "&tripTime=" + tripTime
				+ "&tripMode=" + tripMode + "";
	}
	
	
	@RequestMapping(value= {"/compareTrips"},method=RequestMethod.GET)
	public String compareTripsHandler(HttpServletRequest request,Map<String,Object> map) throws Exception  {
		String tripDate = request.getParameter("tripDate");
	    	String tripTime = request.getParameter("tripTime");
	    		String tripMode = request.getParameter("tripMode");
	    			String siteId = request.getParameter("siteId");
	
		ArrayList<TripDetailsDto> tripSheetList = tripDetailsService.getTripSheetSaved(tripDate, tripMode, siteId, tripTime);
	    ArrayList<TripDetailsDto> tripSheetListActual = tripDetailsService.getTripSheetActual(tripDate, tripMode, siteId, tripTime);
	    map.put("tripSheetList", tripSheetList);
		map.put("tripSheetListActual", tripSheetListActual);
		return "compare_trips";
	}
	
	/*Assigning Trips Sheet to VEndors*/
	
	@RequestMapping(value= {"/assignVendor"})
	public String assignVendorHandler(HttpServletRequest request,Map<String,Object> map) throws Exception  {

		String tripDate =OtherFunctions.changeDateFromatToIso(request.getParameter("tripDate"));
			String siteId = request.getParameter("siteId");
				String tripMode =request.getParameter("tripMode");
					String tripTime = request.getParameter("tripTime");
		ArrayList<TripDetailsDto> vendorAssignTrip = tripDetailsService.getTripToAssignVendor(tripDate, tripMode, siteId, tripTime);
		List<Vendormaster> masterVendorList = vendorMasterService.getMasterVendorlist();
				map.put("vendorAssignTrip", vendorAssignTrip);
					map.put("masterVendorList", masterVendorList);
		return "assignVendor";
	
	}
	
	@RequestMapping(value= {"/assignTripsToVendor"},method=RequestMethod.POST)
	public String assignTripsToVendorHandler(HttpServletRequest request,Map<String,Object> map) throws Exception  {

		String tripDate = request.getParameter("tripDate");
			String tripTime = request.getParameter("tripTime");
				String tripMode = request.getParameter("tripMode");
					String siteId = request.getParameter("siteId");
					Tripvendorassign tva=null;
		
		try {
			String vendorId = request.getParameter("vendor");
			String[] trips = request.getParameterValues("selectedtrip");
			Vendormaster vm=vendorMasterService.getVendorMasterById(Integer.valueOf(vendorId));
			/*tva=new Tripvendorassign();
			tva.setVendormaster(vm);
			tva.setStatus("a");*/
			Tripvendorassign tvaEntity=null;
			for (String trip : trips) {
				logger.debug("Inside for loop"+trip);
				TripDetail td=tripDetailsService.getTripsById(trip);
				tva=new Tripvendorassign();
				tva.setVendormaster(vm);
				tva.setStatus("a");
				tva.setTripDetail(td);
				tvaEntity = vendorMasterService.assignVendorTrip(tva);
			}
			
			if(tvaEntity!=null)
			{
				map.put("status",
						"<div class=\"success\" > Trip successfully Assigned to vendor </div>");
			} else {
				map.put("status",
						"<div class=\"failure\" > Trip Assigned to vendor Failed!</div>");	
			}

		} catch (Exception e) {
			logger.error("Error in assignTripsToVendor ",e);
			map.put("status",
					"<div class=\"failure\" > Trip Assigned to vendor Failed!</div>");
		}
		return "redirect:/assignVendor?siteId=" + siteId+ "&tripDate=" + tripDate + "&tripTime=" + tripTime+ "&tripMode=" + tripMode;
	
	}
	
	/*.......END 'Assigning Trips Sheet to VEndors'*/
	
	
	/*Allocation implementation started......*/
	@RequestMapping(value= {"/viewRoutingVendor"})
	public String viewRoutingVendorHandler(HttpServletRequest request,Map<String,Object> map) throws Exception  {
		map.put("siteList", siteService.getSites());
		return "view_routing_vendor";
	}
	
	@RequestMapping(value= {"/vendorViewTripSheet"})
	public String vendorViewTripSheetHandler(HttpServletRequest request,Map<String,Object> map) throws Exception  {
		map.put("siteList", siteService.getSites());
		return "vendor_viewTripSheet";
	
	}
	
	@RequestMapping(value= {"/vendorVehicleEntry"},method=RequestMethod.POST)
	public String tripSheetAllocationHandler(HttpServletRequest request,HttpSession session,Map<String,Object> map) throws Exception  {
		String tripDate = OtherFunctions.changeDateFromatToIso(request.getParameter("tripDate"));
			String siteId = request.getParameter("siteId");
				String tripMode =  request.getParameter("tripMode");
					String tripTime[] =  request.getParameterValues("tripTime");
		 
		String vendorCompany = (String )session.getAttribute("vendorCompany");
		ArrayList<TripDetailsDto> tripSheetSaved = tripDetailsService.getAssignedTrip(vendorCompany, siteId, tripDate, tripTime,tripMode, "assign");
		List<Escort> escortList = escortService.getAllEscorts(siteId);	
		ArrayList<VehicleDto> vehicleDtos = null;
		ArrayList<DriverVehicleDto> dtos=null;
		
		vendorMasterService.loadVehiclesVendor(vendorCompany);
		for (TripDetailsDto tripDetailsDtoObj : tripSheetSaved) {
			vehicleDtos=vendorMasterService.getVendorVehicles(tripDetailsDtoObj.getVehicle());
			dtos = vehicleTypeService.getVehicleDetails(tripDetailsDtoObj.getVehicleId());
		}
		
		 
		map.put("tripSheetSaved", tripSheetSaved);
		map.put("vehicleDtos", vehicleDtos);
		map.put("dtos", dtos);
		map.put("escortList", escortList);
		return "vendorVehicleEntry";
	
	}
	
	@RequestMapping(value= {"/getVehicleDriver"},method=RequestMethod.POST)
	public @ResponseBody String ajaxGetVehicleDriverHandler(HttpServletRequest request) throws Exception  {
		logger.debug("In Ajax getVehicleDriver");
		
		String vehicleId = request.getParameter("vehicle");
			ArrayList<DriverVehicleDto> dtos = vehicleTypeService.getVehicleDetails(vehicleId);
				String returnString = "";
				
		for (DriverVehicleDto dto : dtos) {
			DriverDto ddto = dto.getDriverDto();
			returnString += "<option value='" + ddto.getDriverId() + "'>"
					+ ddto.getDriverName() + "</option>";
		}
		
		return returnString;
	}
	
	@RequestMapping(value= {"/tripAssignVehicle"},method=RequestMethod.POST)
	public String tripAssignVehicleSubmissionHandler(HttpServletRequest request,Map<String,Object> map) throws Exception  {
		boolean isAnyEscort=false;
		
			String[] tripIds = request.getParameterValues("tripId");
				String vendorId = request.getParameter("vendorId");
					String tTime = null;
					
			List<TripDetail> tdList = new ArrayList<TripDetail>();
				VehicleDto dto = null;
						
			ArrayList<EscortDto> escortList = new ArrayList<EscortDto>();
				EscortDto edto = null;
					boolean validEscortFlag=true;
						boolean validDriverFlag=true;
			
						
			
			for(String tripId : tripIds) {
				TripDetail td=tripDetailsService.getTripsById(tripId);
				//Vendor vendor=vendorService.getVendorById(vendorId);
				Vehicle vehicle=vehicleTypeService.getVehicleById(request.getParameter("vehicle" + tripId));
				Driver driver=driverService.getDriverById(request.getParameter("driver" + tripId));
						td.setVehicleBean(vehicle);
						td.setTravelTime(request.getParameter("traveltime"+tripId));
						td.setDriver(driver);
			
				String isSecurity= request.getParameter("isSecurity" + tripId);
				isSecurity = isSecurity==null?"":isSecurity;
				if(isSecurity.equals("YES")) {
					isAnyEscort=true;
					edto = new EscortDto();
					edto.setTripId(tripId);
					edto.setId(request.getParameter("escort" + tripId));
					logger.debug("ESCORT ID"+request.getParameter("escort" + tripId));
					escortList.add(edto);
					if(edto.getId().trim().equals("")) {
							//	validEscortFlag=false;
						//			break;
					}
					
				}
				tdList.add(td);
			}
	if(validEscortFlag && validDriverFlag)
	{
				int val=vendorService.assaginTripVehicle(tdList);
						
		 if(val>=0)
		 {
			 
			 if(tripDetailsService.setTripsReadyForTracking(tdList)>0) {
					map.put("status",
							"<div class=\"success\" width=\"100%\" > Allocation successful</div>");
					
			 } else {
				 map.put("status","<div class=\"failure\" width=\"100%\" > Escort allocation failed</div>");
				 	}
			 
			 if(isAnyEscort)
			 {
				 	val=	escortService.assignTripEscort(escortList);
				 	if(val>=0) {
				 		map.put("status",	"<div class=\"success\" width=\"100%\" > Allocation successful</div>");
				 	} else {
				 		map.put("status",	"<div class=\"failure\" width=\"100%\" > Escort allocation failed</div>");
				 			}
			 }
			  
		 } else {
			 map.put("status",
					"<div class=\"failure\" width=\"100%\" > Driver & Escort allocations failed</div>");
		 		}
	} else if(!validEscortFlag){
			map.put("status",
					"<div class=\"failure\" width=\"100%\" > Escort validation failed</div>");
			} else if(!validDriverFlag ) {
				map.put("status",
					"<div class=\"failure\" width=\"100%\" > Driver validation failed</div>");
			}
		
		return "redirect:/vendorsHome";
	}
	
	@RequestMapping(value= {"/tripSheetAssignVehicle"},method=RequestMethod.POST)
	public String tripSheetAssignVehicleSubmissionHandler(HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception  {
		boolean isAnyEscort=false;
		
			String[] tripIds = request.getParameterValues("tripId");
			String tTime = null;
					
			List<TripDetail> tdList = new ArrayList<TripDetail>();
				VehicleDto dto = null;
						
			ArrayList<EscortDto> escortList = new ArrayList<EscortDto>();
				EscortDto edto = null;
					boolean validEscortFlag=true;
						boolean validDriverFlag=true;
			
						
			
			for(String tripId : tripIds) {
				TripDetail td=tripDetailsService.getTripsById(tripId);
				
				Vehicle vehicle=vehicleTypeService.getVehicleById(request.getParameter("vehicle" + tripId));
				Driver driver=driverService.getDriverById(request.getParameter("driver" + tripId));
				
				Escort escort=escortService.getEscortById(request.getParameter("escort" + tripId));
						td.setVehicleBean(vehicle);
						td.setDriver(driver);
						td.setEscort(escort);
				
				String isSecurity= request.getParameter("isSecurity" + tripId);
				isSecurity = isSecurity==null?"":isSecurity;
				if(isSecurity.equals("YES")) {
					isAnyEscort=true;
					edto = new EscortDto();
					edto.setTripId(tripId);
					edto.setId(request.getParameter("escort" + tripId));
					logger.debug("Security ESCORT ID"+request.getParameter("escort" + tripId));
					escortList.add(edto);
								
				}
				tdList.add(td);
			}
	if(validEscortFlag && validDriverFlag)
	{
				int val=vendorService.assaginTripVehicle(tdList);
					
		 if(val>=0)
		 {
			
			  if(tripDetailsService.setTripsReadyForTracking(tdList)>0) {
				 redirectAttributes.addFlashAttribute("status",
							"<div class=\"success\" width=\"100%\" > Allocation successful</div>");
					
			 } else {
				 redirectAttributes.addFlashAttribute("status","<div class=\"failure\" width=\"100%\" > Escort allocation failed</div>");
				 	}
			 
			 /*if(isAnyEscort)
			 {
				 	val=	escortService.assignTripEscort(escortList);
				 	if(val>=0) {
				 		redirectAttributes.addFlashAttribute("status",	"<div class=\"success\" width=\"100%\" > Allocation successful</div>");
				 	} else {
				 		redirectAttributes.addFlashAttribute("status",	"<div class=\"failure\" width=\"100%\" > Escort allocation failed</div>");
				 			}
			 }*/
			  
		 } else {
			 redirectAttributes.addFlashAttribute("status",
					"<div class=\"failure\" width=\"100%\" > Driver & Escort allocations failed</div>");
		 		}
	} else if(!validEscortFlag){
		redirectAttributes.addFlashAttribute("status",
					"<div class=\"failure\" width=\"100%\" > Escort validation failed</div>");
			} else if(!validDriverFlag ) {
				redirectAttributes.addFlashAttribute("status",
					"<div class=\"failure\" width=\"100%\" > Driver validation failed</div>");
			}
		
		return "redirect:/tripGeneration";
	}
	
	@RequestMapping(value= {"/transportRequest"})
	public String transportRequestHandler(Map<String,Object> map) throws Exception  {
		List<Site> sites = siteService.getSites();
		List<Branch> branchList=companyBranchService.getBranchs();
		//String branchId="1";
		//Branch branch=companyBranchService.getBranchById(Integer.valueOf(branchId));
		map.put("sites", sites);
		map.put("branchList", branchList);
		return "transportRequest1";
	}
	
	@RequestMapping(value= {"/editEmployeeInfo"})
	public String editEmployeeInfoHandler(Map<String,Object> map) throws Exception  {
		map.put("sites",siteService.getSites());
		return "EditEmployeeInfo";
	}
	
	@RequestMapping(value= {"/viewBookingTransport"})
	public String viewBookingTransportHandler() throws Exception  {
		return "viewBooking";
	}
	
	
	@RequestMapping(value= {"/geoTagging"})
	public String geoCodingHandler() throws Exception  {
		return "GeoCodingPage";
	}
	
	@RequestMapping(value= {"/geoCodingUpdate"},method=RequestMethod.POST)
	public String geoCodingUpdateHandler(HttpServletRequest request,HttpSession session,RedirectAttributes redirectAttributes) throws Exception  {
		String latlong=request.getParameter("homelatlong");
		Employee emp=(Employee)session.getAttribute("userLoggedIn");
		latlong=latlong.replace("(", "");
		latlong=latlong.replace(")", "");
		String lat=(latlong.split(", "))[0];
		String lng=(latlong.split(", "))[1];
		
		emp.setEmpLat(lat);
		emp.setEmpLong(lng);
		
		Employee employee=employeeService.insertEmployee(emp);
		if (employee!=null) {
			redirectAttributes.addFlashAttribute("status",
					"<div class='success'>Employee Registration and Geo coding successful...Thank you!</div");
			return "redirect:/transportRequest";
		} else {
			redirectAttributes.addFlashAttribute("status",
					"<div class='failure'>Geo Coding Failed....</div");
			return "redirect:/geoTagging";
		}
		
	}
	
	
	
	@RequestMapping(value= {"/transportBooking"},method=RequestMethod.POST)
	public String transportRequestSubmissionHandler(HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception  {
		HttpSession session = request.getSession(true);
		Employee emp = (Employee)session.getAttribute("userLoggedIn");
		String siteId = request.getParameter("siteId");	
		//JOptionPane.showMessageDialog(null,siteId,"SiteId",JOptionPane.WARNING_MESSAGE);
		String fromDate = request.getParameter("travelDate");
		String toDate = request.getParameter("travelDate1");
		String time = request.getParameter("tripTime");
		String log = request.getParameter("tripMode");
				
		Date frmDat=DateUtil.convertStringToDate(fromDate, "dd/MM/yyyy", "yyyy-MM-dd hh:mm::ss");
		Date toDat=DateUtil.convertStringToDate(toDate, "dd/MM/yyyy", "yyyy-MM-dd hh:mm::ss");
		
					
		EmployeeSchedule empSch=new EmployeeSchedule();
		empSch.setFromDate(frmDat);
		empSch.setToDate(toDat);
		if(log.equalsIgnoreCase("IN")){
			empSch.setLoginTime(time);
		}else if(log.equalsIgnoreCase("OUT")){
			empSch.setLogoutTime(time);
		}
		
		empSch.setEmployee1(emp);
		empSch.setStatus("a");
				EmployeeSchedule es=schedulingService.insertionOnEmployeeSchedule(empSch);
				if(es!=null){
						String subject = "Transport Booking order";	
						String message = settingsService.getEmployeeBookingMessage(es);
						System.out.println("messageeee"+message);
						SendMail mail = SendMailFactory.getMailInstance();
						mail.send(emp.getEmailAddress(), subject, message);
										
					redirectAttributes.addFlashAttribute("status","<div class=\"success\" > Transport booking successful.... </div>");
					return "redirect:/employeeviewtrip";
				}else{
					redirectAttributes.addFlashAttribute("status","<div class=\"success\" > Transport booking Failed... </div>");
					return "redirect:/transportRequest";

				}
		
		
	}
	
	@RequestMapping(value= {"/getBookingValidation"},method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String getBookingValidation(HttpServletRequest request,HttpSession session) throws Exception  {
		/*session.invalidate();*/
		session.removeAttribute("status");
		logger.debug("IN getBookingValidation");
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		String time = request.getParameter("time");
		Employee e=(Employee)request.getSession().getAttribute("userLoggedIn");
		logger.debug("vvvvvv"+fromDate+toDate+time);
		String retVal="";
			
		EmployeeSchedule esDetails=schedulingService.getExistingSchDetails(fromDate,toDate,time,e.getId());
		if(esDetails!=null){
			retVal="Booking exist for the same date. Please cancel the earlier booking before making a new booking";
		}
		
		return retVal;
	}
	
	
	
	
	
	
	
	
}
