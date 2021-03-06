
package com.agiledge.atom.dao.impl;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.agiledge.atom.controller.SetupController;
import com.agiledge.atom.dao.intfc.VehicleTypeDao;
import com.agiledge.atom.dto.DriverDto;
import com.agiledge.atom.dto.DriverVehicleDto;
import com.agiledge.atom.dto.VehicleDto;
import com.agiledge.atom.dto.VehicleTypeDto;
import com.agiledge.atom.entities.Site;
import com.agiledge.atom.entities.Vehicle;
import com.agiledge.atom.entities.VehicleType;

@Repository
public class VehicleTypeDaoImpl extends AbstractDao implements VehicleTypeDao {
	
	private static final Logger logger = Logger.getLogger(SetupController.class);
	
	public List<VehicleType> getAllVehicleType() {
		String query="from VehicleType order by sitCap";
		Query q=getEntityManager().createQuery(query);
		List<VehicleType> vehicleTypeList=q.getResultList();
					
		return vehicleTypeList;
	}
	
	public VehicleType getVehicleTypeById(int vehicleTypeId) throws Exception {
		String query="from VehicleType where id="+vehicleTypeId;
		Query q=getEntityManager().createQuery(query);
		List<VehicleType> vehicleTypeList=q.getResultList();
					
		return vehicleTypeList.get(0);
	}

	public VehicleType insertVehicleType(VehicleType vt) throws Exception {
		persist(vt);
		flush();
		return vt;
	}

	public ArrayList<VehicleTypeDto> getAllVehicleTypeBySite(int siteId) throws Exception {
		VehicleTypeDto vehicleTypeDtoObj = null;
		ArrayList<VehicleTypeDto> vehicleTypeDtoList = new ArrayList<VehicleTypeDto>();
		
		String query="from VehicleType" ;
		Query q=getEntityManager().createQuery(query);
		List<VehicleType> vehicleTypeBySiteList=q.getResultList();
		for(VehicleType vt: vehicleTypeBySiteList){
			for(Site s : vt.getSites()){
				if(s.getId()==siteId){
					vehicleTypeDtoObj = new VehicleTypeDto();
					vehicleTypeDtoObj.setId(vt.getId());
					vehicleTypeDtoObj.setType(vt.getType());
					vehicleTypeDtoObj.setSeat(vt.getSeat());
					vehicleTypeDtoObj.setSittingCopacity(vt.getSitCap());
					vehicleTypeDtoList.add(vehicleTypeDtoObj);
				}
			}
		}
		return vehicleTypeDtoList;
	}

	

	public void getAllVehicleTypeByToAppend(ArrayList<VehicleTypeDto> vehicleType) throws Exception {
		String query="from VehicleType order by sitCap";
		Query q=getEntityManager().createQuery(query);
		List<VehicleType> vtList=q.getResultList();
		
			for(VehicleType vt : vtList){
				for(VehicleTypeDto vt1: vehicleType){
						if(vt.getId()==vt1.getId()){
							vt1.setType(vt.getType());
							vt1.setSeat(vt.getSeat());
							vt1.setSittingCopacity(vt.getSitCap());
								break;
								}
							}	
						}	
			
		
	}

	public ArrayList<VehicleDto> getVehicleTrackInInterval(String tripId) throws Exception {
		
		VehicleDto vehicleDto = null;
		ArrayList<VehicleDto> vehicleDtos = new ArrayList<VehicleDto>();
		String query="SELECT vehicleid,lattitude,longitude,DATE_FORMAT(date_time,'%d-%m-%Y %H:%i') as dttime FROM vehicle_position WHERE  tripid="+tripId+" group by dttime";
			Query q=getEntityManager().createNamedQuery(query);
			List<Object[]> oList=q.getResultList();
			for(Object[] o: oList) {
				vehicleDto = new VehicleDto();
				vehicleDto.setId(String.valueOf(o[0]));
				vehicleDto.setLattitude(String.valueOf(o[1]));
				vehicleDto.setLongitude(String.valueOf(o[2]));
				vehicleDto.setDateTime(String.valueOf(o[3]));
				vehicleDtos.add(vehicleDto);

			}
		 
		return vehicleDtos;
	}

