package com.agiledge.atom.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.agiledge.atom.dao.intfc.CompanyDao;
import com.agiledge.atom.entities.Branch;
import com.agiledge.atom.entities.Company;
@Repository
public class CompanyDaoImpl extends AbstractDao implements CompanyDao {
	
	private static final Logger logger = Logger.getLogger(CompanyDaoImpl.class);
	
	public Company addNewCompany(Company comp) throws Exception {
			persist(comp);
				flush();
		return comp;
			}

	/*public void auditLogEntryforaddcompany(int autoincNumber, String Module,
			int changedBy, String preworkflowState,
			String curworkflowState, String auditLogAction) {
		AuditLogDTO auditLog = new AuditLogDTO();

		auditLog.setRelatedNodeId(autoincNumber);
		auditLog.setModuleName(Module);
		auditLog.setChangedBy(changedBy);
		auditLog.setDateChanged(new Date());
		auditLog.setPreviousState(preworkflowState);
		auditLog.setCurrentState(curworkflowState);
		auditLog.setAction(auditLogAction);

		AuditLogDAO auditDAO = new AuditLogDAO();
		auditDAO.addAuditLogEntry(auditLog);

	}*/
	
	public Company getCompany() throws Exception{
	    String query="from Company";
			Query q = getEntityManager().createQuery(query);
			List<Company> companyList = q.getResultList();
			if(companyList!=null && companyList.size()>0){
				return companyList.get(0);
			}else{			
		return null;
		}	
}

	public Company modifyCompany(Company comp) throws Exception {		
				update(comp);
				flush();
	  return comp;			
	}
	/*public void auditLogEntryformodifycompany(int autoincNumber, String Module,
			int changedBy, String preworkflowState,
			String curworkflowState, String auditLogAction) {
		AuditLogDTO auditLog = new AuditLogDTO();

		auditLog.setRelatedNodeId(autoincNumber);
		auditLog.setModuleName(Module);
		auditLog.setChangedBy(changedBy);
		auditLog.setDateChanged(new Date());
		auditLog.setPreviousState(preworkflowState);
		auditLog.setCurrentState(curworkflowState);
		auditLog.setAction(auditLogAction);

		AuditLogDAO auditDAO = new AuditLogDAO();
		auditDAO.addAuditLogEntry(auditLog);

	}*/
	public Company getCompDetails(int companyId) throws Exception{
		String query="from Company c where c.id="+companyId;
		Query q = getEntityManager().createQuery(query);
		List<Company> companyList = q.getResultList();
		
	return companyList.get(0);
	}

	public List<Branch> getBranchesByCompId(int companyId) throws Exception {
		String query="from Company c where c.id="+companyId;
		Query q = getEntityManager().createQuery(query);
		List<Company> companyList = q.getResultList();
		
	return companyList.get(0).getBranches();
	}
	
}
