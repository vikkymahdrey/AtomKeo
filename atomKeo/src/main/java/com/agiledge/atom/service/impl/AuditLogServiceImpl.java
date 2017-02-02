package com.agiledge.atom.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agiledge.atom.dao.impl.AbstractDao;
import com.agiledge.atom.dao.intfc.AuditLogDao;
import com.agiledge.atom.entities.AuditLog;
import com.agiledge.atom.entities.Employee;
import com.agiledge.atom.service.intfc.AuditLogService;
@Service
public class AuditLogServiceImpl extends AbstractDao implements AuditLogService {

	@Autowired
	AuditLogDao auditLogDao;
	
	public List<AuditLog> getAllAuditLogEntries(int id, String moduleName,
			String current) throws Exception {
		return auditLogDao.getAllAuditLogEntries(id ,moduleName ,current);
	}

	public void auditLogEntryforaddModule(int moduleId, String Module,
			String changedBy, String preworkflowState, String curworkflowState,
			String auditLogAction) throws Exception {
		AuditLog auditLog = new AuditLog();

		auditLog.setReferenceId(moduleId);
		auditLog.setModuleName(Module);
		Employee changedByEmp = auditLogDao.getEmployeebyId(changedBy);
		auditLog.setEmployee(changedByEmp);
		auditLog.setDateChanged(new Date());
		auditLog.setPreviousState(preworkflowState);
		auditLog.setCurrentState(curworkflowState);
		auditLog.setAction(auditLogAction);
		
		auditLogDao.addAuditLogEntry(auditLog);
	}
	
	public void auditLogEntryfordeleteModule(int moduleId, String Module,String changedBy,
			String preworkflowState, String curworkflowState,String auditLogAction) throws Exception {
		AuditLog auditLog = new AuditLog();
		auditLog.setReferenceId(moduleId);
		auditLog.setModuleName(Module);
		Employee changedByEmp = auditLogDao.getEmployeebyId(changedBy);
		auditLog.setEmployee(changedByEmp);
		auditLog.setDateChanged(new Date());
		auditLog.setCurrentState(curworkflowState);
		auditLog.setAction(auditLogAction);
		if(preworkflowState==null || preworkflowState.equals("")) {
		 auditLog.setPreviousState(auditLogDao.getPreviouseState(auditLog));
		} else {
			auditLog.setPreviousState(preworkflowState);
		}
		auditLog.setPreviousState(preworkflowState);
		auditLogDao.addAuditLogEntry(auditLog);
	}
	
	public void auditLogEntryforupdateModule(int moduleId, String Module,
			String changedBy, String preworkflowState, String curworkflowState,
			String auditLogAction) throws Exception {
		AuditLog auditLog = new AuditLog();
		auditLog.setReferenceId(moduleId);
		auditLog.setModuleName(Module);
		Employee changedByEmp = auditLogDao.getEmployeebyId(changedBy);
		auditLog.setEmployee(changedByEmp);
		auditLog.setDateChanged(new Date());
		auditLog.setPreviousState(preworkflowState);
		auditLog.setCurrentState(curworkflowState);
		auditLog.setAction(auditLogAction);

		auditLogDao.addAuditLogEntry(auditLog);
		
	}
	
	public Employee getEmployeebyId(String changedBy) throws Exception{
		return auditLogDao.getEmployeebyId(changedBy);
	}

	
}
