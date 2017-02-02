package com.agiledge.atom.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.agiledge.atom.constants.AuditLogConstants;
import com.agiledge.atom.dao.intfc.AuditLogDao;
import com.agiledge.atom.entities.AuditLog;
import com.agiledge.atom.entities.Employee;

@Repository
public class AuditLogDaoImpl extends AbstractDao implements AuditLogDao{

	private final static Logger logger = Logger.getLogger(AuditLogDaoImpl.class);

	public List<AuditLog> getAllAuditLogEntries(int id, String moduleName,String current) throws Exception {
		String query="";
		if(current==null){
			query="from AuditLog al where al.referenceId="+id+" and al.moduleName='"+moduleName+"'";
		}
		else {
			query="from AuditLog al where al.referenceId="+id+" and al.moduleName='"+moduleName+"' and (al.currentState LIKE '%"+current+"%')";
		}
				
		Query q=getEntityManager().createQuery(query);
		List<AuditLog> auditLogList=q.getResultList();
		return auditLogList;
	}
	
	public Employee getEmployeebyId(String changedBy) throws Exception {
		logger.debug("EmployeeIDDDD"+changedBy);
		Query query =  getEntityManager().createQuery("from Employee employee where employee.id = :id");
		query.setParameter("id", changedBy);
		 List<Employee> employees = (List<Employee>) query.getResultList();
			return employees.get(0);
	}

		
	public void addAuditLogEntry(AuditLog auditLog) throws Exception{
		persist(auditLog);
		logger.debug("IN Add AUditlogEntry");
		flush();
	}
	
	public String getPreviouseState(AuditLog auditLog) throws Exception{
		String previouseState = AuditLogConstants.WORKFLOW_STATE_EMPTY;
		String query="from AuditLog al where al.referenceId=:refId and al.moduleName=:modN and id=(select max(alog.id) from AuditLog alog where alog.referenceId=:rfId and alog.moduleName=:mdN)";
		Query q=getEntityManager().createQuery(query);
		q.setParameter("refId",auditLog.getReferenceId());
		q.setParameter("modN", auditLog.getModuleName());
		q.setParameter("rfId", auditLog.getReferenceId());
		q.setParameter("mdN", auditLog.getModuleName());
		List<AuditLog> auditLogList=q.getResultList();
		if(auditLogList.get(0).getPreviousState()==null && auditLogList.get(0).getPreviousState().equals("")){
					return previouseState;
		} else {
					return auditLogList.get(0).getPreviousState();
		}
	}
}
