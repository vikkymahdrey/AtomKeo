package com.agiledge.atom.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agiledge.atom.config.files.OtherFunctions;
import com.agiledge.atom.dao.impl.RoutingDaoImpl;
import com.agiledge.atom.dao.intfc.RoutingDao;
import com.agiledge.atom.dto.AplDistanceDto;
import com.agiledge.atom.dto.RoutingDto;
import com.agiledge.atom.dto.TripDetailsChildDto;
import com.agiledge.atom.dto.VehicleTypeDto;
import com.agiledge.atom.entities.Employee;
import com.agiledge.atom.entities.EmployeeSchedule;
import com.agiledge.atom.entities.Landmark;
import com.agiledge.atom.entities.Site;
import com.agiledge.atom.entities.TripDetail;
import com.agiledge.atom.entities.TripDetailsChild;
import com.agiledge.atom.entities.VehicleType;
import com.agiledge.atom.service.intfc.AplService;
import com.agiledge.atom.service.intfc.DistanceListService;
import com.agiledge.atom.service.intfc.EmployeeService;
import com.agiledge.atom.service.intfc.RoutingService;
import com.agiledge.atom.service.intfc.SchedulingService;
import com.agiledge.atom.service.intfc.SiteService;
import com.agiledge.atom.service.intfc.VehicleTypeService;
import com.agiledge.atom.utils.APLDto;
import com.agiledge.atom.utils.DateUtil;

@Service
public class RoutingServiceImpl implements RoutingService{

	private static final Logger logger = Logger.getLogger(RoutingServiceImpl.class);
	
	HashMap<String, RoutingDto> employeeDtoList = null;
	ArrayList<String> employeeInVehicle = new ArrayList<String>();
	
	LinkedHashMap<String, RoutingDto> employeesInRoute = new LinkedHashMap<String, RoutingDto>();
	HashMap<String, RoutingDto> allocatedEmployeeDtoList = new HashMap<String, RoutingDto>();
	RoutingDao routingDaoObj = null;
	int selectedRoute = 0;
	int routeid = 0;
	TripDetailsChildDto tripDetailsChildDtoObj = new TripDetailsChildDto();
	ArrayList<TripDetailsChildDto> tripChildDto=null;
	VehicleTypeDto chosenVehicleType = null;
	int employeeCountInRoute = 0;
	ArrayList<Integer> selectedRoutesLandmarks = null;
	StringBuilder selectedEmpSubscribedIDString = null;
	StringBuilder selectedEmployeeIDString = null;
	RoutingDto routingDtoForEmps = null;
	
	int remainingAvaialableSeat = 0;
	//TripDetailsDto tripDetailsDtoObj = new TripDetailsDto();
	//TripDetailsChildDto tripDetailsChildDtoObj = new TripDetailsChildDto();
	int needSecurity = 0;
	String isSecurityIn = "NO";
	public HashSet<String> projects = new HashSet<String>();
	public String routedType = "";
	public String doneBy = "";
	//ArrayList<TripDetailsChildDto> tripChildDto=null;
	String siteLandmark=null;
	
	
	@Autowired
	private RoutingDao routingDao;
	
	@Autowired
	private DistanceListService distanceListService;
	
	@Autowired
	private VehicleTypeService vehicleTypeService;
	
	
	@Autowired
	private AplService aplService;
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private SchedulingService schedulingService;
	
	
	
	
	 
	public String routeProcess(RoutingDto routingDto, int siteid,String overwrite,String doneBy, ArrayList<VehicleTypeDto> vehicleType)  throws Exception {
		String status = "success";
		  boolean empFlag = false;
		
			if (overwrite != null && overwrite.equals("yes")) {
				overWriteTrips(routingDto, siteid);
			}
			
			List<RoutingDto> routingDtos = getAllrouting(routingDto,siteid);
			logger.debug("List of getallRouting"+routingDtos.size());
								 
                int shiftIndex=0;

                for (RoutingDto eachRoutingDto : routingDtos) {
    				int routeIndex=0; 
    					if(setEmployeesForTravel(eachRoutingDto, siteid, "o")) {
    						empFlag = true;
    						routedType = "o";
    							while(getBestRouteId(eachRoutingDto, siteid)) {
    								getNodesOfTheSelectedRoute();
    									checkSecurity(eachRoutingDto, siteid);
    										selectVehicleType(siteid, vehicleType);
    									if (allocateEmployee(eachRoutingDto)) {
    											storeInDatabase(eachRoutingDto, siteid, doneBy);
    							   							routeIndex ++;
    						}
    					}
    				}
    					shiftIndex++;
                }	
			
		if (!empFlag) {
			status = "noEmps";
		}
			
		return status;
	}
	
