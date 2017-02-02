package com.agiledge.atom.service.intfc;

import java.util.ArrayList;
import java.util.List;

import com.agiledge.atom.dto.TripDetailsDto;
import com.agiledge.atom.entities.Tripshuttle;

public interface TripService {
	public ArrayList<TripDetailsDto> getStationaryVehiclePosition(String branch) throws Exception;

	public List<Tripshuttle> getShuttleTripDetailsByVehicleImei(String site,String fromDate, String toDate, String vehicleImei)throws Exception;
}
