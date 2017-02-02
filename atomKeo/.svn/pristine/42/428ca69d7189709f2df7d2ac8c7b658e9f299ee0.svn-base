package com.agiledge.atom.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agiledge.atom.entities.Area;
import com.agiledge.atom.entities.Branch;
import com.agiledge.atom.service.intfc.AplService;
import com.agiledge.atom.service.intfc.CompanyBranchService;
import com.agiledge.atom.service.intfc.OtherService;
import com.agiledge.atom.service.intfc.TripDetailsService;
import com.agiledge.atom.service.intfc.TripService;
import com.agiledge.atom.service.intfc.VehicleTypeService;

@Controller
public class GeoCoding {
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
	
	@RequestMapping(value= {"/pinLocationOnMap"})
	public String pinLocationOnMap(HttpServletRequest request,Map<String,Object> map) throws Exception  {
		
		return "trackVehicle";
	}

}
