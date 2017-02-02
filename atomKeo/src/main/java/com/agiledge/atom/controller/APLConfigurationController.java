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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agiledge.atom.dao.intfc.BranchDao;
import com.agiledge.atom.entities.Area;
import com.agiledge.atom.entities.Branch;
import com.agiledge.atom.entities.Employee;
import com.agiledge.atom.entities.Landmark;
import com.agiledge.atom.entities.Place;
import com.agiledge.atom.service.intfc.AplService;
import com.agiledge.atom.service.intfc.AuditLogService;
import com.agiledge.atom.utils.UploadFileValidtor;

@Controller
public class APLConfigurationController {
private static final Logger logger = Logger.getLogger(APLConfigurationController.class);
	
    UploadFileValidtor  fileValidator= new UploadFileValidtor();
		
	@Autowired
	private AplService aplService;
		
	@Autowired
	private AuditLogService auditLogService;
	
	@Autowired
	private BranchDao branchDao;
	
	
	@RequestMapping(value= {"/aplConfig"}, method=RequestMethod.GET)
	public String getAPLHandler(Map<String,Object> map, HttpServletRequest request) throws Exception{
		 map.put("branches", aplService.getBranches());
		 String branchId=request.getParameter("branchId");
		 if(branchId != null && !(branchId.isEmpty())){
			 List<Area> areaList=aplService.getAreasBybranchId(Integer.parseInt(branchId));
			 map.put("areaList",areaList);
		 }
		 return "area";
	}
	
	
	@RequestMapping(value= {"/marklandmark"}, method=RequestMethod.GET)
	public String getAPLFromGoogleMap(Map<String,Object> map) throws Exception{

		List<Area> areas=aplService.getAreas();
		map.put("areas",areas);
			
		 return "marklandmark";
	}
	
	@RequestMapping(value= {"/updateArea"}, method=RequestMethod.GET)
	public String getUpdatedArea(HttpServletRequest request,Map<String,Object> map) throws Exception{

		String areaId = request.getParameter("areaId");
		String areaName = request.getParameter("area");
		String location = request.getParameter("location");
		HttpSession session = request.getSession(true);
		Employee emp = (Employee)session.getAttribute("userLoggedIn");
		String doneBy=emp.getId();
		try{
		Area area=aplService.getAreaById(areaId,Integer.parseInt(location)); 
		area.setArea(areaName);
		Area a=aplService.updateArea(area);
		
		if (a==null) {
			map.put("status",
					"<div class=\"failure\" > Area Already Exist !</div>");
			}

		else  {
			map.put("status",
					"<div class=\"success\" > Area updated</div>");
			}
	}catch(Exception e){
		logger.error("Error in updating area ",e);
		map.put("status",
				"<div class=\"failure\" > Area updating failed</div>");
			}
			
		return "redirect:/aplConfig?branchId="+location;
	}
			
	
	@RequestMapping(value= {"/addArea"}, method=RequestMethod.GET)
	public String getArea(HttpSession session,HttpServletRequest request,Map<String,Object> map) throws Exception{
		String areaName = request.getParameter("area");
		String location = request.getParameter("location");
		Employee emp = (Employee)session.getAttribute("userLoggedIn");
		String doneBy=emp.getId();
		Area areaObj=null;
		try{
			areaObj=new Area();
		  Branch branch=branchDao.getBranchById(Integer.parseInt(location));	
		  areaObj.setArea(areaName);
		  areaObj.setBranch(branch);
		  Area a=aplService.insertArea(areaObj,location);
		
		if (a==null) {
			map.put("status",
					"<div class=\"failure\" > Area Already Exist !</div>");
			}

		else  {
			map.put("status",
					"<div class=\"success\" > New area added</div>");
			}
	}catch(Exception e){
		logger.error("Error in adding area ",e);
		map.put("status",
				"<div class=\"failure\" > Area adding failed</div>");
			}
			
		return "redirect:/aplConfig?branchId="+location;
	}
	
