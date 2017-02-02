package com.agiledge.atom.dao.impl;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agiledge.atom.config.files.OtherFunctions;
import com.agiledge.atom.constants.SettingsConstant;
import com.agiledge.atom.dao.intfc.TripDetailsDao;
import com.agiledge.atom.dto.ModifyTripDto;
import com.agiledge.atom.dto.TripDetailsChildDto;
import com.agiledge.atom.dto.TripDetailsDto;
import com.agiledge.atom.dto.VehicleTypeDto;
import com.agiledge.atom.entities.Employee;
import com.agiledge.atom.entities.EmployeeSchedule;
import com.agiledge.atom.entities.EmployeeScheduleAlter;
import com.agiledge.atom.entities.Landmark;
import com.agiledge.atom.entities.Shuttleposition;
import com.agiledge.atom.entities.Site;
import com.agiledge.atom.entities.TripDetail;
import com.agiledge.atom.entities.TripDetailsChild;
import com.agiledge.atom.entities.Tripshuttle;
import com.agiledge.atom.entities.Vehicle;
import com.agiledge.atom.entities.VehiclePosition;
import com.agiledge.atom.entities.VehicleType;
import com.agiledge.atom.entities.VendorTripSheet;
import com.agiledge.atom.entities.VendorTripSheetParent;
import com.agiledge.atom.service.intfc.EmployeeService;
import com.agiledge.atom.utils.DateUtil;


@Repository
public class TripDetailsDaoImpl extends AbstractDao implements TripDetailsDao{
	private static final Logger logger = Logger.getLogger(TripDetailsDaoImpl.class);
	
	@Autowired
	private EmployeeService employeeService;
	
	String uniqueId = "";
	String uniqueCode = "";
	int uniqueNo = 1;
	
	public ArrayList<TripDetailsDto> getRoutingSummary(int siteid,String tripDate, String tripLog, String tripTime)throws Exception {
		
		ArrayList<TripDetailsDto> tripSummary = new ArrayList<TripDetailsDto>();
		TripDetailsDto dto = null;
		VehicleTypeDto vehicle=null;
		HashSet<String> vehicleTypes = new HashSet<String>();
		ArrayList<VehicleTypeDto> vehicles = null;
		String siteId=String.valueOf(siteid);
		String subQuery = "";
	
		try{
				Date date=OtherFunctions.stringToDate(tripDate);
					String dbDate=OtherFunctions.changeDateFromatToSqlFormat(date);
		
				
		if (!tripLog.equalsIgnoreCase("all")) {
			subQuery = " and td1.trip_log='" + tripLog + "' ";
			if (!tripTime.equalsIgnoreCase("all")) {
				subQuery += " and  td1.trip_time='" + tripTime + "' ";
			}
		}
		
			String query = "select count(id) tripCount,siteid,sum(empcount) empcount,trip_date,trip_log,trip_time,sum(secCount) secCount from (select td.siteid,td.id,td.trip_date,td.trip_time,td.trip_log,(select count(*) from trip_details td1 where td1.id=td.id and td1.security_status='yes') as secCount,(select count(*) from trip_details_child tdc where tdc.tripid=td.id) as empcount from trip_details td,(select distinct siteid,trip_date,trip_time,trip_log from trip_details td1 where td1.siteId="
					+ siteId
					+ " and  td1.trip_date='"+ dbDate + "' "
					+ subQuery
					+ " ) as  subtable where subtable.siteid=td.siteid and subtable.trip_date=td.trip_date and subtable.trip_time=td.trip_time and subtable.trip_log=td.trip_log)as alltable group by  trip_date,trip_time,trip_log";
			
			String query1 = "";
			
			//TypedQuery<Object[]> q=getEntityManager().createQuery(query,Object[].class);
			//TypedQuery<Object[]> q=(TypedQuery<Object[]>) getEntityManager().createNativeQuery(query,Object[].class);
			Query q=getEntityManager().createNativeQuery(query);
			
			
			
			//q.setParameter("tripDate", DateUtil.convertStringToDate(tripDate,"dd/MM/yyyy","yyyy-MM-dd hh:mm::ss"));
			//q.setParameter("siteid",Integer.valueOf(siteid));
			List<Object[]> sList= q.getResultList();
				
			
			for(Object[] s: sList ){
				String tripCount=String.valueOf(s[0]);
				String sId=String.valueOf(s[1]);
				String empcount=String.valueOf(s[2]);
				String tripdate=OtherFunctions.changeDateFromat((Date)s[3]);
				String tl=String.valueOf(s[4]);
				String tt=String.valueOf(s[5]);
				int secCount=Integer.valueOf(String.valueOf(s[1]));
				dto = new TripDetailsDto();
				dto.setTrip_time(tt);
				dto.setTrip_log(tl);
				dto.setTrip_date(tripdate);
				dto.setEmpInCount(Integer.parseInt(empcount));
				dto.setTripCount(tripCount);
				dto.setSecCount(secCount);
				query1 = "select vt.id,vt.type,count(vehicle_type) as vehiclecount from vehicle_type vt ,trip_details td where td.vehicle_type=vt.id and td.siteid="
						+ sId
						+ " and td.trip_date='"
						+ tripdate
						+ "' and td.trip_time='"
						+ tt
						+ "' and trip_log='"
						+ tl + "' group by vt.id,vt.type";
				
				Query q1=getEntityManager().createNativeQuery(query1);
				vehicles = new ArrayList<VehicleTypeDto>();
				/* Excecuting only for single row */
				List<Object[]> vList=q1.getResultList(); 
				for(Object[] v:vList){
				vehicle = new VehicleTypeDto();
				int vId=Integer.valueOf(String.valueOf(v[0]));
				String type=String.valueOf(v[1]);
				int vehCount=Integer.valueOf(String.valueOf(v[2]));
				
				vehicle.setId(vId);
				vehicle.setType(type);
				vehicle.setCount(vehCount);
				vehicles.add(vehicle);
				vehicleTypes.add(type);
				}
				dto.setVehicles(vehicles);
				tripSummary.add(dto);
			}
			tripSummary.get(0).setVehicleTypes(vehicleTypes);
			/*if(tripSummary.size()>0 && !tripSummary.isEmpty()){
			tripSummary.get(0).setVehicleTypes(vehicleTypes);
			}else{
				tripSummary.get(0).setVehicleTypes(new HashSet<String>());
			}*/
			}catch(Exception e){
				logger.error("Error in Dao",e);
			}
			
			return tripSummary;
	}		
			
