package com.agiledge.atom.dao.intfc;

import java.util.ArrayList;
import java.util.List;

import com.agiledge.atom.dto.VehicleDto;
import com.agiledge.atom.entities.Tripvendorassign;
import com.agiledge.atom.entities.Vehicle;
import com.agiledge.atom.entities.Vendormaster;


public interface VendorMasterDao {
	
	List<Vendormaster> getMasterVendorList() throws Exception;
    Vendormaster addVendorMaster(Vendormaster Vendormaster) throws Exception;
    void deleteUpdateVendorMaster(Vendormaster vm) throws Exception;
	Vendormaster getVendorMasterById(int vendormasterId);
	Tripvendorassign assignVendorTrip(Tripvendorassign tva) throws Exception;
	ArrayList<VehicleDto> getVendorVehicles(String vendorId) throws Exception;
	List<Vehicle> getVendorVehiclesById(String vendorId) throws Exception;
	
	

}
