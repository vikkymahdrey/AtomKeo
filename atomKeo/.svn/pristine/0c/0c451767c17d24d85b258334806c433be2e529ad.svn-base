package com.agiledge.atom.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.agiledge.atom.constants.SettingsConstant;
import com.agiledge.atom.dao.intfc.MobileTripSheetDao;
import com.agiledge.atom.dto.TripDetailsChildDto;
import com.agiledge.atom.dto.TripDetailsDto;
import com.agiledge.atom.entities.VendorTripSheetParent;

@Repository
public class MobileTripSheetDaoImpl extends AbstractDao implements MobileTripSheetDao {
	private static final Logger logger = Logger.getLogger(MobileTripSheetDaoImpl.class);

	public ArrayList<String> getDriverPasswords(String tripId, int priorDays, int postDays) throws Exception {
		logger.debug("hi in getDriverPasword");
			 	ArrayList<String> passwords = new ArrayList<String>();
			 	String query="select driverPswd from trip_details where trip_date  between date_add(trip_date,  interval "+priorDays+" day ) and date_add(trip_date,  interval "+postDays+" day )  and driverPswd is not null";
				Query q=getEntityManager().createNativeQuery(query);
					/*q.setParameter(1, priorDays); 
					q.setParameter(2, postDays); */
					List<Object[]> oList=q.getResultList();
				
					
					for(Object[] o : oList) {
						String driverPswd=String.valueOf(o[0]);
                      //driverPswd!=null&&driverPswd.trim().equals("")==false) {
							passwords.add(driverPswd);
					}
					
				logger.debug("SIzeeee of list"+passwords.size());					
				return passwords;
	}

	public ArrayList<String> getEscortPasswords(String tripId, int priorDays, int postDays) throws Exception {
		ArrayList<String> passwords = new ArrayList<String>();
		Query q=getEntityManager().createNativeQuery("select escortPswd from trip_details where trip_date   between  (select date_add(trip_date,  interval -? day ) from trip_details where id=?) and (select date_add(trip_date,  interval ? day ) from trip_details where id=?)");
		q.setParameter(1, priorDays); 
		q.setParameter(2, tripId);
		q.setParameter(3, priorDays); 
		q.setParameter(4, tripId);
		
		List<Object[]> oList=q.getResultList();
		for(Object[] o : oList) {
			String escortPswd=String.valueOf(o[0]);
				if(escortPswd!=null && escortPswd.trim().equals("")==false) {
					passwords.add(escortPswd);
				}
		}
		
			
		return passwords;
	}