	public ArrayList<TripDetailsDto> getTripSheetModified(String tripDate, String tripLog,String siteId, String tripTime) throws Exception {
		
					ArrayList<TripDetailsDto> tripSheet = null;
					TripDetailsDto tripDetailsDtoObj = null;
					TripDetailsChildDto tripDetailsChildDtoObj = null;
					tripSheet = new ArrayList<TripDetailsDto>();
					ArrayList<TripDetailsChildDto> tripSheetChild = null;
					
					String subQuery = "";

					String query = "SELECT t.id,t.trip_code,t.trip_date,t.trip_time,t.trip_log,t.routeid,vt.type,t.security_status, p.logTime,t.distance,t.travelTime,t.preTripId,ifnull(p.approvalStatus,'Open')  approvalStatus, p.vehicleNo   FROM trip_details t left join vendor_trip_sheet_parent p on t.id=p.tripId,vehicle_type vt where t.siteId="+siteId+" and (t.status='routed' or t.status='addmod' or t.status='modified') and vt.id=t.vehicle_type ";
					if (tripLog.equals("ALL")) {
						subQuery = " and t.trip_date='" + tripDate + "'";

					} else if (tripTime.equals("ALL")) {
						subQuery = " and t.trip_date='" + tripDate
								+ "' and t.trip_log='" + tripLog + "' ";
					} else {
						subQuery = " and t.trip_date='" + tripDate
								+ "' and t.trip_log='" + tripLog
								+ "' and t.trip_time='" + tripTime + "'";
					}
					String lastQuery = " order by t.id ";
					
					
					Query q=getEntityManager().createNativeQuery(query+subQuery+lastQuery);
						List<Object[]> objList=q.getResultList();
					
					for(Object[] o:objList) {

						tripDetailsDtoObj = new TripDetailsDto();
						tripDetailsDtoObj.setId(String.valueOf(o[0]));
						tripDetailsDtoObj.setTrip_code(String.valueOf(o[1]));
						tripDetailsDtoObj.setTrip_date(String.valueOf(o[2]));
						tripDetailsDtoObj.setTrip_time(String.valueOf(o[3]));
						tripDetailsDtoObj.setTrip_log(String.valueOf(o[4]));
						tripDetailsDtoObj.setRouteId(String.valueOf(o[5]));
						tripDetailsDtoObj.setVehicle_type(String.valueOf(o[6]));
						tripDetailsDtoObj.setIsSecurity(String.valueOf(o[7]));
						tripDetailsDtoObj.setActualLogTime(String.valueOf(o[8]));
						tripDetailsDtoObj.setDistance(String.valueOf(o[9]));
						tripDetailsDtoObj.setTravelTime(String.valueOf(o[10]));
						tripDetailsDtoObj.setApprovalStatus(String.valueOf(o[11]));
						tripDetailsDtoObj.setVehicleNo(String.valueOf(o[12]));
						
						tripSheetChild = new ArrayList<TripDetailsChildDto>();
						query = "select tdc.employeeId,e.displayname as EmployeeName, ifnull(e.contactNumber1,'') contactNumber1,a.area,p.place,l.id as landmarkId,l.landmark, vt.employeeId approvedEmployee,tdc.time,tdc.dist,e.gender,tdc.transportType  from trip_details_child tdc  left  join vendor_trip_sheet vt on tdc.employeeId=vt.employeeId and tdc.tripId=vt.tripId ,employee e ,area a,place p,landmark l where e.id=tdc.employeeId and tdc.tripId="
								+ String.valueOf(o[0])
								+ " and tdc.landmarkId=l.id and l.place=p.id and p.area=a.id order by tdc.routedOrder";
						
						Query q1=getEntityManager().createNativeQuery(query);
						List<Object[]> list=q1.getResultList();
						for(Object[] obj:list) {

							tripDetailsChildDtoObj = new TripDetailsChildDto();
							tripDetailsChildDtoObj.setEmployeeId(String.valueOf(obj[0]));
							tripDetailsChildDtoObj.setEmployeeName(String.valueOf(obj[1]));
							tripDetailsChildDtoObj.setContactNumber(String.valueOf(obj[2]));
							tripDetailsChildDtoObj.setArea(String.valueOf(obj[3]));
							tripDetailsChildDtoObj.setPlace(String.valueOf(obj[4]));
							tripDetailsChildDtoObj.setLandmarkId(String.valueOf(obj[5]));
							tripDetailsChildDtoObj.setLandmark(String.valueOf(obj[6]));
							tripDetailsChildDtoObj.setApprovedEmployee(String.valueOf(obj[7]));
							if (tripDetailsChildDtoObj.getApprovedEmployee() != null
									&& !tripDetailsChildDtoObj.getApprovedEmployee()
											.equals("")) {
								tripDetailsDtoObj.setCanTravel(true);
							}
							tripDetailsChildDtoObj.setTime(String.valueOf(obj[8]));
							tripDetailsChildDtoObj.setDistance(((Double) obj[9]).floatValue());
							tripDetailsChildDtoObj.setGender(String.valueOf(obj[10]));
							tripDetailsChildDtoObj.setTransportType(String.valueOf(obj[11]));
							tripDetailsChildDtoObj.setTripId(String.valueOf(o[0]));
										
							tripSheetChild.add(tripDetailsChildDtoObj);

						}
						tripDetailsDtoObj.setTripDetailsChildDtoList(tripSheetChild);
						tripSheet.add(tripDetailsDtoObj);
					}
				
				

				return tripSheet;
			}
		
	
	

	
		public ArrayList<TripDetailsDto> getTripSheetSaved(String tripDate,	String tripLog, String siteId, String tripTime) throws Exception{
				ArrayList<TripDetailsDto> tripSheet = null;
				TripDetailsDto tripDetailsDtoObj = null;
				TripDetailsChildDto tripDetailsChildDtoObj = null;
				tripSheet = new ArrayList<TripDetailsDto>();
				ArrayList<TripDetailsChildDto> tripSheetChild = null;
				
				String subQuery = "";
				String query = "SELECT t.id,t.trip_code,t.trip_date,t.trip_time,t.trip_log,t.routeid,vt.id as vehicletypeId,vm.Company,vt.sit_cap,vt.type,t.security_status,t.distance,t.travelTime,t.preTripId,t.distanceFromPreTrip,case when t.vehicle is not null then (select regno from vehicles where id=t.vehicle) else 'Not set' end as vehicleNo,case when t.driverid is not null then (select name from driver where id=t.driverid) else 'Not set' end as driver,case when t.driverid is not null then (select contact from driver where id=t.driverid) else ' ' end as	driverContact,case when t.escortId is not null then (select concat(escortClock,'-',name) from escort where id=t.escortid) else ' ' end as escort FROM vehicle_type vt,trip_details t LEFT JOIN tripvendorassign tva on t.id=tva.tripID LEFT JOIN vendormaster vm on tva.vendorId=vm.id where t.siteId="+siteId+" and vt.id=t.vehicle_type and  (t.status='saved' or t.status='addsave' or t.status='saveedit')";
				if (tripLog.equals("ALL")) {
					subQuery = " and t.trip_date='" + tripDate + "'";

				} else if (tripTime.equals("ALL")) {
					subQuery = " and t.trip_date='" + tripDate
							+ "' and t.trip_log='" + tripLog + "' ";
				} else {
					subQuery = " and t.trip_date='" + tripDate
							+ "' and t.trip_log='" + tripLog
							+ "' and t.trip_time='" + tripTime + "'";
				}
				String lastQuery = " order by t.id ";
				Query q=getEntityManager().createNativeQuery(query+subQuery + lastQuery);
				List<Object[]> objList=q.getResultList();
				
				for(Object[] o:objList) {
					tripDetailsDtoObj = new TripDetailsDto();
					tripDetailsDtoObj.setSiteId(siteId);
					tripDetailsDtoObj.setId(String.valueOf(o[0]));
					tripDetailsDtoObj.setTrip_code(String.valueOf(o[1]));
					tripDetailsDtoObj.setTrip_date(String.valueOf(o[2]));
					tripDetailsDtoObj.setTrip_time(String.valueOf(o[3]));
					tripDetailsDtoObj.setTrip_log(String.valueOf(o[4]));
					tripDetailsDtoObj.setRouteId(String.valueOf(o[5]));
					tripDetailsDtoObj.setVehicleTypeId(String.valueOf(o[6]));
					tripDetailsDtoObj.setCompany(String.valueOf(o[7]));
					tripDetailsDtoObj.setSeat(((Integer)o[8]).intValue());
					tripDetailsDtoObj.setVehicle_type(String.valueOf(o[9]));
					tripDetailsDtoObj.setIsSecurity(String.valueOf(o[10]));
					tripDetailsDtoObj.setDistance(String.valueOf(o[11]));
					tripDetailsDtoObj.setTravelTime(String.valueOf(o[12]));
					tripDetailsDtoObj.setPreviousTripId(String.valueOf(o[13]));
					tripDetailsDtoObj.setDistanceFromPrevioustrip(((Double)o[14]).floatValue());
					tripDetailsDtoObj.setVehicleNo(String.valueOf(o[15]));
					tripDetailsDtoObj.setDriverName(String.valueOf(o[16]));
					tripDetailsDtoObj.setDriverContact(String.valueOf(o[17]));
					tripDetailsDtoObj.setEscort(String.valueOf(o[18]));
					
					tripSheetChild = new ArrayList<TripDetailsChildDto>();
					
					
					query = "select tdc.scheduleId, tdc.employeeId as employeeId,e.displayname as EmployeeName,e.personnelNo,e.gender,a.area,p.place,l.id as landmarkId,l.landmark, ifnull(e.contactNumber1,'') as contactno, vt.employeeId approvedEmployee,  vt.showStatus, vt.noShowReason,tdc.time,tdc.dist,tdc.transportType from trip_details_child tdc left  join vendor_trip_sheet vt on tdc.employeeid=vt.employeeId and tdc.tripId=vt.tripId ,employee e ,area a,place p,landmark l where e.id=tdc.employeeId and tdc.tripId="
							+ String.valueOf(o[0])
							+ " and tdc.landmarkId=l.id and l.place=p.id and p.area=a.id order by tdc.routedOrder";
					Query q1=getEntityManager().createNativeQuery(query);
					List<Object[]> list=q1.getResultList();
					for(Object[] obj:list) {

						tripDetailsChildDtoObj = new TripDetailsChildDto();
						tripDetailsChildDtoObj.setScheduleId(String.valueOf(obj[0]));
						tripDetailsChildDtoObj.setEmployeeId(String.valueOf(obj[1]));
						tripDetailsChildDtoObj.setEmployeeName(String.valueOf(obj[2]));
						tripDetailsChildDtoObj.setPersonnelNo(String.valueOf(obj[3]));
						tripDetailsChildDtoObj.setGender(String.valueOf(obj[4]));
						tripDetailsChildDtoObj.setArea(String.valueOf(obj[5]));
						tripDetailsChildDtoObj.setPlace(String.valueOf(obj[6]));
						tripDetailsChildDtoObj.setLandmarkId(String.valueOf(obj[7]));
						tripDetailsChildDtoObj.setLandmark(String.valueOf(obj[8]));
						tripDetailsChildDtoObj.setContactNumber(String.valueOf(obj[9]));
						tripDetailsChildDtoObj.setApprovedEmployee(String.valueOf(obj[10]));
						tripDetailsChildDtoObj.setShowStatus(String.valueOf(obj[11]));
						tripDetailsChildDtoObj.setReason(String.valueOf(obj[12]));
						tripDetailsChildDtoObj.setTime(String.valueOf(obj[13]));
						tripDetailsChildDtoObj.setDistance(((Double)obj[14]).floatValue());
						tripDetailsChildDtoObj.setTransportType(String.valueOf(obj[15]));

						if (tripDetailsChildDtoObj.getApprovedEmployee() != null && !tripDetailsChildDtoObj.getApprovedEmployee().equals("")) {
							tripDetailsDtoObj.setCanTravel(true);
						}

						tripDetailsChildDtoObj.setTripId(String.valueOf(o[0]));
						tripSheetChild.add(tripDetailsChildDtoObj);
					}

					tripDetailsDtoObj.setTripDetailsChildDtoList(tripSheetChild);
					tripSheet.add(tripDetailsDtoObj);
					
				}

			
			return tripSheet;
	}

		public ArrayList<TripDetailsDto> getTripSheetActual(String tripDate,String tripLog, String siteId, String tripTime) throws Exception {
			ArrayList<TripDetailsDto> tripSheet = null;
			
				TripDetailsDto tripDetailsDtoObj = null;
				TripDetailsChildDto tripDetailsChildDtoObj = null;
				tripSheet = new ArrayList<TripDetailsDto>();
				ArrayList<TripDetailsChildDto> tripSheetChild = null;
				String subQuery = "";

				String query = "SELECT t.id,t.trip_code,t.trip_date,t.trip_time,t.trip_log,t.routeid,vt.type,t.security_status,t.distance,t.travelTime,t.preTripId,t.distanceFromPreTrip,t.status, vt.id vehicleTypeId ,'actual' as modifyStatus FROM trip_details t ,vehicle_type vt where t.siteId="+siteId+" and (t.status='routed' or t.status='saved') and vt.id=t.vehicle_type ";
				String query1 = "SELECT t.tripid as id,t.trip_code,t.trip_date,t.trip_time,t.trip_log,t.routeid,vt.type,t.security_status,t.distance,t.travelTime,t.preTripId,t.distanceFromPreTrip,t.status, vt.id vehicleTypeId ,'modified' as modifyStatus  FROM trip_details_parent_modified t ,vehicle_type vt where t.siteId="+siteId+"  and vt.id=t.vehicle_type ";
				if (tripLog.equals("ALL")) {
					subQuery = " and t.trip_date='" + tripDate + "'";

				} else if (tripTime.equals("ALL")) {
					subQuery = " and t.trip_date='" + tripDate
							+ "' and t.trip_log='" + tripLog + "' ";
				} else {
					subQuery = " and t.trip_date='" + tripDate
							+ "' and t.trip_log='" + tripLog
							+ "' and t.trip_time='" + tripTime + "'";
				}
				String lastQuery = " as  tble order by tble.id,tble.modifyStatus";
				Query q=getEntityManager().createNativeQuery("select * from (" + query + subQuery
						+ " union " + query1 + subQuery + ")" + lastQuery);
				List<Object[]> objList=q.getResultList();
			
				
				for(Object[] o:objList) {
					tripDetailsDtoObj = new TripDetailsDto();
					tripDetailsDtoObj.setId(String.valueOf(o[0]));
					tripDetailsDtoObj.setTrip_code(String.valueOf(o[1]));
					tripDetailsDtoObj.setTrip_date(String.valueOf(o[2]));
					tripDetailsDtoObj.setTrip_time(String.valueOf(o[3]));
					tripDetailsDtoObj.setActualLogTime(String.valueOf(o[3]));
					tripDetailsDtoObj.setTrip_log(String.valueOf(o[4]));
					tripDetailsDtoObj.setRouteId(String.valueOf(o[5]));
					tripDetailsDtoObj.setVehicle_type(String.valueOf(o[6]));
					tripDetailsDtoObj.setIsSecurity(String.valueOf(o[7]));
					tripDetailsDtoObj.setDistance(String.valueOf(o[8]));
					tripDetailsDtoObj.setTravelTime(String.valueOf(o[9]));
					tripDetailsDtoObj.setPreviousTripId(String.valueOf(o[10]));
					tripDetailsDtoObj.setDistanceFromPrevioustrip(((Double)o[11]).floatValue());
					String status = String.valueOf(o[12]);
					tripDetailsDtoObj.setVehicleId(String.valueOf(o[13]));
					String modifyStatus=String.valueOf(o[14]);
					
					
					tripSheetChild = new ArrayList<TripDetailsChildDto>();
					if (modifyStatus.equalsIgnoreCase("actual")) {
						query = "select tdc.employeeId,e.displayname as EmployeeName,a.area,p.place,l.id as landmarkId,l.landmark,tdc.dist,tdc.time from trip_details_child tdc  left  join vendor_trip_sheet vt on tdc.employeeId=vt.employeeId and tdc.tripId=vt.tripId ,employee e ,area a,place p,landmark l where e.id=tdc.employeeId and tdc.tripId="
								+ String.valueOf(o[0])
								+ " and tdc.landmarkId=l.id and l.place=p.id and p.area=a.id order by tdc.routedOrder";
					} else {
						query = "select tdc.empid as employeeId,e.displayname as EmployeeName,a.area,p.place,l.id as landmarkId,l.landmark,tdc.dist,tdc.time from trip_details_modified tdc left  join vendor_trip_sheet vt on tdc.empId=vt.employeeId and tdc.tripId=vt.tripId ,employee e ,area a,place p,landmark l where e.id=tdc.empid and tdc.tripId="
								+ String.valueOf(o[0])
								+ " and tdc.landmarkId=l.id and l.place=p.id and p.area=a.id order by tdc.routedOrder";
					}

					Query q1=getEntityManager().createNativeQuery(query);
					List<Object[]> list=q1.getResultList();
					for(Object[] obj:list) {
						tripDetailsChildDtoObj = new TripDetailsChildDto();
						tripDetailsChildDtoObj.setTripId(String.valueOf(o[0]));
						tripDetailsChildDtoObj.setEmployeeId(String.valueOf(obj[0]));
						tripDetailsChildDtoObj.setEmployeeName(String.valueOf(obj[1]));
						tripDetailsChildDtoObj.setArea(String.valueOf(obj[2]));
						tripDetailsChildDtoObj.setPlace(String.valueOf(obj[3]));
						tripDetailsChildDtoObj.setLandmarkId(String.valueOf(obj[4]));
						tripDetailsChildDtoObj.setLandmark(String.valueOf(obj[5]));
						tripDetailsChildDtoObj.setDistance(((Double)obj[6]).floatValue());
						tripDetailsChildDtoObj.setTime(String.valueOf(obj[7]));
						
						tripSheetChild.add(tripDetailsChildDtoObj);
					}
					tripDetailsDtoObj.setTripDetailsChildDtoList(tripSheetChild);
					tripSheet.add(tripDetailsDtoObj);
				}

			return tripSheet;
		}

