package com.agiledge.atom.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.agiledge.atom.config.files.OtherFunctions;
import com.agiledge.atom.dao.intfc.RoutingDao;
import com.agiledge.atom.dto.AplDistanceDto;
import com.agiledge.atom.dto.RoutingDto;
import com.agiledge.atom.dto.VehicleTypeDto;
import com.agiledge.atom.entities.Adhocbooking;
import com.agiledge.atom.entities.Distchart;
import com.agiledge.atom.entities.Employee;
import com.agiledge.atom.entities.EmployeeSchedule;
import com.agiledge.atom.entities.EmployeeScheduleAlter;
import com.agiledge.atom.entities.EmployeeSubscription;
import com.agiledge.atom.entities.Logtime;
import com.agiledge.atom.entities.Route;
import com.agiledge.atom.entities.Routechild;
import com.agiledge.atom.entities.Site;
import com.agiledge.atom.entities.SiteShift;
import com.agiledge.atom.entities.Timesloat;
import com.agiledge.atom.entities.TripDetail;
import com.agiledge.atom.entities.TripDetailsChild;
import com.agiledge.atom.utils.DateUtil;

@Repository
public class RoutingDaoImpl extends AbstractDao implements RoutingDao {
	private static final Logger logger = Logger.getLogger(RoutingDaoImpl.class);

	static final int LADY_SECURTY = 1;
	StringBuilder selectedEmpSubscribedIDString = null;
	StringBuilder selectedEmployeeIDString = null;
	ArrayList<Integer> selectedRoutesLandmarks = new ArrayList<Integer>();
	String uniqueId = "";
	String uniqueCode = "";
	int uniqueNo = 1;
	public int siteLandmarkId;
	
	boolean distanceInDb = true;
	ArrayList<AplDistanceDto> aplDistanceList;
	ArrayList<AplDistanceDto> landmarkDistanceList;
	