	/*@RequestMapping(value= {"/addArea"}, method=RequestMethod.GET)
	public @ResponseBody String getArea(HttpSession session,HttpServletRequest request,Map<String,Object> map) throws Exception{
		String areaName = request.getParameter("area");
		String location = request.getParameter("location");
		Employee emp = (Employee)session.getAttribute("userLoggedIn");
		String doneBy=emp.getId();
		String response="";
		Area areaObj=null;
		try{
			areaObj=new Area();
		  Branch branch=branchDao.getBranchById(Integer.parseInt(location));	
		  areaObj.setArea(areaName);
		  areaObj.setBranch(branch);
		  Area a=aplService.insertArea(areaObj,location);
		
		if (a==null) {
			response="<div class=\"failure\" > Area Already Exist !</div>";
			}

		else  {
			response="<div class=\"success\" > New area added</div>";
			}
	}catch(Exception e){
		logger.error("Error in adding area ",e);
		response="<div class=\"failure\" > Area adding failed</div>";
			}
			
		return response;
	}*/
	
	
	// An ajax call comes from marklandmark.jsp
		@RequestMapping(value = {"/getLandmarks"},  method=RequestMethod.POST)
		public  @ResponseBody String getLandmarks(@RequestParam(required = false, value = "place") String place,
				@RequestParam(required = false, value = "area") String area, @RequestParam(required = false, value = "location") String location,
				@RequestParam(required = false, value = "placeforLandmark") String placeforLandmark)
		{
			String response = "";
		
			if (placeforLandmark != null) {
				try {
					List<Landmark> landmarks  = aplService.getLandmarksByPlaceId(placeforLandmark);
					response = "<select name='landmarkId' id='landmarkId'>";
				
					for (Landmark eachlandmark : landmarks) {
						response += "<option value='" + eachlandmark.getId()
								+ "' >" + eachlandmark.getLandmark() + "</option>";
					}
					response += "</select>";
				}catch(Exception ex){
					logger.error("Error in fetching landmarks for place ", ex);
				} 
			} else {
				try {
				
					List<Landmark> landmarks = aplService.getSpecificLandmarks(area,place,location);
					
					for (Landmark eachlandmark : landmarks) {
						response +=  "$" + eachlandmark.getPlaceBean().getAreaBean().getArea() + ":"
								+ eachlandmark.getPlaceBean().getPlace() + ":"
								+ eachlandmark.getId() + ":"
								+ eachlandmark.getLandmark() + ":"
								+ eachlandmark.getLatitude() + ":"
								+ eachlandmark.getLongitude();
					}
				
				}catch(Exception ex){
					logger.error("Error in fetching the specific landmarks ",ex);												
				}
			}
			
			
			return response;
		}
		
		
		@RequestMapping(value= {"/showPlace"}, method=RequestMethod.GET)
		public String getPlaceHandler(Map<String,Object> map, HttpServletRequest request) throws Exception{
			String areaId=request.getParameter("areaId"); 
			
			try{
				List<Place> placeList=aplService.getPlaceByAreaId(areaId);
				Area areas=aplService.getAreaById(areaId);
				
				map.put("placeList",placeList);
				map.put("areas", areas);
			}catch(Exception ex){
				logger.error("Error in showPlace",ex);
			}
			
			return "place";
		}
		
		@RequestMapping(value= {"/addPlace"}, method=RequestMethod.GET)
		public String addPlace(HttpSession session,HttpServletRequest request,Map<String,Object> map) throws Exception{
			
			String placeName = request.getParameter("place");
			String areaId = request.getParameter("areaId");
			String isShuttle = request.getParameter("isShuttle");
			Employee emp = (Employee)session.getAttribute("userLoggedIn");
			String doneBy=emp.getId();
			try{
				Area areaBean = aplService.getAreaById(areaId);
				Place place = new Place();
				place.setPlace(placeName);
				place.setAreaBean(areaBean);
							
		if (isShuttle == null) {
			Place p = aplService.insertPlace(place,areaId);
			if (p==null) {
				map.put("status",
						"<div class=\"failure\" > Place Already Exist in the area!</div>");
				}

			else  {
				map.put("status",
						"<div class=\"success\" > New place added</div>");
				}
			} 

		else {
				//Place pl=aplService.insertShuttlePlace(place);
		}
			
			
	}catch(Exception e){
			logger.error("Error in adding place ",e);
			map.put("status",
					"<div class=\"failure\" > Place adding failed</div>");
				}
				
			return "redirect:/showPlace?areaId="+areaId;
		}
		
