package com.agiledge.atom.service.intfc;

import java.util.List;

import com.agiledge.atom.entities.Employee;
import com.agiledge.atom.entities.TripDetail;
import com.agiledge.atom.entities.Vendor;


public interface VendorService {

	List<Vendor> getVendorList() throws Exception;
	Vendor addVendor(Vendor vendor) throws Exception;
	Vendor getVendorById(String vendorId) throws Exception;
	void deleteUpdateVendor(Vendor vm) throws Exception;
	Employee getEmpByLoginId(String loginId) throws Exception;
	int assaginTripVehicle(List<TripDetail> tdList) throws Exception;
	List<Vendor> getVendorInSite(String siteId)throws Exception;
	List<Vendor> getVendorNotInSite(String siteId)throws Exception;
	
	
	

}
