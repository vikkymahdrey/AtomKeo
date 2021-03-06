package com.agiledge.atom.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.agiledge.atom.dto.TripDetailsChildDto;
import com.agiledge.atom.dto.TripDetailsDto;
import com.agiledge.atom.dto.VehicleDto;
import com.agiledge.atom.entities.Area;
import com.agiledge.atom.entities.Branch;
import com.agiledge.atom.entities.Vehicle;
import com.agiledge.atom.service.intfc.AplService;
import com.agiledge.atom.service.intfc.CompanyBranchService;
import com.agiledge.atom.service.intfc.OtherService;
import com.agiledge.atom.service.intfc.TripDetailsService;
import com.agiledge.atom.service.intfc.TripService;
import com.agiledge.atom.service.intfc.VehicleTypeService;

@Controller
public class LiveTrackingController {
	private static final Logger logger = Logger.getLogger(LiveTrackingController.class);
	
	@Autowired
	private AplService aplService;
	
	@Autowired
	private OtherService otherService;
	
	@Autowired
    private CompanyBranchService companyBranchService;
	
	@Autowired
	private TripDetailsService tripDetailsService;
	
	@Autowired
	private TripService tripService;
	
	@Autowired
	private VehicleTypeService vehicleTypeService;
	
	
	@RequestMapping(value= {"/trackVehicle"})
	public String trackingVehicleHandler(HttpServletRequest request,HttpSession session,Map<String,Object> map) throws Exception  {
		//String site=session.getAttribute("site").toString();
		String site="1";
		logger.debug("Sitessssss"+site);
		List<Area> areas=aplService.getAreas();
		List<Branch> branchs=companyBranchService.getBranchs();
			map.put("areas",areas);
			map.put("branchs", branchs);
			map.put("od",otherService.getCity(site));
		return "trackVehicle";
	}
	
	@RequestMapping(value= {"/getVehiclePosition"},method=RequestMethod.POST)
	public @ResponseBody String getVehiclePositionHandler(HttpServletRequest request) throws Exception  {
		
		String retString = "";
			String tripId = request.getParameter("tripId");
				String tripInterval = request.getParameter("tripInterval");

		if (tripId == null) {
			String branch = request.getParameter("branch");
				String filshift=request.getParameter("shift");
					String vehno=request.getParameter("regno");
			if(filshift==null)
			{
				filshift="ALL";
			}
			if(vehno==null)
			{
				vehno="ALL";
			}
			
			ArrayList<TripDetailsDto> dtos = tripDetailsService.getTripsWithVehicle(branch,filshift,vehno);
			dtos.addAll(tripService.getStationaryVehiclePosition(branch));
			
			for (TripDetailsDto dto : dtos) {
				retString += dto.getId() + "~" + dto.getVehicleNo() + "~"
						+ dto.getLatitude() + "~" + dto.getLongitude() + "~"
						+ dto.getStatus() + "~" + dto.getLadyInCount() + "~"
						+ dto.getEmpInCount() + "~" + dto.getIsSecurity() + "~"
						+ dto.getPanicAck() + "~" + dto.getTrip_code() +"~"
						+ dto.getEmpCount() + "~" + dto.getTrip_date() +"~"
						+dto.getTrip_log()+"|";
				if(dto.getTripDetailsChildDtoList()!=null&&dto.getTripDetailsChildDtoList().size()>0)
				for (TripDetailsChildDto childDto : dto
						.getTripDetailsChildDtoList()) {
					retString += childDto.getLatitude() + ":"
							+ dto.getLongitude() + "~";
				}
				retString += "#";
			}

			return retString;
		}
		else if(tripInterval!=null) {
			ArrayList<VehicleDto> dtos = vehicleTypeService.getVehicleTrackInInterval(tripId);

			for (VehicleDto dto : dtos) {
				retString += dto.getDateTime() + "~" + dto.getLattitude()
						+ "~" + dto.getLongitude() + "|";
			}
			
			return retString;
		}
		else {
			ArrayList<VehicleDto> dtos =vehicleTypeService.vehicleTrack(tripId);

			for (VehicleDto dto : dtos) {
				retString += dto.getVehicleNo() + "~" + dto.getLattitude()
						+ "~" + dto.getLongitude() + "|";
			}
			return retString;
		}
		
	}
	
	@RequestMapping(value= {"replaydata"})
	public @ResponseBody String getreplaydata(HttpServletRequest request) throws Exception  {
		String fromdate=request.getParameter("fromdate");
		String todate=request.getParameter("todate");
		String vehicleid=request.getParameter("vehicleid");
		JSONObject json=tripDetailsService.getReplayData(fromdate, todate, vehicleid);
		return json.toJSONString();
	}
	
	@RequestMapping(value= {"replayreport"},method=RequestMethod.GET)
	public ModelAndView replayreport() throws Exception{
		ModelAndView mv=new ModelAndView("replayreport");
		List<Vehicle> vlist=vehicleTypeService.getAllVehicle();
		mv.addObject("list",vlist);
		return mv;
		
	}
	
	@RequestMapping(value= {"getlivetrackingdata"})
	public @ResponseBody String getlivetrackingdata(HttpServletRequest request) throws Exception  {
		JSONObject json=tripDetailsService.getlivetrackingdata();
		return json.toJSONString();
	}
	
	@RequestMapping(value= {"livetracking"},method=RequestMethod.GET)
	public ModelAndView livetracking() throws Exception{
		ModelAndView mv=new ModelAndView("livetracking");
		return mv;
		
	}
	
}
