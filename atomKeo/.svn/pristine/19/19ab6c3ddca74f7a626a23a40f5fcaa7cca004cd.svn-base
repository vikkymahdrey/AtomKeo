package com.agiledge.atom.dao.intfc;

import java.util.List;

import com.agiledge.atom.entities.Adhocapprovar;
import com.agiledge.atom.entities.Adhocbooking;
import com.agiledge.atom.entities.Adhocrequester;
import com.agiledge.atom.entities.Adhoctype;
import com.agiledge.atom.entities.Settingsstring;


public interface AdhocDao {	
	public List<Adhocbooking> getAllPendingAhdocBookings() throws Exception;
	public int[] getAdhocNotificationCount(String employeeId, String roleId,String role) throws Exception;
	public List<Settingsstring> getSettingsStrings(String adhoc, String type,String siteId, String projectUnit) throws Exception;
	public Adhoctype getAdhocTypeByType(String adhoctype) throws Exception;
	public List<String> getProjectUnits() throws Exception;
	public List<Adhoctype> getAdhoctypeDetails(String adhoctype, String siteId, String projectUnit) throws Exception;
	public void getRequesterDetails(Adhoctype at)throws Exception;
	public List<Adhocrequester> getAdhocReqList() throws Exception;
	public void getApprovalDetails(Adhoctype at) throws Exception;
	public List<Adhocapprovar> getApproverRoles()throws Exception;
	

}
