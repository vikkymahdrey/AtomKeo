package com.agiledge.atom.service.intfc;

import java.util.List;

import com.agiledge.atom.entities.Adhocbooking;
import com.agiledge.atom.entities.Adhocrequester;
import com.agiledge.atom.entities.Adhoctype;
import com.agiledge.atom.entities.Settingsstring;

public interface AdhocService {
	
	public List<Adhocbooking> getAllPendingAdhocBookings() throws Exception;
	public int[] getAdhocNotificationCount(String employeeId, String roleId,String role) throws Exception;
	public List<Settingsstring> getSettingsStrings(String adhoc, String type,String siteId, String projectUnit) throws Exception;
	public Adhoctype getAdhocTypeByType(String adhoctype) throws Exception;
	public List<String> getProjectUnits() throws Exception;
	public List<Adhoctype> getSetupDetails(String adhoctype, String siteId, String projectUnit)throws Exception;
	
}