		public int checkSecurity(String siteId, String tripTime) throws Exception {
			int retVal=0;
			int LADY_SECURTY=1;

			String query = "from Site where (nightShiftStart<'"
					+ tripTime + "' OR nightShiftEnd>'"
					+ tripTime + "') AND ladySecurity=:ladySecurity and id=:siteid";
			
			Query q=getEntityManager().createQuery(query);
			q.setParameter("ladySecurity", Integer.valueOf(LADY_SECURTY));
			q.setParameter("siteid",Integer.valueOf(siteId));
			List<Site> siteList=q.getResultList();
				if(siteList!=null && siteList!=null && !siteList.isEmpty()){
						retVal= 1;
				}
			
				
			return retVal;
		}

		public ArrayList<TripDetailsChildDto> getTripSheetRemoved(String tripDate, String siteId, String tripLog, String tripTime)	throws Exception {
			
			TripDetailsChildDto tripDetailsChildDtoObj = null;
			ArrayList<TripDetailsChildDto> tripSheetChild = new ArrayList<TripDetailsChildDto>();			
			String query = "select tdr.employeeid as employeeId,e.displayname as EmployeeName,a.area,p.place,l.id as landmarkId,l.landmark,tdr.scheduleId from trip_details_removed tdr, employee e,area a,place p,landmark l where tdr.trip_date='"+tripDate+"' and tdr.siteid="+siteId+" and tdr.trip_time='"+tripTime+"' and tdr.in_out='"+tripLog+"' and e.id=tdr.employeeid  and tdr.landmarkId=l.id and l.place=p.id and p.area=a.id";
			
			Query q=getEntityManager().createNativeQuery(query);
			List<Object[]> objList=q.getResultList();
			
			for(Object[] o:objList) {
					tripDetailsChildDtoObj = new TripDetailsChildDto();
					tripDetailsChildDtoObj.setEmployeeId(String.valueOf(o[0]));
					tripDetailsChildDtoObj.setEmployeeName(String.valueOf(o[1]));
					tripDetailsChildDtoObj.setArea(String.valueOf(o[2]));
					tripDetailsChildDtoObj.setPlace(String.valueOf(o[3]));
					tripDetailsChildDtoObj.setLandmarkId(String.valueOf(o[4]));
					tripDetailsChildDtoObj.setLandmark(String.valueOf(o[5]));
					tripDetailsChildDtoObj.setScheduleId(String.valueOf(o[6]));
					tripSheetChild.add(tripDetailsChildDtoObj);
				}
			 
			return tripSheetChild;
		}
			
		/*Calling directly in jsp due to jsp logic like this... */
		/*calling in SMS Service for sending otp to drivers*/
		public TripDetailsDto getTripSheetByTrip(String tripId) throws Exception {
			logger.debug("IN GETtripsheetbytrip"+tripId);
			TripDetailsDto tripDetailsDtoObj = new TripDetailsDto();
			TripDetailsChildDto tripDetailsChildDtoObj = null;
			ArrayList<TripDetailsChildDto> tripSheetChild = null;
		
				String query="SELECT t.id,t.trip_code, t.siteId, t.driverId, t.driverPswd, t.trip_date,t.trip_time,t.trip_log,t.routeid,vt.type,t.security_status,t.distance,t.travelTime,t.preTripId,t.distanceFromPreTrip, v.regNo, t.escortId, t.escortPswd,d.name as driverName,d.contact as driverConatct FROM trip_details t left join vehicles v on t.vehicle=v.id left join driver d  on t.driverid=d.id,vehicle_type vt where t.id="+tripId+" and vt.id=t.vehicle_type";
				
				Query q=getEntityManager().createNativeQuery(query);
				List<Object[]> objList=q.getResultList();
							
				for(Object[] o:objList) {
					tripDetailsDtoObj.setId(String.valueOf(o[0]));
					tripDetailsDtoObj.setTrip_code(String.valueOf(o[1]));
					tripDetailsDtoObj.setSiteId(String.valueOf(o[2]));
					tripDetailsDtoObj.setDriverId(String.valueOf(o[3]));
					tripDetailsDtoObj.setDriverPassword(String.valueOf(o[4]));
					tripDetailsDtoObj.setTrip_date(String.valueOf(o[5]));
					tripDetailsDtoObj.setTrip_time(String.valueOf(o[6]));
					tripDetailsDtoObj.setTrip_log(String.valueOf(o[7]));
					tripDetailsDtoObj.setRouteId(String.valueOf(o[8]));
					tripDetailsDtoObj.setVehicle_type(String.valueOf(o[9]));
					tripDetailsDtoObj.setIsSecurity(String.valueOf(o[10]));
					tripDetailsDtoObj.setDistance(String.valueOf(o[11]));
					tripDetailsDtoObj.setTravelTime(String.valueOf(o[12]));
					tripDetailsDtoObj.setPreviousTripId(String.valueOf(o[13]));
					tripDetailsDtoObj.setDistanceFromPrevioustrip(((Double)o[14]).floatValue());
					tripDetailsDtoObj.setVehicleNo(String.valueOf(o[15]));
					tripDetailsDtoObj.setEscortId(String.valueOf(o[16]));
					tripDetailsDtoObj.setEscortPassword(String.valueOf(o[17]));
					tripDetailsDtoObj.setDriverName(String.valueOf(o[18]));
					tripDetailsDtoObj.setDriverContact(String.valueOf(o[19]));
					tripSheetChild = new ArrayList<TripDetailsChildDto>();
					query="select tdc.tripid,e.gender, tdc.employeeId,e.displayname as EmployeeName,a.area,p.place,l.id as landmarkId,l.landmark,tdc.scheduleId,tdc.time,vts.keyPin,e.loginid,e.personnelno,e.contactnumber1 from  trip_details_child tdc left outer join landmark l on tdc.landmarkId=l.id left outer join place p on l.place=p.id left outer join area a on p.area=a.id left outer join vendor_trip_sheet vts on tdc.tripid=vts.tripid and tdc.employeeid=vts.employeeid, employee e where tdc.tripid="
							+ tripId
							+ " and e.id=tdc.employeeId   order by tdc.routedOrder";

					Query q1=getEntityManager().createNativeQuery(query);
					List<Object[]> oList=q1.getResultList();
					
					for(Object[] obj:oList) {
						tripDetailsChildDtoObj = new TripDetailsChildDto();
						tripDetailsChildDtoObj.setTripId(String.valueOf(o[0]));
						tripDetailsChildDtoObj.setGender(String.valueOf(obj[1]));
						tripDetailsChildDtoObj.setEmployeeId(String.valueOf(obj[2]));
						tripDetailsChildDtoObj.setEmployeeName(String.valueOf(obj[3]));
						tripDetailsChildDtoObj.setArea(String.valueOf(obj[4]));
						tripDetailsChildDtoObj.setPlace(String.valueOf(obj[5]));
						tripDetailsChildDtoObj.setLandmarkId(String.valueOf(obj[6]));
						tripDetailsChildDtoObj.setLandmark(String.valueOf(obj[7]));
						tripDetailsChildDtoObj.setScheduleId(String.valueOf(obj[8]));
						tripDetailsChildDtoObj.setTime(String.valueOf(obj[9]));
						if (SettingsConstant.empAuthinticationForComet.equalsIgnoreCase("pin")) {
							tripDetailsChildDtoObj.setKeyPin(String.valueOf(obj[10]));
						} else if (SettingsConstant.empAuthinticationForComet.equalsIgnoreCase("loginid")) {
							int loginidlength = String.valueOf(obj[11]).length();
							tripDetailsChildDtoObj.setKeyPin(String.valueOf(obj[11]).substring(loginidlength - 4));
						} else {
							tripDetailsChildDtoObj.setKeyPin(String.valueOf(obj[12]));
						}
						tripDetailsChildDtoObj.setContactNumber(String.valueOf(obj[13]));
						tripSheetChild.add(tripDetailsChildDtoObj);
					}
					tripDetailsDtoObj.setTripDetailsChildDtoList(tripSheetChild);
				}
			
			return tripDetailsDtoObj;
		}

		

		public void createUniqueID(String date, String mode, String siteid) throws Exception {
			
			try {
				uniqueCode = OtherFunctions.getOrdinaryDateFormat(date);
				uniqueCode = uniqueCode.replaceAll("/", "");
				if (mode.equals("IN"))
					uniqueCode += "P";
				if (mode.equals("OUT"))
					uniqueCode += "D";

				String query = "select CAST(SUBSTRING(trip_code,8,3) as UNSIGNED) as serial from trip_details where siteId="
						+ siteid
						+ " and trip_date='"
						+ date
						+ "' and trip_log='"
						+ mode
						+ "' and status in ('saved','addsave','saveedit') order by  serial desc limit 1";
				Query q=getEntityManager().createNativeQuery(query);
				List<Object[]> oList=q.getResultList();
				
				for(Object[] obj:oList) {
					uniqueNo = ((BigInteger)obj[0]).intValue() + 1;
				}
			} catch (Exception ex) {
				logger.error("Error in save", ex);
			} 
		}