	public void overWriteTrips(RoutingDto routingDto, int siteid) throws Exception{
		//List<TripDetailsChild> tdc=routingDao.getTripDetailsChildInfor(routingDto,siteid);
		List<TripDetail> td=routingDao.getTripDetailsInfor(routingDto,siteid);
					routingDao.overWriteTrips(td);
	}
	
	public List<RoutingDto> getAllrouting(RoutingDto routingDto,int siteid) throws Exception {
		return routingDao.getAllrouting(routingDto,siteid);
	}
	   
	
	public boolean setEmployeesForTravel(RoutingDto routingDto, int siteid,	String routingType) throws Exception {
	
		employeeDtoList = routingDao.setEmployeesForTravel(routingDto,siteid, routingType);
		
		logger.debug("Size of employeeDtoList"+employeeDtoList.size());
		//siteLandmark=Integer.toString(routingDao.siteLandmarkId);
		if (employeeDtoList == null) {
			employeeInVehicle.clear();
			return false;
		}else{
			return true;
		}

	}
	
	public boolean getBestRouteId(RoutingDto routingDto, int siteid) throws Exception {
			routeid = 0;
			try {
				if (employeeDtoList.size() <= 0) {
					return false;
				}
				selectedEmpSubscribedIDString = null;
				selectedEmployeeIDString = null;
				selectedEmpSubscribedIDString = new StringBuilder("");
				selectedEmployeeIDString = new StringBuilder("");
				RoutingDto routingDtoObj = null;
				
				for (Iterator it = employeeDtoList.entrySet().iterator(); it
						.hasNext();) {
					java.util.Map.Entry entry = (java.util.Map.Entry) it.next();
					routingDtoObj = (RoutingDto) entry.getValue();
					selectedEmpSubscribedIDString.append(
							routingDtoObj.getSubscriptionId()).append(",");
					selectedEmployeeIDString.append(routingDtoObj.getEmployeeId())
							.append(",");
				}
				/*for(Employee emp: empList){
					for(EmployeeSubscription esub: emp.getEmployeeSubscriptions1()){
						selectedEmpSubscribedIDString.append(esub.getSubscriptionID()).append(",");
					}
						selectedEmployeeIDString.append(emp.getId()).append(",");
				}*/
				
				selectedEmpSubscribedIDString.delete(
						selectedEmpSubscribedIDString.length() - 1,
						selectedEmpSubscribedIDString.length());
				selectedEmployeeIDString.delete(
						selectedEmployeeIDString.length() - 1,
						selectedEmployeeIDString.length());

				int[] returnArray = routingDao.getBestRouteId(selectedEmpSubscribedIDString, selectedEmployeeIDString,siteid, routingDto);
				routeid = returnArray[0];
				employeeCountInRoute = returnArray[1];
			
			} catch (Exception ex) {
				logger.debug("Error" + ex);
			}
			logger.debug("ROUteeeeeeeid"+routeid);
			if (routeid == 0) {
				return false;
			} else {
				return true;
			}
	
		}


	public void getNodesOfTheSelectedRoute() throws Exception {
		try {
				selectedRoutesLandmarks = routingDao.getLandmarksOfTheSelectedRoute(routeid);
				//ordertheLandmarks(selectedRoutesLandmarks);
				
			} catch (Exception ex) {
				logger.error("Error in getNodesOfTheSelectedRoute", ex);
			}
		}
		
	@Autowired
	private RoutingDaoImpl routingDaoImpl;
	