		@RequestMapping(value= {"/updatePlace"}, method=RequestMethod.GET)
		public String getUpdatedPlace(HttpServletRequest request,Map<String,Object> map) throws Exception{
			HttpSession session = request.getSession(true);
			Employee emp = (Employee)session.getAttribute("userLoggedIn");
			String doneBy=emp.getId();
			String placeId = request.getParameter("placeId");
			String placeName = request.getParameter("place");
			String areaId = request.getParameter("areaId");
			String isShuttle=request.getParameter("isShuttle");
			try{
				Place place=aplService.getPlaceByPlaceId(placeId);
				Area area=aplService.getAreaById(areaId);
				place.setPlace(placeName);
				place.setAreaBean(area);
			
			if(isShuttle==null)
			{
				Place p=aplService.updatePlace(place,areaId);
				if (p==null) {
					map.put("status",
							"<div class=\"failure\" > Place Already Exist !</div>");
					}

				else  {
					map.put("status",
							"<div class=\"success\" > Place updated</div>");
					}
			}
			else
				{
					//
				}
		}catch(Exception e){
				logger.error("Error in updating place ",e);
				map.put("status",
						"<div class=\"failure\" > Place updating failed</div>");
					}
						return "redirect:/showPlace?areaId="+areaId;
			}
			
			
		@RequestMapping(value= {"/showLandmark"}, method=RequestMethod.GET)
		public String getLandmarkHandler(Map<String,Object> map, HttpServletRequest request) throws Exception{
			String placeId=request.getParameter("placeId");
			try{
				List<Landmark> landmarkList=aplService.getLandmarkByPlaceId(placeId);
				Place place=aplService.getPlaceByPlaceId(placeId);
				
				map.put("landmarkList",landmarkList);
				map.put("place", place);
			}catch(Exception ex){
				logger.error("Error in showLandmark",ex);
			}
			
			return "landmark";
		}
		
		@RequestMapping(value= {"/addLandmark"}, method=RequestMethod.GET)
		public String addLandmark(HttpSession session,HttpServletRequest request,Map<String,Object> map) throws Exception{
			Employee emp = (Employee)session.getAttribute("userLoggedIn");
			String doneBy=emp.getId();
			String landmarkName = request.getParameter("landmark");
			String placeId = request.getParameter("placeId");
			String isShuttle = request.getParameter("isShuttle");
			Landmark landmark=null;
			try{				
			     landmark=new Landmark();
			Place place=aplService.getPlaceByPlaceId(placeId);
			landmark.setLandmark(landmarkName);
			landmark.setPlaceBean(place);
			
			if (isShuttle == null){
				Landmark l=aplService.insertLandmark(landmark,placeId);
			if (l==null) {
				map.put("status",
						"<div class=\"failure\" > Landmark Already Exist in the place!</div>");
				}

			else  {
				map.put("status",
						"<div class=\"success\" > New landmark added</div>");
				}
			} 
			else{
				//
			}
		}catch(Exception e){
				logger.error("Error in adding landmark ",e);
				map.put("status",
						"<div class=\"failure\" > Landmark adding failed</div>");
					}
					
				return "redirect:/showLandmark?placeId=" + placeId;
			}
				