		public List<TripDetail> getTripDetails(String tripDate, String tripLog,String siteId, String tripTime,String tripids) throws Exception {
			logger.debug("Dattttttttt"+tripDate);
			String query="";
			if(tripids!=null){
				query="from TripDetail td where td.id in (" + tripids + ") and (td.status='routed' or td.status='modified' or td.status='addmod') and td.tripDate=:tripDate and td.site.id=:siteId and td.tripLog=:tripLog and td.tripTime=:tripTime";
			} else {
			 query="from TripDetail td where (td.status='routed' or td.status='modified' or td.status='addmod') and td.tripDate=:tripDate and td.site.id=:siteId and td.tripLog=:tripLog and td.tripTime=:tripTime";
			}
			Query q=getEntityManager().createQuery(query);
			q.setParameter("tripDate", DateUtil.convertStringToDate(tripDate,"yyyy-MM-dd","yyyy-MM-dd hh:mm::ss"));
			q.setParameter("tripLog", tripLog);
			q.setParameter("siteId", Integer.valueOf(siteId));
			q.setParameter("tripTime", tripTime);
			List<TripDetail> tdList=q.getResultList();
			return tdList;
		}
		public List<TripDetail> getTripDetailsForTripSheet(String tripDate, String tripLog,String siteId, String tripTime,String tripids) throws Exception {
			String query="";
			if(tripids!=null){
			
				query="from TripDetail td where td.id in (" + tripids + ") and (td.status='routed' or td.status='modify' ) and td.tripDate=:tripDate  and td.tripLog=:tripLog and td.tripTime=:tripTime";
			} else {
							 query="from TripDetail td where (td.status='routed' or td.status='modify' ) and td.tripDate=:tripDate and td.tripLog=:tripLog and td.tripTime=:tripTime";
			}
			Query q=getEntityManager().createQuery(query);
			q.setParameter("tripDate", DateUtil.convertStringToDate(tripDate,"dd/MM/yyyy","yyyy-MM-dd hh:mm::ss"));
			q.setParameter("tripLog", tripLog);
			q.setParameter("tripTime", tripTime);
			List<TripDetail> tdList=q.getResultList();
			return tdList;
		}

		public TripDetail saveTrip(TripDetail td) throws Exception {
			td.setTripCode(uniqueId);
			persist(td);
			flush();
			return td;
						
		}
		
		public void getIncremenetedUnique() throws Exception{
			if (uniqueNo < 10) {
				uniqueId = uniqueCode + "00" + uniqueNo;
			} else if (uniqueNo < 100) {
				uniqueId = uniqueCode + "0" + uniqueNo;
			} else {
				uniqueId = uniqueCode + uniqueNo;
			}

			uniqueNo++;

		}

		public void saveTripCode(TripDetail td) throws Exception {
			td.setTripCode(uniqueId);
			flush();
			}

		
		public ArrayList<TripDetailsDto> getTripSheetSavedNotVendorAssigned(String tripDate, String tripLog, String siteId, String tripTime) throws Exception {
					ArrayList<TripDetailsDto> tripSheet = null;
					TripDetailsDto tripDetailsDtoObj = null;
					TripDetailsChildDto tripDetailsChildDtoObj = null;
					tripSheet = new ArrayList<TripDetailsDto>();
					ArrayList<TripDetailsChildDto> tripSheetChild = null;
					
					String subQuery = "";
					String query = "SELECT t.id,t.trip_code,t.trip_date,t.trip_time,t.trip_log,t.routeid,vt.id as vehicletypeId,vt.sit_cap,vt.type,t.security_status,t.distance,t.travelTime,t.preTripId,t.distanceFromPreTrip, case when t.vehicle is not null then (select regno from vehicles where id=t.vehicle) else 'Not set' end as vehicleNo,case when t.driverid is not null then (select name from driver where id=t.driverid) else 'Not set' end as driver,case when t.driverid is not null then (select contact from driver where id=t.driverid) else ' ' end as driverContact,case when t.escortId is not null then (select concat(escortClock,'-',name) from escort where id=t.escortid) else ' ' end as escort    FROM trip_details t,vehicle_type vt where t.siteId="+siteId+" and vt.id=t.vehicle_type and  (t.status='saved' or t.status='addsave' or t.status='saveedit') ";
					if (tripLog.equals("ALL")) {
						subQuery = " and t.trip_date='" + tripDate + "'";

					} else if (tripTime.equals("ALL")) {
						subQuery = " and t.trip_date='" + tripDate
								+ "' and t.trip_log='" + tripLog + "' ";
					} else {
						subQuery = " and t.trip_date='" + tripDate
								+ "' and t.trip_log='" + tripLog
								+ "' and t.trip_time='" + tripTime + "'";
					}
					String lastQuery = " and t.id not in (select tripid from tripvendorassign) order by t.id ";
					// System.out.println("HHHHHHH" + query + subQuery + lastQuery);
					Query q=getEntityManager().createNativeQuery(query + subQuery + lastQuery);
					List<Object[]> oList=q.getResultList();
					
					for(Object[] o:oList) {
						tripDetailsDtoObj = new TripDetailsDto();
						tripDetailsDtoObj.setId(String.valueOf(o[0]));
						tripDetailsDtoObj.setTrip_code(String.valueOf(o[1]));
						tripDetailsDtoObj.setTrip_date(String.valueOf(o[2]));
						tripDetailsDtoObj.setTrip_time(String.valueOf(o[3]));
						tripDetailsDtoObj.setTrip_log(String.valueOf(o[4]));
						tripDetailsDtoObj.setRouteId(String.valueOf(o[5]));
						tripDetailsDtoObj.setVehicleTypeId(String.valueOf(o[6]));
						tripDetailsDtoObj.setSeat(((Integer)o[7]).intValue());
						tripDetailsDtoObj.setVehicle_type(String.valueOf(o[8]));
						tripDetailsDtoObj.setIsSecurity(String.valueOf(o[9]));
						tripSheetChild = new ArrayList<TripDetailsChildDto>();
						tripDetailsDtoObj.setDistance(String.valueOf(o[10]));
						tripDetailsDtoObj.setTravelTime(String.valueOf(o[11]));
						tripDetailsDtoObj.setPreviousTripId(String.valueOf(o[12]));
						tripDetailsDtoObj.setDistanceFromPrevioustrip(((Double)o[13]).floatValue());
						tripDetailsDtoObj.setVehicleNo(String.valueOf(o[14]));
						tripDetailsDtoObj.setDriverName(String.valueOf(o[15]));
						tripDetailsDtoObj.setDriverContact(String.valueOf(o[16]));
						tripDetailsDtoObj.setEscort(String.valueOf(o[17]));
						tripDetailsDtoObj.setSiteId(siteId);
						query = "select tdc.scheduleId, tdc.employeeId as employeeId,e.displayname as EmployeeName,e.personnelNo,e.gender,a.area,p.place,l.id as landmarkId,l.landmark, ifnull(e.contactNumber1,'') as contactno, vt.employeeId approvedEmployee,  vt.showStatus, vt.noShowReason,tdc.time,tdc.dist,tdc.transportType from trip_details_child tdc left  join vendor_trip_sheet vt on tdc.employeeid=vt.employeeId and tdc.tripId=vt.tripId ,employee e ,area a,place p,landmark l where e.id=tdc.employeeId and tdc.tripId="
								+ String.valueOf(o[0])
								+ " and tdc.landmarkId=l.id and l.place=p.id and p.area=a.id order by tdc.routedOrder";
						
						Query q1=getEntityManager().createNativeQuery(query);
						List<Object[]> list=q1.getResultList();
						for(Object[] obj:list) {
							tripDetailsChildDtoObj = new TripDetailsChildDto();
							tripDetailsChildDtoObj.setScheduleId(String.valueOf(obj[0]));
							tripDetailsChildDtoObj.setEmployeeId(String.valueOf(obj[1]));
							tripDetailsChildDtoObj.setEmployeeName(String.valueOf(obj[2]));
							tripDetailsChildDtoObj.setPersonnelNo(String.valueOf(obj[3]));
							tripDetailsChildDtoObj.setGender(String.valueOf(obj[4]));
							tripDetailsChildDtoObj.setArea(String.valueOf(obj[5]));
							tripDetailsChildDtoObj.setPlace(String.valueOf(obj[6]));
							tripDetailsChildDtoObj.setLandmarkId(String.valueOf(obj[7]));
							tripDetailsChildDtoObj.setLandmark(String.valueOf(obj[8]));
							tripDetailsChildDtoObj.setContactNumber(String.valueOf(obj[9]));
							tripDetailsChildDtoObj.setApprovedEmployee(String.valueOf(obj[10]));
							tripDetailsChildDtoObj.setShowStatus(String.valueOf(obj[11]));
							tripDetailsChildDtoObj.setReason(String.valueOf(obj[12]));
							tripDetailsChildDtoObj.setTime(String.valueOf(obj[13]));
							tripDetailsChildDtoObj.setDistance(((Double)obj[14]).floatValue());
							tripDetailsChildDtoObj.setTransportType(String.valueOf(obj[15]));

							if (tripDetailsChildDtoObj.getApprovedEmployee() != null && !tripDetailsChildDtoObj.getApprovedEmployee().equals("")) {
								tripDetailsDtoObj.setCanTravel(true);
							}

							tripDetailsChildDtoObj.setTripId(String.valueOf(o[0]));
							
							tripSheetChild.add(tripDetailsChildDtoObj);
						}

						tripDetailsDtoObj.setTripDetailsChildDtoList(tripSheetChild);
						tripSheet.add(tripDetailsDtoObj);
						
					}

				
				return tripSheet;
	}

		public TripDetail getTripsById(String tripId) throws Exception {
			logger.debug("Tripin dao"+tripId);
			Query query =  getEntityManager().createQuery("from TripDetail td where td.id=:tripId");
			query.setParameter("tripId", tripId);
			List<TripDetail> tdList=query.getResultList();
			return tdList.get(0);
		}