	public void ordertheLandmarks(ArrayList<Integer> landmarks) throws Exception {
		Map<Integer, Float> LandmarkDist = new HashMap<Integer, Float>();
		
		for (int i = 0; i < landmarks.size(); i++) {
			if (i == 0) 
				LandmarkDist.put(routingDaoImpl.siteLandmarkId, (float) 0.0);
							
				float distt = distanceListService.getDistance(siteLandmark,landmarks.get(i).toString());
				LandmarkDist.put(landmarks.get(i), distt);
						
		}
		// logger.debug("LandmarAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+LandmarkDist.size());
		LandmarkDist=sortTheMap(LandmarkDist);		
	}
	
	public <K, V extends Comparable<? super V>> Map<K, V> sortTheMap( Map<K, V> map ) throws Exception
	{

		List<Map.Entry<K, V>> list = new LinkedList<Entry<K, V>>( map.entrySet() );
			Collections.sort( list, new Comparator<Map.Entry<K, V>>()
						{
				public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
				{
					return (o1.getValue()).compareTo( o2.getValue() );
				}
						} );

		Map<K, V> result = new LinkedHashMap<K, V>();
		selectedRoutesLandmarks=null;
		selectedRoutesLandmarks=new ArrayList<Integer>();
		// logger.debug(list.size());
		for (Map.Entry<K, V> entry : list)
		{
			result.put( entry.getKey(), entry.getValue() );

			if(!entry.getKey().equals(siteLandmark))        	
				selectedRoutesLandmarks.add((Integer) entry.getKey());
		}
// logger.debug(" 1"+selectedRoutesLandmarks.get(0));

		return result;
}
	public void checkSecurity(RoutingDto routingDto, int siteid) throws Exception {
		try {
			needSecurity = 0;
			if ((routingDao.checkSecurity(routingDto, siteid)) == 1) {
				if (routingDao.checkFemaleInLast(selectedRoutesLandmarks.get(selectedRoutesLandmarks.size() - 1)) >= 1) {
					employeeCountInRoute++;
					needSecurity = 1;
				}
			}
			// logger.debug("empcnt" + employeeCountInRoute);
		} catch (Exception ex) {
			logger.error("Error",ex);
		}
	}
	
	
	public void selectVehicleType(int siteid,ArrayList<VehicleTypeDto> vehicleType) throws Exception {
		
		
			vehicleTypeService.getAllVehicleTypeByToAppend(vehicleType);
			int availVehicle = -1;
			
			for (int i = 0; i < vehicleType.size(); i++) {
				if (vehicleType.get(i).getCount() > 0) {
					availVehicle = i;
				}
				if (vehicleType.get(i).getSittingCopacity() >= employeeCountInRoute
						&& vehicleType.get(i).getCount() > 0) {
					vehicleType.get(i).setCount(
							vehicleType.get(i).getCount() - 1);
					chosenVehicleType = vehicleType.get(i);
					remainingAvaialableSeat = chosenVehicleType
							.getSittingCopacity();
				
					return;
				}

				if (i == vehicleType.size() - 1) {
					if (availVehicle != -1) {
						vehicleType.get(availVehicle).setCount(vehicleType.get(availVehicle).getCount() - 1);
						chosenVehicleType = vehicleType.get(availVehicle);
						remainingAvaialableSeat = chosenVehicleType.getSittingCopacity();
						
						return;
					} else {
						for (int j = 0; j < vehicleType.size(); j++) {
							if (vehicleType.get(j).getSittingCopacity() >= employeeCountInRoute) {
								vehicleType.get(j).setCount(
										vehicleType.get(j).getCount() - 1);
								chosenVehicleType = vehicleType.get(j);
								remainingAvaialableSeat = chosenVehicleType
										.getSittingCopacity();
								return;
							}
							if (j == vehicleType.size() - 1) {
								 
								vehicleType.get(j).setCount(vehicleType.get(j).getCount() - 1);
								chosenVehicleType = vehicleType.get(j);
								remainingAvaialableSeat = chosenVehicleType
										.getSittingCopacity();
								return;

							}
						}
					}

				}
			}
		}