	public RoutingDto routeProcess(RoutingDto routingDto, int siteid,
			String overwrite,ArrayList<VehicleTypeDto> vehicleType)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RoutingDto> getAllrouting(RoutingDto routingDto,int siteid) throws Exception {
		
		List<RoutingDto> routingDtos  = new ArrayList<RoutingDto>();
		
		RoutingDto routingDtoRes = null;
		if ( !(routingDto.getTime().equalsIgnoreCase("all"))) {
			routingDtos.add(routingDto);				
		} else {
									
			Query queryEmpScd = getEntityManager().createQuery("from EmployeeSchedule where fromDate<=:tripDate and toDate>=:tripDate and status='a'");
			queryEmpScd.setParameter("tripDate", DateUtil.convertStringToDate(routingDto.getDate(),  "dd/MM/yyyy","yyyy-MM-dd hh:mm::ss"));
			List<EmployeeSchedule> empSchedules =  queryEmpScd.getResultList();
			
			
			Query queryTrpDet = getEntityManager().createQuery("from TripDetail where tripDate=:tripDate"
					+ " and site.id=:siteId and tripTime!='' and tripTime!='null' and tripTime!='none' and tripTime!='' and tripTime!='weekly off' and tripTime is not null   order by tripTime");
			queryTrpDet.setParameter("siteId", siteid);
			queryTrpDet.setParameter("tripDate", DateUtil.convertStringToDate(routingDto.getDate(), "dd/MM/yyyy", "yyyy-MM-dd hh:mm::ss"));
			// queryTrpDet.setParameter("tripLog", routingDto.getTime());
			List<TripDetail> tripDetails = queryTrpDet.getResultList();
			
			List<EmployeeScheduleAlter> empScheduleAlters = null;
			List<Adhocbooking> adhocBookings = null;
			
			if(empSchedules != null && empSchedules.size() > 0){
				for(EmployeeSchedule eachSchedule : empSchedules){
					for(TripDetail eachTripDetail : tripDetails){
						if(!(eachSchedule.getLoginTime().equalsIgnoreCase(eachTripDetail.getTripTime()))){
							if (!(routingDto.getTravelMode().equalsIgnoreCase("All"))) {
								if(eachTripDetail.getTripLog().equalsIgnoreCase(routingDto.getTravelMode())){
									routingDtoRes = new RoutingDto();
									routingDtoRes.setDate(routingDto.getDate());
									routingDtoRes.setTravelMode(eachTripDetail.getTripLog());
									routingDtoRes.setTransportType("shift");
									routingDtos.add(routingDtoRes);
								}
							}else{
								routingDtoRes = new RoutingDto();
								routingDtoRes.setDate(routingDto.getDate());
								routingDtoRes.setTravelMode(eachTripDetail.getTripLog());
								routingDtoRes.setTransportType("shift");
								routingDtos.add(routingDtoRes);
							}
							
						}
					}
					
					empScheduleAlters = eachSchedule.getEmployeeScheduleAlters();
					for(EmployeeScheduleAlter eachScheduleAlter : empScheduleAlters){
						for(TripDetail eachTripDetail : tripDetails){
							if(!(eachScheduleAlter.getLoginTime().equalsIgnoreCase(eachTripDetail.getTripTime()))){
								if (!(routingDto.getTravelMode().equalsIgnoreCase("All"))) {
									if(eachTripDetail.getTripLog().equalsIgnoreCase(routingDto.getTravelMode())){
										routingDtoRes = new RoutingDto();
										routingDtoRes.setDate(routingDto.getDate());
										routingDtoRes.setTravelMode(eachTripDetail.getTripLog());
										routingDtoRes.setTransportType("shift");
										routingDtos.add(routingDtoRes);
									}
								}else{
									routingDtoRes = new RoutingDto();
									routingDtoRes.setDate(routingDto.getDate());
									routingDtoRes.setTravelMode(eachTripDetail.getTripLog());
									routingDtoRes.setTransportType("shift");
									routingDtos.add(routingDtoRes);
								}
								
							}
						}
						
					}
					
					adhocBookings = eachSchedule.getAdhocbookings();
					for(Adhocbooking eachAdhocBooking : adhocBookings){
						for(TripDetail eachTripDetail : tripDetails){
							if(!(eachAdhocBooking.getStartTime().equalsIgnoreCase(eachTripDetail.getTripTime()))){
								if (!(routingDto.getTravelMode().equalsIgnoreCase("All"))) {
									if(eachTripDetail.getTripLog().equalsIgnoreCase(routingDto.getTravelMode())){
										routingDtoRes = new RoutingDto();
										routingDtoRes.setDate(routingDto.getDate());
										routingDtoRes.setTravelMode(eachTripDetail.getTripLog());
										routingDtoRes.setTransportType("adhoc");
										routingDtos.add(routingDtoRes);
									}
								}else{
									routingDtoRes = new RoutingDto();
									routingDtoRes.setDate(routingDto.getDate());
									routingDtoRes.setTravelMode(eachTripDetail.getTripLog());
									routingDtoRes.setTransportType("adhoc");
									routingDtos.add(routingDtoRes);
								}
							}
						}

						
					
					}
				}
			}
	}		
		
		
		
		return routingDtos;
	}

	
public HashMap<String, RoutingDto> setEmployeesForTravel(RoutingDto routingDto, int siteid, String routingType) throws Exception {
		
	getSitelandmark(siteid);	
		String logtimequery="from Logtime where logtime=:travelTime and logtype=:logtype";
			Query q1=getEntityManager().createQuery(logtimequery);
				q1.setParameter("travelTime", routingDto.getTime());
					q1.setParameter("logtype", routingDto.getTravelMode());
						List<Logtime> logtimes=q1.getResultList();
		
		
						
						String empSchquery="from EmployeeSchedule es where es.status='a' and es.fromDate<=:fromDate and es.toDate>=:toDate";
						Query q=getEntityManager().createQuery(empSchquery);
							q.setParameter("fromDate", DateUtil.convertStringToDate(routingDto.getDate(), "dd/MM/yyyy", "yyyy-MM-dd hh:mm::ss"));
								q.setParameter("toDate", DateUtil.convertStringToDate(routingDto.getDate(), "dd/MM/yyyy", "yyyy-MM-dd hh:mm::ss"));
									List<EmployeeSchedule> employeeSchList=q.getResultList();				
						
		List<Employee> empList=new ArrayList<Employee>();
				
			for(Logtime lt : logtimes){
				if(lt.getLogtype().equalsIgnoreCase("IN")){
					for(EmployeeSchedule  es : employeeSchList){
						if(es.getLoginTime().equals(routingDto.getTime())){ 
							Employee e= es.getEmployeeSubscription().getEmployee1();
								empList.add(e);
								
									for(EmployeeScheduleAlter esa : e.getEmployeeScheduleAlters()){
										if((esa.getDate().compareTo(DateUtil.convertStringToDate(routingDto.getDate(), "dd/MM/yyyy", "yyyy-MM-dd hh:mm::ss")))==0){	
											if(esa.getStatus().equals("a") && esa.getLoginTime().equals(routingDto.getTime())){
												if(!empList.contains(esa.getEmployee())){ 
												empList.add(esa.getEmployee());
											}
										}
									}
								}							
						
									for(Adhocbooking adhoc : e.getAdhocbookings3()){
										if((adhoc.getTravelDate().compareTo(DateUtil.convertStringToDate(routingDto.getDate(), "dd/MM/yyyy", "yyyy-MM-dd hh:mm::ss")))==0 && 
												adhoc.getStartTime().equals(routingDto.getTime()) && adhoc.getPickDrop().equals("IN")){
													if(!empList.contains(adhoc.getEmployee3())){
														empList.add(adhoc.getEmployee3());
										}
									}		
								}
							}else if(es.getLoginTime().equalsIgnoreCase("none") || es.getLoginTime()==null || es.getLoginTime().equals("")){
								Employee e= es.getEmployeeSubscription().getEmployee1();
								
								for(EmployeeScheduleAlter esa :e.getEmployeeScheduleAlters()){
										if((esa.getDate().compareTo(DateUtil.convertStringToDate(routingDto.getDate(), "dd/MM/yyyy", "yyyy-MM-dd hh:mm::ss")))==0){	
											if(esa.getStatus().equals("a") && esa.getLoginTime().equals(routingDto.getTime())){
												empList.add(esa.getEmployee());
											}
										}
									}
															
								
								for(Adhocbooking adhoc : e.getAdhocbookings3()){logger.debug("In adhoc bokingg");
										if((adhoc.getTravelDate().compareTo(DateUtil.convertStringToDate(routingDto.getDate(), "dd/MM/yyyy", "yyyy-MM-dd hh:mm::ss")))==0 && 
												adhoc.getStartTime().equals(routingDto.getTime()) && adhoc.getPickDrop().equals("IN")){logger.debug("In adhoc bokingg inside");
													if(!empList.contains(adhoc.getEmployee3())){
															empList.add(adhoc.getEmployee3());		
											}
										}
									}
							}					
						}
				}else if(lt.getLogtype().equalsIgnoreCase("OUT")){
					for(EmployeeSchedule  es : employeeSchList){
						if(es.getLogoutTime().equals(routingDto.getTime())){
							Employee e= es.getEmployeeSubscription().getEmployee1();
								empList.add(e);
									for(EmployeeScheduleAlter esa : e.getEmployeeScheduleAlters()){
										if((esa.getDate().compareTo(DateUtil.convertStringToDate(routingDto.getDate(), "dd/MM/yyyy", "yyyy-MM-dd hh:mm::ss")))==0){	
											if(esa.getStatus().equals("a")){
												if(!empList.contains(esa.getEmployee())){
													empList.add(esa.getEmployee());
											}
										}
									}
								}							
						
									for(Adhocbooking adhoc : e.getAdhocbookings3()){
										if((adhoc.getTravelDate().compareTo(DateUtil.convertStringToDate(routingDto.getDate(), "dd/MM/yyyy", "yyyy-MM-dd hh:mm::ss")))==0 && 
												adhoc.getStartTime().equals(routingDto.getTime()) && adhoc.getPickDrop().equals("OUT")){
													if(!empList.contains(adhoc.getEmployee3())){
														empList.add(adhoc.getEmployee3());
										}
									}
								}
									
						}else if(es.getLogoutTime().equalsIgnoreCase("none") || es.getLogoutTime()==null || es.getLogoutTime().equals("")){
							Employee e= es.getEmployeeSubscription().getEmployee1();
									
								for(EmployeeScheduleAlter esa : e.getEmployeeScheduleAlters()){
									if((esa.getDate().compareTo(DateUtil.convertStringToDate(routingDto.getDate(), "dd/MM/yyyy", "yyyy-MM-dd hh:mm::ss")))==0){	
										if(esa.getStatus().equals("a")){
											empList.add(esa.getEmployee());
										}
									}
								}
														
					
								for(Adhocbooking adhoc : e.getAdhocbookings3()){
									if((adhoc.getTravelDate().compareTo(DateUtil.convertStringToDate(routingDto.getDate(), "dd/MM/yyyy", "yyyy-MM-dd hh:mm::ss")))==0 && 
											adhoc.getStartTime().equals(routingDto.getTime()) && adhoc.getPickDrop().equals("OUT")){
												if(!empList.contains(adhoc.getEmployee3())){
													empList.add(adhoc.getEmployee3());
									}
								}
							}
						}
					}
				}
			}	
	
			HashMap<String, RoutingDto> mapList=new HashMap<String, RoutingDto>();
			RoutingDto routingDtoToRoute = null;
			for(Employee e : empList){
				routingDtoToRoute = new RoutingDto();
				routingDtoToRoute.setDate(routingDto.getDate());
				routingDtoToRoute.setTime(routingDto.getTime());
				routingDtoToRoute.setTravelMode(routingDto.getTravelMode());
				routingDtoToRoute.setEmployeeId(e.getId());
				routingDtoToRoute.setSubscriptionId(e.getEmployeeSubscriptions1().get(0).getSubscriptionID());
				routingDtoToRoute.setScheduleId(e.getEmployeeSchedules1().get(0).getId());
				routingDtoToRoute.setEmployeeLandmark(e.getEmployeeSubscriptions1().get(0).getLandmark().getId());
				routingDtoToRoute.setProject(e.getAtsconnect().getProject());
				routingDtoToRoute.setEmployeegender(e.getGender());
				//routingDtoToRoute.setTransportType(e.getAdhocbookings1().get(0).getAdhocType());
				mapList.put(routingDtoToRoute.getEmployeeId(),
						routingDtoToRoute);
			}
	return mapList;
}	



public int[] getBestRouteId(StringBuilder selectedEmpSubscribedIDString,StringBuilder selectedEmployeeIDString, int siteid,RoutingDto routingDto) throws Exception {
	int routeId = 0;
	int employeeCount = 0;
	int[] returnArray = new int[2];
	if (!(selectedEmpSubscribedIDString.length() < 1)) {
		this.selectedEmpSubscribedIDString = selectedEmpSubscribedIDString;
		this.selectedEmployeeIDString = selectedEmployeeIDString;
			String query = "";
		try {
			for (int i = 1; i <= 1; i++) {
										
				query="from EmployeeSubscription es where es.subscriptionID  in ("+ selectedEmpSubscribedIDString +" )  and (es.subscriptionStatus='subscribed' or es.subscriptionStatus='pending')" ;
				Query q=getEntityManager().createQuery(query);
				List<EmployeeSubscription> empSubsList=q.getResultList();
				
						
				for(EmployeeSubscription esubs: empSubsList){
					for(Routechild rc: esubs.getLandmark().getRoutechilds()){
						Route r=rc.getRoute();
							if(r.getSite().getId()==siteid){
								if(r.getRoutetype().getPriority()<=1){
									routeId=r.getId();
									employeeCount=1;
								}
							}
						}
					}
				if (employeeCount <= 3) {
					if (!checkCombainedRouteStatus(routingDto, siteid)) {
						break;
							}
				} else {
					break;
				}
						
			}
				
				
			returnArray[0] = routeId;
			returnArray[1] = employeeCount;
		} catch (Exception e) {
			logger.error("ERROR in getBestRouteId", e);
		}
		
	}
	return returnArray;
}	

