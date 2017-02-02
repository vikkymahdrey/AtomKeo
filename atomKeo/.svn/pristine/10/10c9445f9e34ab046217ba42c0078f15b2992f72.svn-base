package com.agiledge.atom.service.intfc;

import java.util.List;

import com.agiledge.atom.entities.AuditLog;
import com.agiledge.atom.entities.Employee;

public interface AuditLogService {
	
	public List<AuditLog> getAllAuditLogEntries(int id, String moduleName,String current) throws Exception;
	public void auditLogEntryforaddModule(int moduleId, String Module,String changedBy, String preworkflowState, String curworkflowState,String auditLogAction) throws Exception;
	public void auditLogEntryfordeleteModule(int moduleId, String Module,String changedBy, String preworkflowState, String curworkflowState,String auditLogAction) throws Exception;
	public Employee getEmployeebyId(String changedBy) throws Exception;
	public void auditLogEntryforupdateModule(int autoincNumber, String Module,String changedBy, String preworkflowState, String curworkflowState,String auditLogAction) throws Exception;

	}