	public boolean allocateEmployee(RoutingDto routingDto) throws Exception{

		if (employeeInVehicle != null) {
			employeeInVehicle.clear();
		}
		boolean flag = false;
		try {
			routingDtoForEmps = null;
			String employeeId = "";
			routingDtoForEmps = new RoutingDto();
			// logger.debug("Satge 1");
			employeesInRoute.clear();
			int lastLandmark = 0;
			for (int i = selectedRoutesLandmarks.size() - 1; i >= 0; i--) {
				// logger.debug("selected landmark :" + selectedRoutesLandmarks.get(i).toString());
				if (i == selectedRoutesLandmarks.size() - 1) {
					lastLandmark = selectedRoutesLandmarks.get(i);
					
				}
				int iteratorCount =0;
				for (Iterator it = employeeDtoList.entrySet().iterator(); it
						.hasNext();) {
					java.util.Map.Entry entry = (java.util.Map.Entry) it.next();
					employeeId = (String) entry.getKey();
					routingDtoForEmps = (RoutingDto) entry.getValue();
					if (routingDtoForEmps.getEmployeeLandmark().equals(
							selectedRoutesLandmarks.get(i).toString())
							&& remainingAvaialableSeat > 0) {

						employeesInRoute.put(employeeId, routingDtoForEmps);
						// logger.debug("emp :"+ employeeId);

					}
				}
				
			}
			flag = false;
			isSecurityIn = "NO";
			for (Iterator it = employeesInRoute.entrySet().iterator(); it
					.hasNext();) {
				java.util.Map.Entry entry = (java.util.Map.Entry) it.next();
				employeeId = (String) entry.getKey();
				routingDtoForEmps = (RoutingDto) entry.getValue();
				if (needSecurity == 1
						&& routingDtoForEmps.getEmployeeLandmark().equals(
								"" + lastLandmark)
						&& routingDtoForEmps.getEmployeegender().equals("F")) {
					if (remainingAvaialableSeat > 1) {
						allocatedEmployeeDtoList.put(employeeId,
								routingDtoForEmps);
						employeeInVehicle
								.add(routingDtoForEmps.getEmployeeId());
						flag = true;
						employeeDtoList.remove(employeeId);
						isSecurityIn = "YES";
						remainingAvaialableSeat = remainingAvaialableSeat - 2;

					}
				} else if (remainingAvaialableSeat > 0) {
					allocatedEmployeeDtoList.put(employeeId, routingDtoForEmps);
					employeeInVehicle.add(routingDtoForEmps.getEmployeeId());
					flag = true;
					employeeDtoList.remove(employeeId);
					remainingAvaialableSeat--;
				} else if (remainingAvaialableSeat <= 0) {
					break;
				}
			}
		} catch (Exception e) {
			logger.debug("Erro in allocation" + e);
			try {
				throw (e);
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}

		}
		return flag;
	}
	