 public boolean checkCombainedRouteStatus(RoutingDto routingDto, int siteId) throws Exception {
	 logger.debug("In combained query");
	 	boolean retFlag = false;
	 		
		String query="from Logtime lt where lt.logtime=:logtime and lt.logtype=:logtype";
		Query q=getEntityManager().createQuery(query);
		q.setParameter("logtime", routingDto.getTime());
		q.setParameter("logtype", routingDto.getTravelMode());
		List<Logtime> logtimeList=q.getResultList();
		
		for(Logtime lt : logtimeList){
			for(SiteShift ss :lt.getSiteShifts()){
				if(ss.getSite().getId()==siteId){
					if(ss.getCombainroute().equalsIgnoreCase("yes")){
						retFlag=true;
					}else if(ss.getSite().getCombainedRouteOnweekoff()==1 && (OtherFunctions.getDay(routingDto.getDate()) == 1 || OtherFunctions.getDay(routingDto.getDate()) == 7)){
						retFlag=true;
					}
				}
			}
		}
		
	return retFlag;
}
 public ArrayList<Integer> getLandmarksOfTheSelectedRoute(int routeId) throws Exception {
		selectedRoutesLandmarks.clear();
		
		/*String query="from Routechild rc where rc.route.id="+routeId+" order by rc.position";
		Query q=getEntityManager().createQuery(query);
		List<Routechild> routeChildList=q.getResultList();
		for(Routechild rc : routeChildList){
			for(EmployeeSubscription esubs: rc.getLandmark().getEmployeeSubscriptions()){
				if(esubs.getSubscriptionStatus().equalsIgnoreCase("subscribed") || esubs.getSubscriptionStatus().equalsIgnoreCase("pending")){
					if(esubs.getSubscriptionID())
				}
			}*/
		
		
			String query="from EmployeeSubscription es where es.subscriptionID  in ("+ selectedEmpSubscribedIDString +" )  and (es.subscriptionStatus='subscribed' or es.subscriptionStatus='pending')" ;
			Query q=getEntityManager().createQuery(query);
			List<EmployeeSubscription> empSubsList=q.getResultList();
			
					
			for(EmployeeSubscription esubs: empSubsList){
				for(Routechild rc: esubs.getLandmark().getRoutechilds()){
					selectedRoutesLandmarks.add(Integer.valueOf(rc.getLandmark().getId()));
						}
					}
				
		return selectedRoutesLandmarks;
	}
 