	public TripDetailsDto getVehicleTripSheet(String tripId) throws Exception {
		
		TripDetailsDto tripDetailsDtoObj = null;
		TripDetailsChildDto tripDetailsChildDtoObj = null;
		ArrayList<TripDetailsChildDto> tripSheetChild = null;
			 
			String querytemp="SELECT t.id,t.trip_code,t.trip_date,t.trip_time,t.trip_log,t.routeid,v.regNo,t.security_status,vt.type,t.distance,t.travelTime,t.preTripId,t.distanceFromPreTrip, ifnull(vtp.status,'initial') status, ifnull(vtp.escort,'NO') escort, vtp.distanceCovered, vtp.timeElapsed, vtp.downloadStatus, t.siteId FROM trip_details t left join vendor_trip_sheet_parent vtp on t.id=vtp.tripId ,vehicles v, vehicle_type vt where  vt.id=t.vehicle_type  and v.id=t.vehicle and t.id="+tripId+"";
			Query q=getEntityManager().createNativeQuery(querytemp);
			List<Object[]> oList=q.getResultList();
			
			for(Object[] o :oList) {
				tripDetailsDtoObj = new TripDetailsDto();
				tripDetailsDtoObj.setId(String.valueOf(o[0]));
				tripDetailsDtoObj.setTrip_code(String.valueOf(o[1]));
				tripDetailsDtoObj.setTrip_date(String.valueOf(o[2]));
				tripDetailsDtoObj.setTrip_time(String.valueOf(o[3]));
				tripDetailsDtoObj.setTrip_log(String.valueOf(o[4]));
				tripDetailsDtoObj.setRouteId(String.valueOf(o[5]));
				tripDetailsDtoObj.setVehicleNo(String.valueOf(o[6]));
				tripDetailsDtoObj.setIsSecurity(String.valueOf(o[7]));
				//tripDetailsDtoObj.setStatus(String.valueOf(o[8]));
				tripDetailsDtoObj.setVehicle_type(String.valueOf(o[8]));
				tripDetailsDtoObj.setDistance(String.valueOf(o[9]));
				tripDetailsDtoObj.setTravelTime(String.valueOf(o[10]));
				tripDetailsDtoObj.setPreviousTripId(String.valueOf(o[11]));
				tripDetailsDtoObj.setDistanceFromPrevioustrip(((Double)o[12]).floatValue());
				tripDetailsDtoObj.setStatus(String.valueOf(o[13]));
				tripDetailsDtoObj.setEscort(String.valueOf(o[14]));
				tripDetailsDtoObj.setDistanceCovered(((Double)o[15]).floatValue());
				if(String.valueOf(o[16])!=null && !String.valueOf(o[16]).isEmpty() && String.valueOf(o[16]).equals(null)){
				logger.debug("TimeElapsed"+String.valueOf(o[16]) );
				tripDetailsDtoObj.setTimeElapsed(((Long)o[16]).longValue());
				}
				tripDetailsDtoObj.setDownloadStatus(String.valueOf(o[17]));
				tripDetailsDtoObj.setSiteId(String.valueOf(o[18]));
			
				tripSheetChild = new ArrayList<TripDetailsChildDto>();
		//		if (status.equals("modified") || status.equals("addmod")) {
		//			rs1 = st.executeQuery("select tdc.empid as employeeId,e.displayname as EmployeeName,a.area,p.place,l.id as landmarkId,l.landmark, tdc.status from trip_details_modified tdc,employee e,area a,place p,landmark l where e.id=tdc.empid and tdc.tripId="
			//				+ rs.getString(1)
			//				+ " and tdc.status='modified' and tdc.landmarkId=l.id and l.place=p.id and p.area=a.id order by tdc.routedOrder");
			//	} else {
				Query q1=getEntityManager().createNativeQuery("select e.Gender, tdc.employeeId,e.displayname as EmployeeName,a.area,p.place,l.id as landmarkId,l.landmark,tdc.time,vts.showStatus, ifnull(vts.curStatus,'INIT') curStatus,vts.keypin,e.loginid,e.personnelno,e.contactnumber1,tdc.status from employee e,area a,place p,landmark l,trip_details_child tdc  left outer join vendor_trip_sheet vts on tdc.tripId=vts.tripId and tdc.employeeId=vts.employeeId where e.id=tdc.employeeId and tdc.tripId="+String.valueOf(o[0])+"  and tdc.landmarkId=l.id and l.place=p.id and p.area=a.id order by tdc.routedOrder");
				List<Object[]> objList=q1.getResultList();
				//}
				for(Object[] obj : objList) {
					tripDetailsChildDtoObj = new TripDetailsChildDto();
					tripDetailsChildDtoObj.setGender(String.valueOf(obj[0]));
					tripDetailsChildDtoObj.setTripId(String.valueOf(o[0]));
					tripDetailsChildDtoObj.setEmployeeId(String.valueOf(obj[1]));
					tripDetailsChildDtoObj.setEmployeeName(String.valueOf(obj[2]));
					tripDetailsChildDtoObj.setArea(String.valueOf(obj[3]));
					tripDetailsChildDtoObj.setPlace(String.valueOf(obj[4]));
					tripDetailsChildDtoObj.setLandmarkId(String.valueOf(obj[5]));
					tripDetailsChildDtoObj.setLandmark(String.valueOf(obj[6]));
					tripDetailsChildDtoObj.setTime(String.valueOf(obj[7]));
					tripDetailsChildDtoObj.setShowStatus(String.valueOf(obj[8]));
					tripDetailsChildDtoObj.setGetInStatus(String.valueOf(obj[9]));
					
					if(SettingsConstant.empAuthinticationForComet.equalsIgnoreCase("pin"))
					{
					tripDetailsChildDtoObj.setKeyPin(String.valueOf(obj[10]));
					}
					
					else if(SettingsConstant.empAuthinticationForComet.equalsIgnoreCase("loginid"))
					{
						int loginidlength=String.valueOf(obj[11]).length();
						tripDetailsChildDtoObj.setKeyPin(String.valueOf(obj[11]).substring(loginidlength-4));
					}
					else
					{
					tripDetailsChildDtoObj.setKeyPin(String.valueOf(obj[12]));
					}
					tripDetailsChildDtoObj.setPersonnelNo(String.valueOf(obj[12]));
					tripDetailsChildDtoObj.setContactNumber(String.valueOf(obj[13]));
					
					tripSheetChild.add(tripDetailsChildDtoObj);
					
				}
				tripDetailsDtoObj.setTripDetailsChildDtoList(tripSheetChild);
			}

			Query q2=getEntityManager().createQuery("from VendorTripSheetParent vtsp where vtsp.tripDetail.id=:tripId");
			q2.setParameter("tripId", tripId);
			
			VendorTripSheetParent vtspp=(VendorTripSheetParent) q2.getResultList().get(0);
			/*if(vtspp!=null)
			{
			tripDetailsDtoObj.setStatus(vtspp.getStatus());	
			}
			else
			{
				tripDetailsDtoObj.setStatus("new");	
			}*/
			return tripDetailsDtoObj;
	}

}