	public void storeInDatabase(RoutingDto routingDto, int siteid, String doneBy) throws Exception {
		
			float distance[] = getDistance(routingDto);
			float time[] = getTime(routingDto, distance);
			int autoincNumber = 0, changedBy = 0;
			Site s=siteService.getSiteById(siteid);
			VehicleType vehicleType=vehicleTypeService.getVehicleTypeById(chosenVehicleType.getId());
			TripDetail tripDetail=null;
			tripDetail=new TripDetail();
			tripDetail.setSite(s);
			tripDetail.setTripDate(DateUtil.convertStringToDate(routingDto.getDate(),"dd/MM/yyyy","yyyy-MM-dd hh:mm:ss"));
			tripDetail.setTripTime(routingDto.getTime());
			tripDetail.setTripLog(routingDto.getTravelMode());
			tripDetail.setRouteId(routeid);
			tripDetail.setVehicleTypeBean(vehicleType);
			tripDetail.setSecurityStatus(isSecurityIn);
			tripDetail.setRoutingType(routedType);
			tripDetail.setDistance(routingDto.getDistance());
			tripDetail.setTravelTime(""+routingDto.getTravellingTime());
						
			
		TripDetail tripD= routingDao.storeInTripMaster(tripDetail);
		
		//int insertedID=Integer.valueOf(tripD.getId());
		String insertedID=tripD.getId();
		logger.debug("INSERTEDDDDID"+insertedID);
		tripChildDto=null;
			tripChildDto=new ArrayList<TripDetailsChildDto>();
			/*if (insertedID != 0) {
				changedBy = Integer.parseInt(doneBy);
				autoincNumber = insertedID;
				auditLogEntryforinserttripsheet(autoincNumber,
						AuditLogConstants.TRIPSHEET_MODULE, changedBy,
						AuditLogConstants.WORKFLOW_STATE_EMPTY,
						AuditLogConstants.WORKFLOW_STATE_TRIPSHEET_GENERATED,
						AuditLogConstants.AUDIT_LOG_CREATE_ACTION);
			}*/
			
			int empCountInvehicle = employeeInVehicle.size();
			if (routingDto.getTravelMode().equals("IN")) {
				for (int i = 0; i < empCountInvehicle; i++) {
					tripDetailsChildDtoObj=new TripDetailsChildDto();
					tripDetailsChildDtoObj.setTripId( insertedID);
					tripDetailsChildDtoObj.setEmployeeId(employeeInVehicle.get(i));
					tripDetailsChildDtoObj.setLandmarkId(allocatedEmployeeDtoList.get(employeeInVehicle.get(i)).getEmployeeLandmark());
					tripDetailsChildDtoObj.setScheduleId(allocatedEmployeeDtoList.get(employeeInVehicle.get(i)).getScheduleId());
					tripDetailsChildDtoObj.setRoutedOrder("" + (i + 1));
					tripDetailsChildDtoObj.setDistance(distance[i]);
					tripDetailsChildDtoObj.setTime("" + time[i]);
					tripChildDto.add(tripDetailsChildDtoObj);
				
				}
			} else {
				for (int i = empCountInvehicle - 1; i >= 0; i--) {
					
					tripDetailsChildDtoObj=new TripDetailsChildDto();
					tripDetailsChildDtoObj.setTripId(insertedID);
					tripDetailsChildDtoObj.setEmployeeId(employeeInVehicle.get(i));
					tripDetailsChildDtoObj.setLandmarkId(allocatedEmployeeDtoList.get(employeeInVehicle.get(i)).getEmployeeLandmark());
					tripDetailsChildDtoObj
							.setScheduleId(allocatedEmployeeDtoList.get(
									employeeInVehicle.get(i)).getScheduleId());
					tripDetailsChildDtoObj
					.setTransportType(allocatedEmployeeDtoList.get(
							employeeInVehicle.get(i)).getTransportType());

					tripDetailsChildDtoObj.setRoutedOrder(""
							+ (empCountInvehicle - i));
					tripDetailsChildDtoObj.setDistance(distance[i]);
					tripDetailsChildDtoObj.setTime("" + time[i]);
					tripChildDto.add(tripDetailsChildDtoObj);
					
					
				}
			}
			storeInTripChild(tripChildDto);			
			
			// logger.debug("emp in veh" + employeeInVehicle);
		
	}
	