 public void getSitelandmark(int siteId) throws Exception{

	{
		String query="from Site where id="+siteId;
		Query q=getEntityManager().createQuery(query);
		Site site=(Site) q.getResultList().get(0);
		siteLandmarkId=Integer.parseInt(site.getLandmarkBean().getId());
			
		}

	}

public int checkSecurity(RoutingDto routingDto, int siteid) throws Exception {
	
	int retVal=0;

	String query = "from Site where (nightShiftStart<'"
			+ routingDto.getTime() + "' OR nightShiftEnd>'"
			+ routingDto.getTime() + "') AND ladySecurity=:ladySecurity and id=:siteid";
	
	Query q=getEntityManager().createQuery(query);
	q.setParameter("ladySecurity", Integer.valueOf(LADY_SECURTY));
	q.setParameter("siteid",Integer.valueOf(siteid));
	List<Site> siteList=q.getResultList();
		if(siteList!=null && siteList!=null && !siteList.isEmpty()){
				retVal= 1;
		}
	
		
	return retVal;

}

public int checkFemaleInLast(int lastLandmark) throws Exception {
	
	int retVal=0;
	String query="from EmployeeSubscription es where es.subscriptionID  in ("+ selectedEmpSubscribedIDString +" )  and es.landmark.id=:landmarkId";
	Query q=getEntityManager().createQuery(query);
	q.setParameter("landmarkId", String.valueOf(lastLandmark));
	List<EmployeeSubscription> empSubsList=q.getResultList();
	
			
	for(EmployeeSubscription esubs: empSubsList){
		Employee emp=esubs.getEmployee1();
			if(emp.getGender().equalsIgnoreCase("F")){
				retVal=1;
			}
		}	
	
	return retVal;

}

public float[] getDistances(int[] landmarks, RoutingDto routingDto) throws Exception{
	setDistanceInDb(true);
	float totalDistance = (float) 0.0;
	float distance[] = new float[landmarks.length];
	aplDistanceList = new ArrayList< AplDistanceDto> (); 
	landmarkDistanceList = new ArrayList< AplDistanceDto> ();
	String query="from Distchart dc where (dc.landmark2.id=:srcId and dc.landmark1.id=:destId) or (dc.landmark1.id=:srcId and dc.landmark2.id=:destId) ";
	//String query = "select distance from distchart where (srcId=? and destId=?) or (destId=? and srcId=?)";
	Query q=null;	
	int srcId=0;
		int destId=0;
		if(routingDto.getTravelMode().equalsIgnoreCase("OUT" )) {
			for (int i = landmarks.length; i > 0; i--) {
				
				if(i==landmarks.length) {
					srcId=siteLandmarkId;
					AplDistanceDto aplDto1 = new AplDistanceDto();
					aplDto1.setSourceId(String.valueOf(srcId));
					aplDto1.setDistance(0); 
					landmarkDistanceList.add(aplDto1);
					
				} else {
					srcId=landmarks[i];
					}
					destId = landmarks[i-1];

					
					q=getEntityManager().createQuery(query);
					q.setParameter("srcId", String.valueOf(srcId));
					q.setParameter("destId",String.valueOf(destId));
					
					List<Distchart> dcList=q.getResultList();
					if(dcList.size()!=0 && dcList!=null){
						for(Distchart dc: dcList){
							distance[i-1] = (float) dc.getDistance();
							totalDistance += dc.getDistance();
						}
					}else{	
					if(srcId!=destId) {  
						setDistanceInDb(false);
									}	
					}
					
					/* adding the site landmarks in queue */
					AplDistanceDto aplDto1 = new AplDistanceDto();
					aplDto1.setSourceId(String.valueOf(destId));
					aplDto1.setDistance(distance[i-1]); 
					landmarkDistanceList.add(aplDto1);
					 
						/* src, dest setup for db insert */
						AplDistanceDto aplDDto = new AplDistanceDto();
						aplDDto.setSourceId( String.valueOf(srcId) );
						aplDDto.setTargetId(String.valueOf(destId));
						aplDDto.setDistance(distance[i-1] );
						aplDistanceList.add(aplDDto);
						
					 
					 
			}
		
		} else if(routingDto.getTravelMode().equalsIgnoreCase("IN" )) {
			for (int i = 0; i<=landmarks.length; i++) {
				
				if(i==0) {
					
					/*srcId = landmarks[landmarks.length-1];
					destId=siteLandmarkId;
					*/
					distance[i]=0;
/*					AplDistanceDto aplDto1 = new AplDistanceDto();
					aplDto1.setSourceId(String.valueOf(landmarks[i]));
					aplDto1.setDistance(0); 
					landmarkDistanceList.add(aplDto1);
*/					
					
				} else if(i==landmarks.length) {
					srcId=landmarks[i-1];
					destId = siteLandmarkId;
					q=getEntityManager().createQuery(query);
					q.setParameter("srcId", String.valueOf(srcId));
					q.setParameter("destId",String.valueOf(destId));
					
					List<Distchart> dcList=q.getResultList();
					float distanceTemp =0f;
					if(dcList.size()!=0 && dcList!=null){
						for(Distchart dc: dcList){
							distanceTemp= (float) dc.getDistance();
							totalDistance += dc.getDistance();
						}
					}else{	
					if(srcId!=destId) {  
						setDistanceInDb(false);
									}	
					}
					
								 
					/* adding the site landmarks in queue */

					AplDistanceDto aplDto1 = new AplDistanceDto();
					aplDto1.setSourceId(String.valueOf(srcId));
					aplDto1.setDistance(0); 
					landmarkDistanceList.add(aplDto1);
					AplDistanceDto aplDto2 = new AplDistanceDto();
					aplDto2.setSourceId(String.valueOf(destId));
					aplDto2.setDistance(0); 
					landmarkDistanceList.add(aplDto2);
					
					/* src, dest setup for db insert */
					AplDistanceDto aplDDto = new AplDistanceDto();
					aplDDto.setSourceId( String.valueOf(srcId) );
					aplDDto.setTargetId(String.valueOf(destId));
					aplDDto.setDistance(distanceTemp );
					aplDistanceList.add(aplDDto);
					
					
					
					
				}else {
			//		System.out.println(i + " -> " + landmarks[i]);
					srcId=landmarks[i-1];
					destId = landmarks[i];
					
					q=getEntityManager().createQuery(query);
					q.setParameter("srcId", String.valueOf(srcId));
					q.setParameter("destId",String.valueOf(destId));
					
					List<Distchart> dcList=q.getResultList();
					float distanceTemp =0f;
					if(dcList.size()!=0 && dcList!=null){
						for(Distchart dc: dcList){
							distance[i]= (float) dc.getDistance();
							totalDistance += dc.getDistance();
						}
					}else{
						distance[i] = 0;
					if(srcId!=destId) {  
						setDistanceInDb(false);
									}	
					}
					
					
			
					AplDistanceDto aplDto1 = new AplDistanceDto();
					aplDto1.setSourceId(String.valueOf(srcId));
					aplDto1.setDistance(distance[i]); 
					landmarkDistanceList.add(aplDto1);
					
					/* src, dest setup for db insert */
					AplDistanceDto aplDDto = new AplDistanceDto();
					aplDDto.setSourceId( String.valueOf(srcId) );
					aplDDto.setTargetId(String.valueOf(destId));
					aplDDto.setDistance(distance[i]);
					aplDistanceList.add(aplDDto);
					
					 
				}
			}
			
		}
		
		
	routingDto.setDistance(totalDistance);
	return distance;
}

public void setDistanceInDb(boolean distanceInDb) throws Exception {
	this.distanceInDb = distanceInDb;
}

public boolean isDistanceInDb() throws Exception{
	return distanceInDb;
}

public ArrayList<AplDistanceDto> getAplDistanceList() throws Exception{
	return aplDistanceList;
}

public void setAplDistanceList(ArrayList<AplDistanceDto> aplDistanceList) throws Exception {
	this.aplDistanceList = aplDistanceList;
}

public ArrayList<AplDistanceDto> getLandmarkDistanceList() throws Exception{
	return landmarkDistanceList;
}

public void setLandmarkDistanceList(ArrayList<AplDistanceDto> landmarkDistanceList) throws Exception{
	this.landmarkDistanceList = landmarkDistanceList;
}

public float[] getTime(RoutingDto routingDto, float[] distance) throws Exception {
	PreparedStatement pst = null;
	ResultSet rs = null;
	float time[] = new float[distance.length];
	float totalMinutes = (float) 0.0;
	float currentDistance = (float) 0.0;
	
	String query="from Timesloat ts where startTime<=:startTime and endTime>:endTime";
	//String query="select ROUND(:distance/ts.speedpkm*60,0) as time from Timesloat ts where startTime<=:startTime and endTime>:endTime";

	//String query = "select ROUND( ?/speedpkm*60,0)as time from timeSloat where startTime<=? and endTime>?";
	Query q=getEntityManager().createQuery(query);
	
		
		if (routingDto.getTravelMode().equals("IN")) {
			
			for (int i = 0; i < distance.length; i++) {
				if(i==0)
				{
				currentDistance=routingDto.getDistance();
				}
				currentDistance -= distance[i];
				//q.setParameter("distance", currentDistance);
				q.setParameter("startTime", routingDto.getTime());
				q.setParameter("endTime", routingDto.getTime());
				
				List<Timesloat> tsList=q.getResultList();
				if(tsList!=null && tsList.size()!=0){
					for(Timesloat ts : tsList){
					float speedpkm=(float)ts.getSpeedpkm();
						time[i]=currentDistance/speedpkm*60;
						if(i == 0)
							totalMinutes = time[i];		
					}
						
				}
			}	
			
		} else {
			for (int i = 0; i < distance.length; i++) {
				q.setParameter("startTime", routingDto.getTime());
				q.setParameter("endTime", routingDto.getTime());
				List<Timesloat> tsList=q.getResultList();
				if(tsList!=null && tsList.size()!=0){
					for(Timesloat ts : tsList){
					float speedpkm=(float)ts.getSpeedpkm();	
					time[i] = currentDistance/speedpkm*60;
					totalMinutes += time[i];
				}
			}
		}
	}		
		routingDto.setTravellingTime(totalMinutes);
	
	return time;

}

/* Insertion into TripDetails */
public TripDetail storeInTripMaster(TripDetail tripDetail)	throws Exception {
	
	uniqueId = "" + uniqueNo;
	uniqueNo++;
	
	tripDetail.setTripCode(uniqueId);
	tripDetail.setStatus("routed");
	persist(tripDetail);
	flush(); 
	
	return tripDetail;
	
}



public TripDetail getTripDetailById(String tripId) throws Exception {
	String query="from TripDetail td where td.id=:tripId";
	Query q=getEntityManager().createQuery(query);
	q.setParameter("tripId", tripId);
	List<TripDetail> tdList=q.getResultList();
		
	return tdList.get(0);
}

public void storeInTripChild(TripDetailsChild tdc) throws Exception {
	persist(tdc);
	flush();
	
}

public int checkTripExist(RoutingDto routingDto, int siteid) throws Exception{
	
	int retunVal = 0;
	Query q=null;
	
		if (routingDto.getTravelMode().equalsIgnoreCase("All")) {
			String query="from TripDetail td where td.site.id=:siteid and td.tripDate=:tripDate and td.status!=:status1 and td.status!=:status2 and td.status!=:status3";	
			//String query = "select id from trip_details where siteId=? and trip_date=? and status!=? and status!=? and status!=?";
			q=getEntityManager().createQuery(query);
			q.setParameter("siteid",Integer.valueOf(siteid));
			q.setParameter("tripDate",DateUtil.convertStringToDate(routingDto.getDate(),  "dd/MM/yyyy","yyyy-MM-dd hh:mm::ss"));
			q.setParameter("status1", "saved");
			q.setParameter("status2", "addsaved");
			q.setParameter("status3", "saveedit");
			
		} else if (routingDto.getTime().equalsIgnoreCase("All")) {
			String query="from TripDetail td where td.site.id=:siteid and td.tripDate=:tripDate and td.tripLog=:tripLog and td.status!=:status1 and td.status!=:status2 and td.status!=:status3";	
			q=getEntityManager().createQuery(query);
			q.setParameter("siteid",Integer.valueOf(siteid));
			q.setParameter("tripDate",DateUtil.convertStringToDate(routingDto.getDate(),  "dd/MM/yyyy","yyyy-MM-dd hh:mm::ss"));
			q.setParameter("tripLog", routingDto.getTravelMode());
			q.setParameter("status1", "saved");
			q.setParameter("status2", "addsaved");
			q.setParameter("status3", "saveedit");
						
		} else {
			
			String query="from TripDetail td where td.site.id=:siteid and td.tripDate=:tripDate and td.tripLog=:tripLog and tripTime=:tripTime and td.status!=:status1 and td.status!=:status2 and td.status!=:status3";	
			q=getEntityManager().createQuery(query);
			q.setParameter("siteid",Integer.valueOf(siteid));
			q.setParameter("tripDate",DateUtil.convertStringToDate(routingDto.getDate(),  "dd/MM/yyyy","yyyy-MM-dd hh:mm::ss"));
			q.setParameter("tripLog", routingDto.getTravelMode());
			q.setParameter("tripTime", routingDto.getTime());
			q.setParameter("status1", "saved");
			q.setParameter("status2", "addsaved");
			q.setParameter("status3", "saveedit");
			
			
		}
		List<TripDetail> tdList=q.getResultList();
		if(tdList!=null && !tdList.isEmpty() && tdList.size()!=0){
			retunVal = 1;
		} else {
			retunVal = 0;
		}

	return retunVal;
}


public List<TripDetail> getTripDetailsInfor(RoutingDto routingDto, int siteid) throws Exception {
	List<TripDetail> tdList=null;
    String query = "from TripDetail td where td.site.id=:siteId and td.tripDate=:tripDate and td.status!=:status1 and td.status!=:status2 and td.status!=:status3";
    Query q=getEntityManager().createQuery(query);
    q.setParameter("siteId",Integer.valueOf(siteid));
	q.setParameter("tripDate",DateUtil.convertStringToDate(routingDto.getDate(),"dd/MM/yyyy","yyyy-MM-dd hh:mm::ss"));
	q.setParameter("status1", "saved");
	q.setParameter("status2", "addsaved");
	q.setParameter("status3", "saveedit");
    List<TripDetail> tripDetailsList=q.getResultList();
    tdList=new ArrayList<TripDetail>();
    
		if (routingDto.getTravelMode().equalsIgnoreCase("All")) {
			for(TripDetail td : tripDetailsList){
				tdList.add(td);
			}
			
		} else if (routingDto.getTime().equalsIgnoreCase("All")) {
			for(TripDetail td : tripDetailsList){
				if(td.getTripLog().equalsIgnoreCase(routingDto.getTravelMode())){
					tdList.add(td);
				}
			}
		 
	   }else {
			for(TripDetail td : tripDetailsList){
				if(td.getTripLog().equalsIgnoreCase(routingDto.getTravelMode()) && td.getTripTime().equalsIgnoreCase(routingDto.getTime()) ){
					logger.debug("IN ELSEEEEEEEEE");
					tdList.add(td);
			}
		}
	}		
			
	return tdList;
		
	
}

public void overWriteTrips(List<TripDetail> td)	throws Exception {
	for(TripDetail t: td){
		getEntityManager().remove(t);
		flush();
	}
	
}


	
}
