package com.agiledge.atom.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.agiledge.atom.constants.SettingsConstant;
import com.agiledge.atom.dao.intfc.TripDao;
import com.agiledge.atom.dto.TripDetailsDto;
import com.agiledge.atom.entities.Tripshuttle;

@Repository
public class TripDaoImpl extends AbstractDao implements TripDao {
	private static final Logger logger = Logger.getLogger(TripDaoImpl.class);

	public ArrayList<TripDetailsDto> getStationaryVehiclePosition(String branch) throws Exception {
		
		TripDetailsDto tripDto = null;
 		ArrayList<TripDetailsDto> tripDtailsDtos = new ArrayList<TripDetailsDto>();
 		
			String query = "select v.id vehicleid, v.regNo, vp.lattitude,  vp.longitude, 'stationary' logstatus from vehicles v, full_vehicle_position vp, device d, device_vehicle dv, site_vehicle sv, site s, (select v.regNo,max(vp.date_time) date_time, 'stationary' from vehicles v, full_vehicle_position vp, device d, device_vehicle dv where v.id=dv.vehicleId and d.id=dv.deviceId and dv.id=vp.dvId group by dv.vehicleId ) ddv,  (select vehicleId, max( date_time) date_time from vehicle_position group by vehicleId) rp  where v.id=dv.vehicleId and d.id=dv.deviceId and dv.id=vp.dvId  and ddv.date_time=vp.date_time and ddv.regNo=v.regNo and v.vehicletype= sv.vehicleTypeId and s.id=sv.siteId and s.branch=" + branch + " and rp.vehicleId=v.id and rp.date_time<vp.date_time group by regNo having vp.date_time=max(vp.date_time) order by vp.date_time desc";

			if(SettingsConstant.comp.equalsIgnoreCase("keo"))
			{
				query="SELECT v.id vehicleid, v.regNo,vp.lattitude,vp.longitude,'stationary' logstatus FROM VEHICLE_POSITION vp JOIN vehicles v on vp.vehicleId=v.id group by regno order by vp.date_time desc ";
			}
			
			Query q=getEntityManager().createNamedQuery(query);
			List<Object[]> oList=q.getResultList();
			for(Object[] o: oList) {
				
				tripDto = new TripDetailsDto();
			 
				tripDto.setVehicle(String.valueOf(o[0]));
				tripDto.setVehicleNo(String.valueOf(o[1]));			 
				tripDto.setLatitude(String.valueOf(o[2]));			 
				tripDto.setLongitude(String.valueOf(o[3]));
 				tripDto.setId("-1");
				tripDto.setTrip_code(" ");				
				tripDto.setTrip_date("");
							 
				if(SettingsConstant.comp.equalsIgnoreCase("keo"))
				{
					tripDto.setStatus("");	
				}
				else
				{
					tripDto.setStatus(String.valueOf(o[4]));			 
							
				}
				tripDto.setTrip_log(" ");				 
				tripDto.setIsSecurity(" ");
				
 				tripDto.setLadyInCount(0);
				tripDto.setEmpInCount(0);
				tripDto.setEmpCount(0);
 				tripDto.setPanicAck(" ");
 				 
 				tripDtailsDtos.add(tripDto);
			}

		
		return tripDtailsDtos;
		
	}

	public List<Tripshuttle> getShuttleTripDetailsByVehicleImei(String site,
			String fromDate, String toDate, String vehicleImei)	throws Exception {
		fromDate=fromDate+" 00:00:00";
		toDate=toDate+" 23:59:59";
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		
		Date frmDat=null;
		Date toDat=null;
		try{
			frmDat=sdf.parse(fromDate);
			toDat=sdf.parse(toDate);
		}catch(Exception e){
			logger.error("Error in ",e);
		}	
		
		//String query="from Tripshuttle ts where (ts.tripstarttime<=:fromDate and ts.tripendtime>=:toDate) and ts.imei=:vehicleImei";
		String query="from Tripshuttle ts where  ts.imei=:vehicleImei";
			Query q=getEntityManager().createQuery(query);
				/*q.setParameter("fromDate", DateUtil.convertStringToDate(fromDate, "dd/MM/yyyy", "yyyy-MM-dd hh:mm::ss"));
					q.setParameter("toDate", DateUtil.convertStringToDate(toDate, "dd/MM/yyyy", "yyyy-MM-dd hh:mm::ss"));*/
						/*q.setParameter("fromDate", new Timestamp(frmDat.getTime()));
							q.setParameter("toDate", new Timestamp(toDat.getTime()));*/
								q.setParameter("vehicleImei", vehicleImei);
				
			List<Tripshuttle> tsList=q.getResultList();	
		
					
		return tsList;
	}

}