	public float[] getDistance(RoutingDto routingDto) throws Exception {

		int empCountInvehicle = employeeInVehicle.size();
		int landmarks[] = new int[empCountInvehicle];
		float distance[] = null;
		try {
			for (int i = 0; i < empCountInvehicle; i++) {
				landmarks[i] = Integer.parseInt(allocatedEmployeeDtoList.get(
						employeeInVehicle.get(i)).getEmployeeLandmark());
			}

			distance = routingDao.getDistances(landmarks, routingDto);
			
			if(routingDao.isDistanceInDb()==false) {
				
				float[] distance1 = getMapDistances(landmarks, routingDto, routingDao.getAplDistanceList(), routingDao.getLandmarkDistanceList());
				if(distance1 == null ) {
					} else {
					distance = distance1;
					distanceListService. insertDistanceFromDistanceList(routingDaoObj.getAplDistanceList());
					//trackList.addAll()
//					getErrorMessageList().addAll( distanceListDao.getErrorMessageList());
				}
			
				/*if(routingDto.getTravelMode().equalsIgnoreCase("IN")  && distance[0]!=0) {
					getErrorMessageList() .add("IN : before loop start in storeInDatabase Error routeid :"+routeid);
				}*/
			}
		} catch (Exception e) {
			logger.error("Get distance error", e);
 
			throw (e);
		}
		return distance;
	}
	
public float[] getMapDistances(int landmarks222[], RoutingDto routingDto, ArrayList<AplDistanceDto> aplDistanceList, ArrayList<AplDistanceDto> landmarkDistanceList) throws Exception {
		
		float distance[] = new float[landmarks222.length];
		float totalDistance = 0.0f;
			
		HashMap<String, APLDto> aplMap = new HashMap<String, APLDto>();
		int landmarkDupe[] = new int[landmarkDistanceList.size()];
		try {
			if(landmarkDistanceList.size()<11)
			{
		
			int ctx=0;
		 	for(AplDistanceDto addto : landmarkDistanceList ) {
		 		landmarkDupe[ctx] =Integer.parseInt( addto.getSourceId());
		 		//logger.debug(".. "+ landmarkDupe[ctx]);
		 		ctx++;
		 	}
		 	 
		aplMap = aplService.getAllAPL(landmarkDupe);
		//logger.debug("Okey...");
		int totalPoints = landmarkDistanceList.size();
		//logger.debug("Apl Distance Size :" + aplDistanceList.size());
		//logger.debug("Map size : "+ aplMap.size());
		  
		String sourceLatLng= aplMap.get(landmarkDistanceList.get(0).getSourceId()).getLattitude() + ","+ aplMap.get(landmarkDistanceList.get(0).getSourceId()).getLongitude();
		String destinationLatLng=aplMap.get(landmarkDistanceList.get(totalPoints-1).getSourceId()).getLattitude() + ","+ aplMap.get(landmarkDistanceList.get(totalPoints-1).getSourceId()).getLongitude();
		 //logger.debug("source : " + aplMap.get(landmarkDistanceList.get(0).getSourceId()).getLandMark());
		 
		String wayPointLatLngs = "";
		
		for(int i = 1; i< landmarkDistanceList.size()-1; i++) {
			/*logger.debug("i : "+ i);
			logger.debug("source : " + aplMap.get(landmarkDistanceList.get(i).getSourceId()).getLandMark());*/
			if(i!=1&& i!=landmarkDistanceList.size()-1) {
				wayPointLatLngs+=URLEncoder.encode("|");
			}
			wayPointLatLngs+=  aplMap.get(landmarkDistanceList.get(i).getSourceId()).getLattitude() + ","+ aplMap.get(landmarkDistanceList.get(i).getSourceId()).getLongitude();
		}
		
		if(wayPointLatLngs.trim().equals("")==false) {
			wayPointLatLngs="&waypoints="+wayPointLatLngs;
		}
		/*logger.debug("source : " + aplMap.get(landmarkDistanceList.get(totalPoints-1).getSourceId()).getLandMark());
		logger.debug(wayPointLatLngs);*/
		/* get distances from map api */
		DefaultHttpClient client1 = new DefaultHttpClient();					
	     
		String url="https://maps.googleapis.com/maps/api/directions/json?origin="+sourceLatLng+"&destination="+destinationLatLng+wayPointLatLngs;
		String keyedURL=OtherFunctions.encryptTheMapKey(url);
		HttpGet request = new HttpGet(keyedURL);
	    HttpResponse response1 = 
	    		 client1.execute(request);
	      
	     BufferedReader rd = new BufferedReader (new InputStreamReader(response1.getEntity().getContent()));
	     String line = "";
	     String nLine="";
	     /*logger.debug(response1.getStatusLine());*/
	     while ((line = rd.readLine()) != null) {
	    	 nLine+=line;
	        }
	     //logger.debug(nLine);
	     JSONObject obj = new JSONObject(nLine);
	 /*    		 routes : [0].legs[0].distance.value = meter
	    		 routes : [0].legs[0].duration.value= 1611 second
	    		 routes : [0].legs[0].start_location.lat= 12.9963345
	    		 routes : [0].legs[0].start_location.lng= 77.5646637
	    		 routes : [0].legs[0].end_location.lat= 12.9201671
	    		 routes : [0].legs[0].end_location.lng= 77.5646637
	 */      
	   
	   
	  int legs = ((JSONObject) obj.getJSONArray("routes").get(0)).getJSONArray("legs").length();
	  // logger.debug("Total Legs :"+ legs);
	  for(int i = 0 ; i < legs ; i ++) {
		  JSONObject leg = ((JSONObject) obj.getJSONArray("routes").get(0)).getJSONArray("legs").getJSONObject(i);
		  /*logger.debug( "Distance : " + leg.getJSONObject("distance").getDouble("value"));
		  logger.debug( "Duration : " + leg.getJSONObject("duration").getDouble("value"));
		  logger.debug( "Start Location lat/lng : " + leg.getJSONObject("start_location").getDouble("lat"));
		  logger.debug( "," + leg.getJSONObject("start_location").getDouble("lng"));
		  logger.debug( "End Location lat/lng : " + leg.getJSONObject("end_location").getDouble("lat"));
		  logger.debug( "," + leg.getJSONObject("end_location").getDouble("lng"));*/
		  double distanceM =leg.getJSONObject("distance").getDouble("value");
		  distanceM = distanceM / 1000;
		   /*logger.debug(landmarkDistanceList.get(i).getSourceId());*/
		  /*logger.debug("Disttance (" + i + ") : " + distanceM);*/
		  /*logger.debug();*/
		/* get distance from map api ends */
		/* logger.debug("DISSSSSSSSSS"+routingDaoObj.getLandmarkDistanceList().size());
			 logger.debug("DISSSSSSSSSS11111"+routingDaoObj.getLandmarkDistanceList().get(i));*/
		  routingDaoObj.getLandmarkDistanceList().get(i).setDistance(distanceM);
		   /*logger.debug(routingDaoObj.getLandmarkDistanceList().get(i).getSourceId() +  " > " +routingDaoObj.getLandmarkDistanceList().get(i).getTargetId());*/
		  totalDistance +=distanceM;
		  routingDaoObj.getAplDistanceList().get(i).setDistance(distanceM);
		  if(routingDto.getTravelMode().equalsIgnoreCase("IN")) {
			  if(i==0) {
				  distance[i] = 0;
			  } else if(i<legs-1 ) {
				  distance[i] = (float) distanceM;
			  }
		  } else if(routingDto.getTravelMode().equalsIgnoreCase("OUT")) {
			  
			  if(i<legs) {
			/*	  logger.debug(" [] :" + ((legs-1)-i) );*/
				  distance[(legs-1)-i] = (float) distanceM;
			  }
		  }
		   
		  
		  }
		}
	  /*logger.debug("Total distance : "+ totalDistance);*/
	  routingDto.setDistance(totalDistance);
	   
		}catch(Exception e) {
			logger.error("Error in getMapDistance : ", e);
			distance = null;
		}
		
		return distance;
	}	

public float[] getTime(RoutingDto routingDto, float[] distance) throws Exception{
	int empCountInvehicle = employeeInVehicle.size();
	int firstlandmark = 0;
	float time[] = null;
	try {

		time = routingDao.getTime(routingDto, distance);
	} catch (Exception e) {
		e.printStackTrace();
	}

	return time;
}

public void storeInTripChild(ArrayList<TripDetailsChildDto> tripDetailsChildDtoList) throws Exception
{
	TripDetailsChild tdc=null;
	
	for(TripDetailsChildDto tripDetailsChildDtoObj: tripDetailsChildDtoList){
		tdc=new TripDetailsChild();
		TripDetail td=getTripDetailById(tripDetailsChildDtoObj.getTripId());
		Employee emp=employeeService.getEmployeeById(tripDetailsChildDtoObj.getEmployeeId());
		Landmark lm=aplService.getLandmarkById(tripDetailsChildDtoObj.getLandmarkId());
		EmployeeSchedule empSch=schedulingService.getScheduleDetailsById(tripDetailsChildDtoObj.getScheduleId());
		tdc.setTripDetail(td);
		tdc.setEmployee(emp);
		tdc.setRoutedOrder(Integer.valueOf(tripDetailsChildDtoObj.getRoutedOrder()));
		tdc.setLandmark(lm);
		tdc.setEmployeeSchedule(empSch);
		tdc.setTime(tripDetailsChildDtoObj.getTime());
		tdc.setDist(tripDetailsChildDtoObj.getDistance());
		tdc.setTransportType(tripDetailsChildDtoObj.getTransportType());
		routingDao.storeInTripChild(tdc);
		
	}
}	
	

public TripDetail getTripDetailById(String tripId) throws Exception {
	return routingDao.getTripDetailById(tripId);
}

public int checkTripExist(RoutingDto routingDto, int siteid) throws Exception {
	return routingDao.checkTripExist(routingDto, siteid);
}


}	
