package com.agiledge.atom.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agiledge.atom.dao.intfc.AdhocDao;
import com.agiledge.atom.entities.Adhocbooking;
import com.agiledge.atom.entities.Adhoctype;
import com.agiledge.atom.entities.Settingsstring;
import com.agiledge.atom.service.intfc.AdhocService;

@Service("adhocService")
public class AdhocServiceImpl implements AdhocService{
	
	@Autowired
	private AdhocDao adhocDao;

	public List<Adhocbooking> getAllPendingAdhocBookings() throws Exception {
		return adhocDao.getAllPendingAhdocBookings();
	}

	public int[] getAdhocNotificationCount(String employeeId, String roleId,String role) throws Exception {
		return adhocDao.getAdhocNotificationCount(employeeId,roleId,role);
	}

	public List<Settingsstring> getSettingsStrings(String adhoc, String type,
			String siteId, String projectUnit) throws Exception {
		return adhocDao.getSettingsStrings(adhoc,type,siteId,projectUnit);
	}

	public Adhoctype getAdhocTypeByType(String adhoctype) throws Exception {
		return adhocDao.getAdhocTypeByType(adhoctype);
	}

	public List<String> getProjectUnits() throws Exception {
		return adhocDao.getProjectUnits();
	}

	public List<Adhoctype> getSetupDetails(String adhoctype,String siteId, String projectUnit) throws Exception {
		List<Adhoctype> adhocTypelist=adhocDao.getAdhoctypeDetails(adhoctype,siteId,projectUnit);
		List<Adhoctype> adhocDetailsList=new ArrayList<Adhoctype>();
		if(adhocTypelist!=null && adhocTypelist.size()>0){	
			for(Adhoctype at :adhocTypelist){
				getRequesterDetails(at);
					if (at.getApproval().equalsIgnoreCase("yes")) {
						getApprovalDetails(at);
					}
						at.setAdhocrequesters(adhocDao.getAdhocReqList());
							at.setAdhocapprovars(adhocDao.getApproverRoles());					
								adhocDetailsList.add(at);	
				}
			
		}
		return adhocDetailsList;
	}

	public void getApprovalDetails(Adhoctype at) throws Exception {
		adhocDao.getApprovalDetails(at);
		
	}

	public void getRequesterDetails(Adhoctype at)throws Exception  {
		adhocDao.getRequesterDetails(at);
		
	}

	
}