		@RequestMapping(value= {"/updateLandmark"}, method=RequestMethod.POST)
		public String getUpdatedLandmark(HttpServletRequest request,Map<String,Object> map) throws Exception{
			HttpSession session = request.getSession(true);
			Employee emp = (Employee)session.getAttribute("userLoggedIn");
			String doneBy=emp.getId();
			String retVal = "";
			String placeId = request.getParameter("placeId");
			String landmarkId = request.getParameter("landmarkId");
			String landmark = request.getParameter("landmark");
			String isShuttle = request.getParameter("isShuttle");
			String latLng= request.getParameter("latlng");
			String location= request.getParameter("location");
			String areaId= request.getParameter("area");
			
			logger.debug("latitude longitude = "+latLng);
			Landmark lm = null;
			try{
				lm = aplService.getLandmarkById(landmarkId);
				if(landmark != null)
				lm.setLandmark(landmark);
			}catch(Exception ex){
				logger.error("Error in fetching the landmark ",ex);
			}
			
			if(latLng!=null&&latLng.trim().equals("")==false)  {			
				String latitude=latLng.split(",")[0].substring(1,  latLng.split(",")[0].length()-1);
				String longitude = latLng.split(",")[1].substring(1, latLng.split(",")[1].length()-1);
					 lm.setLatitude(Double.parseDouble(latitude));
					 lm.setLongitude(Double.parseDouble(longitude));
					 lm = aplService.updateLandmark(lm,placeId);
					 if(location != null){
						 retVal =  "redirect:/marklandmark?location="+location;
					 }else if(areaId != null){
						 retVal =  "redirect:/marklandmark?area="+areaId;
					 }else if(placeId != null){
						 retVal =  "redirect:/marklandmark?place="+placeId;
					 }
					 
					 			
			}else if(isShuttle == null){
				lm = aplService.updateLandmark(lm,placeId);
				if (lm==null) {
					map.put("status",
							"<div class=\"failure\" > Landmark Already Exist !</div>");
					}

				else  {
					map.put("status",
							"<div class=\"success\" > Landmark updated</div>");
					}
				retVal =  "redirect:/showLandmark?placeId=" + placeId;
			}/*else{
			}*/
			return retVal;
		}	
		
		/* AJAX calling from marklandmark.jsp */		
		@RequestMapping(value= {"/getPlace"}, method=RequestMethod.GET)
		public @ResponseBody String getPlaceHandler(@RequestParam(required = true, value = "area") String area) throws Exception{
			//String areaId=request.getParameter("area");
			  String response="";
					try {
							List<Place> placesUnderArea=aplService.getPlaceByAreaId(area);
					
						response = "<select name='place' id='place' onchange='showLandmark(this.value)'>";
						response += "<option value='0' >Select</option>";
						for (Place place : placesUnderArea) {
							response += "<option value='" + place.getId() + "' >"
									+ place.getPlace() + "</option>";
						}
						response += "</select>";
					}catch(Exception ex){
						logger.debug("Error during AJAX calling for getPlace",ex);
					}
			return response;
					}
		
		
		/* APL Download handler from downloadapl.jsp */		
		@RequestMapping(value= {"/aplDownload"}, method=RequestMethod.GET)
		public String downloadAPLHandler(HttpServletRequest request,Map<String,Object> map) throws Exception{
			String location=request.getParameter("location");
		List<Area> fetchAPL=aplService.getAreasBybranchId(Integer.parseInt(location));
		map.put("fetchAPL", fetchAPL);
		return "downloadapl";
				}
		
		
		