		public ArrayList<TripDetailsDto> getTripsWithVehicle(String branch,	String shift, String regNo) throws Exception {
			
				TripDetailsDto tripDto = null;
				TripDetailsChildDto childDto = null;
				ArrayList<TripDetailsDto> tripDtailsDtos = new ArrayList<TripDetailsDto>();
				ArrayList<TripDetailsChildDto> childDtos = null;
					String filterquery="";
					if(!shift.equalsIgnoreCase("ALL")&&regNo.equalsIgnoreCase("ALL"))
					{
						System.out.println("here shift");
						filterquery=" and td.trip_log='"+shift+"'";
					}
					else if(shift.equalsIgnoreCase("ALL")&&!regNo.equalsIgnoreCase("ALL"))
					{
						System.out.println("here regNo");
						filterquery=" and v.regNo='"+regNo+"'";
					}
					else if(!shift.equalsIgnoreCase("ALL")&&!regNo.equalsIgnoreCase("ALL"))
					{
						System.out.println("here regNo");
						filterquery=" and v.regNo='"+regNo+"' and td.trip_log='"+shift+"'";
					}
					
					String query = "select vp.vehicleId,vp.date_time,vp.lattitude,vp.longitude,case when timediff(now(),vp.date_time)>'00:10:00' then 'outofreach' else vp.logstatus  end as logstatus,vp.tripId,v.regNo,td.trip_code,td.trip_log,vtsp.escort,td.siteid,(select count(*)  from vendor_trip_sheet vts,employee e where vts.tripId=td.id and  e.id=vts.employeeId and e.gender='F' and vts.curStatus='IN') as  ladyInCount,(select count(*) from vendor_trip_sheet  vts where  vts.tripId=td.id) as empCount,(select count(*)  from vendor_trip_sheet  vts where  vts.tripId=td.id and vts.curStatus='IN') as empInCount, ifnull(pa.acknowledgeBy,'notstop') as acknowledgeBy from vehicles v,vehicle_position vp, (select vp.vehicleId,max(vp.date_time) as date_time from vehicle_position vp group by tripid,vehicleId) as  tg,site s,trip_details td left  outer join  panicaction pa on td.id=pa.tripId join vendor_trip_sheet_parent vtsp on td.id=vtsp.tripid where td.trip_date between  DATE_ADD(CURDATE(),INTERVAL -1 DAY) and curdate() and tg.vehicleId=vp.vehicleId and td.id=vp.tripId and td.siteid=s.id and s.branch="
							+ branch
							+ " and tg.date_time=vp.date_time and  vp.vehicleId=v.id and vp.status='checkedin' and vp.tripid in (select tripid from vendor_trip_sheet_parent where status='started')"+filterquery+"  group by v.id order by vp.date_time desc";
				
					Query q=getEntityManager().createNamedQuery(query);
					List<Object[]> oList=q.getResultList();
				for(Object[] o : oList) {
						tripDto = new TripDetailsDto();
					 	tripDto.setVehicle(String.valueOf(o[0]));
						tripDto.setTrip_date(String.valueOf(o[1]));
						tripDto.setLatitude(String.valueOf(o[2]));			 
						tripDto.setLongitude(String.valueOf(o[3]));
						tripDto.setStatus(String.valueOf(o[4]));
						tripDto.setId(String.valueOf(o[5]));
						tripDto.setVehicleNo(String.valueOf(o[6]));	
						tripDto.setTrip_code(String.valueOf(o[7]));				
						tripDto.setTrip_log(String.valueOf(o[8]));				 
						tripDto.setIsSecurity(String.valueOf(o[9]));
						tripDto.setEmpCount(((Integer)o[11]).intValue());
						tripDto.setLadyInCount(((Integer)o[12]).intValue());
						tripDto.setEmpInCount(((Integer)o[13]).intValue());
						tripDto.setPanicAck(String.valueOf(o[14]));
					
						String actq="";
						String nuquery = " select l.latitude,l.longitude,tdc.routedOrder as pos from landmark l,trip_details_child tdc where tdc.tripId="
								+ tripDto.getId() + " and  tdc.landmarkid=l.id";
						if(tripDto.getTrip_log().equals("IN"))
						
						{
						actq="  select l.latitude,l.longitude,100 as pos from landmark l,site s where  s.id="+String.valueOf(o[10])+" and s.landmark=l.id"; 
						}
						else 
						{
							actq="  select l.latitude,l.longitude,0 as pos from landmark l,site s where  s.id="+String.valueOf(o[10])+" and s.landmark=l.id";	
						}				
//					
						Query q1=getEntityManager().createNamedQuery("select * from ("+nuquery+" union "+actq+") as tble order by pos");
						List<Object[]> objList=q1.getResultList();
						childDtos = new ArrayList<TripDetailsChildDto>();
						for(Object[] obj:objList) {
							 childDto = new TripDetailsChildDto();
							childDto.setLatitude(String.valueOf(obj[0]));
							childDto.setLongitude(String.valueOf(obj[0]));
							childDtos.add(childDto);

						}
						tripDto.setTripDetailsChildDtoList(childDtos);

						tripDtailsDtos.add(tripDto);
					}

				return tripDtailsDtos;
		}

		public ArrayList<TripDetailsDto> getTripSheetSaved(String vendorId, String tripDate,String tripLog, String siteId, String tripTime) throws Exception {
			ArrayList<TripDetailsDto> tripSheet = null;
			TripDetailsDto tripDetailsDtoObj = null;
			TripDetailsChildDto tripDetailsChildDtoObj = null;
			tripSheet = new ArrayList<TripDetailsDto>();
			ArrayList<TripDetailsChildDto> tripSheetChild = null;
			String vId="";
			if(vendorId==null || vendorId.trim().equals("")){
				vId="";	
				}
			else{
				vId=" and tva.vendorId="+vendorId+"";
			}
						
			String subQuery = "";
			String query = "SELECT t.id,t.trip_code,t.trip_date,t.trip_time,t.trip_log,t.routeid,vt.id as vehicletypeId,vm.Company,vt.sit_cap,vt.type,t.security_status,t.distance,t.travelTime,t.preTripId,t.distanceFromPreTrip,case when t.vehicle is not null then (select regno from vehicles where id=t.vehicle) else 'Not set' end as vehicleNo,case when t.driverid is not null then (select name from driver where id=t.driverid) else 'Not set' end as driver,case when t.driverid is not null then (select contact from driver where id=t.driverid) else ' ' end as	driverContact,case when t.escortId is not null then (select concat(escortClock,'-',name) from escort where id=t.escortid) else ' ' end as escort FROM vehicle_type vt,trip_details t LEFT JOIN tripvendorassign tva on t.id=tva.tripID LEFT JOIN vendormaster vm on tva.vendorId=vm.id where t.siteId="+siteId+" and vt.id=t.vehicle_type and  (t.status='saved' or t.status='addsave' or t.status='saveedit') "+vId;
			
			if (tripLog.equals("ALL")) {
				subQuery = " and t.trip_date='" + tripDate + "'";

			} else if (tripTime.equals("ALL")) {
				subQuery = " and t.trip_date='" + tripDate
						+ "' and t.trip_log='" + tripLog + "' ";
			} else {
				subQuery = " and t.trip_date='" + tripDate
						+ "' and t.trip_log='" + tripLog
						+ "' and t.trip_time='" + tripTime + "'";
			}
			String lastQuery = " order by t.id ";
			Query q=getEntityManager().createNativeQuery(query+subQuery + lastQuery);
			List<Object[]> objList=q.getResultList();
			
			for(Object[] o:objList) {
				tripDetailsDtoObj = new TripDetailsDto();
				tripDetailsDtoObj.setSiteId(siteId);
				tripDetailsDtoObj.setId(String.valueOf(o[0]));
				tripDetailsDtoObj.setTrip_code(String.valueOf(o[1]));
				tripDetailsDtoObj.setTrip_date(String.valueOf(o[2]));
				tripDetailsDtoObj.setTrip_time(String.valueOf(o[3]));
				tripDetailsDtoObj.setTrip_log(String.valueOf(o[4]));
				tripDetailsDtoObj.setRouteId(String.valueOf(o[5]));
				tripDetailsDtoObj.setVehicleTypeId(String.valueOf(o[6]));
				tripDetailsDtoObj.setCompany(String.valueOf(o[7]));
				tripDetailsDtoObj.setSeat(((Integer)o[8]).intValue());
				tripDetailsDtoObj.setVehicle_type(String.valueOf(o[9]));
				tripDetailsDtoObj.setIsSecurity(String.valueOf(o[10]));
				tripDetailsDtoObj.setDistance(String.valueOf(o[11]));
				tripDetailsDtoObj.setTravelTime(String.valueOf(o[12]));
				tripDetailsDtoObj.setPreviousTripId(String.valueOf(o[13]));
				tripDetailsDtoObj.setDistanceFromPrevioustrip(((Double)o[14]).floatValue());
				tripDetailsDtoObj.setVehicleNo(String.valueOf(o[15]));
				tripDetailsDtoObj.setDriverName(String.valueOf(o[16]));
				tripDetailsDtoObj.setDriverContact(String.valueOf(o[17]));
				tripDetailsDtoObj.setEscort(String.valueOf(o[18]));
				
				tripSheetChild = new ArrayList<TripDetailsChildDto>();
				
				
				query = "select tdc.scheduleId, tdc.employeeId as employeeId,e.displayname as EmployeeName,e.personnelNo,e.gender,a.area,p.place,l.id as landmarkId,l.landmark, ifnull(e.contactNumber1,'') as contactno, vt.employeeId approvedEmployee,  vt.showStatus, vt.noShowReason,tdc.time,tdc.dist,tdc.transportType from trip_details_child tdc left  join vendor_trip_sheet vt on tdc.employeeid=vt.employeeId and tdc.tripId=vt.tripId ,employee e ,area a,place p,landmark l where e.id=tdc.employeeId and tdc.tripId="
						+ String.valueOf(o[0])
						+ " and tdc.landmarkId=l.id and l.place=p.id and p.area=a.id order by tdc.routedOrder";
				Query q1=getEntityManager().createNativeQuery(query);
				List<Object[]> list=q1.getResultList();
				for(Object[] obj:list) {

					tripDetailsChildDtoObj = new TripDetailsChildDto();
					tripDetailsChildDtoObj.setScheduleId(String.valueOf(obj[0]));
					tripDetailsChildDtoObj.setEmployeeId(String.valueOf(obj[1]));
					tripDetailsChildDtoObj.setEmployeeName(String.valueOf(obj[2]));
					tripDetailsChildDtoObj.setPersonnelNo(String.valueOf(obj[3]));
					tripDetailsChildDtoObj.setGender(String.valueOf(obj[4]));
					tripDetailsChildDtoObj.setArea(String.valueOf(obj[5]));
					tripDetailsChildDtoObj.setPlace(String.valueOf(obj[6]));
					tripDetailsChildDtoObj.setLandmarkId(String.valueOf(obj[7]));
					tripDetailsChildDtoObj.setLandmark(String.valueOf(obj[8]));
					tripDetailsChildDtoObj.setContactNumber(String.valueOf(obj[9]));
					tripDetailsChildDtoObj.setApprovedEmployee(String.valueOf(obj[10]));
					tripDetailsChildDtoObj.setShowStatus(String.valueOf(obj[11]));
					tripDetailsChildDtoObj.setReason(String.valueOf(obj[12]));
					tripDetailsChildDtoObj.setTime(String.valueOf(obj[13]));
					tripDetailsChildDtoObj.setDistance(((Double)obj[14]).floatValue());
					tripDetailsChildDtoObj.setTransportType(String.valueOf(obj[15]));

					if (tripDetailsChildDtoObj.getApprovedEmployee() != null && !tripDetailsChildDtoObj.getApprovedEmployee().equals("")) {
						tripDetailsDtoObj.setCanTravel(true);
					}

					tripDetailsChildDtoObj.setTripId(String.valueOf(o[0]));
					tripSheetChild.add(tripDetailsChildDtoObj);
				}

				tripDetailsDtoObj.setTripDetailsChildDtoList(tripSheetChild);
				tripSheet.add(tripDetailsDtoObj);
				
			}

		
		return tripSheet;
		}

