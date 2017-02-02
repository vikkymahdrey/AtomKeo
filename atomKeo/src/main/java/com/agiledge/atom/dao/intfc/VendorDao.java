package com.agiledge.atom.dao.intfc;

import java.util.List;

import com.agiledge.atom.entities.Employee;
import com.agiledge.atom.entities.Vendor;


public interface VendorDao {

	List<Vendor> getVendorList() throws Exception;
	Vendor addVendor(Vendor vendor) throws Exception;
	Vendor getVendorById(String vendorId) throws Exception;
	void deleteUpdateVendor(Vendor vm) throws Exception;
	Employee getEmpByLoginId(String loginId)throws Exception;
	List<Vendor> getVendorInSite(String siteId)throws Exception;
	List<Vendor> getVendorNotInSite(String siteId)throws Exception;
	
		

}