	public ArrayList<VehicleDto> vehicleTrack(String tripId) throws Exception {
		
		VehicleDto vehicleDto = null;
		ArrayList<VehicleDto> vehicleDtos = new ArrayList<VehicleDto>();
		String query="select vp.vehicleid,vp.lattitude,vp.longitude,vp.logstatus,v.regNo from vehicles v,vehicle_position vp where vp.tripId="
							+ tripId
							+ " and vp.vehicleId=v.id order by vp.date_time";
			Query q=getEntityManager().createNamedQuery(query);
			List<Object[]> oList=q.getResultList();
			for(Object[] o: oList) {
				vehicleDto = new VehicleDto();
				vehicleDto.setId(String.valueOf(o[0]));
				vehicleDto.setLattitude(String.valueOf(o[1]));
				vehicleDto.setLongitude(String.valueOf(o[2]));
				vehicleDto.setStatus(String.valueOf(o[3]));
				vehicleDto.setVehicleNo(String.valueOf(o[4]));
				vehicleDtos.add(vehicleDto);

			}
		 
		return vehicleDtos;
	}

	public ArrayList<DriverVehicleDto> getVehicle(String vehicleId)	throws Exception {
		
		DriverVehicleDto driverVehicleDto = null;
		ArrayList<DriverVehicleDto> drivervehicleDtos = new ArrayList<DriverVehicleDto>();
		VehicleDto vehicleDto = null;
		DriverDto driverDto = null;
		Query q=getEntityManager().createNativeQuery("select d.id,d.name,d.address,d.contact,v.regNo,vt.type,vm.company from driver d,vehicles v,driver_vehicle dv,vehicle_type vt,vendormaster vm where v.id="
							+ vehicleId
							+ " and d.id=dv.driverId and v.id=dv.vehicleid and v.vehicletype=vt.id and d.vendorId=vm.id ");
		System.out.print("VehicleIdddd"+vehicleId);	
		List<Object[]> oList=q.getResultList();
			for(Object[] o:oList) {

				driverVehicleDto = new DriverVehicleDto();
				driverDto = new DriverDto();
				vehicleDto = new VehicleDto();
				driverDto.setDriverId(((Integer)o[0]).intValue());
				driverDto.setDriverName(String.valueOf(o[1]));
				driverDto.setAddress1(String.valueOf(o[2]));
				driverDto.setContactNo(String.valueOf(o[3]));
				vehicleDto.setVehicleNo(String.valueOf(o[4]));
				vehicleDto.setVehicleType(String.valueOf(o[5]));
				driverDto.setVendor(String.valueOf(o[6]));
						
				driverVehicleDto.setVehicleDto(vehicleDto);
				driverVehicleDto.setDriverDto(driverDto);
				drivervehicleDtos.add(driverVehicleDto);
			}
		 
		return drivervehicleDtos;
	}

	public Vehicle getVehicleById(String vehicleId) throws Exception {
			Query q=getEntityManager().createQuery("from Vehicle v where v.status='a' and v.id="+vehicleId);
		return (Vehicle)q.getResultList().get(0);
	}
		
	public List<Vehicle> getVehiclesByIdOrNot(String vehicleId) throws Exception {
			String vId="";
			if(vehicleId!=null && !vehicleId.equals("") && !vehicleId.equals("0")){
				vId=" and v.id="+vehicleId;
			}
				Query q=getEntityManager().createQuery("from Vehicle v where v.status='a' "+vId+"");
			return (List<Vehicle>)q.getResultList();
		}
	
	
	public Vehicle getVehicleByIdNotStatus(String vehicleId) throws Exception {
		Query q=getEntityManager().createQuery("from Vehicle v where v.id="+vehicleId);
	return (Vehicle)q.getResultList().get(0);
}

	public List<Vehicle> getAllVehicle() throws Exception {
		Query q=getEntityManager().createQuery("from Vehicle v where v.status='a'");
		return q.getResultList();
	}

	public Vehicle updateVehicleStatus(Vehicle v) throws Exception {
	    update(v);
			return v;
	}

	public Vehicle addVehicle(Vehicle v) throws Exception {
		persist(v);
			flush();
				return v;
	}

	
	public VehicleType setSiteVehicle(VehicleType vt) throws Exception {
		update(vt);
			return vt;
	}

	public String getVehicleByImei(String imei) throws Exception {
		Query q=getEntityManager().createQuery("from Vehicle v where v.vehicleimei=:imei");
		q.setParameter("imei", imei);
		Vehicle v= (Vehicle)q.getResultList().get(0);
		if(v!=null){
			return v.getRegNo();
		}else{
			return "";
		}
			
	}

	public String getDistance(String imei, String fromDate, String toDate) {
		logger.debug("imeiiiiiiiiiii"+imei);
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
		Query q=getEntityManager().createQuery("select sp.distance from Shuttleposition sp where () sp.tripshuttle.id=:id order by sp.id desc");
		q.setMaxResults(1);
		q.setParameter("id",Integer.valueOf(imei));
		List<Object> objList=q.getResultList();
		logger.debug("size of the list"+objList.size());
		if(objList!=null){
		Object sumobj=objList.get(0);
		return (String) sumobj;
		}else{
			return null;
			}
		
	}
	

}