		public ArrayList<TripDetailsDto> getTripSheetModified(String vendorCompany, String tripDate, String tripLog,String siteId, String tripTime) throws Exception {

			ArrayList<TripDetailsDto> tripSheet = null;
			TripDetailsDto tripDetailsDtoObj = null;
			TripDetailsChildDto tripDetailsChildDtoObj = null;
			tripSheet = new ArrayList<TripDetailsDto>();
			ArrayList<TripDetailsChildDto> tripSheetChild = null;
			
			String subQuery = "";

			String query = "SELECT t.id,t.trip_code,t.trip_date,t.trip_time,t.trip_log,t.routeid,vt.type,t.security_status, p.logTime,t.distance,t.travelTime,t.preTripId,ifnull(p.approvalStatus,'Open')  approvalStatus, p.vehicleNo   FROM trip_details t left join vendor_trip_sheet_parent p on t.id=p.tripId,vehicle_type vt where t.siteId="+siteId+" and (t.status='routed' or t.status='addmod' or t.status='modified') and vt.id=t.vehicle_type ";
			if (tripLog.equals("ALL")) {
				subQuery = " and t.trip_date='" + tripDate + "'";

			} else if (tripTime.equals("ALL")) {
				subQuery = " and t.trip_date='" + tripDate
						+ "' and t.trip_log='" + tripLog + "' ";
			} else {
				subQuery = " and t.trip_date='" + tripDate
						+ "' and t.trip_log='" + tripLog
						+ "' and t.trip_time='" + tripTime + "'";
			}
			String lastQuery = " order by t.id ";
			
			
			Query q=getEntityManager().createNativeQuery(query+subQuery+lastQuery);
				List<Object[]> objList=q.getResultList();
			
			for(Object[] o:objList) {

				tripDetailsDtoObj = new TripDetailsDto();
				tripDetailsDtoObj.setId(String.valueOf(o[0]));
				tripDetailsDtoObj.setTrip_code(String.valueOf(o[1]));
				tripDetailsDtoObj.setTrip_date(String.valueOf(o[2]));
				tripDetailsDtoObj.setTrip_time(String.valueOf(o[3]));
				tripDetailsDtoObj.setTrip_log(String.valueOf(o[4]));
				tripDetailsDtoObj.setRouteId(String.valueOf(o[5]));
				tripDetailsDtoObj.setVehicle_type(String.valueOf(o[6]));
				tripDetailsDtoObj.setIsSecurity(String.valueOf(o[7]));
				tripDetailsDtoObj.setActualLogTime(String.valueOf(o[8]));
				tripDetailsDtoObj.setDistance(String.valueOf(o[9]));
				tripDetailsDtoObj.setTravelTime(String.valueOf(o[10]));
				tripDetailsDtoObj.setApprovalStatus(String.valueOf(o[11]));
				tripDetailsDtoObj.setVehicleNo(String.valueOf(o[12]));
				
				tripSheetChild = new ArrayList<TripDetailsChildDto>();
				query = "select tdc.employeeId,e.displayname as EmployeeName, ifnull(e.contactNumber1,'') contactNumber1,a.area,p.place,l.id as landmarkId,l.landmark, vt.employeeId approvedEmployee,tdc.time,tdc.dist,e.gender,tdc.transportType  from trip_details_child tdc  left  join vendor_trip_sheet vt on tdc.employeeId=vt.employeeId and tdc.tripId=vt.tripId ,employee e ,area a,place p,landmark l where e.id=tdc.employeeId and tdc.tripId="
						+ String.valueOf(o[0])
						+ " and tdc.landmarkId=l.id and l.place=p.id and p.area=a.id order by tdc.routedOrder";
				
				Query q1=getEntityManager().createNativeQuery(query);
				List<Object[]> list=q1.getResultList();
				for(Object[] obj:list) {

					tripDetailsChildDtoObj = new TripDetailsChildDto();
					tripDetailsChildDtoObj.setEmployeeId(String.valueOf(obj[0]));
					tripDetailsChildDtoObj.setEmployeeName(String.valueOf(obj[1]));
					tripDetailsChildDtoObj.setContactNumber(String.valueOf(obj[2]));
					tripDetailsChildDtoObj.setArea(String.valueOf(obj[3]));
					tripDetailsChildDtoObj.setPlace(String.valueOf(obj[4]));
					tripDetailsChildDtoObj.setLandmarkId(String.valueOf(obj[5]));
					tripDetailsChildDtoObj.setLandmark(String.valueOf(obj[6]));
					tripDetailsChildDtoObj.setApprovedEmployee(String.valueOf(obj[7]));
					if (tripDetailsChildDtoObj.getApprovedEmployee() != null
							&& !tripDetailsChildDtoObj.getApprovedEmployee()
									.equals("")) {
						tripDetailsDtoObj.setCanTravel(true);
					}
					tripDetailsChildDtoObj.setTime(String.valueOf(obj[8]));
					tripDetailsChildDtoObj.setDistance(((Double) obj[9]).floatValue());
					tripDetailsChildDtoObj.setGender(String.valueOf(obj[10]));
					tripDetailsChildDtoObj.setTransportType(String.valueOf(obj[11]));
					tripDetailsChildDtoObj.setTripId(String.valueOf(o[0]));
								
					tripSheetChild.add(tripDetailsChildDtoObj);

				}
				tripDetailsDtoObj.setTripDetailsChildDtoList(tripSheetChild);
				tripSheet.add(tripDetailsDtoObj);
			}
		
		

		return tripSheet;
		}

		public ArrayList<TripDetailsDto> getVendorAssignedTrip(String vendorId,String siteId, String tripDate, String[] tripTime,String tripLog, String src) throws Exception {
			
				ArrayList<TripDetailsDto> tripSheet = null;
				String tripTimeQry = "";
				
					if (tripTime != null && tripTime.length > 0) {
						for (String tt : tripTime) {
							tripTimeQry = tripTimeQry + ", '" + tt + "'";
						}
						tripTimeQry = tripTimeQry.substring(1);
					}
					/*String query="from TripDetail td where td.site.id="+siteId+" and (td.status='saved' or td.status='addsave' or t.status='saveedit')";
					Date tripDateFormatted=DateUtil.convertStringToDate(tripDate,"yyyy/MM/dd","yyyy-MM-dd hh:mm:ss");

					if (tripLog.equals("ALL")) {
						subQuery = " and td.tripDate='" + tripDateFormatted + "'";

					} else if (tripTime.equals("ALL")) {
						subQuery = " and td.tripDate='" + tripDateFormatted
								+ "' and td.tripLog='" + tripLog + "' ";
					} else {
						subQuery = " and td.tripDate='" + tripDateFormatted
								+ "' and td.tripLog='" + tripLog
								+ "' and td.tripTime in (" + tripTimeQry + " )";
					}	*/
					
					TripDetailsDto tripDetailsDtoObj = null;
					TripDetailsChildDto tripDetailsChildDtoObj = null;
					tripSheet = new ArrayList<TripDetailsDto>();
					ArrayList<TripDetailsChildDto> tripSheetChild = null;
					String subQuery = "";
					String query = "SELECT t.id, t.vehicle, t.trip_code,t.trip_date,t.trip_time,t.trip_log,t.routeid,t.vehicle_type,vt.type,t.status,t.security_status, p.logTime,t.distance,t.travelTime,t.preTripId,t.distanceFromPreTrip, t.driverId, ifnull(p.approvalStatus,'Open')  approvalStatus, p.vehicleNo, p.onTimeStatus, p.comment, p.escort, p.escortClock, t.escortId, t.escortPswd   FROM trip_details t left join vendor_trip_sheet_parent p on t.id=p.tripId ,vehicle_type vt where t.siteId="+siteId+" and vt.id=t.vehicle_type and  (t.status='saved' or t.status='addsave' or t.status='saveedit') ";
					if (tripLog.equals("ALL")) {
						subQuery = " and t.trip_date='" + tripDate + "'";

					} else if (tripTime.equals("ALL")) {
						subQuery = " and t.trip_date='" + tripDate
								+ "' and t.trip_log='" + tripLog + "' ";
					} else {
						subQuery = " and t.trip_date='" + tripDate
								+ "' and t.trip_log='" + tripLog
								+ "' and t.trip_time in (" + tripTimeQry + " )";
					}
					if (src.equals("assaign")) {
						subQuery += " and t.vehicle_type is null ";
					}
					String lastQuery = " and t.id in ( select tripid from tripvendorassign where vendorId="
							+ vendorId + " ) order by t.id ";
					Query q=getEntityManager().createNativeQuery(query + subQuery + lastQuery);
					List<Object[]> oList=q.getResultList();
					for(Object[] o :oList) {
						tripDetailsDtoObj = new TripDetailsDto();
						tripDetailsDtoObj.setId(String.valueOf(o[0]));
						tripDetailsDtoObj.setVehicleId(String.valueOf(o[1]));
						tripDetailsDtoObj.setTrip_code(String.valueOf(o[2]));
						tripDetailsDtoObj.setTrip_date(String.valueOf(o[3]));
						tripDetailsDtoObj.setTrip_time(String.valueOf(o[4]));
						tripDetailsDtoObj.setTrip_log(String.valueOf(o[5]));
						tripDetailsDtoObj.setRouteId(String.valueOf(o[6]));
						tripDetailsDtoObj.setVehicle(String.valueOf(o[7]));
						tripDetailsDtoObj.setVehicle_type(String.valueOf(o[8]));
						String status =String.valueOf(o[9]);
						tripDetailsDtoObj.setIsSecurity(String.valueOf(o[10]));
						tripDetailsDtoObj.setActualLogTime(String.valueOf(o[11]));
						tripDetailsDtoObj.setDistance(String.valueOf(o[12]));
						tripDetailsDtoObj.setTravelTime(String.valueOf(o[13]));
						tripDetailsDtoObj.setPreviousTripId(String.valueOf(o[14]));
						tripDetailsDtoObj.setDistanceFromPrevioustrip(((Double)(o[15])).floatValue());
						tripDetailsDtoObj.setDriverId(String.valueOf(o[16]));
						tripDetailsDtoObj.setApprovalStatus(String.valueOf(o[17]));
						tripDetailsDtoObj.setVehicleNo(String.valueOf(o[18]));
						tripDetailsDtoObj.setOnTimeStatus(String.valueOf(o[19]));
						tripDetailsDtoObj.setComment(String.valueOf(o[20]));
						tripDetailsDtoObj.setEscort(String.valueOf(o[21]));
						tripDetailsDtoObj.setEscortNo(String.valueOf(o[22]));
						tripDetailsDtoObj.setEscortId(String.valueOf(o[23]));
						tripDetailsDtoObj.setEscortPassword(String.valueOf(o[24]));

						tripSheetChild = new ArrayList<TripDetailsChildDto>();
						
						
						

						query = "select tdc.employeeId,e.displayname as EmployeeName,a.area,p.place,l.id as landmarkId,l.landmark,ifnull(e.contactNumber1,'') as contactno, vt.employeeId approvedEmployee,  vt.showStatus, vt.noShowReason,tdc.time,tdc.dist from trip_details_child tdc  left  join vendor_trip_sheet vt on tdc.employeeId=vt.employeeId and tdc.tripId=vt.tripId ,employee e ,area a,place p,landmark l where e.id=tdc.employeeId and tdc.tripId="
								+ String.valueOf(o[0])
								+ " and tdc.landmarkId=l.id and l.place=p.id and p.area=a.id order by tdc.routedOrder";

						Query q1=getEntityManager().createNativeQuery(query);
						List<Object[]> objList=q1.getResultList();
						for(Object[] obj: objList) {

							tripDetailsChildDtoObj = new TripDetailsChildDto();
							tripDetailsChildDtoObj.setEmployeeId(String.valueOf(obj[0]));
							tripDetailsChildDtoObj.setEmployeeName(String.valueOf(obj[1]));
							tripDetailsChildDtoObj.setArea(String.valueOf(obj[2]));
							tripDetailsChildDtoObj.setPlace(String.valueOf(obj[3]));
							tripDetailsChildDtoObj.setLandmarkId(String.valueOf(obj[4]));
							tripDetailsChildDtoObj.setLandmark(String.valueOf(obj[5]));
							tripDetailsChildDtoObj.setContactNumber(String.valueOf(obj[6]));
							tripDetailsChildDtoObj.setApprovedEmployee(String.valueOf(obj[7]));
							tripDetailsChildDtoObj.setShowStatus(String.valueOf(obj[8]));
							tripDetailsChildDtoObj.setReason(String.valueOf(obj[9]));
							tripDetailsChildDtoObj.setDistance(Float.valueOf((String)obj[10]));
							tripDetailsChildDtoObj.setTime(String.valueOf(obj[11]));
							
							if (tripDetailsChildDtoObj.getApprovedEmployee() != null && !tripDetailsChildDtoObj.getApprovedEmployee().equals("")) {
								tripDetailsDtoObj.setCanTravel(true);
							}

							tripDetailsChildDtoObj.setTripId(String.valueOf(o[0]));
							tripSheetChild.add(tripDetailsChildDtoObj);
						}

						tripDetailsDtoObj.setTripDetailsChildDtoList(tripSheetChild);
						tripSheet.add(tripDetailsDtoObj);
						
					}
				
				return tripSheet;
			}
		
