package com.agiledge.atom.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agiledge.atom.dao.intfc.TripDao;
import com.agiledge.atom.dto.TripDetailsDto;
import com.agiledge.atom.entities.Tripshuttle;
import com.agiledge.atom.service.intfc.TripService;
@Service
public class TripServiceImpl implements TripService {
	private static final Logger logger = Logger.getLogger(TripServiceImpl.class);
	
	@Autowired
	private TripDao tripDao;

	public ArrayList<TripDetailsDto> getStationaryVehiclePosition(String branch) throws Exception {
		
		return tripDao.getStationaryVehiclePosition(branch);
	}

	public List<Tripshuttle> getShuttleTripDetailsByVehicleImei(String site,String fromDate, String toDate, String vehicleImei)	throws Exception { 
		
		return tripDao.getShuttleTripDetailsByVehicleImei(site,fromDate,toDate,vehicleImei);
	}

}
