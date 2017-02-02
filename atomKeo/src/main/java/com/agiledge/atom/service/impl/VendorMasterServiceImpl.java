package com.agiledge.atom.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agiledge.atom.constants.SettingsConstant;
import com.agiledge.atom.dao.intfc.VendorMasterDao;
import com.agiledge.atom.dto.VehicleDto;
import com.agiledge.atom.entities.Tripvendorassign;
import com.agiledge.atom.entities.Vehicle;
import com.agiledge.atom.entities.Vendormaster;
import com.agiledge.atom.service.intfc.VendorMasterService;

@Service
public class VendorMasterServiceImpl implements VendorMasterService{
	private final static Logger logger = Logger.getLogger(VendorMasterServiceImpl.class);
	
	@Autowired
	private VendorMasterDao vendorMasterDao;
	
	public List<Vendormaster> getMasterVendorlist() throws Exception{
		return vendorMasterDao.getMasterVendorList();
	}

	public Vendormaster addVendorMaster(Vendormaster vendormaster) throws Exception{
				
	  return vendorMasterDao.addVendorMaster(vendormaster);
	}
	public void deleteUpdateVendorMaster(Vendormaster vm) throws Exception {
		vendorMasterDao.deleteUpdateVendorMaster(vm);
	}

	public Vendormaster getVendorMasterById(int vendormasterId) {
		return vendorMasterDao.getVendorMasterById(vendormasterId);
	}

	public Tripvendorassign assignVendorTrip(Tripvendorassign tva) throws Exception {
		return vendorMasterDao.assignVendorTrip(tva);
	}

	Map<String, ArrayList<VehicleDto>> vendorVehicle = null;

	public void loadVehiclesVendor(String vendorId) throws Exception{
		String vehicletype = "";
		vendorVehicle = new HashMap<String, ArrayList<VehicleDto>>();
		ArrayList<VehicleDto> dtos = vendorMasterDao.getVendorVehicles(vendorId);
		ArrayList<VehicleDto> typeVehicle = null;
		for (VehicleDto dto : dtos) {
			if (!vehicletype.equals(dto.getVehicleType())) {
				
				vehicletype = dto.getVehicleType();
				typeVehicle = new ArrayList<VehicleDto>();
				typeVehicle.add(dto);
				vendorVehicle.put(dto.getVehicleType(), typeVehicle);

			} else {

				typeVehicle.add(dto);
			}
		}
	}

	public ArrayList<VehicleDto> getVendorVehicles(String vehicle)	throws Exception {
		
			if(SettingsConstant.felxibleVehicleType.equalsIgnoreCase("true"))
			{
				ArrayList<VehicleDto> veh=new ArrayList<VehicleDto>();
				for(Map.Entry<String, ArrayList<VehicleDto>> vehicles : vendorVehicle.entrySet())
				{
				veh.addAll(vehicles.getValue());	
				}
				return veh;
			}
			else
			{
			return vendorVehicle.get(vehicle);
			}
		
	}

	public List<Vehicle> getVendorVehiclesById(String vendorId) throws Exception {
		return vendorMasterDao.getVendorVehiclesById(vendorId);
	}
	

	
	
}