		public boolean updateTripDetails(TripDetail td) throws Exception {
			boolean flag=false;
			 try{
				  update(td);
				  flush();
				  flag=true;
			  }catch(Exception e){
				  flag=false;
				  logger.error("Error in During updating ",e);
			  }
			return flag;
		}

		public int setTripReadyForTracking(String tripId, String vehicleNo)	throws Exception {
			
			int retVal = 0;
			VendorTripSheetParent vtspp=null;
			VendorTripSheet vts=null;
			Vehicle v=getVehicleById(vehicleNo);
			Query q=getEntityManager().createQuery("from VendorTripSheetParent vtsp where vtsp.tripDetail.id="+tripId);
			List<VendorTripSheetParent> vtspList=q.getResultList();
			if(vtspList!=null && !(vtspList.isEmpty())){
			VendorTripSheetParent vtsp=vtspList.get(0);
								
				if(!vtsp.getStatus().equals("started") || !vtsp.getStatus().equals("stopped") || vtsp.getStatus()==null){
				vtsp.setDownloadStatus("ready");
				vtsp.setVehicleNo(v.getRegNo());
				update(vtsp);
				retVal=1;
				}
			}
			else {
				vtspp=new VendorTripSheetParent();
				TripDetail td=getTripsById(tripId);
				vtspp.setTripDetail(td);
				vtspp.setDownloadStatus("ready");
				vtspp.setEscort(td.getSecurityStatus());
				vtspp.setVehicleNo(v.getRegNo());
				vtspp.setStatus("initial");
				vtspp.setOnTimeStatus("On Time");
				persist(vtspp);
				 flush();
				 
				 
				 //Query q1=getEntityManager().createQuery("from TripDetailsChild tdc where tdc.tripDetail.id="+tripId);
				 //List<TripDetailsChild> tdcList=q1.getResultList();
				 Query q1=getEntityManager().createNativeQuery("select tripId,employeeId,routedOrder,round(rand()*10000) from trip_details_child  where tripId="+tripId+"");	
				 List<Object[]> oList=q1.getResultList();
				 logger.debug("1111111111111111@@ "+oList.size());
				 for(Object[] o :oList){
					 TripDetail tripDetail=getTripsById(String.valueOf(o[0]));
					 Employee employee=employeeService.getEmployeeById(String.valueOf(o[1]));
					 	vts=new VendorTripSheet();
						vts.setTripDetail(tripDetail);
						vts.setEmployee(employee);
						vts.setShowStatus("No Show");
						vts.setInsertedOrder(BigInteger.valueOf((Integer)o[2]));
						vts.setKeypin(String.valueOf(o[3]));
						vts.setLatitude(0);
						vts.setLongitude(0);
						persist(vts);
						}
							
					retVal = 1;
				}

			
			return retVal;
		}

		public Vehicle getVehicleById(String vehicleNo) throws Exception{
			return (Vehicle)getEntityManager().createQuery("from Vehicle v where v.id="+vehicleNo).getResultList().get(0);
		}

		public void getDriverNameAndMob(TripDetailsDto dto) throws Exception {
			
					//String query = "select d.name,d.contact from driver d,trip_details td where td.id="+ dto.getId() + " and  td.driverid=d.id";
					String query="from TripDetail td where td.id="+dto.getId();
					Query q=getEntityManager().createQuery(query);
					TripDetail td=(TripDetail)q.getResultList().get(0);
					if (td!=null) {
						dto.setDriverName(td.getDriver().getName());
						dto.setDriverContact(td.getDriver().getContact());
					}
				
			
		}

		public JSONObject getReplayData(String fromdate, String todate, String vid) {
			Vehicle v=getEntityManager().find(Vehicle.class,Integer.parseInt(vid) );
			Query vq=getEntityManager().createQuery("from Tripshuttle ts where ts.tripstarttime between :fromdate and :todate and ts.imei=:imei order by tripstarttime asc");
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			try{
			Date fdate=sdf.parse(fromdate);
			Date edate=sdf.parse(todate);
			vq.setParameter("fromdate", new Timestamp(fdate.getTime()));
			vq.setParameter("todate", new Timestamp(edate.getTime()));
			vq.setParameter("imei", v.getVehicleImei());
			}catch(Exception e){e.printStackTrace();}
			List<Tripshuttle> list=vq.getResultList();
			JSONObject returnobject=new JSONObject();
			JSONArray latarray=new JSONArray();
			JSONArray lngarray=new JSONArray();
			JSONArray timearray=new JSONArray();
			JSONArray distance=new JSONArray();
			JSONArray status=new JSONArray();
			JSONArray starttime=new JSONArray();
			JSONArray trips=new JSONArray();
			int i=0;
			for(Tripshuttle t:list){
				List<Shuttleposition> plist=t.getShuttlepositions();
				for(Shuttleposition sp:plist){
					i=1;
					latarray.add(sp.getLat());
					lngarray.add(sp.getLng());
					timearray.add(sp.getDatetime().toString());
					distance.add(sp.getDistance());
					starttime.add(t.getTripstarttime().toString());
					trips.add(""+t.getId());
					status.add(sp.getStatus());
				}
			}
			if(i==1){
				returnobject.put("RESULT", "TRUE");
			}
			else{
				returnobject.put("RESULT", "FALSE");
			}
			returnobject.put("LATS", latarray);
			returnobject.put("LNGS",lngarray);
			returnobject.put("TIMES", timearray);
			returnobject.put("DISTANCES", distance);
			returnobject.put("STARTTIMES",starttime);
			returnobject.put("TRIPS", trips);
			returnobject.put("STATUS", status);
			flush();
			return returnobject;
		}


public JSONObject getlivetrackingdata() {
			JSONObject returnobject=new JSONObject();
			JSONArray lats=new JSONArray();
			JSONArray lngs=new JSONArray();
			JSONArray status=new JSONArray();
			JSONArray vehicleno=new JSONArray();
			JSONArray triptype=new JSONArray();
			JSONArray emps=new JSONArray();
			JSONArray empsstatus=new JSONArray();
			JSONArray tripid=new JSONArray();
			Timestamp[] ts;
			Query q=getEntityManager().createQuery("select distinct(ts.id) from Tripshuttle ts where date(ts.tripstarttime)=:curdate group by ts.imei");
			q.setParameter("curdate", new Date());
			List<Object> imeis=q.getResultList();
			Query trackingdataquery=getEntityManager().createQuery("from Shuttleposition sp where sp.tripshuttle.id=:shuttleid and date(sp.datetime)=:datetime order by sp.datetime desc");
			Query vehiclequery=getEntityManager().createQuery("from Vehicle v where v.vehicleimei=:imei");
			vehiclequery.setMaxResults(1);
			trackingdataquery.setMaxResults(1);
			ts=new Timestamp[imeis.size()];
			int i=0;
			for(Object o:imeis){
				int imei=(Integer) o;
				trackingdataquery.setParameter("shuttleid",imei);
				trackingdataquery.setParameter("datetime", new Timestamp(new Date().getTime()));
				List<Shuttleposition> list=trackingdataquery.getResultList();
				if(list.size()>0){
				Shuttleposition sp=list.get(0);
				vehiclequery.setParameter("imei", sp.getTripshuttle().getImei());
			   if(vehiclequery.getResultList().size()>0){
				Vehicle v=(Vehicle) vehiclequery.getResultList().get(0);
				lats.add(sp.getLat());
				lngs.add(sp.getLng());
				status.add(sp.getStatus());
				vehicleno.add(v.getRegNo());
				triptype.add("SHUTTLE");
				tripid.add(sp.getTripshuttle().getId()+"");
				ts[i]=sp.getDatetime();
				i++;
			   }
			   }
			}
			Query tripquery=getEntityManager().createQuery("select distinct(vp.vehicle.id) from VehiclePosition vp where date(vp.dateTime)=:datetime");
			tripquery.setParameter("datetime", new Date());
			Query livequery=getEntityManager().createQuery("from VehiclePosition vp where date(vp.dateTime)=:datetime and vp.vehicle.id=:vehicleid order by vp.dateTime desc");
			livequery.setMaxResults(1);
			livequery.setParameter("datetime", new Date());
			List<Object> vehicles=tripquery.getResultList();
			for(Object o:vehicles){
				int vehicleid=(Integer) o;
				livequery.setParameter("vehicleid", vehicleid);
				List<VehiclePosition> vehlist=livequery.getResultList();
				if(vehlist.size()>0){
				VehiclePosition lvp=vehlist.get(0);
				//for(int i1=0;i1<vehicleno.size();i1++){
					//System.out.println(""+vehicleno.get(i1).toString());
					/*if(vehicleno.get(i1).toString().equals(lvp.getVehicle().getRegNo())&&ts[i1].before(lvp.getDateTime())){
						lats.remove(i1);
						lngs.remove(i1);
						status.remove(i1);
						vehicleno.remove(i1);
						triptype.remove(i1);*/
						lats.add(""+lvp.getLattitude());
						lngs.add(""+lvp.getLongitude());
						status.add(lvp.getLogstatus());
						vehicleno.add(lvp.getVehicle().getRegNo());
						for(VendorTripSheet vtp:lvp.getTripDetail().getVendorTripSheets()){
							emps.add(vtp.getEmployee().getDisplayname());
							empsstatus.add(vtp.getShowStatus());
							
						}
						triptype.add("TRIP");
						tripid.add(lvp.getTripDetail().getId()+"");
					//}	
				}
				
				
			}
			returnobject.put("LATS",lats);
			returnobject.put("LNGS",lngs);
			returnobject.put("STATUS",status);
			returnobject.put("VEHICLENO",vehicleno);
			returnobject.put("TRIPTYPE", triptype);
			returnobject.put("EMPS",emps);
			returnobject.put("EMPSTATUS",empsstatus);
			returnobject.put("TRIPID",tripid);
			flush();
			return returnobject;
		}
	



