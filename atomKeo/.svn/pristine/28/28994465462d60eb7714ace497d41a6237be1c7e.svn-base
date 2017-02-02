package com.agiledge.atom.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agiledge.atom.constants.SettingsConstant;
import com.agiledge.atom.dao.intfc.AuditLogDao;
import com.agiledge.atom.dao.intfc.VendorDao;
import com.agiledge.atom.entities.Employee;
import com.agiledge.atom.entities.TripDetail;
import com.agiledge.atom.entities.Vendor;
import com.agiledge.atom.service.intfc.TripDetailsService;
import com.agiledge.atom.service.intfc.VendorService;

@Service
@Transactional
public class VendorServiceImpl implements VendorService {
	private final static Logger logger = Logger.getLogger(VendorServiceImpl.class);
	
	@Autowired
	private VendorDao vendorDao;
	
	@Autowired
	private AuditLogDao auditLogDao;
	
	@Autowired
	private TripDetailsService tripDetailsService;
	
	private String erroMessage = "";
	

	public List<Vendor> getVendorList() throws Exception{
		return vendorDao.getVendorList();
	}
	public Vendor addVendor(Vendor vendor) throws Exception {
		return vendorDao.addVendor(vendor);
		}
	public Vendor getVendorById(String vendorId) throws Exception {
			return vendorDao.getVendorById(vendorId);
	}
	public void deleteUpdateVendor(Vendor vm) throws Exception {
		vendorDao.deleteUpdateVendor(vm);
			}
	public Employee getEmpByLoginId(String loginId) throws Exception {
			return vendorDao.getEmpByLoginId(loginId);
	}
	public int assaginTripVehicle(List<TripDetail> tdList) throws Exception {
		
		int retVal=tripDetailsService.assaginTripVehicle(tdList);
		/*if (retVal>0 && SettingsConstant.TRACKING_METHOD.equalsIgnoreCase("DOUBLE_APPROVAL"))
		{
			tripDetailsService.updateApprovalStatus(tdList);
		}*/
			return retVal;
	}
	public List<Vendor> getVendorInSite(String siteId) throws Exception {
		return vendorDao.getVendorInSite(siteId);
	}
	public List<Vendor> getVendorNotInSite(String siteId) throws Exception {
		return vendorDao.getVendorNotInSite(siteId);
	}
	
	
	
}
