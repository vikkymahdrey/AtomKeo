package com.agiledge.atom.service.intfc;

import java.util.ArrayList;
import java.util.List;

import com.agiledge.atom.dto.VehicleDto;
import com.agiledge.atom.entities.Tripvendorassign;
import com.agiledge.atom.entities.Vehicle;
import com.agiledge.atom.entities.Vendormaster;


public interface VendorMasterService {
	List<Vendormaster> getMasterVendorlist() throws Exception;
	
   Vendormaster addVendorMaster(Vendormaster vendormaster)throws Exception;
   void deleteUpdateVendorMaster(Vendormaster vm) throws Exception;
   Vendormaster getVendorMasterById(int vendormasterId)throws Exception;
   Tripvendorassign assignVendorTrip(Tripvendorassign tva) throws Exception;
   void loadVehiclesVendor(String vendorCompany) throws Exception;
   ArrayList<VehicleDto> getVendorVehicles(String vehicle) throws Exception;
   List<Vehicle> getVendorVehiclesById(String vendorId) throws Exception;

   
}
