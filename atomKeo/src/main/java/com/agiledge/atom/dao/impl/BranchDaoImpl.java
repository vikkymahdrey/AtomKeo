package com.agiledge.atom.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.agiledge.atom.dao.intfc.BranchDao;
import com.agiledge.atom.entities.Branch;
import com.agiledge.atom.entities.Company;

@Repository
public class BranchDaoImpl extends AbstractDao implements BranchDao {

	private static final Logger logger = Logger.getLogger(BranchDaoImpl.class);
	/*public List<Branch> getBranches(int companyId)	 {
		
		List<Branch> branchList = new ArrayList<Branch>();
		Branch b=null;
		Company c=null;
			
			//String query="select b from Branch b join b.company c where c.id= "+companyId;
			String query="from Branch b where b.company.id="+companyId;
			Query q = getEntityManager().createQuery(query);
			List<Branch> branches = q.getResultList();
			b=new Branch();
			c=new Company();
	   if(branch!=null || branch.size()>0){
		    for(Branch br : branch){
			 b.setId(br.getId());
			 b.setLocation(br.getLocation());
			 Company comp=br.getCompany();
			 c.setId(comp.getId());
			 c.setName(comp.getName());
			 b.setCompany(c);
			branchList.add(b);
			
			 }
	   }
	   	
		return branches;
	}
*/
	public Branch modifyBranch(Branch branch) {
		update(branch);
		return branch;
	}

	/*private void auditLogEntryformodifybranch(int autoincNumber, String Module,
			int changedBy, String preworkflowState, String curworkflowState,
			String auditLogAction) {
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

	public void addingBranch(Branch branch) {
		persist(branch);
		flush();
			}

	/*private void auditLogEntryforaddbranch(int autoincNumber, String Module,
			int changedBy, String preworkflowState, String curworkflowState,
			String auditLogAction) {
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

	public String getBranchLocation(int branchId) {
		Branch br=null;
		Company cmp=null;
				
	    String query="from Branch b where b.id="+branchId;	
	    //String qurey="select b.id, b.companyid, b.location, c.name as companyName from branch b join company c on b.companyid=c.id where b.id="+ branchId;
			
	   Query q = getEntityManager().createQuery(query);
	   List<Branch> branch = q.getResultList();
	   br=new Branch();
	   cmp=new Company();
	   if(branch!=null || branch.size()>0){
		   for(Branch b : branch){
			   br.setId(b.getId());
			   br.setLocation(b.getLocation());
			   cmp.setId(b.getCompany().getId());
			   cmp.setName(b.getCompany().getName());
			   br.setCompany(cmp);
	 			}
	   }
				
		return br.getLocation();
	}

	public Branch getBranchById(int branchId) throws Exception {
		String query="from Branch b where b.id="+branchId;
		Query q = getEntityManager().createQuery(query);
			
		return (Branch) q.getResultList().get(0);
	}

	public List<Branch> getBranchs() throws Exception {
		String query="from Branch";
		Query q = getEntityManager().createQuery(query);
		List<Branch> branchList=q.getResultList();
		return branchList;
	}
	
}
	
