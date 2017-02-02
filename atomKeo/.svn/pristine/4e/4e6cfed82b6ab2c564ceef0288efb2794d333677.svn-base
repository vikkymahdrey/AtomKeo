package com.agiledge.atom.dao.intfc;

import java.util.List;

import com.agiledge.atom.entities.AuditLog;
import com.agiledge.atom.entities.Employee;

public interface AuditLogDao {
	public List<AuditLog> getAllAuditLogEntries(int id, String moduleName,String current) throws Exception;
	public Employee getEmployeebyId(String changedBy) throws Exception;
	public void addAuditLogEntry(AuditLog auditLog) throws Exception;
	public String getPreviouseState(AuditLog auditLog) throws Exception;
	
}