		public List<TripDetail> getTripSheet(String tripDate,String tripMode, String tripTime) throws Exception {
			System.out.println(tripDate+tripMode+tripTime);
			String query="from TripDetail td where td.tripDate=:tripDate and td.tripLog=:tripMode and td.tripTime=:tripTime and (td.status=:status1 or td.status=:status2)";
			Query q=getEntityManager().createQuery(query);
			q.setParameter("tripDate", DateUtil.convertStringToDate(tripDate,  "dd/MM/yyyy","yyyy-MM-dd hh:mm::ss"));
				q.setParameter("tripMode", tripMode);
					q.setParameter("tripTime", tripTime);
					q.setParameter("status1", "routed");
					q.setParameter("status2", "modify");
					List<TripDetail> tdList=q.getResultList();
			 return tdList;
		}
		
		
		public List<TripDetail> getTripSheetExist(String tripDate,String tripMode, String tripTime) throws Exception {
			System.out.println(tripDate+tripMode+tripTime);
			String query="from TripDetail td where td.tripDate=:tripDate and td.tripLog=:tripMode and td.tripTime=:tripTime and (td.status=:status1 or td.status=:status2)";
			Query q=getEntityManager().createQuery(query);
			q.setParameter("tripDate", DateUtil.convertStringToDate(tripDate,  "dd/MM/yyyy","yyyy-MM-dd hh:mm::ss"));
				q.setParameter("tripMode", tripMode);
					q.setParameter("tripTime", tripTime);
					q.setParameter("status1", "routed");
					q.setParameter("status2", "modify");
					List<TripDetail> tdList=q.getResultList();
			 return tdList;
		}

		
		public TripDetail insertIntoTripDetails(TripDetail td) throws Exception {
			persist(td);
			flush();
			return td;
		}

		public TripDetailsChild insertIntoTripDetailsChild(TripDetailsChild tdc) throws Exception {
			persist(tdc);
			flush();
			return tdc;
		}

		public List<TripDetail> getTripSheetSaved(String tripDate,String tripMode, String tripTime) throws Exception {
			String query="from TripDetail td where td.tripDate=:tripDate and td.tripLog=:tripMode and td.tripTime=:tripTime and td.status=:status";
			Query q=getEntityManager().createQuery(query);
			q.setParameter("tripDate", DateUtil.convertStringToDate(tripDate,  "dd/MM/yyyy","yyyy-MM-dd hh:mm::ss"));
				q.setParameter("tripMode", tripMode);
					q.setParameter("tripTime", tripTime);
					q.setParameter("status", "saved");
					
					List<TripDetail> tdList=q.getResultList();
			 return tdList;
		}
		
		public List<TripDetail> getTripSheetSavedOrModify(String tripDate,String tripMode, String tripTime) throws Exception {
			String query="from TripDetail td where td.tripDate=:tripDate and td.tripLog=:tripMode and td.tripTime=:tripTime and (td.status=:status1 or td.status=:status2)";
			Query q=getEntityManager().createQuery(query);
			q.setParameter("tripDate", DateUtil.convertStringToDate(tripDate,  "dd/MM/yyyy","yyyy-MM-dd hh:mm::ss"));
				q.setParameter("tripMode", tripMode);
					q.setParameter("tripTime", tripTime);
					q.setParameter("status1", "saved");
					q.setParameter("status2", "modify");
					
					List<TripDetail> tdList=q.getResultList();
			 return tdList;
		}

		public List<TripDetail> getActualTripSheet(String tripDate,	String tripMode, String tripTime) throws Exception {
			String query="from TripDetail td where td.tripDate=:tripDate and td.tripLog=:tripMode and td.tripTime=:tripTime and (td.status=:status1 or td.status=:status2)";
			Query q=getEntityManager().createQuery(query);
			q.setParameter("tripDate", DateUtil.convertStringToDate(tripDate,  "dd/MM/yyyy","yyyy-MM-dd hh:mm::ss"));
				q.setParameter("tripMode", tripMode);
					q.setParameter("tripTime", tripTime);
					q.setParameter("status1", "routed");
					q.setParameter("status2", "saved");
					
					List<TripDetail> tdList=q.getResultList();
			 return tdList;
		}

		public List<EmployeeSchedule> getSchedulesbyDate(String status,String empid,String fromdate,String todate) throws Exception{
			String query="from EmployeeSchedule es where es.fromDate>=:date1 and es.status=:status";
			if(!empid.equalsIgnoreCase("admin")){
				query+=" and es.employee1.id=:empid";
			}
			Query q=getEntityManager().createQuery(query);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date d1=sdf.parse(fromdate);
			Date d2=sdf.parse(todate);
			q.setParameter("date1", d1);
			//q.setParameter("date2", d2);
			q.setParameter("status", status);
			if(!empid.equalsIgnoreCase("admin")){
				q.setParameter("empid", empid);
			}
			List<EmployeeSchedule> list=q.getResultList();
			return list;
		}
		
		
		public boolean CancelSchedule(String schid) throws Exception{
			boolean flag=false;
			EmployeeSchedule es=getEntityManager().find(EmployeeSchedule.class, schid);
			es.setStatus("c");
			update(es);
			flag=true;
			return flag;
			
		}
		
		public EmployeeScheduleAlter ModifySchedule(String schid,String time,String logtype,String date) throws Exception{
			EmployeeSchedule es=getEntityManager().find(EmployeeSchedule.class, schid);
			Query checkqry=getEntityManager().createQuery("from EmployeeScheduleAlter esa where esa.employeeSchedule.id=:schid and esa.date=:dte");
			
			EmployeeScheduleAlter esa=new EmployeeScheduleAlter();
			esa.setEmployeeSchedule(es);
			esa.setEmployee(es.getEmployee1());
			Date d=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			d=sdf.parse(date);
			esa.setStatus("a");
			esa.setStatusDate(new Date());
			esa.setRoutingtype("o");
			esa.setDate(d);
			checkqry.setParameter("schid", schid);
			checkqry.setParameter("dte", d);
			List<EmployeeScheduleAlter> oldlist=checkqry.getResultList();
			if(oldlist!=null&&oldlist.size()>0){
				EmployeeScheduleAlter oldesa=(EmployeeScheduleAlter) oldlist.get(0);
				esa.setId(oldesa.getId());
			}
			if(logtype.equalsIgnoreCase("IN")){
			esa.setLoginTime(time);
			}else{
				esa.setLogoutTime(time);
			}
			EmployeeScheduleAlter empSchAlt=(EmployeeScheduleAlter) update(esa);
			return empSchAlt;
		}
		
		public EmployeeSchedule getschedulebyId(String schid) throws Exception{
			return getEntityManager().find(EmployeeSchedule.class, schid);
		}

		public List<TripDetail> getAllTripSheetDetails(String tripDate,String tripMode, String tripTime) throws Exception {
			String query="from TripDetail td where td.tripDate=:tripDate and td.tripLog=:tripMode and td.tripTime=:tripTime and (td.status=:status1 or td.status=:status2)";
			Query q=getEntityManager().createQuery(query);
			q.setParameter("tripDate", DateUtil.convertStringToDate(tripDate,  "dd/MM/yyyy","yyyy-MM-dd hh:mm::ss"));
				q.setParameter("tripMode", tripMode);
					q.setParameter("tripTime", tripTime);
					q.setParameter("status1", "routed");
					q.setParameter("status2", "modify");
					
					List<TripDetail> tdList=q.getResultList();
			 return tdList;
		}
		
		public Site getSiteById(int siteId) {
			Query q=getEntityManager().createQuery("from Site where id=:siteId");
			q.setParameter("siteId", siteId);
			List<Site> sitesList=q.getResultList();
			return sitesList.get(0);
		}
		
		public VehicleType getVehicleTypeById(int vehicleTypeId) throws Exception {
			String query="from VehicleType where id="+vehicleTypeId;
			Query q=getEntityManager().createQuery(query);
			List<VehicleType> vehicleTypeList=q.getResultList();
						
			return vehicleTypeList.get(0);
		}
		
		public Employee getEmployeeById(String empId) throws Exception {
			Query query = getEntityManager().createQuery("from Employee emp where emp.id =:empId");
			query.setParameter("empId", empId);
			List<Employee> employees = query.getResultList();
			
			if(employees != null && employees.size() > 0)
				return employees.get(0);
			else
				throw new Exception("No data found for empId "+empId);
		}

		public int modifyTrip(ArrayList<ModifyTripDto> modifyTripDetails, String tripId,String empid, String tripMode, String siteId, String tripDate,String tripTime) throws Exception {
			
			int returnInt = 0;
					logger.debug("tripId as"+tripId);
					logger.debug("tripMode as"+tripMode);
					logger.debug("siteId as"+siteId);
					logger.debug("tripDate as"+tripDate);
					logger.debug("tripTime as"+tripTime);
				int order = 1;
				String vehicleType = null;
				String escort=null;
				if(modifyTripDetails!=null && modifyTripDetails.size()>0)
				{
					vehicleType=modifyTripDetails.get(0).getVehicleType();
					escort= modifyTripDetails.get(0).getEscort();
				}
				
				TripDetail td=getTripsById(tripId);
				Site site=getSiteById(Integer.parseInt(siteId));
				VehicleType vt=getVehicleTypeById(Integer.parseInt(vehicleType));
				TripDetail td1=new TripDetail();
				if(td==null){
					logger.debug("Inside td not null");
					td1.setTripCode(tripId);
					td1.setSite(site);
					td1.setTripDate(DateUtil.convertStringToDate(tripDate, "dd/MM/yyyy", "yyyy-MM-dd hh:mm::ss"));
					td1.setTripTime(tripTime);
					td1.setTripLog(tripMode);
					td1.setRouteId(0);
					td1.setVehicleTypeBean(vt);
					td1.setStatus("added");
					td.setSecurityStatus("NO");
					td.setRoutingType("o");
					
						persist(td1);
							flush();
					
				}else{
					td.setStatus("modify");
					td.setSecurityStatus("NO");
					td.setVehicleTypeBean(vt);
					update(td);
					returnInt=1;
				}
				
				if (modifyTripDetails != null && !(modifyTripDetails.isEmpty())) {
						TripDetail t=getTripsById(tripId);
					for (ModifyTripDto modifyTripDto : modifyTripDetails) {
						List<TripDetailsChild> tdcList=td.getTripDetailsChilds();
						TripDetailsChild tdc=new TripDetailsChild();
						Employee emp=getEmployeeById(modifyTripDto.getEmployeId());
						EmployeeSchedule es=getEmployeeSchedule(modifyTripDto.getScheduleId());
						Landmark ln=getLandmarkById(modifyTripDto.getLandmarkId());
						tdc.setTripDetail(t);
						tdc.setEmployee(emp);
						tdc.setRoutedOrder(order);
						tdc.setLandmark(ln);
						tdc.setEmployeeSchedule(es);
						tdc.setTime(tripTime);
						for(TripDetailsChild tdc1: tdcList){
							delete(tdc1);
							
						}
						
						update(tdc);
						returnInt=2;
						order++;
					}
				}		
					
			return returnInt;
		}

		public EmployeeSchedule getEmployeeSchedule(String scheduleId) throws Exception{
			Query q=getEntityManager().createQuery("from EmployeeSchedule es where es.id=:scheduleId");
			q.setParameter("scheduleId", scheduleId);
			List<EmployeeSchedule> esList=q.getResultList();
			return esList.get(0);
		}
		
		public Landmark getLandmarkById(String landmarkId) throws Exception {
			
			String query="from Landmark lm where lm.id=:landmarkId";
			Query q=getEntityManager().createQuery(query);
			q.setParameter("landmarkId", landmarkId);
			List<Landmark> landmarks=q.getResultList();
			if(landmarks.size()>0 && landmarks!=null){
					return landmarks.get(0);
			}
			return null;
		}

		public void deleteEmptyTripIds(String tripId) throws Exception {
			TripDetail td=getTripsById(tripId);
					delete(td);
								
		}

		public void removeTripDetails(TripDetail td) throws Exception {
			delete(td);
		}	
}