		/*@RequestMapping(value="aplUpload", method = RequestMethod.GET)
		 public ModelAndView getAplUploadForm(  
				   @ModelAttribute("uploadedFile") UploadedFile uploadedFile,  
				   BindingResult result) {
			uploadedFile.setPlaceDupe(true);
			uploadedFile.setLandmarkDupe(true);
				  return new ModelAndView("aplUpload");  
				 }  
		
		 
		 @RequestMapping(value = "/aplUpload", method=RequestMethod.POST)  
		 public ModelAndView aplFileUploaded(  
		   @ModelAttribute("uploadedFile") UploadedFile uploadedFile,  
		   BindingResult result, HttpSession session) {  
		  InputStream inputStream = null;  
		  OutputStream outputStream = null;  
		 
		  try {
			   MultipartFile file = uploadedFile.getFile();  
		   fileValidator.validate(uploadedFile, result);  
		  
		  String fileName = file.getOriginalFilename();  
		  
			  int val =new aplService().uploadAPL(file.getInputStream(), uploadedFile.isAreaDupe(), uploadedFile.isPlaceDupe(), uploadedFile.isLandmarkDupe(), uploadedFile.getBranchId());
			  if(val>0) {
				  session.setAttribute("status",
							"<div class=\"success\" > Uploaded successfully!</div>");
			  }else {
				  session.setAttribute("status",
							"<div class=\"failure\" > Uploading failed !</div>");
			  }
		  }catch(Exception e) {
			  ;
		  }
		  if (result.hasErrors()) {  
		   return new ModelAndView("aplUpload");  
		  }  
		  
		   
		  return new ModelAndView("aplUpload");  
		 }
		 */
		 
		 
		 // route upload show 
		 /* @RequestMapping(value="routeUpload", method = RequestMethod.GET)
		 public ModelAndView getRouteUploadForm(  
				   @ModelAttribute("uploadedFile") UploadedFile uploadedFile,  
				   BindingResult result) {
			uploadedFile.setPlaceDupe(true);
			uploadedFile.setLandmarkDupe(true);
			List<SiteDto> sites = new SiteService().getSites();
				  ModelAndView model = new ModelAndView("routeUpload");
				  List<RoutingTypeDto> types = new ArrayList<RoutingTypeDto>();
				  types.add(new RoutingTypeDto("Primary", "p"));
				  types.add(new RoutingTypeDto("Combined", "c"));
				  types.add(new RoutingTypeDto("Full Combined", "f"));
				  model.addObject("types", types);
				 
				  model.addObject("sites", sites);
				  return model;
				 }  
		
		
		 
		 @RequestMapping(value = "/routeUpload", method=RequestMethod.POST)  
		 public ModelAndView routeFileUploaded(  
		   @ModelAttribute("uploadedFile") UploadedFile uploadedFile,  
		   BindingResult result, HttpSession session) {  
		  InputStream inputStream = null;  
		  OutputStream outputStream = null;  
		  System.out.println(" uploading .............");
		  ModelAndView model = new ModelAndView("routeUpload");
		  try {
		  MultipartFile file = uploadedFile.getFile();
		    
		  fileValidator.setUserType( session.getAttribute("role").toString());
		   fileValidator.validate(uploadedFile, result);
		   System.out.println("result.hasErrors() : "+ result.hasErrors());
		   System.out.println("result.hasFieldErrors() : "+ result.hasFieldErrors());
		   System.out.println("result.hasGlobalErrors() : "+ result.hasGlobalErrors());
		    
		  String fileName = file.getOriginalFilename();  
		  List<SiteDto> sites = new SiteService().getSites();
		  List<RoutingTypeDto> types = new ArrayList<RoutingTypeDto>();
		  types.add(new RoutingTypeDto("Primary", "p"));
		  types.add(new RoutingTypeDto("Combined", "c"));
		  types.add(new RoutingTypeDto("Full Combined", "f"));
		  model.addObject("types", types);
		  
		  model.addObject("sites", sites);
		  if(result.hasErrors()==false) {
		  APLService aplService = new APLService();
			  int val =aplService.uploadRoute(file.getInputStream(), uploadedFile.getSite(), uploadedFile.getType(), uploadedFile.isTruncate() );
			  
			  if(val>0) {
				  session.setAttribute("status",
							"<div class=\"success\" > Uploaded successfully!</div>");
			  }else {
				  session.setAttribute("status",
							"<div class=\"failure\" > Uploading failed !</div>");
			  }
		    }else {
		    	  session.setAttribute("status",
							"<div class=\"failure\" > Uploading failed !</div>");
		    }
		  }catch(Exception e) {
			  ;
		  }
		  if (result.hasErrors()) {  
		   return model;  
		  }  
		  
		   
		  return model;  
		 }
		 
		*/
		
		
}
