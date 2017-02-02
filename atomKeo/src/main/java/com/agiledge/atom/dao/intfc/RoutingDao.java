package com.agiledge.atom.dao.intfc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.agiledge.atom.dto.AplDistanceDto;
import com.agiledge.atom.dto.RoutingDto;
import com.agiledge.atom.dto.VehicleTypeDto;
import com.agiledge.atom.entities.TripDetail;
import com.agiledge.atom.entities.TripDetailsChild;

public interface RoutingDao {


	RoutingDto routeProcess(RoutingDto routingDto, int siteid,String overwrite, ArrayList<VehicleTypeDto> vehicleType) throws Exception;
	List<RoutingDto> getAllrouting(RoutingDto routingDto, int siteid) throws Exception;
	HashMap<String, RoutingDto> setEmployeesForTravel(RoutingDto routingDto, int siteid,String routingType) throws Exception;
	int[] getBestRouteId(StringBuilder selectedEmpSubscribedIDString,StringBuilder selectedEmployeeIDString, int siteid,RoutingDto routingDto) throws Exception;
	boolean checkCombainedRouteStatus(RoutingDto routingDto, int siteId) throws Exception;
	ArrayList<Integer> getLandmarksOfTheSelectedRoute(int routeId) throws Exception; 
	void getSitelandmark(int siteId) throws Exception;
	int checkSecurity(RoutingDto routingDto, int siteid) throws Exception;
	int checkFemaleInLast(int lastLandmark) throws Exception; 
	float[] getDistances(int[] landmarks, RoutingDto routingDto) throws Exception;
	boolean isDistanceInDb() throws Exception;
	ArrayList<AplDistanceDto> getAplDistanceList() throws Exception;	
	void setAplDistanceList(ArrayList<AplDistanceDto> aplDistanceList) throws Exception;
	ArrayList<AplDistanceDto> getLandmarkDistanceList() throws Exception;
	void setLandmarkDistanceList(ArrayList<AplDistanceDto> landmarkDistanceList) throws Exception;
	float[] getTime(RoutingDto routingDto, float[] distance) throws Exception;
	TripDetail storeInTripMaster(TripDetail tripDetail) throws Exception;
	//void storeInTripChild(ArrayList<TripDetailsChildDto> tripChildDto) throws Exception;
	TripDetail getTripDetailById(String tripId) throws Exception;
	void storeInTripChild(TripDetailsChild tdc) throws Exception;
	int checkTripExist(RoutingDto routingDto, int siteid) throws Exception;
	void overWriteTrips(List<TripDetail> td) throws Exception;
	//List<TripDetailsChild> getTripDetailsChildInfor(RoutingDto routingDto, int siteid)throws Exception;
	List<TripDetail> getTripDetailsInfor(RoutingDto routingDto, int siteid) throws Exception;
	
	
}
