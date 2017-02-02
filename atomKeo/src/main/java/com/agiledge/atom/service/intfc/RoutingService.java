package com.agiledge.atom.service.intfc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.agiledge.atom.dto.AplDistanceDto;
import com.agiledge.atom.dto.RoutingDto;
import com.agiledge.atom.dto.VehicleTypeDto;

public interface RoutingService {

	 String routeProcess(RoutingDto routingDto, int siteid, String overwrite,String doneBy, ArrayList<VehicleTypeDto> vehicleType) throws Exception;
	 List<RoutingDto> getAllrouting(RoutingDto routingDto,int siteid) throws Exception;
	 boolean setEmployeesForTravel(RoutingDto routingDto, int siteid,	String routingType) throws Exception;
	 boolean getBestRouteId(RoutingDto routingDto, int siteid) throws Exception;
	 void getNodesOfTheSelectedRoute() throws Exception;
	 void ordertheLandmarks(ArrayList<Integer> landmarks) throws Exception;
	 public <K, V extends Comparable<? super V>> Map<K, V> sortTheMap( Map<K, V> map ) throws Exception;
	 void checkSecurity(RoutingDto routingDto, int siteid) throws Exception;
	 void selectVehicleType(int siteid,ArrayList<VehicleTypeDto> vehicleType) throws Exception;
	 boolean allocateEmployee(RoutingDto routingDto) throws Exception;
	 void storeInDatabase(RoutingDto routingDto, int siteid, String doneBy) throws Exception;
	 float[] getDistance(RoutingDto routingDto) throws Exception;
	 float[] getMapDistances(int landmarks222[], RoutingDto routingDto, ArrayList<AplDistanceDto> aplDistanceList, ArrayList<AplDistanceDto> landmarkDistanceList) throws Exception;
	 float[] getTime(RoutingDto routingDto, float[] distance) throws Exception;
	 int checkTripExist(RoutingDto routingDto, int siteid) throws Exception;
	 void overWriteTrips(RoutingDto routingDto, int siteid) throws Exception;
	
}
